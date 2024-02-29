package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.*;
import org.electronicsstore.backend.services.ProductCharService;
import org.electronicsstore.backend.services.ProductCharValueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/{categoryId}/products/{productId}/chars/{charId}/values")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductCharValueController {
    private final ProductCharValueService productCharValueService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCharValueDto> charValues(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        return productCharValueService.findAllDto();
    }

    @GetMapping(value = {"{charValueId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductCharValueDto charValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId
    ) {
        return productCharValueService.findByIdDto(charValueId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCharValueDto createCharValue(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody ProductCharValueCreateRequest dto
    ) {
        return productCharValueService.createOneDto(dto);
    }

    @PutMapping({"{charValueId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCharValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId,
            @RequestBody ProductCharValueUpdateRequest dto) {
        productCharValueService.updateOneDto(charValueId, dto);
    }

    @PatchMapping({"{charValueId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCharValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId,
            @RequestBody ProductCharValuePatchRequest dto
    ) {
        productCharValueService.patchOneDto(charValueId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{charValueId}"})
    public void deleteCharValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId
    ) {
        productCharValueService.deleteOne(charValueId);
    }
}
