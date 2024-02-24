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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"data", "product_char_id"})})
public class ProductCharValue {
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
        return Objects.equals(data, that.data) && Objects.equals(productChar, that.productChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, productChar);
    }
}
