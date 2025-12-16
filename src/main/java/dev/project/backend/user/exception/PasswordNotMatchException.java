package dev.project.backend.user.exception;

import dev.project.backend.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends BusinessException {

    public PasswordNotMatchException() {
        super("user.password.not.match", HttpStatus.BAD_REQUEST);
    }
}