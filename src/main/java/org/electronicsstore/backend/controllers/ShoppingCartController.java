package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.ShoppingCartDto;
import org.electronicsstore.backend.services.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ShoppingCartDto>> shoppingCarts() {
        return ResponseEntity.ok(shoppingCartService.findAll().stream().map(ShoppingCartDto::modelToDto).toList());
    }
}
