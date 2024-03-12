package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.customer.ShoppingCartItemDto;
import org.electronicsstore.backend.dtos.customer.ShoppingCartProj;
import org.electronicsstore.backend.dtos.customer.ShoppingCartPutDto;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ShoppingCartController extends AbstractController {
    private final ShoppingCartService shoppingCartService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"carts"})
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingCartProj> shoppingCarts() {
        return shoppingCartService.findAllBy(ShoppingCartProj.class);
    }

    @GetMapping(value = {"carts/{cartId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShoppingCartProj> getCartByCartId(
            @PathVariable(name = "cartId", required = true) String cartId
    ) {
        var shoppingCart = shoppingCartService.findProjById(cartId, ShoppingCartProj.class);
        return ResponseEntity.ok(shoppingCart);
    }

    @PutMapping(
            value = {"carts/{cartId}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> putCart(
            @PathVariable(name = "cartId", required = true) String cartId,
            @RequestBody ShoppingCartPutDto dto) {
        var cart = shoppingCartService.updateOne(cartId, modelMapper.map(dto, ShoppingCart.class));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = {"carts/{cartId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCart(
            @PathVariable(name = "cartId", required = true) String cartId
    ) {
        shoppingCartService.deleteOne(cartId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = {"customers/{customerId}/cart"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShoppingCartProj> getCartByCustomer(
            @PathVariable(name = "customerId", required = true) String customerId
    ) {
        var shoppingCart = shoppingCartService.findProjByCustomerId(customerId, ShoppingCartProj.class);
        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping(value = {"customers/{customerId}/cart"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> addCartByCustomer(
            HttpServletRequest req,
            @PathVariable(name = "customerId", required = true) String customerId
    ) {
        var shoppingCart = shoppingCartService.createOne(customerId, new ShoppingCart());
        return ResponseEntity.created(buildURI(req, shoppingCart.getId())).build();
    }
}
