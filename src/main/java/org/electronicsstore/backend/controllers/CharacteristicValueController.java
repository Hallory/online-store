package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.CharacteristicValueProj;
import org.electronicsstore.backend.dtos.product.CharacteristicValueDto;
import org.electronicsstore.backend.dtos.product.CharacteristicValueSingleProductProj;
import org.electronicsstore.backend.model.product.CharacteristicValue;
import org.electronicsstore.backend.services.CharacteristicValueService;
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
public class CharacteristicValueController extends AbstractController {
    private final CharacteristicValueService characteristicValueService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"chars/values"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicValueProj> charValues() {
        return characteristicValueService.findAllBy(CharacteristicValueProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/values"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicValueProj> charValues(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return characteristicValueService.findAllProjByCharacteristicCategoryId(categoryId, CharacteristicValueProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/chars/{charId}/values"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicValueProj> charValues(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        return characteristicValueService.findAllProjByCharacteristicId(charId, CharacteristicValueProj.class);
    }
    @GetMapping(value = {"categories/{categoryId}/products/{productId}/chars/values"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicValueSingleProductProj> charValuesByProduct(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        return characteristicValueService.findAllProjByProductsId(productId, CharacteristicValueSingleProductProj.class);
    }

    @GetMapping(
            value = {"categories/{categoryId}/products/{productId}/chars/{charId}/values/{charValueId}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CharacteristicValueProj> charValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId
    ) {
        return ResponseEntity.ok(characteristicValueService.findProjById(charValueId, CharacteristicValueProj.class));
    }

    @PostMapping(
            value = {"categories/{categoryId}/products/{productId}/chars/{charId}/values"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createCharValue(
            HttpServletRequest req,
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody CharacteristicValueDto dto
    ) {
        var characteristicValue = characteristicValueService.createOne(categoryId, productId, charId, modelMapper.map(dto, CharacteristicValue.class));
        return ResponseEntity.ok(buildURI(req, characteristicValue.getId().toString()));
    }

    @PutMapping(
            value = {"categories/{categoryId}/products/{productId}/chars/{charId}/values/{charValueId}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CharacteristicValueDto> updateCharValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId,
            @RequestBody CharacteristicValueDto dto) {
        var characteristicValue = characteristicValueService.updateOne(categoryId, productId, charId, modelMapper.map(dto, CharacteristicValue.class));
        return ResponseEntity.ok(modelMapper.map(characteristicValue, CharacteristicValueDto.class));
    }

    @PatchMapping({"categories/{categoryId}/products/{productId}/chars/{charId}/values/{charValueId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CharacteristicValueProj> patchCharValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId,
            @RequestBody JsonNode dto
    ) {
        var fetched = characteristicValueService.findById(charValueId);
        fetched = characteristicValueService.patchOne(charId, mergePatch(fetched, CharacteristicValue.class, dto));
        return ResponseEntity.ok(characteristicValueService.findProjById(fetched.getId(), CharacteristicValueProj.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"categories/{categoryId}/products/{productId}/chars/{charId}/values/{charValueId}"})
    public void deleteCharValueById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "productId", required = true) String productId,
            @PathVariable(name = "charId", required = true) Long charId,
            @PathVariable(name = "charValueId", required = true) Long charValueId
    ) {
        characteristicValueService.deleteOne(charValueId);
    }
}
