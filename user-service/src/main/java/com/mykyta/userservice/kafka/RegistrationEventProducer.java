package com.mykyta.userservice.kafka;

import com.mykyta.userservice.entity.RegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;



@Service
@RequiredArgsConstructor
public class RegistrationEventProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationEventProducer.class);

    private final NewTopic topic;

    private final KafkaTemplate<String, RegistrationEvent> kafkaTemplate;

    @Async
    public void sendKafkaMessage(RegistrationEvent registrationEvent){
        LOGGER.info(String.format("Registration event => %s", registrationEvent.toString()));

        Message<RegistrationEvent> message = MessageBuilder
                .withPayload(registrationEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
