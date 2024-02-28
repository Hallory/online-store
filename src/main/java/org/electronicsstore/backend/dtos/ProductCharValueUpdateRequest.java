package org.electronicsstore.backend.dtos;

import java.util.Set;

public record ProductCharValueUpdateRequest(
        Set<String> fields,
        String name,
        Long productCharId,
        Set<String> productIds
) {
}
