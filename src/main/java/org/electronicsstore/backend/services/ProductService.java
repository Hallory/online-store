package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.model.Product;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepo productRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
