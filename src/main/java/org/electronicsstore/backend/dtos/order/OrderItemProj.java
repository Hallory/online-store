package org.electronicsstore.backend.dtos.order;

import java.time.LocalDateTime;
import java.util.Set;

public interface OrderItemProj {
    String getId();
    String getProductSKU();
    Double getPricePerItem();
    Double getTotalAmount();
    Integer getQty();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    ProductProjEmb getProduct();

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
