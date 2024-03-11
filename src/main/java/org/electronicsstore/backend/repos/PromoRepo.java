package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.Promo;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PromoRepo extends ListCrudRepository<Promo, Long> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> Optional<P> findProjById(Long promoId, Class<P> type);
    <P> List<P> findAllByIdIn(Iterable<Long> ids, Class<P> clz);
}
