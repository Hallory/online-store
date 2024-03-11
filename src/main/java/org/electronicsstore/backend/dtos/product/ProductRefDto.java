package org.electronicsstore.backend.dtos.product;

import java.util.List;

public record ProductRefDto(
        List<String> productIds
) {
}
