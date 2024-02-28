package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.model.product.ProductCharValue;

import java.util.Set;
import java.util.function.Function;

public record ProductCharValueCreateRequest(
        String data,
        Long productCharId,
        Set<String> productIds
) {
    public static ProductCharValue dtoToModel(
            ProductCharValueCreateRequest dto,
            Function<Long, ProductChar> charMap,
            Function<Set<String>, Set<Product>> productsMap
    ) {
        var productChar = new ProductCharValue();
        productChar.setData(dto.data);
        productChar.setProductChar(charMap.apply(dto.productCharId));
        productChar.setProducts(productsMap.apply(dto.productIds));
        return productChar;
    }
}
