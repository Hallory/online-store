package org.electronicsstore.backend.dtos;

import java.util.Set;

public record ProductUpdateRequest(
        String article,
        String SKU,
        String barcode,
        Integer qtyInStock,
        Double price,
        String description,
        String name,
        Long promoId,
        Set<String> productImages,
        String productIcon,
        Set<Long> productCharValues,
        Long categoryId
) { }
