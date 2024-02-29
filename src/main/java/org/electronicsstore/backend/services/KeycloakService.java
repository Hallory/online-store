package org.electronicsstore.backend.services;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.CustomerCreateRequest;
import org.electronicsstore.backend.dtos.UserRolesResponse;
import org.electronicsstore.backend.exceptions.AuthUserNotCreatedException;
import org.electronicsstore.backend.model.customer.Customer;
import org.electronicsstore.backend.security.CustomJwt;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeycloakService {
    private static final String CUSTOMER_REGISTER_ROLE_DEFAULT = Customer.CustomerRoles.USER.name();
    @Value("${keycloak.backend-client.realm}")
    private String keycloakRealm;
    private final Keycloak keycloak;
    private final CustomerService customerService;

    public Response createUserAndAssignRole(CustomerCreateRequest dto) {
        CredentialRepresentation credentials = prepareCredentialRepresentation(dto.password());
        UserRepresentation user = prepareUserRepresentation(dto, credentials);

        try (Response resp = keycloak.realm(keycloakRealm).users().create(user)) {
            if (resp.getStatus() == 201) {
                UserRepresentation createdUser = findUserByUsername(user.getUsername());
                RoleRepresentation existedRole = findRoleByName(CUSTOMER_REGISTER_ROLE_DEFAULT); // static response
                assignRole(createdUser.getId(), existedRole);

                customerService.createOne(dto, createdUser.getId());

                return resp;
            }
        }

        throw new AuthUserNotCreatedException("Authorization server didn't create user.");
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

    private UserRepresentation prepareUserRepresentation(CustomerCreateRequest dto, CredentialRepresentation credentials) {
        var user = new UserRepresentation();
        user.setUsername(dto.username());
        user.setCredentials(List.of(credentials));
        user.setEnabled(true);
        user.setEmail(dto.username());
        user.setEmailVerified(true);

        user.setLastName(dto.lastName());
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
