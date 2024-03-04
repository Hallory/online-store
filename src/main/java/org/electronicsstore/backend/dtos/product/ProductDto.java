package org.electronicsstore.backend.dtos.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.model.product.ProductCharValue;
import org.electronicsstore.backend.model.product.Promo;
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
public class ProductDto {
    private String id;
    private String article;
    private String SKU;
    private String barcode;
    private Integer qtyInStock;
    private Set<String> productImages;
    private Double price;
    private String brand;
    private String name;
    private String description;
    private String productIcon;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto product = (ProductDto) o;
        return Objects.equals(article, product.article) && Objects.equals(SKU, product.SKU) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, SKU, name);
    }
}
