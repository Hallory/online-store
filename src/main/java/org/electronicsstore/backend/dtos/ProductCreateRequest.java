package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;

import java.util.Set;

public record ProductCreateRequest(
        String article,
        String SKU,
        String barcode,
        Integer qtyInStock,
        Set<String> productImages,
        Double price,
        String description,
        String productIcon,
        Long categoryId,
        String name
) {
    public static Product dtoToModel(ProductCreateRequest dto) {
        var product = new Product();
        product.setArticle(dto.article);
        product.setSKU(dto.SKU);
        product.setBarcode(dto.barcode);
        product.setQtyInStock(dto.qtyInStock);
        product.setProductImages(dto.productImages);
        product.setPrice(dto.price);
        product.setDescription(dto.description);
        product.setProductIcon(dto.productIcon);
        product.setName(dto.name);
        return product;
    }
}
