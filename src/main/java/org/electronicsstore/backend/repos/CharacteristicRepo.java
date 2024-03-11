package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.product.Characteristic;
import org.springframework.data.jpa.repository.Query;
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
    // todo check
    @Query(value = "WITH RECURSIVE category_tree AS (" +
            "select c1.id, c1.name, c1.description, c1.created_at, c1.modified_at, c1.parent_id " +
            "from category c1 " +
            "where id = :categoryId " +
            "UNION ALL " +
            "select c2.id, c2.name, c2.description, c2.created_at, c2.modified_at, c2.parent_id " +
            "from category c2 " +
            "INNER JOIN category_tree ct ON c2.id = ct.parent_id " +
            ") select * " +
            "from characteristic ch" +
            "where ch.category_id = category_tree.id " +
            "INNER JOIN characteristic_value cv ON cv.characteristic_id = ch.id " +
            "INNER JOIN product_characteristic_value_conf_m2m pcv.product_id = :productId", nativeQuery = true)
    List<Characteristic> findAllCharsRelatedToProductId(String productId, Long categoryId);
}
