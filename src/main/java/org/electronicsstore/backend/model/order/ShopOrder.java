package org.electronicsstore.backend.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.customer.Customer;
import org.electronicsstore.backend.model.product.ProductCharValue;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double totalAmount;
    private String shippingAddress;
    private String status;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    private LocalDateTime approvedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "shopOrder")
    private Set<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethod shippingMethod;

    @OneToMany(mappedBy = "shopOrder")
    private Set<OrderItem> orderItems;

    public void addPayment(Payment o) {
        payments.add(o);
        o.setShopOrder(this);
    }

    public void removePayment(Payment o) {
        payments.remove(o);
        o.setShopOrder(null);
    }

    public void addOrderItem(OrderItem o) {
        orderItems.add(o);
        o.setShopOrder(this);
    }

    public void removeOrderItem(OrderItem o) {
        orderItems.remove(o);
        o.setShopOrder(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrder shopOrder = (ShopOrder) o;
        return Objects.equals(createdAt, shopOrder.createdAt) && Objects.equals(customer, shopOrder.customer) && orderItems.equals(shopOrder.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, customer, orderItems);
    }
}
