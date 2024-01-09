package org.electronicsstore.backend.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Category %s not found";
    public ProductCategoryNotFoundException(String identifier) {
        super(String.format(MESSAGE_PATTERN, identifier));
    }
}
