package dev.project.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status;

    protected BusinessException(String messageCode, HttpStatus status) {
        super(messageCode);
        this.status = status;
    }
}