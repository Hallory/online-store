package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.customer.Address;
import org.electronicsstore.backend.model.customer.Customer;

import java.time.LocalDateTime;
import java.util.List;

public record CustomerDto (
        String id,
        String accountId,
        String username,
        String firstName,
        String lastName,
        String middleName,
        String email,
        String phoneNum,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime deletedAt,
        String shoppingCartId,
        List<String> addressIds
) {
    public static CustomerDto modelToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getAccountId(),
                customer.getUsername(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getMiddleName(),
                customer.getEmail(),
                customer.getPhoneNum(),
                customer.getCreatedAt(),
                customer.getModifiedAt(),
                customer.getDeletedAt(),
                customer.getShoppingCart().getId(),
                customer.getAddresses().stream().map(Address::getId).toList()
        );
    }
}
