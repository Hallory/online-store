package org.electronicsstore.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.ProductCharValueCreateRequest;
import org.electronicsstore.backend.model.product.ProductCharValue;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.electronicsstore.backend.repos.ProductCharRepo;
import org.electronicsstore.backend.repos.ProductCharValueRepo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
public class ProductCharValueServiceTest {
    @Autowired
    private ProductCharValueService productCharValueService;
    @Autowired
    private ProductCharValueRepo productCharValueRepo;
    @Autowired
    private ProductCharService productCharService;
    @Autowired
    private ProductCharRepo productCharRepo;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductCategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @BeforeAll
    void initBeforeAll() {
        var category1 = categoryService.createRandomCategory();
        categoryRepo.save(category1);
        var productChar1 = productCharService.createRandomProductChar(category1);
        productCharRepo.save(productChar1);
        var productCharValue1 = productCharValueService.createRandomProductCharValue(productChar1);
        productCharValueRepo.save(productCharValue1);
        productRepo.save(productService.createRandomProduct());
    }
    @Test
    public void findAllTest() {
        assertFalse(productCharValueRepo.findAll().isEmpty());
    }

    @Test
    public void createProductCharValueTest() {
        String randomText = UUID.randomUUID().toString();
        var category = categoryRepo.findById(1L).get();
        var productChar = productCharRepo.findById(1L).get();
        var product = productRepo.findAll().remove(0);
        var productCharValue = new ProductCharValueCreateRequest(
                randomText,
                productChar.getId(),
                new HashSet<>(List.of(product.getId())));
        productCharValueService.createOne(productCharValue);
        var createdProductCharValue = productCharValueRepo.findById(2L);
        assertEquals(1L, createdProductCharValue.get().getProductChar().getId());
    }
}
