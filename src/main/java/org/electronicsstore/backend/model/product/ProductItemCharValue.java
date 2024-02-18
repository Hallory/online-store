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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"value", "product_item_char_id"})})
public class ProductItemCharValue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String value; // black, xl

    @ManyToOne
    @JoinColumn(name = "product_item_char_id")
    private ProductItemChar productItemChar;

    @ManyToMany(mappedBy = "productItemCharValues")
    private Set<ProductItem> productItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItemCharValue that = (ProductItemCharValue) o;
        return Objects.equals(value, that.value) && Objects.equals(productItemChar, that.productItemChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, productItemChar);
    }
}
