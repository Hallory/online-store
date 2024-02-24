package org.electronicsstore.backend.exceptions;

public class CustomEntityExistsException extends RuntimeException {
    public CustomEntityExistsException() {
        super();
    }
    public CustomEntityExistsException(Exception cause) {
        super(cause);
    }
    public CustomEntityExistsException(String message) {
        super(message);
    }
    public CustomEntityExistsException(String message, Exception cause) {
        super(message, cause);
    }
}
