package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.Customer;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepo extends ListCrudRepository<Customer, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
