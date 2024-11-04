package ru.ddc.keycloakadminclient2.service;

public interface EmailService {
    void sendEmail(String subject, String to, String text);
}
