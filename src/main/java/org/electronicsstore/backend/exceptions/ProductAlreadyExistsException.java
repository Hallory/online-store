package org.electronicsstore.backend.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Product %s is already exists";
    public ProductAlreadyExistsException(String identifier) {
        super(String.format(MESSAGE_PATTERN, identifier));
    }
}
