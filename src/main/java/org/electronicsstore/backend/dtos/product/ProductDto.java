package org.electronicsstore.backend.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    @NotBlank
    @Size(min = 5, max = 10)
    private String article;
    @NotBlank
    @Size(min = 5, max = 50)
    private String sku;
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;
    private String barcode;
    private Integer qtyInStock;
    private Set<String> productImages;
    private Double price;
    private String brand;
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
        return Objects.equals(article, product.article) && Objects.equals(sku, product.sku) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, sku, name);
    }
}
