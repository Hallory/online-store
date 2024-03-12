package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.Review;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends ListCrudRepository<Review, String> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> Optional<P> findProjById(String id, Class<P> clz);
    <P> List<P> findAllProjByIdIn(List<String> ids, Class<P> clz);
    <P> List<P> findAllProjByCustomerId(String customerId, Class<P> clz);
    <P> List<P> findAllProjByProductId(String productId, Class<P> clz);
}
