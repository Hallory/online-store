package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "parent_product_category_id", nullable = true)
    private ProductCategory parentProductCategory;

    @OneToMany(mappedBy = "parentProductCategory")
    private Set<ProductCategory> childCategories;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductChar> productChars;

    @OneToMany(mappedBy = "productCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Product> products;

    public void addProductCategory(ProductCategory o) {
        childCategories.add(o);
        o.setParentProductCategory(this);
    }

    public void removeProductCategory(ProductCategory o) {
        childCategories.remove(o);
        o.setParentProductCategory(null);
    }

    public void addProductChar(ProductChar o) {
        productChars.add(o);
        o.setProductCategory(this);
    }

    public void removeProductChar(ProductChar o) {
        productChars.remove(o);
        o.setProductCategory(null);
    }

    public void addProduct(Product o) {
        products.add(o);
        o.setProductCategory(this);
    }

    public void removeProduct(Product o) {
        products.remove(o);
        o.setProductCategory(null);
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
