# KafkaMailer

## Overview

KafkaMailer is a scalable microservices project designed to optimize user registration processes using Kafka-based asynchronous communication. The system involves user registrations triggering messages to a Kafka topic. A dedicated service listens to this topic and, upon receiving a message, sends an email to the registered user. The email service is designed with horizontal scalability in mind, allowing it to handle increased loads by adding more resources or instances.

## Project Details

- **Technology Stack:**
  - Java
  - Spring Boot
  - Kafka
  - MySQL
  - AWS (Amazon Web Services)

## Key Features

1. **User Registration:**
   - When a user registers, the system sends a message to a Kafka topic containing registration details, including first name, last name, and email.

2. **Notification Service:**
   - A dedicated service subscribes to the specified Kafka topic.
   - Upon receiving a message, the service sends an email to the provided email address.

3. **Scalable Email Service:**
   - The email sending service is designed for horizontal scalability, enabling it to handle increased loads by adding more resources or instances.
