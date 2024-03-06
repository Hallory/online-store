package org.electronicsstore.backend.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 5, max = 10)
    @Column(nullable = false, unique = true)
    private String article;
    @NotBlank
    @Size(min = 5, max = 50)
    @Column(nullable = false)
    private String sku;
    @NotBlank
    @Size(min = 5, max = 50)
    @Column(nullable = false)
    private String name;
    private String barcode;
    @NotNull
    @Size(min = 5)
    @Column(columnDefinition = "integer not null check(price >= 0)")
    private Integer qtyInStock;
    private Set<String> productImages;
    @NotNull
    @Size(min = 5)
    @Column(columnDefinition = "float(53) not null check(price >= 0.0)")
    private Double price;
    private String brand; // should be extended to hierarchy
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
    @JoinTable(name = "product_characteristic_value_conf_m2m",
        joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns =
            @JoinColumn(name = "characteristic_value_id", referencedColumnName = "id", unique = true)
    )
    private Set<CharacteristicValue> characteristicValues;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "promo_id")
    private Promo promo;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

    public Set<CharacteristicValue> getCharacteristicValues() {
        return (characteristicValues == null) ? characteristicValues = new HashSet<>() : characteristicValues;
    }

    public void addCharacteristicValue(CharacteristicValue o) {
        getCharacteristicValues().add(o);
        o.getProducts().add(this);
    }

    public void addCharacteristicValue(Collection<CharacteristicValue> o) {
        getCharacteristicValues().addAll(o);
        o.forEach(p -> p.getProducts().add(this));
    }

    public void removeCharacteristicValue(CharacteristicValue o) {
        getCharacteristicValues().remove(o);
        o.getProducts().remove(this);
    }

    public void removeCharacteristicValue() {
        getCharacteristicValues().forEach(c -> c.getProducts().remove(this));
        getCharacteristicValues().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(article, product.article) && Objects.equals(sku, product.sku) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, sku, name);
    }
}
