package org.electronicsstore.backend.model;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"value", "product_item_variation_id"})})
public class ProductItemVariationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String value; // black, xl

    @ManyToOne
    @JoinColumn(name = "product_item_variation_id")
    private ProductItemVariation productItemVariation;

    @ManyToMany(mappedBy = "productItemVariationOptions")
    private Set<ProductItem> productItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItemVariationOption that = (ProductItemVariationOption) o;
        return Objects.equals(value, that.value) && Objects.equals(productItemVariation, that.productItemVariation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, productItemVariation);
    }
}
