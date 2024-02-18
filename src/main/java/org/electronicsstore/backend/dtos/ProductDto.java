package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.ProductItem;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDto(
        String id,
        String name,
        String description,
        String productImage,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt,
        String productCategoryId,
        Set<String> productItemIds
) {
    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getProductImage(),
                product.getCreatedAt(),
                product.getModifiedAt(),
                product.getDeletedAt(),
                product.getProductCategory().getId(),
                product.getProductItems().stream().map(ProductItem::getId).collect(Collectors.toSet())
        );
    }
}
