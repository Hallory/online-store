package org.electronicsstore.backend.dtos.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ShopOrderProj {
    String getId();
    Double getTotalAmount();
    String getShippingAddress();
    String getStatus();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    LocalDateTime getApprovedAt();
    CustomerProjEmb getCustomer();
    List<PaymentProjEmb> getPayments();
    ShippingMethodProj getShippingMethods();
    List<OrderItemProjEmb> getOrderItems();

    interface CustomerProjEmb {
        String getId();
        Double getTotalAmount();
        String getShippingAddress();
        String getStatus();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        LocalDateTime getApprovedAt();
    }

    interface PaymentProjEmb {
        String getId();
        Double getAmount();
        String getPaymentMethod();
        LocalDateTime getCreatedAt();
    }

    interface OrderItemProjEmb {
        String getId();
        String getProductSKU();
        Double getPricePerItem();
        Double getTotalAmount();
        Integer getQty();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        ProductProjEmb getProduct();
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
}
