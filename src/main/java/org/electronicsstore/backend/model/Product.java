package org.electronicsstore.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String brand;
    private String description;
    private BigDecimal price;
    @Column(name = "discount_percentage")
    private Double discountPercentage;
    private Double rating;
    private Integer stock;
    private String thumbnail;
    private List<String> images;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(title, product.title) && Objects.equals(brand, product.brand) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, brand, description);
    }
}
