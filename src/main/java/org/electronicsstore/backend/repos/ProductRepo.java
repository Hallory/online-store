package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ProductRepo extends ListCrudRepository<Product, String> {
    Optional<Product> findByTitle(String title);
    boolean existsByTitle(String title);
}
