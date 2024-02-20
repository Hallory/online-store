package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;

public interface ShoppingCartRepo extends ListCrudRepository<ShoppingCart, String> {
}
