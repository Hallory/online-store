package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.Characteristic;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CharacteristicRepo extends ListCrudRepository<Characteristic, Long> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> List<P> findAllByIdIn(Iterable<Long> ids, Class<P> clz);
    <P> Optional<P> findProjById(Long id, Class<P> type);
    Optional<Characteristic> findByName(String name);
    boolean existsByName(String name);
    <P> List<P> findAllProjByCategoryId(Long id, Class<P> clz);
//    boolean existsByIdAndCategoryId()
}
