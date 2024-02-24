package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.customer.Customer;

public record CustomerCreateReq(
        String username,
        String firstName,
        String lastName,
        String password
) {
    public static Customer dtoToModel(CustomerCreateReq dto) {
        var customer = new Customer();
        customer.setUsername(dto.username);
        customer.setFirstName(dto.firstName);
        customer.setLastName(dto.lastName);
        return customer;
    }
}
