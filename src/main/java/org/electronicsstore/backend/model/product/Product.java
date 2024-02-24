package org.electronicsstore.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.customer.CustomerReview;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.model.order.OrderItem;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
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

    @ManyToOne
    @JoinColumn(name = "promo_id")
    private Promo promo;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // ?uni
    private Set<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerReview> customerReviews;

    public void addCustomerReview(CustomerReview o) {
        customerReviews.add(o);
        o.setProduct(this);
    }

    public void removeCustomerReview(CustomerReview o) {
        customerReviews.remove(o);
        o.setProduct(null);
    }

    public void addOrderItem(OrderItem o) {
        orderItems.add(o);
        o.setProduct(this);
    }

    public void removeProduct(OrderItem o) {
        orderItems.remove(o);
        o.setProduct(null);
    }

    public void addProductCharValue(ProductCharValue o) {
        productCharValues.add(o);
        o.getProducts().add(this);
    }

    public void removeProductCharValue(ProductCharValue o) {
        productCharValues.remove(o);
        o.getProducts().remove(this);
    }

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
