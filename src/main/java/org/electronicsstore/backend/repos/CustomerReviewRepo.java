package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.CustomerReview;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerReviewRepo extends ListCrudRepository<CustomerReview, String> {
}
