package org.electronicsstore.backend.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "promo", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Product> products;

    public Set<Product> getProducts() {
        return (products == null) ? products = new HashSet<>() : products;
    }

    public void addProduct(Product product) {
        getProducts().add(product);
        product.setPromo(this);
    }

    public void removeProduct(Product product) {
        getProducts().remove(product);
        product.setPromo(null);
    }

    public void removeProduct() {
        getProducts().forEach(p -> p.setPromo(null));
        getProducts().clear();
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
