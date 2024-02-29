package org.electronicsstore.backend.dtos;

import java.util.Set;

public record ProductPatchRequest(
        Set<String> fields,
        String article,
        String SKU,
        String barcode,
        Integer qtyInStock,
        Double price,
        String description,
        String name,
        Long promoId,
        Set<CharacterMap> productCharValues
//        todo productImages, productIcon, category
) {
    public record CharacterMap(
            Long charId,
            String data
    ) {}
}
