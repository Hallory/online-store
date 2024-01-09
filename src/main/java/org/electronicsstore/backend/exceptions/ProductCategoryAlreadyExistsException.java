package org.electronicsstore.backend.exceptions;

public class ProductCategoryAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Category %s is already exists";
    public ProductCategoryAlreadyExistsException(String identifier) {
        super(String.format(MESSAGE_PATTERN, identifier));
    }
}
