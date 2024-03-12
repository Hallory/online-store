package org.electronicsstore.backend.dtos.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.dtos.product.ProductRefDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private String text;
    private Double rating;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime deletedAt;
    private CustomerRefDto customerId;
    private ProductRefDto productId;
}
