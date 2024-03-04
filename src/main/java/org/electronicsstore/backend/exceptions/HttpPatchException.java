package org.electronicsstore.backend.exceptions;

public class HttpPatchException extends RuntimeException {
    public HttpPatchException() {
        super();
    }
    public HttpPatchException(Exception cause) {
        super(cause);
    }
    public HttpPatchException(String message) {
        super(message);
    }
    public HttpPatchException(String message, Exception cause) {
        super(message, cause);
    }
}
