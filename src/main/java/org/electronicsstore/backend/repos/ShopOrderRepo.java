package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.order.ShopOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShopOrderRepo extends ListCrudRepository<ShopOrder, String> {

    <P> List<P> findAllBy(Class<P> clz);

    <P> Optional<P> findProjById(String id, Class<P> clz);

    <P> List<P> findAllProjByIdIn(List<String> ids, Class<P> clz);
}
