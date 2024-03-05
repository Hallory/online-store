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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Category> children;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Characteristic> characteristics;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Product> products;

    public Set<Category> getChildren() {
        return (children == null) ? children = new HashSet<>() : children;
    }

    public Set<Characteristic> getCharacteristics() {
        return (characteristics == null) ? characteristics = new HashSet<>() : characteristics;
    }

    public Set<Product> getProducts() {
        return (products == null) ? products = new HashSet<>() : products;
    }

    public void addChild(Category o) {
        getChildren().add(o);
        o.setParent(this);
    }

    public void addChild(Collection<Category> productCategories) {
        getChildren().addAll(productCategories);
        productCategories.forEach(c -> c.setParent(this));
    }

    public void removeChild(Category o) {
        getChildren().remove(o);
        o.setParent(null);
    }

    public void removeChild() {
        getChildren().forEach(c -> c.setParent(null));
        getChildren().clear();
    }

    public void addCharacteristic(Characteristic o) {
        getCharacteristics().add(o);
        o.setCategory(this);
    }

    public void addCharacteristic(Collection<Characteristic> o) {
        getCharacteristics().addAll(o);
        o.forEach(c -> c.setCategory(this));
    }

    public void removeCharacteristic(Characteristic o) {
        getCharacteristics().remove(o);
        o.setCategory(null);
    }

    public void removeCharacteristic() {
        getCharacteristics().forEach(c -> c.setCategory(null));
        getCharacteristics().clear();
    }

    public void addProduct(Product o) {
        getProducts().add(o);
        o.setCategory(this);
    }

    public void addProduct(Collection<Product> products) {
        this.getProducts().addAll(products);
        products.forEach(p -> p.setCategory(this));
    }

    public void removeProduct(Product o) {
        getProducts().remove(o);
        o.setCategory(null);
    }

    public void removeProduct(Collection<Product> o) {
        getProducts().removeAll(o);
        o.forEach(c -> c.setCategory(null));
    }

    public void removeProduct() {
        getProducts().forEach(c -> c.setCategory(null));
        getProducts().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
