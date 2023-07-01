package com.mykyta.userservice.auth;

import com.mykyta.userservice.entity.Role;
import com.mykyta.userservice.entity.User;
import com.mykyta.userservice.repository.UserRepository;
import com.mykyta.userservice.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authenticationService = new AuthenticationService(
                userRepository,
                passwordEncoder,
                jwtService,
                authenticationManager
        );
    }

    @Test
    public void register() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password")
                .build();

        User savedUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(savedUser)).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.register(request);

        verify(userRepository).save(any(User.class));
        verify(jwtService).generateToken(savedUser);
        assertEquals("jwtToken", response.getToken());
    }

    @Test
    public void authenticate() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        User user = User.builder()
                .username("testUser")
                .email(request.getEmail())
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(java.util.Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(mock(Authentication.class));

        AuthenticationResponse response = authenticationService.authenticate(request);

        verify(userRepository).findByEmail(request.getEmail());
        verify(jwtService).generateToken(user);
        assertEquals("jwtToken", response.getToken());
    }
}