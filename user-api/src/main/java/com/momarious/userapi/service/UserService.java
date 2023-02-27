package com.momarious.userapi.service;

import com.momarious.userapi.dto.UserDto;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.authentication.ClientCredentialsProvider;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Service
public class UserService {

    @Value("${keycloak.auth-server-url}")
    public String keycloakServerUrl;

    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Value("${keycloak.resource}")
    public String keycloakClientId;

    @Value("${keycloak.credentials.secret}")
    public String keycloakClientSecret;

    public String createUser(UserDto userDTO) {
        try (Keycloak keycloak = getKeycloakClient()) {
            keycloak.tokenManager().getAccessTokenString();
            UserRepresentation user = getUserRepresentation(userDTO);

            RealmResource realmResource = keycloak.realm(keycloakRealm);
            UsersResource usersResource = realmResource.users();
            Response response = usersResource.create(user);

            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                String userId = CreatedResponseUtil.getCreatedId(response);
                UserResource userResource = usersResource.get(userId);
                setUserPassword(userDTO, userResource);
                assignRealmRoleToUserResource(realmResource, userResource);
                return "User successfully created";
            } else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode()) {
                return "Email already exists";
            } else {
                return "Error creating user: " + response.getStatusInfo().getReasonPhrase();
            }

        } catch (Exception e) {
            return "Exception creating user: " + e.getMessage();
        }
    }

    private Keycloak getKeycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm("master")
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username("admin")
                .password("admin")
                // .realm("momarious-realm")
                // .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                // .clientId("userservice")
                // .clientSecret("qIJjYOZqWAduy5VX9LUNuo2xb6Ff8vbT")
                .build();
    }

    private UserRepresentation getUserRepresentation(UserDto userDTO) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    private void setUserPassword(UserDto userDto, UserResource userResource) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        userResource.resetPassword(passwordCred);
    }

    public void assignRealmRoleToUserResource(RealmResource realmResource,
            UserResource userResource) {
        RoleRepresentation realmRoleUser = realmResource.roles().get("ROLE_USER").toRepresentation();
        userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
    }
}
