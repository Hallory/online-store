package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;

public interface CategoryProj {
    Long getId();
    String getName();
    String getDescription();
    LocalDateTime getCreatedAt();
}
