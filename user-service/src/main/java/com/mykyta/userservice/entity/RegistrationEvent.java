package com.mykyta.userservice.entity;

import com.mykyta.userservice.auth.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEvent {
    private String message;
    private String status;
    private String username;
    private String email;
}
