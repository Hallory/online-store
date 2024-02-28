package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.ProductCharCreateRequest;
import org.electronicsstore.backend.dtos.ProductCharDto;
import org.electronicsstore.backend.dtos.ProductCharPatchRequest;
import org.electronicsstore.backend.dtos.ProductCharUpdateRequest;
import org.electronicsstore.backend.services.ProductCharService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/{categoryId}/chars")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class ProductCharController {
    private final ProductCharService productCharService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCharDto> chars() {
        return productCharService.findAllDto();
    }

    @GetMapping(value = {"{charId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductCharDto charById(@PathVariable(name = "charId", required = true) Long charId) {
        return productCharService.findByIdDto(charId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCharDto createChar(@RequestBody ProductCharCreateRequest dto) {
        return productCharService.createOneDto(dto);
    }

    @PutMapping({"{charId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCharById(
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody ProductCharUpdateRequest dto) {
        productCharService.updateOneDto(charId, dto);
    }

    @PatchMapping({"{charId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCharById(
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody ProductCharPatchRequest dto) {
        productCharService.patchOneDto(charId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"{charId}"})
    public void deleteCharById(@PathVariable(name = "charId", required = true) Long charId) {
        productCharService.deleteOne(charId);
    }
}
