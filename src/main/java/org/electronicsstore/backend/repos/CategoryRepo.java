package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends ListCrudRepository<Category, Long> {
    <P> List<P> findAllBy(Class<P> clz);
    <P> List<P> findAllByIdIn(Iterable<Long> ids, Class<P> clz);
    <P> Optional<P> findProjById(Long aLong, Class<P> type);
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    boolean existsByParentId(Long parentId);
    <P> Optional<P> findByParentIsNull(Class<P> clz);
    boolean existsByParentIsNull();

    @Query(value = "WITH RECURSIVE category_tree AS (" +
            "select c1.id, c1.name, c1.description, c1.created_at, c1.modified_at, c1.parent_id " +
            "from category c1 " +
            "where id = :id " +
            "UNION ALL " +
            "select c2.id, c2.name, c2.description, c2.created_at, c2.modified_at, c2.parent_id " +
            "from category c2 " +
            "INNER JOIN category_tree ct ON c2.parent_id = ct.id " +
            ") select * from category_tree", nativeQuery = true)
    List<Category> findCategoryTreeDown(Long id);

    @Query(value = "WITH RECURSIVE category_tree AS (" +
            "select c1.id, c1.name, c1.description, c1.created_at, c1.modified_at, c1.parent_id " +
            "from category c1 " +
            "where id = :id " +
            "UNION ALL " +
            "select c2.id, c2.name, c2.description, c2.created_at, c2.modified_at, c2.parent_id " +
            "from category c2 " +
            "INNER JOIN category_tree ct ON c2.id = ct.parent_id " +
            ") select * from category_tree", nativeQuery = true)
    List<Category> findCategoryTreeUp(Long id);
}
