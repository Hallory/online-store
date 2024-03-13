package org.electronicsstore.backend.dtos.customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CustomerProj {
    String getId();
    String getAccountId();
    String getUsername();
    String getFirstName();
    String getLastName();
    String getMiddleName();
    String getEmail();
    String getPhoneNum();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    LocalDateTime getDeletedAt();
    Boolean getIsDeleted();
    List<AddressProjEmb> getAddresses();
    ShoppingCartProjEmb getShoppingCart();
    List<ShopOrderProjEmb> getShopOrders();
    List<ReviewProjEmb> getReviews();

    interface AddressProjEmb {
        String getId();
        String getUnitNum();
        String getStreetNum();
        String getAddressLine1();
        String getAddressLine2();
        String getCity();
        String getRegion();
        String getPostalCode();
        Boolean getIsDefault();
    }

    interface ShoppingCartProjEmb {
        String getId();
        Double getTotal();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
    }

    interface ShopOrderProjEmb {
        String getId();
        Double getTotalAmount();
        String getShippingAddress();
        String getStatus();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        LocalDateTime getApprovedAt();
        List<OrderItemProjEmb> getOrderItems();
    }

    interface ReviewProjEmb {
        String getId();
        String getText();
        Double getRating();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        LocalDateTime getDeletedAt();
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
