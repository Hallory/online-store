package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"data", "product_char_id"})})
public class ProductCharValue {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String data; // black, xl
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "product_char_id")
    private ProductChar productChar;

    @ToString.Exclude
    @ManyToMany(mappedBy = "productCharValues", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Product> products;

    public Set<Product> getProducts() {
        return (products == null) ? products = new HashSet<>() : products;
    }

    public void addProducts(Product product) {
        getProducts().add(product);
        product.getProductCharValues().add(this);
    }

    public void addProducts(Collection<Product> products) {
        getProducts().addAll(products);
        products.forEach(p -> p.getProductCharValues().add(this));
    }

    public void removeProducts(Product product) {
        getProducts().remove(product);
        product.getProductCharValues().remove(this);
    }

    public void removeProducts() {
        getProducts().forEach(p -> p.getProductCharValues().remove(this));
        getProducts().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCharValue that = (ProductCharValue) o;
        return Objects.equals(data, that.data) && Objects.equals(productChar, that.productChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, productChar);
    }
}
