package org.electronicsstore.backend.exceptions;

public class AuthUserNotCreatedException extends RuntimeException {

    public AuthUserNotCreatedException() {
        super();
    }
    public AuthUserNotCreatedException(Exception cause) {
        super(cause);
    }
    public AuthUserNotCreatedException(String message) {
        super(message);
    }
    public AuthUserNotCreatedException(String message, Exception cause) {
        super(message, cause);
    }
}
