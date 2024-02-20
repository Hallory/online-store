package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.order.ShopOrder;
import org.springframework.data.repository.ListCrudRepository;

public interface ShopOrderRepo extends ListCrudRepository<ShopOrder, String> {
}
