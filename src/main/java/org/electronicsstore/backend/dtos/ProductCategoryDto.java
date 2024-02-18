package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.ProductCategory;

import java.util.Set;
import java.util.stream.Collectors;

public record ProductCategoryDto(
        String id,
        String name,

        String description,
        String parentProductCategoryId,
        Set<String> childProductCategories
) {
    public static ProductCategoryDto categoryToCategoryDto(ProductCategory productCategory) {
        return new ProductCategoryDto(
                productCategory.getId(),
                productCategory.getName(),
                productCategory.getDescription(),
                productCategory.getParentProductCategory().getId(),
                productCategory.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));
    }
}
