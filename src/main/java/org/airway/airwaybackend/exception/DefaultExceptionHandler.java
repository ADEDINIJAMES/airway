package org.airway.airwaybackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<ApiError> handleUserNotVerifiedException(
            UserNotVerifiedException e, HttpServletRequest request) {
        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, e.getMessage());
    }



    private ResponseEntity<ApiError> buildErrorResponse(
            HttpServletRequest request, HttpStatus status, String message) {
        return buildErrorResponse(request, status, message, null);
    }

    private ResponseEntity<ApiError> buildErrorResponse(
            HttpServletRequest request, HttpStatus status, String message, List<ValidationError> errors) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                message,
                status.value(),
                LocalDateTime.now(),
                errors
        );
        return new ResponseEntity<>(apiError, status);
    }
}
