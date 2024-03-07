package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface CharacteristicValueSingleProductProj {
    Long getId();
    String getData();
    LocalDateTime getCreatedA();
    CharacteristicProjEmb getCharacteristic();

    interface CharacteristicProjEmb {
        Long getId();
        String getName();
        String getDataType();
        LocalDateTime getCreatedAt();
    }
}
