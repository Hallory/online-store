package org.electronicsstore.backend.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShippingMethod {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String price;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "shippingMethod") // ?uni
    private Set<ShopOrder> shopOrders;

    public void addShopOrder(ShopOrder o) {
        shopOrders.add(o);
        o.setShippingMethod(this);
    }

    public void removeShopOrder(ShopOrder o) {
        shopOrders.remove(o);
        o.setShippingMethod(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingMethod that = (ShippingMethod) o;
        return Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createdAt);
    }
}
