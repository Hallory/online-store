package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.ProductCategory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductCategoryDto(
        Long id,
        String name,

        String description,
        Long parentProductCategoryId,
        Set<String> childProductCategoryIds
) {
    public static ProductCategoryDto modelToDto(ProductCategory productCategory) {
        var parentProductCategoryId =
                (productCategory.getParent() != null)
                        ? productCategory.getParent().getId()
                        : null;
        return new ProductCategoryDto(
                productCategory.getId(),
                productCategory.getName(),
                productCategory.getDescription(),
                parentProductCategoryId,
                productCategory.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));
    }
}
