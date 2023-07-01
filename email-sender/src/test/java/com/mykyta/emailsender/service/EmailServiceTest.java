package com.mykyta.emailsender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    private EmailService emailService;

    @Mock
    private JavaMailSender emailSender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(emailSender);
    }

    @Test
    public void sendMessage() {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Message";

        emailService.sendMessage(to, subject, text);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setFrom("niqityuka@gmail.com");
        expectedMessage.setTo(to);
        expectedMessage.setSubject(subject);
        expectedMessage.setText(text);

        verify(emailSender, times(1)).send(expectedMessage);
    }
}

