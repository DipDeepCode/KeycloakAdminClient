package ru.ddc.keycloakadminclient2.controller.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
