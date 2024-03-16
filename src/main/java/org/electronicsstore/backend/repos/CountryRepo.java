package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.Country;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepo extends ListCrudRepository<Country, Long> {
    <P> List<P> findAllBy(Class<P> clz);

    <P> Optional<P> findProjById(Long countryId, Class<P> clz);
}
