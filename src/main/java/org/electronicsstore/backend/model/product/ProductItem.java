package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_item")
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String article; // ?should it be unique
    @Column(nullable = false, unique = true)
    private String SKU; // probably unique
    @Column(nullable = false)
    private Integer qtyInStock;
    private Set<String> productImages;
    private BigDecimal price;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private String brand; // should be extended to hierarchy

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany
    @JoinTable(name = "product_item_char_value_conf_m2m",
        joinColumns =
            @JoinColumn(name = "product_item_id", referencedColumnName = "id"),
        inverseJoinColumns =
            @JoinColumn(name = "product_item_char_value_id", referencedColumnName = "id")
    )
    private Set<ProductItemCharValue> productItemCharValues;

    @ManyToMany(mappedBy = "productItems")
    private Set<Promo> promos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return Objects.equals(article, that.article) && Objects.equals(SKU, that.SKU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, SKU);
    }
}
