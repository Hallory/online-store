package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface ProductProj {
        String getId();
        String getArticle();
        String getBarcode();
        String getName();
        String getSKU();
        String getBrand();
        Double getPrice();
        String getDescription();
        Integer getQtyInStock();
        Set<String> getProductImages();
        String getProductIcon();
        LocalDateTime getCreatedAt();
        LocalDateTime getDeletedAt();
        Set<CharacteristicValueProjEmb> getCharacteristicValues();
        CategoryProjEmb getCategory();
        PromoProjEmb getPromo();

        interface CharacteristicValueProjEmb {
                Long getId();
                String getData();
                CharacteristicProjEmb getCharacteristic();
        }

        interface CategoryProjEmb {
                Long getId();
                String getName();
                String getDescription();
        }

        interface PromoProjEmb {
                Long getId();
                String getName();
                String getDescription();
                Double getDiscountRate();
                LocalDateTime getStartDate();
                LocalDateTime getEndDate();
        }

        interface CharacteristicProjEmb {
                Long getId();
                String getName();
                String getDataType();
        }
}
