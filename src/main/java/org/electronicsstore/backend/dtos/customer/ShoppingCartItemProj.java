package org.electronicsstore.backend.dtos.customer;

import java.time.LocalDateTime;

public interface ShoppingCartItemProj {
    String getId();
    Integer getQty();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    ShoppingCartProjEmb getShoppingCart();
    ProductProjEmb getProduct();

    interface ShoppingCartProjEmb {
        String getId();
        Double getTotal();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
    }

    interface ProductProjEmb {
        String getId();
        String getArticle();
        String getBarcode();
        String getName();
        String getSku();
        String getBrand();
        Double getPrice();
        String getProductIcon();
        LocalDateTime getDeletedAt();
    }
}
