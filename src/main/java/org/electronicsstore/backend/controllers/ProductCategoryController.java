package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.*;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductCategoryController extends AbstractController {
    private final ProductCategoryService productCategoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryProj> categories() {
        return productCategoryService.findAllBy(CategoryProj.class);
    }

    @GetMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryTraversedDownProj> categoryById(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return ResponseEntity.ok(productCategoryService.findProjById(categoryId, CategoryTraversedDownProj.class));
    }

    @GetMapping({"{categoryId}/tree"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> treeById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestParam(value = "direction", required = true) String direction
    ) {
        var tree = switch (direction) {
            case "UP" -> productCategoryService.findProjById(categoryId, CategoryTraversedUpProj.class);
            case "DOWN" -> productCategoryService.findProjById(categoryId, CategoryTraversedDownProj.class);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
        return ResponseEntity.ok(tree);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createCategory(
            HttpServletRequest req,
            @RequestBody CategoryDto dto
    ) {
        var category = productCategoryService.createOne(modelMapper.map(dto, ProductCategory.class));
        return ResponseEntity.created(buildCreatedUrl(req, category.getId().toString())).build();
    }

    @PutMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody CategoryDto dto) {
        var category = productCategoryService.updateOne(categoryId, modelMapper.map(dto, ProductCategory.class));
        return ResponseEntity.ok(modelMapper.map(category, CategoryDto.class));
    }

    @PatchMapping(value = {"{categoryId}"}, consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CategoryDto> patchCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody JsonNode mergePatchDto
    ) {
        var fetchedCategory = productCategoryService.findById(categoryId);
        fetchedCategory = productCategoryService.patchOne(categoryId, mergePatch(fetchedCategory, ProductCategory.class, mergePatchDto));
        return ResponseEntity.ok(modelMapper.map(fetchedCategory, CategoryDto.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{categoryId}"})
    public void deleteCategory(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        productCategoryService.deleteOne(categoryId);
    }
}
