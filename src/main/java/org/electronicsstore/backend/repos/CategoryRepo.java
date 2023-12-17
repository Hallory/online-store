package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.Category;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface CategoryRepo extends ListCrudRepository<Category, String> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
