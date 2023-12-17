package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.Category;
import org.electronicsstore.backend.model.Product;

import java.util.List;

public record CategoryDto(
        String id,
        String name,

        List<String> productIds
) {
    public static CategoryDto categoryToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getProducts().stream().map(Product::getId).toList());
    }
}
