package org.electronicsstore.backend.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoPutDto {
    private Long id;
    private String name;
    private String description;
    private Double discountRate;
    private List<String> productIds;

    public List<String> getProductIds() {
        return (productIds == null) ? productIds = new ArrayList<>() : productIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoPutDto that = (PromoPutDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
