package org.electronicsstore.backend.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Category %s not found";
    public CategoryNotFoundException(String identifier) {
        super(String.format(MESSAGE_PATTERN, identifier));
    }
}
