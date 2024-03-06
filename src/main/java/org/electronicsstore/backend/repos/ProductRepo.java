package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.model.product.Product;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends ListCrudRepository<Product, String> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> List<P> findAllProjByCategoryId(Long categoryId, Class<P> clz);
    <P> List<P> findAllByIdIn(Iterable<String> ids, Class<P> clz);
    <P> Optional<P> findProjById(String id, Class<P> type);
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    boolean existsByArticle(String name);
}
