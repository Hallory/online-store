package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.Promo;
import org.springframework.data.repository.ListCrudRepository;

public interface PromoRepo extends ListCrudRepository<Promo, String> {
}
