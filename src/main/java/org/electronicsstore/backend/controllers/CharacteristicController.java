package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.*;
import org.electronicsstore.backend.model.product.Characteristic;
import org.electronicsstore.backend.services.CharacteristicService;
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
public class CharacteristicController extends AbstractController {
    private final CharacteristicService characteristicService;
    private final ModelMapper modelMapper;

    @GetMapping(value = {"categories/chars", "categories/chars/"})
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicProj> chars() {
        return characteristicService.findAllBy(CharacteristicProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/chars", "categories/{categoryId}/chars/"})
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicProj> charsByCategory(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return characteristicService.findAllProjByCategoryId(categoryId, CharacteristicProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/chars/inherited", "categories/{categoryId}/chars/inherited/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CharacteristicNoRefProj>> charsByCategoryInherited(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return ResponseEntity.ok(characteristicService.findAllProjByCategoryIdInheritedCTE(categoryId));
    }

    @GetMapping(value = {"categories/{categoryId}/chars/{charId}", "categories/{categoryId}/chars/{charId}/"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CharacteristicNoRefProj> charById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        return ResponseEntity.ok(characteristicService.findProjById(categoryId, charId, CharacteristicNoRefProj.class));
    }

    @PostMapping(value = {"categories/{categoryId}/chars", "categories/{categoryId}/chars/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createChar(
            HttpServletRequest req,
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody CharacteristicPostDto dto
    ) {
        var characteristic = characteristicService.createOne(categoryId, modelMapper.map(dto, Characteristic.class));
        return ResponseEntity.created(buildURI(req, characteristic.getId().toString())).build();
    }

    @PutMapping(value = {"categories/{categoryId}/chars/{charId}", "categories/{categoryId}/chars/{charId}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateCharById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody CharacteristicPutDto dto
    ) {
        var characteristic = characteristicService.updateOne(categoryId, charId, modelMapper.map(dto, Characteristic.class));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            value = {"categories/{categoryId}/chars/{charId}", "categories/{categoryId}/chars/{charId}/"},
            consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> patchCharById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody CharacteristicPatchDto dto
    ) {
        var fetched = characteristicService.findById(charId);
        fetched = characteristicService.patchOne(categoryId, charId, mergePatch(fetched, Characteristic.class, dto));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"categories/{categoryId}/chars/{charId}", "categories/{categoryId}/chars/{charId}/"})
    public ResponseEntity<?> deleteCharById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        characteristicService.deleteOne(categoryId, charId);
        return ResponseEntity.noContent().build();
    }
}
