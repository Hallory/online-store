package org.electronicsstore.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductCategoryRepo categoryRepo;

    @BeforeAll
    void initBeforeAll() {
        var category1 = categoryService.createRandomCategory();
        categoryRepo.save(category1);
    }

    @Test
    public void createOneTest() {
        var category = categoryRepo.findById(1L).get();
        String testText = UUID.randomUUID().toString();
        var product = new Product();
                product.setName(testText);
                product.setArticle(testText);
                product.setSKU(testText);
                product.setQtyInStock(5);
                product.setPrice(1.);
                product.setProductCategory(category);
        productService.createOne(product);
        assertTrue(productRepo.findAll().stream().anyMatch(p -> p.getName().equals(testText)));
    }
}
