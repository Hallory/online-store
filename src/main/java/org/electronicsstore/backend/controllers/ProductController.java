package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.product.ProductDto;
import org.electronicsstore.backend.dtos.product.ProductProj;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/{categoryId}/products")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductController extends AbstractController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductProj> products(@PathVariable(name = "categoryId", required = true) Long categoryId) {
        return productService.findAllBy(ProductProj.class);
    }

    @GetMapping(value = {"{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductProj> productById(@PathVariable(name = "productId", required = true) String productId) {
        return ResponseEntity.ok(productService.findProjById(productId, ProductProj.class));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(
            HttpServletRequest req,
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody ProductDto dto
    ) {
        var product = productService.createOne(modelMapper.map(dto, Product.class));
        return ResponseEntity.created(buildCreatedUrl(req, product.getId())).build();
    }

    @PutMapping({"{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProductDto> updateById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductDto dto) {
        var product = modelMapper.map(dto, Product.class);
        productService.updateOne(productId, product);
        return ResponseEntity.ok(modelMapper.map(product, ProductDto.class));
    }

    @PatchMapping({"{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProductDto> patchById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody JsonNode mergePatchDto
    ) {
        var fetchedProduct = productService.findById(productId);
        fetchedProduct = productService.patchOne(productId, mergePatch(fetchedProduct, Product.class, mergePatchDto));
        return ResponseEntity.ok(modelMapper.map(fetchedProduct, ProductDto.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{productId}"})
    public void deleteById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId
    ) {
        productService.deleteOne(productId);
    }
}
