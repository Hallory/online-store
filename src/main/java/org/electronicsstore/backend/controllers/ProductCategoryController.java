package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.ProductCategoryDto;
import org.electronicsstore.backend.services.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }
)
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCategoryDto> products() {
        return productCategoryService.findAll();
    }

    @GetMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.OK)
    public ProductCategoryDto productById(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        return productCategoryService.findById(categoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryDto addProduct(@RequestBody ProductCategoryDto categoryDto) {
        return productCategoryService.saveOne(categoryDto);
    }

    // 200 ok / 204 no_content
    // !201 created
    // !409 conflict
    // 400 bad request // with explanation
    @PutMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(
            @PathVariable(name = "productId", required = true) Long categoryId,
            @RequestBody ProductCategoryDto productCategoryDto) {
        productCategoryService.updateOne(categoryId, productCategoryDto);
    }

    // 204 no_content
    // 202 accepted not completed
    // 200 with response required
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{categoryId}"})
    public void deleteById(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        productCategoryService.deleteOne(categoryId);
    }
}
