package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepo extends ListCrudRepository<Address, String> {
}
