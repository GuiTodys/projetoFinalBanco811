package com.santander.banco811.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    @Builder
    public UserNotFoundException() {
        super("Usuario não encontrado!");
    }
}
