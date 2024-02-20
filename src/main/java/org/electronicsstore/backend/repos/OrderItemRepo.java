package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.order.OrderItem;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderItemRepo extends ListCrudRepository<OrderItem, String> {
}
