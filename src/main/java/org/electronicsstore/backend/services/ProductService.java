package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.ProductDto;
import org.electronicsstore.backend.exceptions.ProductAlreadyExistsException;
import org.electronicsstore.backend.exceptions.ProductNotFoundException;
import org.electronicsstore.backend.model.Category;
import org.electronicsstore.backend.model.Product;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryService categoryService;

    public List<ProductDto> findAll() {
        return productRepo.findAll().stream().map(ProductDto::productToProductDto).toList();
    }

    public ProductDto findById(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        return ProductDto.productToProductDto(product);
    }

    public ProductDto saveOne(ProductDto productDto) {
        if (productRepo.existsByTitle(productDto.title())) {
            throw new ProductAlreadyExistsException("Product " + productDto.title() + " is present");
        }

        Category category = categoryService.findByCategoryName(productDto.category());
        Product product = new Product();
        product.setTitle(productDto.title());
        product.setDescription(productDto.description());
        product.setPrice(BigDecimal.valueOf(productDto.price()));
        product.setDiscountPercentage(productDto.discountPercentage());
        product.setRating(productDto.rating());
        product.setStock(productDto.stock());
        product.setBrand(productDto.brand());
        product.setThumbnail(productDto.thumbnail());
        product.setImages(productDto.images());
        product.setCategory(category);

        return ProductDto.productToProductDto(productRepo.save(product));
    }

    public void updateOne(String productId, ProductDto productDto) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));;

        Category category = categoryService.findByCategoryName(productDto.category());
        LocalDateTime timeNow = LocalDateTime.now();

        product.setTitle(productDto.title());
        product.setDescription(productDto.description());
        product.setPrice(BigDecimal.valueOf(productDto.price()));
        product.setDiscountPercentage(productDto.discountPercentage());
        product.setRating(productDto.rating());
        product.setStock(productDto.stock());
        product.setBrand(productDto.brand());
        product.setThumbnail(productDto.thumbnail());
        product.setImages(productDto.images());
        product.setCategory(category);
        product.setCreatedAt(timeNow);
        product.setUpdatedAt(timeNow);

        productRepo.save(product);
    }

    public void deleteOne(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));;
        productRepo.delete(product);
    }
}
