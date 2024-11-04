package ru.ddc.keycloakadminclient2.service.impl;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ddc.keycloakadminclient2.controller.dto.UserDto;
import ru.ddc.keycloakadminclient2.service.UserService;

@Service
@RequiredArgsConstructor
public class KeycloakUserService implements UserService {
    private final static String REALM = "TestRealm";
    private final Keycloak keycloakClient;
    private final ModelMapper modelMapper;

    @Override
    public void createUser(UserDto userDto) {
        UserRepresentation keycloakUser = modelMapper.map(userDto, UserRepresentation.class);
        keycloakUser.setEmailVerified(false);
        keycloakUser.setEnabled(false);
        RealmResource realmResource = keycloakClient.realm(REALM);
        try (Response response = realmResource.users().create(keycloakUser)) {

            if (201 != response.getStatus()) {
                throw new Exception();
            }
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            userDto.setUserId(userId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void activateUser(String token) {
        UserRepresentation userRepresentation = getKeycloakUserById(token);
        assert userRepresentation != null;
        userRepresentation.setEnabled(Boolean.TRUE);
        userRepresentation.setEmailVerified(Boolean.TRUE);
        keycloakClient.realm(REALM).users().get(token).update(userRepresentation);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        return null;
    }

    private UserRepresentation getKeycloakUserById(String userId) {
        UserResource userResource = keycloakClient.realm(REALM).users().get(userId);

        if (userId == null) {
            throw new RuntimeException("User not found");
        }
        return userResource.toRepresentation();
    }
}
