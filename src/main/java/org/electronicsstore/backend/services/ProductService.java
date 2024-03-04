package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class ProductService implements BaseService<Product, String> {
    private final ProductRepo productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return productRepo.findAllBy(clz);
    }

    @Override
    public List<Product> findAllById(List<String> ids) {
        return productRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<String> ids, Class<P> clz) {
        return productRepo.findAllByIdIn(ids, clz);
    }

    @Override
    public Product findById(String productId) {
        return productRepo.findById(productId).orElseThrow(() -> new CustomEntityNotFoundException("Product not found, id = " + productId));
    }

    @Override
    public <P> P findProjById(String id, Class<P> clz) {
        return productRepo.findProjById(id, clz).orElseThrow(() -> new CustomEntityNotFoundException("Product not found, id = " + id));
    }

    @Override
    public Product createOne(Product entity) {
        if (productRepo.existsByArticle(entity.getArticle())) {
            throw new CustomEntityExistsException("Product is already present, article = " + entity.getArticle());
        }
        return productRepo.save(entity);
    }

    @Override
    public Product updateOne(String id, Product entity) {
        return productRepo.save(entity);
    }

    @Override
    public Product patchOne(String id, Product entity) {
        return productRepo.save(entity);
    }

    @Override
    public void deleteOne(String productId) {
        var product = findById(productId);
        productRepo.delete(product);
    }

    Product createRandomProduct() {
        String randomText = UUID.randomUUID().toString();
        var product = new Product();
        product.setArticle(randomText);
        product.setName(randomText);
        product.setSKU(randomText);
        product.setQtyInStock(1);
        product.setPrice(1.);
        return product;
    }
}
