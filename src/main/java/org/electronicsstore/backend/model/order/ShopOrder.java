package org.electronicsstore.backend.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.electronicsstore.backend.model.customer.Customer;
import org.electronicsstore.backend.model.product.ProductCharValue;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
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

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ToString.Exclude
    @OneToMany(mappedBy = "shopOrder", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST}, orphanRemoval = false)
    private Set<Payment> payments;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethod shippingMethod;

    @ToString.Exclude
    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    public Set<Payment> getPayments() {
        return (payments == null) ? payments = new HashSet<>() : payments;
    }

    public Set<OrderItem> getOrderItems() {
        return (orderItems == null) ? orderItems = new HashSet<>() : orderItems;
    }

    public void addPayment(Payment o) {
        getPayments().add(o);
        o.setShopOrder(this);
    }

    public void removePayment(Payment o) {
        getPayments().remove(o);
        o.setShopOrder(null);
    }

    public void addOrderItem(OrderItem o) {
        getOrderItems().add(o);
        o.setShopOrder(this);
    }

    public void removeOrderItem(OrderItem o) {
        getOrderItems().remove(o);
        o.setShopOrder(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrder shopOrder = (ShopOrder) o;
        return Objects.equals(shippingAddress, shopOrder.shippingAddress) && Objects.equals(createdAt, shopOrder.createdAt) && Objects.equals(customer, shopOrder.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingAddress, createdAt, customer);
    }
}
