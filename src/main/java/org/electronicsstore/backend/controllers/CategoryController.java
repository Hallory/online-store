package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.CategoryDto;
import org.electronicsstore.backend.dtos.product.CategoryProj;
import org.electronicsstore.backend.dtos.product.CategoryTraversedDownProj;
import org.electronicsstore.backend.dtos.product.CategoryTraversedUpProj;
import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.services.CategoryService;
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
public class CategoryController extends AbstractController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryProj> categories() {
        return categoryService.findAllBy(CategoryProj.class);
    }

    @GetMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryTraversedDownProj> categoryById(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return ResponseEntity.ok(categoryService.findProjById(categoryId, CategoryTraversedDownProj.class));
    }

    @GetMapping({"{categoryId}/tree"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> treeById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestParam(value = "direction", required = true) String direction
    ) {
        var tree = switch (direction) {
            case "UP" -> categoryService.findProjById(categoryId, CategoryTraversedUpProj.class);
            case "DOWN" -> categoryService.findProjById(categoryId, CategoryTraversedDownProj.class);
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
        var category = categoryService.createOne(modelMapper.map(dto, Category.class));
        return ResponseEntity.created(buildURI(req, category.getId().toString())).build();
    }

    @PutMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody CategoryDto dto) {
        var category = categoryService.updateOne(categoryId, modelMapper.map(dto, Category.class));
        return ResponseEntity.ok(modelMapper.map(category, CategoryDto.class));
    }

    @PatchMapping(value = {"{categoryId}"}, consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CategoryProj> patchCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody JsonNode dto
    ) {
        var fetched = categoryService.findById(categoryId);
        fetched = categoryService.patchOne(categoryId, mergePatch(fetched, Category.class, dto));
        return ResponseEntity.ok(categoryService.findProjById(fetched.getId(), CategoryProj.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{categoryId}"})
    public void deleteCategory(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        categoryService.deleteOne(categoryId);
    }
}
