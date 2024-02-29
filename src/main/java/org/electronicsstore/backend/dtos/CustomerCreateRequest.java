package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.customer.Customer;

public record CustomerCreateRequest(
        String username,
        String firstName,
        String lastName,
        String password
) {
}
