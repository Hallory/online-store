package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemRepo extends ListCrudRepository<ShoppingCartItem, String> {
    <P> List<P> findAllProjByShoppingCartCustomerId(String customerId, Class<P> clz);

    <P> List<P> findAllBy(Class<P> clz);

    <P> Optional<P> findProjById(String id, Class<P> clz);

    <P> List<P> findAllByIdIn(List<String> ids, Class<P> clz);
}
