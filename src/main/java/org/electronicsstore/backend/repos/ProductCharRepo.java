package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.ProductChar;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductCharRepo extends ListCrudRepository<ProductChar, Long> {
    boolean existsByName(String name);
}
