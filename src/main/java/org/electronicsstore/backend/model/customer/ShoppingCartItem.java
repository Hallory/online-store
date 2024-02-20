package org.electronicsstore.backend.model.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.product.Product;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"shopping_cart_id", "product_id"}))
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(columnDefinition = "integer not null CHECK (qty > 0)")
    private Integer qty; // > 0
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @NotNull
    @OneToOne // optional = false
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return Objects.equals(shoppingCart, that.shoppingCart) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCart, product);
    }
}
