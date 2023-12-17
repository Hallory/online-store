package org.electronicsstore.backend.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Product %s not found";
    public ProductNotFoundException(String identifier) {
        super(String.format(MESSAGE_PATTERN, identifier));
    }
}
