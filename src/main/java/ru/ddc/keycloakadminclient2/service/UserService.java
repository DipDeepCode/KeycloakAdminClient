package ru.ddc.keycloakadminclient2.service;

import ru.ddc.keycloakadminclient2.controller.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
    void activateUser(String token);
    UserDto getUserByUsername(String username);
    UserDto getUserByUserId(String userId);
}
