package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.ProductCreateRequest;
import org.electronicsstore.backend.dtos.ProductDto;
import org.electronicsstore.backend.dtos.ProductPatchRequest;
import org.electronicsstore.backend.dtos.ProductUpdateRequest;
import org.electronicsstore.backend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> products() {
        return productService.findAllDto();
    }

    @GetMapping(value = {"{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto productById(@PathVariable(name = "productId", required = true) String productId) {
        return productService.findByIdDto(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductCreateRequest dto) {
        return productService.createOneDto(dto);
    }

    @PutMapping({"{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductUpdateRequest productDto) {
        productService.updateOneDto(productId, productDto);
    }

    @PatchMapping({"{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchById(
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductPatchRequest productDto) {
        productService.patchOneDto(productId, productDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{productId}"})
    public void deleteById(@PathVariable(name = "productId", required = true) String productId) {
        productService.deleteOne(productId);
    }
}
