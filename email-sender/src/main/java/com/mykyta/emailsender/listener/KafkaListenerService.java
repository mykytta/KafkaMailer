package com.mykyta.emailsender.listener;

import com.mykyta.emailsender.service.EmailService;
import com.mykyta.userservice.entity.RegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);
    private final EmailService emailService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}")
    public void consume(RegistrationEvent registrationEvent){
        LOGGER.info(String.format("Order received in email service => %s", registrationEvent.toString()));

        String to = registrationEvent.getEmail();
        String subject = "Registration Confirmation";
        String text = "Thank you for registering, " + registrationEvent.getUsername() +"!";
        emailService.sendMessage(to, subject, text);
    }
}
