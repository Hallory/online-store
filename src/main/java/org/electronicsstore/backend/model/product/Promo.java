package org.electronicsstore.backend.model.product;

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
public class Promo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double discountRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "promo", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Product> products;

    public void addProduct(Product product) {
        products.add(product);
        product.setPromo(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setPromo(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promo promo = (Promo) o;
        return Objects.equals(name, promo.name) && Objects.equals(createdAt, promo.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createdAt);
    }
}
