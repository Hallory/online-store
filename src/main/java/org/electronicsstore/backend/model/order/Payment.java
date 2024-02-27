package org.electronicsstore.backend.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double amount;
    private String paymentMethod;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(amount, payment.amount) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(createdAt, payment.createdAt) && Objects.equals(shopOrder, payment.shopOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, paymentMethod, createdAt, shopOrder);
    }
}
