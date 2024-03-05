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
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"data", "characteristic_id"})})
public class CharacteristicValue {
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
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "characteristicValues", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Product> products;

    public Set<Product> getProducts() {
        return (products == null) ? products = new HashSet<>() : products;
    }

    public void addProducts(Product product) {
        getProducts().add(product);
        product.getCharacteristicValues().add(this);
    }

    public void addProducts(Collection<Product> products) {
        getProducts().addAll(products);
        products.forEach(p -> p.getCharacteristicValues().add(this));
    }

    public void removeProducts(Product product) {
        getProducts().remove(product);
        product.getCharacteristicValues().remove(this);
    }

    public void removeProducts() {
        getProducts().forEach(p -> p.getCharacteristicValues().remove(this));
        getProducts().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacteristicValue that = (CharacteristicValue) o;
        return Objects.equals(data, that.data) && Objects.equals(createdAt, that.createdAt) && Objects.equals(characteristic, that.characteristic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, createdAt, characteristic);
    }
}
