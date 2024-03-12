package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.customer.ShoppingCartItemDto;
import org.electronicsstore.backend.dtos.customer.ShoppingCartItemProj;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.services.ShoppingCartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
public class ShoppingCartItemController extends AbstractController {
    private final ShoppingCartItemService shoppingCartItemService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"customers/{customerId}/cart/items"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ShoppingCartItemProj>> getCartByCustomer(
            @PathVariable(name = "customerId", required = true) String customerId
    ) {
        var shoppingCartItems = shoppingCartItemService.findAllProjByCustomerId(customerId, ShoppingCartItemProj.class);
        return ResponseEntity.ok(shoppingCartItems);
    }

    @PostMapping(value = {"customers/{customerId}/cart/items"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> addCartItem(
            HttpServletRequest req,
            @PathVariable(name = "customerId", required = true) String customerId,
            @RequestBody List<ShoppingCartItemDto> dtos
    ) {
        var shoppingCartItems = dtos.stream().map(sci -> modelMapper.map(sci, ShoppingCartItem.class)).toList();
        shoppingCartItemService.createAll(shoppingCartItems);
        return ResponseEntity.created(buildURI(req)).build();
    }

    @DeleteMapping(value = {"customers/{customerId}/cart/items/{cartItemId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCartItem(
            @PathVariable(name = "customerId", required = true) String customerId,
            @PathVariable(name = "cartItemId", required = true) String cartItemId
    ) {
        shoppingCartItemService.deleteOne(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
