package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.product.ProductPatchDto;
import org.electronicsstore.backend.dtos.product.ProductPostDto;
import org.electronicsstore.backend.dtos.product.ProductProj;
import org.electronicsstore.backend.dtos.product.ProductPutDto;
import org.electronicsstore.backend.exceptions.EntityBadRequestException;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.services.CategoryService;
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
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductController extends AbstractController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @GetMapping(value = {"products", "products/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductProj> products() {
        return productService.findAllBy(ProductProj.class);
    }

    @GetMapping(value = {"products/{productId}", "products/{productId}/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductProj> productById(@PathVariable(name = "productId", required = true) String productId) {
        return ResponseEntity.ok(productService.findProjById(productId, ProductProj.class));
    }

    @PostMapping(value = {"products", "products/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createProduct(
            HttpServletRequest req,
            @RequestBody ProductPostDto dto
    ) {
        var product = productService.createOne(modelMapper.map(dto, Product.class));
        return ResponseEntity.created(buildURI(req, product.getId())).build();
    }

    @PutMapping(value = {"products/{productId}", "products/{productId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateProductById(
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductPutDto dto
    ) {
        var product = modelMapper.map(dto, Product.class);
        productService.updateOne(productId, product);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"products/{productId}", "products/{productId}/"},
            consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> patchProductById(
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductPatchDto dto
    ) {
        var fetched = productService.findById(productId);
        fetched = productService.patchOne(productId, mergePatch(fetched, Product.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"products/{productId}", "products/{productId}/"})
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "productId", required = true) String productId) {
        productService.deleteOne(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = {"categories/{categoryId}/products", "categories/{categoryId}/products/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductProj> productsByCategoryId(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        if (categoryService.existsByParentId(categoryId)) {
            throw new EntityBadRequestException("Category cannot have assigned products");
        }
        return productService.findAllProjByCategoryId(categoryId, ProductProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/products/{productId}", "categories/{categoryId}/products/{productId}/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductProj> productByCategoryIdAndId(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId
    ) {
        if (categoryService.existsByParentId(categoryId)) {
            throw new EntityBadRequestException("Category cannot have assigned products");
        }
        return ResponseEntity.ok(productService.findProjById(productId, ProductProj.class));
    }

    @PostMapping(value = {"categories/{categoryId}/products", "categories/{categoryId}/products/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createProductInCategory(
            HttpServletRequest req,
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody ProductPostDto dto
    ) {
        var product = productService.createOne(categoryId, modelMapper.map(dto, Product.class));
        return ResponseEntity.created(buildURI(req, product.getId())).build();
    }

    @PutMapping(value = {"categories/{categoryId}/products/{productId}", "categories/{categoryId}/products/{productId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateProductByIdInCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductPutDto dto) {
        var product = modelMapper.map(dto, Product.class);
        productService.updateOne(categoryId, productId, product);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"categories/{categoryId}/products/{productId}", "categories/{categoryId}/products/{productId}/"},
            consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> patchProductByIdInCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @RequestBody ProductPatchDto dto
    ) {
        var fetched = productService.findById(productId);
        fetched = productService.patchOne(categoryId, productId, mergePatch(fetched, Product.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"categories/{categoryId}/products/{productId}", "categories/{categoryId}/products/{productId}/"})
    public ResponseEntity<?> deleteProductByIdInCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId
    ) {
        productService.deleteOne(categoryId, productId);
        return ResponseEntity.noContent().build();
    }
}
