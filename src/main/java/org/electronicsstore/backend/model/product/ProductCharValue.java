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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"value", "product_char_id"})})
public class ProductCharValue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String value; // black, xl

    @ManyToOne
    @JoinColumn(name = "product_char_id")
    private ProductChar productChar;

    @ManyToMany(mappedBy = "productCharValues")
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCharValue that = (ProductCharValue) o;
        return Objects.equals(value, that.value) && Objects.equals(productChar, that.productChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, productChar);
    }
}
