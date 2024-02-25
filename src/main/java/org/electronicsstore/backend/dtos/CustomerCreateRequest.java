package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.customer.Customer;

public record CustomerCreateRequest(
        String username,
        String firstName,
        String lastName,
        String password
) {
    public static Customer dtoToModel(CustomerCreateRequest dto) {
        var customer = new Customer();
        customer.setUsername(dto.username);
        customer.setFirstName(dto.firstName);
        customer.setLastName(dto.lastName);
        return customer;
    }
}
