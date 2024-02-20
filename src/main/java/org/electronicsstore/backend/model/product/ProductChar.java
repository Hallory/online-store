package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "product_category_id"}))
public class ProductChar {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name; // colour, size

    @ManyToOne // or m2m if restricted variety of products is chosen
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "productChar", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductCharValue> productCharValues;

    public void addProductCharValue(ProductCharValue o) {
        productCharValues.add(o);
        o.setProductChar(this);
    }

    public void removeProductCharValue(ProductCharValue o) {
        productCharValues.remove(o);
        o.setProductChar(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductChar that = (ProductChar) o;
        return Objects.equals(name, that.name) && Objects.equals(productCategory, that.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, productCategory);
    }
}
