package ru.ddc.keycloakadminclient2.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.keycloakadminclient2.controller.dto.CreateUserRequest;
import ru.ddc.keycloakadminclient2.controller.dto.UserDto;
import ru.ddc.keycloakadminclient2.service.EmailService;
import ru.ddc.keycloakadminclient2.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        UserDto userDto = modelMapper.map(request, UserDto.class);
        userService.createUser(userDto);
        emailService.sendEmail(
                "Activate your account",
                userDto.getEmail(),
                "http://link-to-activate/" + userDto.getUserId());
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable String id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }
}
