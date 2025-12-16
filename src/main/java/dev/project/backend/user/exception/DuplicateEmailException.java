package dev.project.backend.user.exception;

import dev.project.backend.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException() {
        super("user.signup.duplicate", HttpStatus.CONFLICT);
    }
}
