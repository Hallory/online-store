package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.ProductCategory;

public record ProductCategoryCreateRequest(
        String name,
        String description,
        Long parentProductCategoryId
) {
    public static ProductCategory dtoToModel(ProductCategoryCreateRequest dto) {
        var productCategory = new ProductCategory();
        productCategory.setName(dto.name);
        productCategory.setDescription(dto.description);
        return productCategory;
    }
}
