package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.ProductCategoryDto;
import org.electronicsstore.backend.exceptions.ProductCategoryAlreadyExistsException;
import org.electronicsstore.backend.exceptions.ProductCategoryNotFoundException;
import org.electronicsstore.backend.model.ProductCategory;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;

    public ProductCategory findByCategoryName(String name) {
        return productCategoryRepo.findByName(name).orElseThrow(() -> new ProductCategoryNotFoundException(name));
    }

    public List<ProductCategoryDto> findAll() {
        return productCategoryRepo.findAll().stream().map(ProductCategoryDto::categoryToCategoryDto).toList();
    }

    public ProductCategoryDto findById(String categoryId) {
        ProductCategory productCategory = productCategoryRepo.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(categoryId));
        return ProductCategoryDto.categoryToCategoryDto(productCategory);
    }

    public ProductCategoryDto saveOne(ProductCategoryDto productCategoryDto) {
        if (productCategoryRepo.existsByName(productCategoryDto.name())) {
            throw new ProductCategoryAlreadyExistsException("Product category " + productCategoryDto.name() + " is present");
        }

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(productCategoryDto.name());

        throw new NotImplementedException();

//        return ProductCategoryDto.categoryToCategoryDto(productCategoryRepo.save(productCategory));
    }

    public void updateOne(String categoryId, ProductCategoryDto categoryDto) {
        ProductCategory productCategory = productCategoryRepo.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(categoryId));;
        productCategory.setName(categoryDto.name());

        throw new NotImplementedException();
//        productCategoryRepo.save(productCategory);
    }

    public void deleteOne(String categoryId) {
        ProductCategory productCategory = productCategoryRepo.findById(categoryId).orElseThrow(() -> new ProductCategoryNotFoundException(categoryId));;

        throw new NotImplementedException();
//        productCategoryRepo.delete(productCategory);
    }
}
