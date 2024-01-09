package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.ProductItemVariation;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductItemVariationOptionRepo extends ListCrudRepository<ProductItemVariation, String> {
}
