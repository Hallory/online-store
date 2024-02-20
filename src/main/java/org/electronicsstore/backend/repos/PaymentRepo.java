package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.order.Payment;
import org.springframework.data.repository.ListCrudRepository;

public interface PaymentRepo extends ListCrudRepository<Payment, String> {
}
