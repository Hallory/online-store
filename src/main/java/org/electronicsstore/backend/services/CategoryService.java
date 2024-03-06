package org.electronicsstore.backend.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.exceptions.EntityBadRequestException;
import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.CategoryRepo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryService implements BaseService<Category, Long> {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return categoryRepo.findAllBy(clz);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Category not found, name = " + id));
    }

    @Override
    public <P> P findProjById(Long id, Class<P> clz) {
        return categoryRepo.findProjById(id, clz).orElseThrow(() -> new CustomEntityNotFoundException("Category not found, name = " + id));
    }

    @Override
    public List<Category> findAllById(List<Long> ids) {
        return categoryRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<Long> ids, Class<P> clz) {
        return categoryRepo.findAllByIdIn(ids, clz);
    }

    @Valid
    @Override
    public Category createOne(Category entity) {
        var parent = entity.getParent();
        if (parent == null) {
            throw new EntityBadRequestException("Parent category is not defined");
        }
        if (parent.getId() == null) {
            throw new EntityBadRequestException("Parent category id is not defined");
        }
        if (parent.isLeaf()) {
            var products = productRepo.findAllProjByCategoryId(parent.getId(), Product.class);
            entity.addProduct(products);
        }
        if (!categoryRepo.existsById(parent.getId())) {
            throw new EntityBadRequestException("Parent category is not existed");
        }
        if (categoryRepo.existsByName(entity.getName())) {
            throw new CustomEntityNotFoundException("Product category is already present, name = " + entity.getName());
        }
        return categoryRepo.save(entity);
    }

    @Valid
    @Override
    public Category updateOne(Long id, Category entity) {
        return categoryRepo.save(entity);
    }

    @Valid
    @Override
    public Category patchOne(Long id, Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteOne(Long id) {
        var category = findById(id);
        boolean isRootDelete = category.getParent() == null;
        categoryRepo.delete(category);
    }

    public boolean existsByParentId(Long parentId) {
        return categoryRepo.existsByParentId(parentId);
    }

    public <P> P findByParentIsNull(Class<P> clz) {
        return categoryRepo.findByParentIsNull(clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    public boolean existsByParentIsNull() {
        return categoryRepo.existsByParentIsNull();
    }

    private boolean isCategoryParent(Category category) {
        return category.getChildren().isEmpty();
    }

    Category createRandomCategory() {
        Category category = new Category();
        String name = UUID.randomUUID().toString();
        category.setName(name);
        category.setDescription("name");
        return category;
    }

    public Category createRootCategory() {
        var rootCategory = new Category();
        rootCategory.setName("Root Category");
        rootCategory.setDescription("Mandatory root category");
        return categoryRepo.save(rootCategory);
    }
}
