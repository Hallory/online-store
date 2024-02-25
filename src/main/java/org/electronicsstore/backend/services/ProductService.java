package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.ProductDto;
import org.electronicsstore.backend.exceptions.ProductAlreadyExistsException;
import org.electronicsstore.backend.exceptions.ProductNotFoundException;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepo productRepo;

    public List<ProductDto> findAll() {
        return productRepo.findAll().stream().map(ProductDto::productModelToDto).toList();
    }

    public ProductDto findById(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        return ProductDto.productModelToDto(product);
    }

    public List<Product> findAllById(Collection<String> ids) {
        return productRepo.findAllById(ids);
    }

    public ProductDto saveOne(ProductDto productDto) {
        if (productRepo.existsByName(productDto.name())) {
            throw new ProductAlreadyExistsException("Product " + productDto.name() + " is present");
        }

        throw new NotImplementedException();
    }

    public void updateOne(String productId, ProductDto productDto) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));;

        throw new NotImplementedException();
    }

    public void deleteOne(String productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        throw new NotImplementedException();
//        productRepo.delete(product);
    }
}
