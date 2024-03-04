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
import java.util.Collection;
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
        qtyInStock = 0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String article; // ?should it be unique
    @Column(nullable = false, unique = true)
    private String SKU; // probably unique
    private String barcode;
    @Column(columnDefinition = "integer not null check(price >= 0)")
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

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_char_value_conf_m2m",
        joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns =
            @JoinColumn(name = "product_char_value_id", referencedColumnName = "id", unique = true)
    )
    private Set<ProductCharValue> productCharValues;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "promo_id")
    private Promo promo;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    public Set<ProductCharValue> getProductCharValues() {
        return (productCharValues == null) ? productCharValues = new HashSet<>() : productCharValues;
    }

    public void addProductCharValue(ProductCharValue o) {
        getProductCharValues().add(o);
        o.getProducts().add(this);
    }

    public void addProductCharValue(Collection<ProductCharValue> o) {
        getProductCharValues().addAll(o);
        o.forEach(p -> p.getProducts().add(this));
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
