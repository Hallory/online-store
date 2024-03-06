package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface PromoProj {
    Long getId();
    String getName();
    String getDescription();
    Double getDiscountRate();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    Set<ProductProjEmb> getProducts();

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
}
