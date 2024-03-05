package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.CharacteristicValue;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CharacteristicValueRepo extends ListCrudRepository<CharacteristicValue, Long> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> List<P> findAllByIdIn(Iterable<Long> ids, Class<P> clz);
    <P> Optional<P> findProjById(Long id, Class<P> type);
    <P> List<P> findAllProjByCharacteristicCategoryId(Long categoryId, Class<P> clz);
    <P> List<P> findAllProjByCharacteristicId(Long charId, Class<P> clz);
    <P> List<P> findAllProjByProductsId(String productId, Class<P> clz);
}
