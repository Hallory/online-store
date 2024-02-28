package org.electronicsstore.backend.dtos;

import java.util.Set;

public record ProductCharUpdateRequest(
        Set<String> fields,
        String name,
        String dataType,
        Long categoryId
        // todo ProductCharValue
) {
}
