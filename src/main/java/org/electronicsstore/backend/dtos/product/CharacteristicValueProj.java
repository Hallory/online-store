package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface CharacteristicValueProj {
    Long getId();
    String getData();
    LocalDateTime getCreatedAt();
    CharacteristicProjEmb getCharacteristic();
    Set<ProductProjEmb> getProducts();

    interface CharacteristicProjEmb {
        Long getId();
        String getName();
        String getDataType();
        LocalDateTime getCreatedAt();
        CategoryProjEmb getCategory();
    }

    interface CategoryProjEmb {
        Long getId();
        String getName();
    }

    interface ProductProjEmb {
        String getId();
        String getArticle();
        String getBarcode();
        String getName();
        String getSku();
        String getBrand();
        Double getPrice();
        LocalDateTime getCreatedAt();
        LocalDateTime getDeletedAt();
    }
}
