package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;

import java.time.LocalDateTime;
import java.util.Set;

public record ProductDto(
        String id,
        String article,
        String SKU,
        String barcode,
        Integer qtyInStock,
        Set<String> productImages,
        Double price,
        String description,
        String productIcon,
        Long categoryId,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt,
        Long promoId
) {
    public static ProductDto modelToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getArticle(),
                product.getSKU(),
                product.getBarcode(),
                product.getQtyInStock(),
                product.getProductImages(),
                product.getPrice(),
                product.getDescription(),
                product.getProductIcon(),
                (product.getProductCategory() == null) ? null : product.getProductCategory().getId(),
                product.getName(),
                product.getCreatedAt(),
                product.getModifiedAt(),
                product.getDeletedAt(),
                (product.getPromo() == null) ? null : product.getPromo().getId()
        );
    }
}
