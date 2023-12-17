package org.electronicsstore.backend.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE_PATTERN = "Category %s is already exists";
    public CategoryAlreadyExistsException(String identifier) {
        super(String.format(MESSAGE_PATTERN, identifier));
    }
}
