package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.ProductItemChar;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductItemCharValueRepo extends ListCrudRepository<ProductItemChar, String> {
}
