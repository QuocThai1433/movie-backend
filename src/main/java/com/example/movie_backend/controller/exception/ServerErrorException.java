package com.example.movie_backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(String message, Exception exception) {
        super(message, exception);
    }
}
