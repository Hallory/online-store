package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_product_category_id", nullable = true)
    private ProductCategory parentProductCategory;

    @OneToMany(mappedBy = "parentProductCategory")
    private Set<ProductCategory> childCategories;

    @OneToMany(mappedBy = "productCategory")
    private Set<ProductChar> productChars;

    @OneToMany(mappedBy = "productCategory")
    private Set<Product> products;

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
