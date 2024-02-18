package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.ProductItemChar;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductItemCharValueRepo extends ListCrudRepository<ProductItemChar, String> {
}
