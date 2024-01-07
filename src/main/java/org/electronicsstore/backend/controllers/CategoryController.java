package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.CategoryDto;
import org.electronicsstore.backend.services.CategoryService;
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
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> products() {
        return categoryService.findAll();
    }

    @GetMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto productById(@PathVariable(name = "categoryId", required = true) String categoryId) {
        return categoryService.findById(categoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addProduct(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveOne(categoryDto);
    }

    // 200 ok / 204 no_content
    // !201 created
    // !409 conflict
    // 400 bad request // with explanation
    @PutMapping({"{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(
            @PathVariable(name = "productId", required = true) String categoryId,
            @RequestBody CategoryDto categoryDto) {
        categoryService.updateOne(categoryId, categoryDto);
    }

    // 204 no_content
    // 202 accepted not completed
    // 200 with response required
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{categoryId}"})
    public void deleteById(@PathVariable(name = "categoryId", required = true) String categoryId) {
        categoryService.deleteOne(categoryId);
    }
}
