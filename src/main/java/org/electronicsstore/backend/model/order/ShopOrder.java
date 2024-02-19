package org.electronicsstore.backend.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.customer.Customer;

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
    private LocalDateTime createdAt;
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
