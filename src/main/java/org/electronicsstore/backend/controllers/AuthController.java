package org.electronicsstore.backend.controllers;

import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.electronicsstore.backend.dtos.UserDto;
import org.electronicsstore.backend.dtos.UserRolesResponse;
import org.electronicsstore.backend.security.CustomJwt;
import org.electronicsstore.backend.services.KeycloakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private final KeycloakService keycloakService;

    @PostMapping({"public/users"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        Response resp = keycloakService.createUserAndAssignRole(userDto);
        if (resp.getStatus() != 201)
            throw new RuntimeException();
        return ResponseEntity.created(resp.getLocation()).build();
    }

    @GetMapping({"protected/user/roles"})
    public UserRolesResponse userRoles(CustomJwt authentication) {
        return keycloakService.userRoles(authentication);
    }

    @GetMapping({"temp/data"})
    public Object tempData() {
        return keycloakService.tempClientsData();
    }

    @GetMapping({"temp/public"})
    public Map<String, String> tempPublicData() {
        return Map.of("accessibleOnlyFor", "anon");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"temp/protected/user"})
    public Map<String, String> tempUserData() {
        return Map.of("accessibleOnlyFor", "user");
    }

    @PreAuthorize("hasRole('MODER')")
    @GetMapping({"temp/protected/moder"})
    public Map<String, String> tempModerData() {
        return Map.of("accessibleOnlyFor", "moder");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"temp/protected/admin"})
    public Map<String, String> tempAdminData() {
        return Map.of("accessibleOnlyFor", "admin");
    }
}
