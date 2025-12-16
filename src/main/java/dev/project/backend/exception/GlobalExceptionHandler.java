package dev.project.backend.exception;

import dev.project.backend.user.exception.DuplicateEmailException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
        MethodArgumentNotValidException e) {

        List<String> messages = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        return ResponseEntity
            .badRequest()
            .body(new ErrorResponse(messages));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
        BusinessException e) {

        String message = messageSource.getMessage(
            e.getMessage(),
            null,
            LocaleContextHolder.getLocale()
        );

        return ResponseEntity
            .status(e.getStatus())
            .body(new ErrorResponse(List.of(message)));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation() {

        String message = messageSource.getMessage(
            "error.data.integrity",
            null,
            LocaleContextHolder.getLocale()
        );

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(List.of(message)));
    }
}