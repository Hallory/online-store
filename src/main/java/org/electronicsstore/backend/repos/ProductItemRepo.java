package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.ProductItem;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductItemRepo extends ListCrudRepository<ProductItem, String> {
}
