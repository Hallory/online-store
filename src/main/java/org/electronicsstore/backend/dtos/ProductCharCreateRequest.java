package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.model.product.ProductChar;

import java.util.function.Function;

public record ProductCharCreateRequest(
    String name,
    String dataType,
    Long categoryId
    // todo ProductCharValue
) {
    public static ProductChar dtoToModel(
            ProductCharCreateRequest dto,
            Function<Long, ProductCategory> categoryMap
    ) {
        var productChar = new ProductChar();
        productChar.setName(dto.name());
        productChar.setDataType(dto.dataType());
        productChar.setProductCategory(categoryMap.apply(dto.categoryId));
        return productChar;
    }
}
