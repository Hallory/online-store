package org.electronicsstore.backend.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Category %s not found";
    public ProductCategoryNotFoundException(Long identifier) {
        super(String.format(MESSAGE_PATTERN, String.valueOf(identifier)));
    }
    public ProductCategoryNotFoundException(String name) {
        super(String.format(MESSAGE_PATTERN, name));
    }
}
