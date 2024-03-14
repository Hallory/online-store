package org.electronicsstore.backend.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.dtos.customer.CustomerRefDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private Double totalAmount;
    private String shippingAddress;
    private String status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime approvedAt;
    private CustomerRefDto customerId;
    private ShippingMethodRefDto shippingMethodId;
    private List<OrderItemDto> orderItems;
}
