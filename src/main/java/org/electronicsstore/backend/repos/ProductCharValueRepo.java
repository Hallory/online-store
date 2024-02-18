package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.ProductChar;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductCharValueRepo extends ListCrudRepository<ProductChar, String> {
}
