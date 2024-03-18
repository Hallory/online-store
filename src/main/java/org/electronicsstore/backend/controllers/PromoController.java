package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.*;
import org.electronicsstore.backend.model.product.Promo;
import org.electronicsstore.backend.services.PromoService;
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
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class PromoController extends AbstractController {
    private final PromoService promoService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"promos", "promos/"})
    @ResponseStatus(HttpStatus.OK)
    public List<PromoProj> promos() {
        return promoService.findAllBy(PromoProj.class);
    }

    @GetMapping(value = {"promos/{promoId}", "promos/{promoId}/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PromoProj> promoById(
            @PathVariable(name = "promoId", required = true) Long promoId
    ) {
        return ResponseEntity.ok(promoService.findProjById(promoId, PromoProj.class));
    }

    @PostMapping(
            value = {"promos", "promos/"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createPromo(
            HttpServletRequest req,
            @RequestBody PromoPostDto dto
    ) {
        var promo = promoService.createOne(modelMapper.map(dto, Promo.class));
        return ResponseEntity.created(buildURI(req, promo.getId().toString())).build();
    }

    @PutMapping(
            value = {"promos/{promoId}", "promos/{promoId}/"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> putPromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody PromoPutDto dto) {
        var promo = promoService.updateOne(promoId, modelMapper.map(dto, Promo.class));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"promos/{promoId}", "promos/{promoId}/"},
            consumes = "application/merge-patch+json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> patchPromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody PromoPatchDto dto
    ) {
        var fetched = promoService.findById(promoId);
        fetched = promoService.patchOne(promoId, mergePatch(fetched, Promo.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"promos/{promoId}", "promos/{promoId}/"})
    public ResponseEntity<?> deletePromo(
            @PathVariable(name = "promoId", required = true) Long promoId
    ) {
        promoService.deleteOne(promoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            value = {"promos/{promoId}/products", "promos/{promoId}/products/"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> addProducts(
            HttpServletRequest req,
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody List<ProductRefDto> dtos
    ) {
        var promo = promoService.addProducts(promoId, dtos.stream().map(ProductRefDto::getId).toList());
        return ResponseEntity.created(buildURI(req)).build();
    }

    @PutMapping(
            value = {"promos/{promoId}/products", "promos/{promoId}/products/"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> putPromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody List<ProductRefDto> dtos
    ) {
        var promo = promoService.addProducts(promoId, dtos.stream().map(ProductRefDto::getId).toList());
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"promos/{promoId}/products/{productId}", "promos/{promoId}/products/{productId}/"})
    public ResponseEntity<?> deletePromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @PathVariable(name = "productId", required = true) String productId
    ) {
        promoService.removeProduct(promoId, productId);
        return ResponseEntity.noContent().build();
    }
}
