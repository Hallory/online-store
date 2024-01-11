package org.electronicsstore.backend.services;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.UserDto;
import org.electronicsstore.backend.dtos.UserRolesResponse;
import org.electronicsstore.backend.security.CustomJwt;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    @Value("${keycloak.backend-client.realm}")
    private String keycloakRealm;
    private final Keycloak keycloak;

    public Response createUser(UserDto userDto) {
        CredentialRepresentation credentials = prepareCredentialRepresentation(userDto.password());
        UserRepresentation user = prepareUserRepresentation(userDto, credentials);
        return keycloak.realm(keycloakRealm).users().create(user);
    }

    public Response createUserAndAssignRole(UserDto userDto) {
        CredentialRepresentation credentials = prepareCredentialRepresentation(userDto.password());
        UserRepresentation user = prepareUserRepresentation(userDto, credentials);
        Response resp = keycloak.realm(keycloakRealm).users().create(user);

        if (resp.getStatus() == 201) {
            UserRepresentation createdUser = findUserByUsername(user.getUsername());
            RoleRepresentation existedRole = findRoleByName(userDto.roleName());
            assignRole(createdUser.getId(), existedRole);
        }

        return resp;
    }

    public UserRepresentation findUserByUsername(String name) {
        var users = keycloak
                .realm(keycloakRealm)
                .users()
                .search(name);
        if (users.size() > 1) throw new RuntimeException("User is not unique");
        return users.getFirst();
    }

    public UserRolesResponse userRoles(CustomJwt authentication) {
        return new UserRolesResponse(authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }

    public Object tempClientsData() {
        return keycloak.realm(keycloakRealm).users().list();
    }

    public void assignRole(String userId, RoleRepresentation roleRepresentation) {
        keycloak
                .realm(keycloakRealm)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(List.of(roleRepresentation));
    }

    public RoleRepresentation findRoleByName(String name) {
        return keycloak
                .realm(keycloakRealm)
                .roles()
                .get(name)
                .toRepresentation();
    }

    private UserRepresentation prepareUserRepresentation(UserDto userDto, CredentialRepresentation credentials) {
        var user = new UserRepresentation();
        user.setUsername(userDto.username());
        user.setCredentials(List.of(credentials));
        user.setEnabled(true);
        user.setEmail(userDto.email());
        user.setEmailVerified(true);
        return user;
    }

    private CredentialRepresentation prepareCredentialRepresentation(String password) {
        var credentials = new CredentialRepresentation();
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(password);
        return credentials;
    }
}
