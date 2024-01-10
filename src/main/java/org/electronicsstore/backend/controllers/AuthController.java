package org.electronicsstore.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

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

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"temp/protected/admin"})
    public Map<String, String> tempAdminData() {
        return Map.of("accessibleOnlyFor", "admin");
    }
}
