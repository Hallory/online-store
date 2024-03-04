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
@Table(name = "product_category", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class ProductCategory {
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
    private ProductCategory parent;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<ProductCategory> children;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductChar> productChars;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "productCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Product> products;

    public Set<ProductCategory> getChildren() {
        return (children == null) ? children = new HashSet<>() : children;
    }

    public Set<ProductChar> getProductChars() {
        return (productChars == null) ? productChars = new HashSet<>() : productChars;
    }

    public Set<Product> getProducts() {
        return (products == null) ? products = new HashSet<>() : products;
    }

    public void addChild(ProductCategory o) {
        getChildren().add(o);
        o.setParent(this);
    }

    public void addChild(Collection<ProductCategory> productCategories) {
        getChildren().addAll(productCategories);
        productCategories.forEach(c -> c.setParent(this));
    }

    public void removeChild(ProductCategory o) {
        getChildren().remove(o);
        o.setParent(null);
    }

    public void removeChild() {
        getChildren().forEach(c -> c.setParent(null));
        getChildren().clear();
    }

    public void addProductChar(ProductChar o) {
        getProductChars().add(o);
        o.setProductCategory(this);
    }

    public void addProductChar(Collection<ProductChar> o) {
        getProductChars().addAll(o);
        o.forEach(c -> c.setProductCategory(this));
    }

    public void removeProductChar(ProductChar o) {
        getProductChars().remove(o);
        o.setProductCategory(null);
    }

    public void removeProductChar() {
        getProductChars().forEach(c -> c.setProductCategory(null));
        getProductChars().clear();
    }

    public void addProduct(Product o) {
        getProducts().add(o);
        o.setProductCategory(this);
    }

    public void addProduct(Collection<Product> products) {
        this.getProducts().addAll(products);
        products.forEach(p -> p.setProductCategory(this));
    }

    public void removeProduct(Product o) {
        getProducts().remove(o);
        o.setProductCategory(null);
    }

    public void removeProduct(Collection<Product> o) {
        getProducts().removeAll(o);
        o.forEach(c -> c.setProductCategory(null));
    }

    public void removeProduct() {
        getProducts().forEach(c -> c.setProductCategory(null));
        getProducts().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
