package dev.project.backend.user.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super("user.signup.duplicate");
    }
}
