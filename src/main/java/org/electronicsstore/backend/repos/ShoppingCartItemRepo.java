package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.springframework.data.repository.ListCrudRepository;

public interface ShoppingCartItemRepo extends ListCrudRepository<ShoppingCartItem, String> {
}
