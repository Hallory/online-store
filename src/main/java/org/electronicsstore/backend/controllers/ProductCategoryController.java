package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.ProductCategoryCreateRequest;
import org.electronicsstore.backend.dtos.ProductCategoryDto;
import org.electronicsstore.backend.dtos.ProductCategoryUpdateRequest;
import org.electronicsstore.backend.services.ProductCategoryService;
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
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductCategoryDto>> categories() {
        log.info("abc");
        return ResponseEntity.ok(productCategoryService.findAllDto());
    }

    @GetMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductCategoryDto> categoryById(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        return ResponseEntity.ok(productCategoryService.findByIdDto(categoryId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCategory(@RequestBody ProductCategoryCreateRequest dto, HttpServletRequest req) {
        var category = productCategoryService.createOneDto(dto);
        return ResponseEntity.created(
                URI.create(req.getRequestURI())
                        .resolve("/api/categories")
                        .resolve(category.id().toString()))
                .build();
    }

    @PutMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody ProductCategoryUpdateRequest dto) {
        productCategoryService.updateOneDto(categoryId, dto);
    }

    @PatchMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody ProductCategoryUpdateRequest dto) {
        productCategoryService.patchOneDto(categoryId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{categoryId}"})
    public void deleteCategory(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        productCategoryService.deleteOne(categoryId);
    }
}
