package org.electronicsstore.backend.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.dtos.customer.AddressRefDto;
import org.electronicsstore.backend.dtos.customer.CustomerRefDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderProcessDto {
    private AddressRefDto addressId;
    private CustomerRefDto customerId;
    private ShippingMethodRefDto shippingMethodId;
}
