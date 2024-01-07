package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.ProductDto;
import org.electronicsstore.backend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }
)
public class ProductController {
    private final ProductService productService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> products() {
        return productService.findAll();
    }

    @GetMapping(value = {"{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto productById(@PathVariable(name = "productId", required = true) String productId) {
        return productService.findById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.saveOne(productDto);
    }

    // 200 ok / 204 no_content
    // !201 created
    // !409 conflict
    // 400 bad request // with explanation
    @PutMapping({"{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductDto productDto) {
        productService.updateOne(productId, productDto);
    }

    // 204 no_content
    // 202 accepted not completed
    // 200 with response required
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{productId}"})
    public void deleteById(@PathVariable(name = "productId", required = true) String productId) {
        productService.deleteOne(productId);
    }
}
