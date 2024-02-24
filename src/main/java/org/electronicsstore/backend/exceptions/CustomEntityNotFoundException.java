package org.electronicsstore.backend.exceptions;

public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException() {
        super();
    }
    public CustomEntityNotFoundException(Exception cause) {
        super(cause);
    }
    public CustomEntityNotFoundException(String message) {
        super(message);
    }
    public CustomEntityNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
