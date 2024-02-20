package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;

import java.time.LocalDateTime;

public record ProductDto(
        String id,
        String name,
        String description,
        String productIcon,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt,
        Long productCategoryId
) {
    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getProductIcon(),
                product.getCreatedAt(),
                product.getModifiedAt(),
                product.getDeletedAt(),
                product.getProductCategory().getId()
        );
    }
}
