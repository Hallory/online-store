package org.electronicsstore.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.ProductCategoryCreateRequest;
import org.electronicsstore.backend.dtos.ProductCategoryPatchRequest;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
public class ProductCategoryServiceTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @BeforeAll
    void initBeforeAll() {
        var category1 = productCategoryService.createRandomCategory();
        var category2 = productCategoryService.createRandomCategory();
        var category3 = productCategoryService.createRandomCategory();
        var category4 = productCategoryService.createRandomCategory();
        var category5 = productCategoryService.createRandomCategory();
        category1.addChild(List.of(category2, category3));
        category2.addChild(List.of(category4, category5));
        productCategoryRepo.save(category1);
        log.info("{}", List.of(category1, category2, category3, category4, category5));
    }
    @Test
    public void findAllTest() {
        assertTrue(!productCategoryService.findAll().isEmpty());
    }

    @Test
    public void createCategoryTest() {
        ProductCategoryCreateRequest req = new ProductCategoryCreateRequest(
                "test-create",
                "desc1",
                null
        );
        productCategoryService.createOne(req);
        assertEquals("test-create", productCategoryRepo.findByName("test-create").get().getName());
    }

    @Test
    public void patchNameCategoryTest() {
        ProductCategoryPatchRequest req = new ProductCategoryPatchRequest(
                List.of("name"),
                "new name",
                "desc",
                null,
                null,
                null,
                null
        );

        productCategoryService.patchOne(1L, req);
        assertEquals("new name", productCategoryRepo.findById(1L).get().getName());
    }

    @Test
    public void deleteCategoryTest() {
        var categories = productCategoryRepo.findAll();
        productCategoryService.deleteOne(2L);
        assertTrue(productCategoryRepo.findById(2L).isEmpty());
        categories = productCategoryRepo.findAll();
        log.info("deleteCategoryTest -> {}", categories);
    }
}
