package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.Product;

import java.util.List;

public record ProductDto(
        String id,
        String title,
        String description,
        Double price,
        Double discountPercentage,
        Double rating,
        Integer stock,
        String brand,
        String category,
        String thumbnail,
        List<String> images
) {
    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice().doubleValue(),
                product.getDiscountPercentage(),
                product.getRating(),
                product.getStock(),
                product.getBrand(),
                product.getCategory().getName(),
                product.getThumbnail(),
                product.getImages());
    }
}
