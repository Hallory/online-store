package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.Country;
import org.springframework.data.repository.ListCrudRepository;

public interface CountryRepo extends ListCrudRepository<Country, Long> {
}
