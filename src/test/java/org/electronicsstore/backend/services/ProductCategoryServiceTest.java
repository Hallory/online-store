package org.electronicsstore.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.category.CategoryTraversedDownProj;
import org.electronicsstore.backend.dtos.category.CategoryTraversedUpProj;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(args = {"--KEYCLOAK_BACKEND_CLIENT_SECRET=yWzrTIs2DWRX28mYhPPWgNrVewBYmU8a"})
public class ProductCategoryServiceTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductCategoryRepo productCategoryRepo;
    @Autowired
    private ObjectMapper om;

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
        ProductCategory dto = new ProductCategory();
        dto.setName("test-create");
        dto.setDescription("desc");
        productCategoryService.createOne(dto);
        assertEquals("test-create", productCategoryRepo.findByName("test-create").get().getName());
    }

    @Test
    public void patchNameCategoryTest() {
        ProductCategory dto = new ProductCategory();
        dto.setId(1L);
        dto.setName("new name");
        dto.setDescription("desc");
        productCategoryService.patchOne(1L, dto);
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

    @Test
    public void traverseDownCategoryTest() throws JsonProcessingException {
        var tree = productCategoryService.findProjById(1L, CategoryTraversedDownProj.class);
        assertNotNull(tree);
        log.info("{}", om.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
    }

    @Test
    public void traverseUpCategoryTest() throws JsonProcessingException {
        var tree = productCategoryService.findProjById(1L, CategoryTraversedUpProj.class);
        assertNotNull(tree);
        log.info("{}", om.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
    }
}
