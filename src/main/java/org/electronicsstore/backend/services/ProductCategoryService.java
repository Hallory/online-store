package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.ProductCategoryCreateRequest;
import org.electronicsstore.backend.dtos.ProductCategoryUpdateRequest;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;
    private final ProductService productService;
    private final ProductCharService productCharService;

    public ProductCategory findByCategoryName(String name) {
        return productCategoryRepo.findByName(name).orElseThrow(() -> new CustomEntityNotFoundException("Category not found, name = " + name));
    }

    public List<ProductCategory> findAll() {
        return productCategoryRepo.findAll();
    }

    public ProductCategory findById(Long id) {
        return productCategoryRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Category not found, name = " + id));
    }

    public List<ProductCategory> findAllById(List<Long> ids) {
        return productCategoryRepo.findAllById(ids);
    }

    public ProductCategory saveOneInit(ProductCategoryCreateRequest req) {
        if (productCategoryRepo.existsByName(req.name())) {
            throw new CustomEntityNotFoundException("Product category is already present, name = " + req.name());
        }
        var productCategory = assignParentProductCategory(req);
        return productCategoryRepo.save(productCategory);
    }

    private ProductCategory assignParentProductCategory(ProductCategoryCreateRequest req) {
        var productCategory = ProductCategoryCreateRequest.dtoToModel(req);
        if (req.parentProductCategoryId() != null) {
            var parentProductCategory = findById(req.parentProductCategoryId());
            if (isCategoryHasProducts(parentProductCategory)) {
                productCategory.addProduct(parentProductCategory.getProducts());
                parentProductCategory.getProducts().clear();
            }
            parentProductCategory.addChild(productCategory);
        } else {
            productCategory.setParent(null);
        }
        return productCategory;
    }

    private boolean isCategoryHasProducts(ProductCategory category) {
        return category.getChildren().isEmpty();
    }

    public void updateOne(Long categoryId, ProductCategoryUpdateRequest dto) {
        ProductCategory productCategory = findById(categoryId);
        productCategory.setName(dto.name());
        productCategory.setDescription(dto.description());
        productCategory.removeProduct();
        productCategory.addProduct(productService.findAllById(dto.productIds()));
        productCategory.removeProductChar();
        productCategory.addProductChar(productCharService.findAllById(dto.productCharIds()));
        productCategory.removeChild();
        productCategory.addChild(findAllById(dto.childProductCategoryIds()));
        productCategory.removeParent();
        productCategory.addParent(findById(dto.parentProductCategoryId()));
        productCategoryRepo.save(productCategory);
    }

    public void patchOne(Long categoryId, ProductCategoryUpdateRequest dto) {
        ProductCategory productCategory = findById(categoryId);
        for (String field : dto.fields()) {
            switch (field) {
                case "name": productCategory.setName(dto.name()); break;
                case "description": productCategory.setDescription(dto.description()); break;
                case "productIds": {
                    productCategory.removeProduct();
                    productCategory.addProduct(productService.findAllById(dto.productIds()));
                    break;
                }
                case "productCharIds": {
                    productCategory.removeProductChar();
                    productCategory.addProductChar(productCharService.findAllById(dto.productCharIds()));
                    break;
                }
                case "parentProductCategoryId": {
                    productCategory.removeParent();
                    productCategory.addParent(findById(dto.parentProductCategoryId()));
                    break;
                }
                case "childProductCategoryIds": {
                    productCategory.removeChild();
                    productCategory.addChild(findAllById(dto.childProductCategoryIds()));
                    break;
                }
                default: log.warn("Such field is not expected {}", field);
            }
        }
        productCategoryRepo.save(productCategory);
    }

    public void deleteOne(long categoryId) {
        var category = findById(categoryId);
        productCategoryRepo.delete(category);
    }

    ProductCategory createRandomCategory() {
        ProductCategory category = new ProductCategory();
        String name = UUID.randomUUID().toString();
        category.setName(name);
        category.setDescription("name");
        return category;
    }
}
