package org.electronicsstore.backend.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String description;
    private Double discountRate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime startDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime endDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<ProductDto> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoDto promoDto = (PromoDto) o;
        return Objects.equals(name, promoDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
