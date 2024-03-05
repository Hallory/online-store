package org.electronicsstore.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.model.product.Characteristic;
import org.electronicsstore.backend.repos.CategoryRepo;
import org.electronicsstore.backend.repos.CharacteristicRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
public class CharacteristicServiceTest {
    @Autowired
    private CharacteristicService characteristicService;
    @Autowired
    private CharacteristicRepo characteristicRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;

    @BeforeAll
    void initBeforeAll() {
        var category1 = categoryService.createRandomCategory();
        categoryRepo.save(category1);
        var char1 = characteristicService.createRandomProductChar(category1);
        characteristicRepo.save(char1);
    }
    @Test
    public void findAllTest() {
        assertFalse(characteristicRepo.findAll().isEmpty());
    }

    @Test
    public void createCharacteristicTest() {
        var category = categoryRepo.findById(1L).get();
        String randomText = UUID.randomUUID().toString();
        var characteristic = new Characteristic();
        characteristic.setName(randomText);
        characteristic.setDataType(randomText);
        characteristic.setCategory(category);
        characteristicRepo.save(characteristic);
        var createdCharacteristic = characteristicRepo.findById(2L);
        assertEquals(1L, createdCharacteristic.get().getCategory().getId());
    }
}
