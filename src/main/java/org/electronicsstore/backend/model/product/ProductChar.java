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
    private String dataType;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}) // or m2m if restricted variety of products is chosen
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "productChar", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductCharValue> productCharValues;

    public Set<ProductCharValue> getProductCharValues() {
        return (productCharValues == null) ? productCharValues = new HashSet<>() : productCharValues;
    }

    public void addProductCharValue(ProductCharValue o) {
        getProductCharValues().add(o);
        o.setProductChar(this);
    }

    public void removeProductCharValue(ProductCharValue o) {
        getProductCharValues().remove(o);
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
