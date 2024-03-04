package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;

public interface CharProj {
    Long getId();
    String getName();
    String getDataType();
    LocalDateTime getCreatedAt();
}
