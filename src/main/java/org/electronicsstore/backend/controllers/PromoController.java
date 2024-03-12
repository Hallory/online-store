package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.ProductRefDto;
import org.electronicsstore.backend.dtos.product.PromoDto;
import org.electronicsstore.backend.dtos.product.PromoProj;
import org.electronicsstore.backend.dtos.product.PromoPutDto;
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
@RequestMapping("/api/promos")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class PromoController extends AbstractController {
    private final PromoService promoService;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<PromoProj> promos() {
        return promoService.findAllBy(PromoProj.class);
    }

    @GetMapping(value = {"{promoId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PromoProj> promoById(
            @PathVariable(name = "promoId", required = true) Long promoId
    ) {
        return ResponseEntity.ok(promoService.findProjById(promoId, PromoProj.class));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createPromo(
            HttpServletRequest req,
            @RequestBody PromoDto dto
    ) {
        var promo = promoService.createOne(modelMapper.map(dto, Promo.class));
        return ResponseEntity.created(buildURI(req, promo.getId().toString())).build();
    }

    @PutMapping(
            value = {"{promoId}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> putPromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody PromoPutDto dto) {
        var promo = promoService.updateOne(promoId, modelMapper.map(dto, Promo.class));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"{promoId}"},
            consumes = "application/merge-patch+json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> patchPromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody JsonNode dto
    ) {
        var fetched = promoService.findById(promoId);
        fetched = promoService.patchOne(promoId, mergePatch(fetched, Promo.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"{promoId}"})
    public ResponseEntity<?> deletePromo(
            @PathVariable(name = "promoId", required = true) Long promoId
    ) {
        promoService.deleteOne(promoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            value = {"{promoId}/products"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
            value = {"{promoId}/products"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> putPromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @RequestBody List<ProductRefDto> dtos
    ) {
        var promo = promoService.addProducts(promoId, dtos.stream().map(ProductRefDto::getId).toList());
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"{promoId}/products/{productId}"})
    public ResponseEntity<?> deletePromo(
            @PathVariable(name = "promoId", required = true) Long promoId,
            @PathVariable(name = "productId", required = true) String productId
    ) {
        promoService.removeProduct(promoId, productId);
        return ResponseEntity.noContent().build();
    }
}
