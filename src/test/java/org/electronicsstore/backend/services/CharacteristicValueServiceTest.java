package org.electronicsstore.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.model.product.CharacteristicValue;
import org.electronicsstore.backend.repos.CategoryRepo;
import org.electronicsstore.backend.repos.CharacteristicRepo;
import org.electronicsstore.backend.repos.CharacteristicValueRepo;
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
public class CharacteristicValueServiceTest {
    @Autowired
    private CharacteristicValueService characteristicValueService;
    @Autowired
    private CharacteristicValueRepo characteristicValueRepo;
    @Autowired
    private CharacteristicService characteristicService;
    @Autowired
    private CharacteristicRepo characteristicRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @BeforeAll
    void initBeforeAll() {
        var category1 = categoryService.createRandomCategory();
        categoryRepo.save(category1);
        var productChar1 = characteristicService.createRandomProductChar(category1);
        characteristicRepo.save(productChar1);
        var productCharValue1 = characteristicValueService.createRandomProductCharValue(productChar1);
        characteristicValueRepo.save(productCharValue1);
        productRepo.save(productService.createRandomProduct());
    }
    @Test
    public void findAllTest() {
        assertFalse(characteristicValueRepo.findAll().isEmpty());
    }

    @Test
    public void createProductCharValueTest() {
        String randomText = UUID.randomUUID().toString();
        var category = categoryRepo.findById(1L).get();
        var productChar = characteristicRepo.findById(1L).get();
        var product = productRepo.findAll().remove(0);
        var productCharValue = new CharacteristicValue();
        productCharValue.setData(randomText);
        productCharValue.setCharacteristic(productChar);
        productCharValue.setProducts(new HashSet<>(List.of(product)));
        characteristicValueService.createOne(productCharValue);
        var createdProductCharValue = characteristicValueRepo.findById(2L);
        assertEquals(1L, createdProductCharValue.get().getCharacteristic().getId());
    }
}
