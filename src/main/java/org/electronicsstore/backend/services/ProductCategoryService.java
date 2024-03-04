package org.electronicsstore.backend.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.ProductCategoryCreateRequest;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class ProductCategoryService implements BaseService<ProductCategory, Long> {
    private final ProductCategoryRepo productCategoryRepo;

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return productCategoryRepo.findAllBy(clz);
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Category not found, name = " + id));
    }

    @Override
    public <P> P findProjById(Long id, Class<P> clz) {
        return productCategoryRepo.findProjById(id, clz).orElseThrow(() -> new CustomEntityNotFoundException("Category not found, name = " + id));
    }

    @Override
    public List<ProductCategory> findAllById(List<Long> ids) {
        return productCategoryRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<Long> ids, Class<P> clz) {
        return productCategoryRepo.findAllByIdIn(ids, clz);
    }

    @Valid
    @Override
    public ProductCategory createOne(ProductCategory entity) {
        if (productCategoryRepo.existsByName(entity.getName())) {
            throw new CustomEntityNotFoundException("Product category is already present, name = " + entity.getName());
        }
        return productCategoryRepo.save(entity);
    }

//    @Valid
//    public ProductCategory createOne(ProductCategoryCreateRequest req) { // todo children manipulating
//        if (productCategoryRepo.existsByName(req.name())) {
//            throw new CustomEntityNotFoundException("Product category is already present, name = " + req.name());
//        }
//        var productCategory = assignParentProductCategory(req);
//        return createOne(productCategory);
//    }

    @Valid
    @Override
    public ProductCategory updateOne(Long id, ProductCategory entity) {
        return productCategoryRepo.save(entity);
    }

    @Valid
    @Override
    public ProductCategory patchOne(Long id, ProductCategory category) {
        return productCategoryRepo.save(category);
    }

    @Override
    public void deleteOne(Long id) {
        var category = findById(id);
        productCategoryRepo.delete(category);
    }

    private ProductCategory assignParentProductCategory(ProductCategoryCreateRequest req) {
        var productCategory = ProductCategoryCreateRequest.dtoToModel(req);
        if (req.parentProductCategoryId() != null) {
            var parentProductCategory = findById(req.parentProductCategoryId());
            if (isCategoryParent(parentProductCategory)) {
                productCategory.addProduct(parentProductCategory.getProducts());
                parentProductCategory.getProducts().clear();
            }
            parentProductCategory.addChild(productCategory);
        } else {
            productCategory.setParent(null);
        }
        return productCategory;
    }

    private boolean isCategoryParent(ProductCategory category) {
        return category.getChildren().isEmpty();
    }

    ProductCategory createRandomCategory() {
        ProductCategory category = new ProductCategory();
        String name = UUID.randomUUID().toString();
        category.setName(name);
        category.setDescription("name");
        return category;
    }
}
