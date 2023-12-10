package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepo extends ListCrudRepository<Product, String> {
}
