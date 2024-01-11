package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.Customer;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepo extends ListCrudRepository<Customer, String> {
}
