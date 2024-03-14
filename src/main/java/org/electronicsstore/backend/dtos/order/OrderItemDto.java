package org.electronicsstore.backend.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.dtos.product.ProductRefDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private String productSKU;
    private Double pricePerItem;
    private Double totalAmount;
    private Integer qty;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedAt;
    private ProductRefDto productId;
}
