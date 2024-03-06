package org.electronicsstore.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.CategoryTraversedDownProj;
import org.electronicsstore.backend.dtos.product.CategoryTraversedUpProj;
import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.repos.CategoryRepo;
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
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ObjectMapper om;

    @BeforeAll
    void initBeforeAll() {
        var root = new Category();
        root.setId(1L);
        var category1 = categoryService.createRandomCategory();
        var category2 = categoryService.createRandomCategory();
        var category3 = categoryService.createRandomCategory();
        var category4 = categoryService.createRandomCategory();
        var category5 = categoryService.createRandomCategory();
        category1.addChild(List.of(category2, category3));
        category2.addChild(List.of(category4, category5));
        category1.setParent(root);
        categoryRepo.save(category1);
        log.info("{}", List.of(category1, category2, category3, category4, category5));
    }
    @Test
    public void findAllTest() {
        assertTrue(!categoryService.findAll().isEmpty());
    }

    @Test
    public void createCategoryTest() {
        var cats = categoryService.findAll();
        var root = new Category();
        root.setId(1L);
        Category dto = new Category();
        dto.setName("test-create");
        dto.setDescription("desc");
        dto.setParent(root);
        categoryService.createOne(dto);
        assertEquals("test-create", categoryRepo.findByName("test-create").get().getName());
    }

    @Test
    public void patchNameCategoryTest() {
        Category dto = new Category();
        dto.setId(1L);
        dto.setName("new name");
        dto.setDescription("desc");
        categoryService.patchOne(1L, dto);
        assertEquals("new name", categoryRepo.findById(1L).get().getName());
    }

    @Test
    public void deleteCategoryTest() {
        var categories = categoryRepo.findAll();
        categoryService.deleteOne(2L);
        assertTrue(categoryRepo.findById(2L).isEmpty());
        categories = categoryRepo.findAll();
        log.info("deleteCategoryTest -> {}", categories);
    }

    @Test
    public void traverseDownCategoryTest() throws JsonProcessingException {
        var tree = categoryService.findProjById(1L, CategoryTraversedDownProj.class);
        assertNotNull(tree);
        log.info("{}", om.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
    }

    @Test
    public void traverseUpCategoryTest() throws JsonProcessingException {
        var tree = categoryService.findProjById(5L, CategoryTraversedUpProj.class);
        assertNotNull(tree);
        log.info("{}", om.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
    }
}
