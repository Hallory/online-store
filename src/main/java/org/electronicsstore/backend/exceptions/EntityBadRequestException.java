package org.electronicsstore.backend.exceptions;

public class EntityBadRequestException extends RuntimeException {
    public EntityBadRequestException() {
        super();
    }
    public EntityBadRequestException(Exception cause) {
        super(cause);
    }
    public EntityBadRequestException(String message) {
        super(message);
    }
    public EntityBadRequestException(String message, Exception cause) {
        super(message, cause);
    }
}
