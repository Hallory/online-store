package org.electronicsstore.backend.dtos;

import java.util.Set;

public record ProductCharPatchRequest(
        Set<String> fields,
        String name,
        String dataType,
        Long categoryId
        // todo ProductCharValue
) {
}
