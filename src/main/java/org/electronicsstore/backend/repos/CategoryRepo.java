package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends ListCrudRepository<Category, Long> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> List<P> findAllByIdIn(Iterable<Long> ids, Class<P> clz);
    <P> Optional<P> findProjById(Long aLong, Class<P> type);
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
