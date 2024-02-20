package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.order.ShippingMethod;
import org.springframework.data.repository.ListCrudRepository;

public interface ShippingMethodRepo extends ListCrudRepository<ShippingMethod, Long> {
}
