package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.model.product.ProductCharValue;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductCharDto(
        Long id,
        String name,
        String dataType,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        Long productCategoryId,
        Set<Long> productCharValueIds
) {
    public static ProductCharDto modelToDto(ProductChar productChar) {
        return new ProductCharDto(
                productChar.getId(),
                productChar.getName(),
                productChar.getDataType(),
                productChar.getCreatedAt(),
                productChar.getModifiedAt(),
                (productChar.getProductCategory() == null) ? null : productChar.getProductCategory().getId(),
                (productChar.getProductCharValues() == null) ? null : productChar.getProductCharValues().stream().map(ProductCharValue::getId).collect(Collectors.toSet())
        );
    }
}
