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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    {
        price = 0.;
    }
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
    @Column(columnDefinition = "float(53) not null check(price >= 0.0)")
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
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

    public Set<ProductCharValue> getProductCharValues() {
        return (productCharValues == null) ? productCharValues = new HashSet<>() : productCharValues;
    }

    public void assignPromo(Promo promo) {
        this.promo = promo;
        promo.addProduct(this);
    }

    public void denyPromo(Promo promo) {
        this.promo = null;
        promo.getProducts().remove(this);
    }

    public void assignProductCategory(ProductCategory category) {
        this.productCategory = category;
        category.addProduct(this);
    }

    public void denyProductCategory(ProductCategory category) {
        this.productCategory = null;
        category.getProducts().remove(this);
    }

    public void addProductCharValue(ProductCharValue o) {
        getProductCharValues().add(o);
        o.getProducts().add(this);
    }

    public void removeProductCharValue(ProductCharValue o) {
        getProductCharValues().remove(o);
        o.getProducts().remove(this);
    }

    public void removeProductCharValue() {
        getProductCharValues().forEach(c -> c.getProducts().remove(this));
        getProductCharValues().clear();
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
