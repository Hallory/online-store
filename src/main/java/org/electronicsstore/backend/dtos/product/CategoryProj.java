package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface CategoryProj {
    Long getId();
    String getName();
    String getDescription();
    LocalDateTime getCreatedAt();
    CategoryProjEmb getParent();
    Set<CategoryProjEmb> getChildren();
    Set<ProductProjEmb> getProducts();
    Set<CharacteristicProjEmb> getCharacteristics();

    interface CategoryProjEmb {
        Long getId();
        String getName();
        String getDescription();
        CategoryProjEmb getParent();
        Set<CharacteristicProjEmb> getCharacteristics();

    }

    interface ProductProjEmb {
        String getId();
        String getArticle();
        String getBarcode();
        String getName();
        String getSku();
        String getBrand();
        Double getPrice();
        String getDescription();
        Integer getQtyInStock();
        Set<String> getProductImages();
        String getProductIcon();
        LocalDateTime getCreatedAt();
        LocalDateTime getDeletedAt();
    }

    interface CharacteristicProjEmb {
        Long getId();
        String getName();
        String getDataType();
    }
}
