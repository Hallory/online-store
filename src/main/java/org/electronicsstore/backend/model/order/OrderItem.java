package org.electronicsstore.backend.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.model.product.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    public static OrderItem prepareOrderItemFromShoppingCartItem(ShoppingCartItem cartItem) {
        var orderItem = new OrderItem(
                null,
                cartItem.getProduct().getSku(),
                cartItem.getProduct().getPrice(),
                null,
                cartItem.getQty(),
                null,
                null,
                cartItem.getProduct(),
                null);
        orderItem.setTotalAmount(orderItem.calcTotal().doubleValue());
        return orderItem;
    }

    public BigDecimal calcTotal() {
        return BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(qty));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, updatable = false)
    private String productSKU;
    private Double pricePerItem;
    private Double totalAmount;
    private Integer qty;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(productSKU, orderItem.productSKU) && Objects.equals(createdAt, orderItem.createdAt) && Objects.equals(shopOrder, orderItem.shopOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSKU, createdAt, shopOrder);
    }
}
