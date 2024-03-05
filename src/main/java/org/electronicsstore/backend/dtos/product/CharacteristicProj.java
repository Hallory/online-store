package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;

public interface CharacteristicProj {
    Long getId();
    String getName();
    String getDataType();
    LocalDateTime getCreatedAt();
    CategoryProjEmb getCategory();

    interface CategoryProjEmb {
        Long getId();
        String getName();
        String getDescription();
    }
}
