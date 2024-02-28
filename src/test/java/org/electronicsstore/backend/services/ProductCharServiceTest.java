package org.electronicsstore.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.electronicsstore.backend.repos.ProductCharRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
public class ProductCharServiceTest {
    @Autowired
    private ProductCharService productCharService;
    @Autowired
    private ProductCharRepo productCharRepo;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductCategoryRepo categoryRepo;

    @BeforeAll
    void initBeforeAll() {
        var category1 = categoryService.createRandomCategory();
        categoryRepo.save(category1);
        var productChar1 = productCharService.createRandomCategory(category1);
        productCharRepo.save(productChar1);
    }
    @Test
    public void findAllTest() {
        assertEquals(1, productCharRepo.findAll().size());
    }

    @Test
    public void createCategoryTest() {
        var category = categoryRepo.findById(1L).get();
        String randomText = UUID.randomUUID().toString();
        var productChar = new ProductChar();
        productChar.setName(randomText);
        productChar.setDataType(randomText);
        productChar.setProductCategory(category);
        productCharRepo.save(productChar);
        var createdProductChar = productCharRepo.findById(2L);
        assertEquals(1L, createdProductChar.get().getProductCategory().getId());
    }
}
