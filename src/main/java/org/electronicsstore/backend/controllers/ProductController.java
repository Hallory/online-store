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

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductController extends AbstractController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductProj> products() {
        return productService.findAllBy(ProductProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/products/{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductProj> productById(@PathVariable(name = "productId", required = true) String productId) {
        return ResponseEntity.ok(productService.findProjById(productId, ProductProj.class));
    }

    @PostMapping(value = "categories/{categoryId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createProduct(
            HttpServletRequest req,
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody ProductDto dto
    ) {
        var product = productService.createOne(categoryId, modelMapper.map(dto, Product.class));
        return ResponseEntity.created(buildURI(req, product.getId())).build();
    }

    @PutMapping(value = {"categories/{categoryId}/products/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProductDto> updateById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductDto dto) {
        var product = modelMapper.map(dto, Product.class);
        productService.updateOne(productId, product);
        return ResponseEntity.ok(modelMapper.map(product, ProductDto.class));
    }

    @PatchMapping(value = {"categories/{categoryId}/products/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProductProj> patchById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody JsonNode dto
    ) {
        var fetched = productService.findById(productId);
        fetched = productService.patchOne(productId, mergePatch(fetched, Product.class, dto));
        return ResponseEntity.ok(productService.findProjById(fetched.getId(), ProductProj.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"categories/{categoryId}/products/{productId}"})
    public void deleteById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId
    ) {
        productService.deleteOne(productId);
    }
}
