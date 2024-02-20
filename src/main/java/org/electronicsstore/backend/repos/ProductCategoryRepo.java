package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.ProductCategory;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ProductCategoryRepo extends ListCrudRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
    boolean existsByName(String name);
}
