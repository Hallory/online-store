package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepo extends ListCrudRepository<ShoppingCart, String> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> Optional<P> findProjById(String promoId, Class<P> type);
    <P> List<P> findAllByIdIn(Iterable<String> ids, Class<P> clz);
    <P> Optional<P> findProjByCustomerId(String customerId, Class<P> clz);
    boolean existsByCustomerId(String customerId);
}
