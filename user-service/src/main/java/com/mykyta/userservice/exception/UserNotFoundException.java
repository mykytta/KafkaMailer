package com.mykyta.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    private Long id;

    public UserNotFoundException(Long id) {
        super(String.format("User with such id: '%s' not found", id));
        this.id = id;
    }
}
