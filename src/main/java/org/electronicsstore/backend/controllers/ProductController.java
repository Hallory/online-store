package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.model.Product;
import org.electronicsstore.backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> products() {
        return ResponseEntity.ok(Map.of("products", productService.findAll()));
    }

    @PostMapping
    public ResponseEntity<?> addProduct() {
        Product product = new Product();
        product.setArticle("article");
        product.setSku("sku");
        product.setDescription("desc");
        productService.addProduct(product);
        return ResponseEntity.ok(Map.of("id", productService.addProduct(product).getId()));
    }
}
