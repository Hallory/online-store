package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;

public interface CategoryTraversedUpProj {
        Long getId();
        String getName();
        String getDescription();
        LocalDateTime getCreatedAt();
        CategoryTraversedUpProj getParent();
}
