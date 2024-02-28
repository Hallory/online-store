package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.model.product.ProductCharValue;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductCharValueDto(
        Long id,
        String data,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long productCharId,
        Set<String> productIds
) {
    public static ProductCharValueDto modelToDto(ProductCharValue productCharValue) {
        return new ProductCharValueDto(
                productCharValue.getId(),
                productCharValue.getData(),
                productCharValue.getCreatedAt(),
                productCharValue.getModifiedAt(),
                (productCharValue.getProductChar() == null) ? null : productCharValue.getProductChar().getId(),
                (productCharValue.getProducts() == null) ? null : productCharValue.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
        );
    }
}
