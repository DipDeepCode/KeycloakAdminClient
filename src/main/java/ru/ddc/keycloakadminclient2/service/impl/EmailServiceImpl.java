package ru.ddc.keycloakadminclient2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.ddc.keycloakadminclient2.service.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String subject, String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setTo(to);
        message.setText(text);
        emailSender.send(message);
    }
}
