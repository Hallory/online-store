package org.electronicsstore.backend.dtos.order;

import java.time.LocalDateTime;

public interface PaymentProj {
    String getId();
    Double getAmount();
    String getPaymentMethod();
    LocalDateTime getCreatedAt();
    ShopOrderProjEmb getShopOrder();

    interface ShopOrderProjEmb {
        String getId();
        Double getTotalAmount();
        String getShippingAddress();
        String getStatus();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        LocalDateTime getApprovedAt();
        CustomerProjEmb getCustomer();
    }

    interface CustomerProjEmb {
        String getId();
        Double getTotalAmount();
        String getShippingAddress();
        String getStatus();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        LocalDateTime getApprovedAt();
    }
}
