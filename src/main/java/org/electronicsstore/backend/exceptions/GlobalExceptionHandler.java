package org.electronicsstore.backend.exceptions;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class, ProductCategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(RuntimeException ex, WebRequest req) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler({ProductAlreadyExistsException.class, ProductCategoryAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage resourceNotRequiredException(RuntimeException ex, WebRequest req) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleMethodAuthorizationDefaultExceptions(RuntimeException ex, WebRequest req) {
        return;
    }
}
