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
        Set<CharValueProj> getProductCharValue();

}
