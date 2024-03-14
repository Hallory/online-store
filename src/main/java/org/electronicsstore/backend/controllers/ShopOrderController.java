package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.customer.ReviewDto;
import org.electronicsstore.backend.dtos.customer.ReviewProj;
import org.electronicsstore.backend.dtos.order.ShopOrderDto;
import org.electronicsstore.backend.dtos.order.ShopOrderProcessDto;
import org.electronicsstore.backend.dtos.order.ShopOrderProj;
import org.electronicsstore.backend.model.customer.Review;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.model.order.ShopOrder;
import org.electronicsstore.backend.services.ReviewService;
import org.electronicsstore.backend.services.ShopOrderService;
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
@RequestMapping("/api/orders")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ShopOrderController extends AbstractController {
    private final ShopOrderService shopOrderService;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ShopOrderProj> orders() {
        return shopOrderService.findAllBy(ShopOrderProj.class);
    }

    @GetMapping(value = {"{orderId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShopOrderProj> orderById(
            @PathVariable(name = "orderId", required = true) String orderId
    ) {
        return ResponseEntity.ok(shopOrderService.findProjById(orderId, ShopOrderProj.class));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createOrder(
            HttpServletRequest req,
            @RequestBody ShopOrderDto dto
    ) {
        var order = shopOrderService.createOne(modelMapper.map(dto, ShopOrder.class));
        return ResponseEntity.created(buildURI(req, order.getId())).build();
    }

    @PostMapping(
            value = {"process"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> processOrder(
            HttpServletRequest req,
            @RequestBody ShopOrderProcessDto dto
    ) {
        var order = shopOrderService.processOrder(dto);
        return ResponseEntity.created(buildURI(req, order.getId())).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"{orderId}"})
    public ResponseEntity<?> deleteOrder(
            @PathVariable(name = "orderId", required = true) String orderId
    ) {
        shopOrderService.deleteOne(orderId);
        return ResponseEntity.noContent().build();
    }
}
