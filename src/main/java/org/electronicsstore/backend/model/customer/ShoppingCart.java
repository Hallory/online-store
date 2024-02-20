package org.electronicsstore.backend.model.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.order.ShopOrder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private Double total; // > 0
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @NotNull
    @OneToOne
    @JoinColumn(name = "customer_id", unique = true) // nullable = false
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShoppingCartItem> shoppingCartItems;

    public void addShoppingCartItem(ShoppingCartItem o) {
        shoppingCartItems.add(o);
        o.setShoppingCart(this);
    }

    public void removeShoppingCartItem(ShoppingCartItem o) {
        shoppingCartItems.remove(o);
        o.setShoppingCart(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer);
    }
}
