package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.model.order.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
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
    private Double price;
    private String brand; // should be extended to hierarchy
    @Column(nullable = false) // ?should be unique
    private String name;
    private String description;
    private String productIcon; // general image in the product list
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    @ManyToMany
    @JoinTable(name = "product_char_value_conf_m2m",
        joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns =
            @JoinColumn(name = "product_char_value_id", referencedColumnName = "id")
    )
    private Set<ProductCharValue> productCharValues;

    @ManyToMany(mappedBy = "products")
    private Set<Promo> promos;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // ?uni
    private Set<OrderItem> orderItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(article, product.article) && Objects.equals(SKU, product.SKU) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, SKU, name);
    }
}
