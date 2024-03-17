package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.*;
import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class CategoryController extends AbstractController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"categories", "categories/"})
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryProj> categories() {
        return categoryService.findAllBy(CategoryProj.class);
    }

    @GetMapping(value = {"categories/tree", "categories/tree/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryTraversedDownProj> categoryTree() {
        return ResponseEntity.ok(categoryService.findByParentIsNull(CategoryTraversedDownProj.class));
    }

    @GetMapping(value = {"categories/{categoryId}", "categories/{categoryId}/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryTraversedDownProj> categoryById(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return ResponseEntity.ok(categoryService.findProjById(categoryId, CategoryTraversedDownProj.class));
    }

    @GetMapping(value = {"categories/{categoryId}/tree-down", "categories/{categoryId}/tree-down/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> treeDownById(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        var tree = categoryService.findProjById(categoryId, CategoryTraversedDownProj.class);
        return ResponseEntity.ok(tree);
    }

    @GetMapping(value = {"categories/{categoryId}/tree-up", "categories/{categoryId}/tree-up/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> treeUpById(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        var tree = categoryService.findProjById(categoryId, CategoryTraversedUpProj.class);
        return ResponseEntity.ok(tree);
    }

    @PostMapping(
            value = {"categories", "categories/"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createCategory(
            HttpServletRequest req,
            @RequestBody CategoryPostDto dto
    ) {
        var category = categoryService.createOne(modelMapper.map(dto, Category.class));
        return ResponseEntity.created(buildURI(req, category.getId().toString())).build();
    }

    @PostMapping(value = {"categories/create-root", "categories/create-root/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createRootCategory(
            HttpServletRequest req
    ) {
        var category = categoryService.createRootCategory();
        return ResponseEntity.created(buildURI(req, category.getId().toString())).build();
    }

    @PutMapping(
            value = {"categories/{categoryId}", "categories/{categoryId}/"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody CategoryPutDto dto) {
        var category = categoryService.updateOne(categoryId, modelMapper.map(dto, Category.class));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"categories/{categoryId}", "categories/{categoryId}/"},
            consumes = "application/merge-patch+json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CategoryProj> patchCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody CategoryPatchDto dto
    ) {
        var fetched = categoryService.findById(categoryId);
        fetched = categoryService.patchOne(categoryId, mergePatch(fetched, Category.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"categories/{categoryId}", "categories/{categoryId}/"})
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        categoryService.deleteOne(categoryId);
        return ResponseEntity.noContent().build();
    }
}
