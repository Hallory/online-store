package org.electronicsstore.backend.dtos;

import java.util.List;

public record ProductCategoryPatchRequest(
        List<String> fields,
        String name,
        String description,
        List<String> productIds,
        List<Long> productCharIds,
        Long parentProductCategoryId,
        List<Long> childProductCategoryIds
) {
}
