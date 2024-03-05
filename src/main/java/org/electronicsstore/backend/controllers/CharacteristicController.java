package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.CharacteristicProj;
import org.electronicsstore.backend.dtos.product.CharacteristicNoRefProj;
import org.electronicsstore.backend.dtos.product.CharacteristicDto;
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
@RequestMapping("/api")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class CharacteristicController extends AbstractController {
    private final CharacteristicService characteristicService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "chars")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicProj> chars() {
        return characteristicService.findAllBy(CharacteristicProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/chars"})
    @ResponseStatus(HttpStatus.OK)
    public List<CharacteristicProj> chars(
            @PathVariable(name = "categoryId", required = true) Long categoryId
    ) {
        return characteristicService.findAllProjByCategoryId(categoryId, CharacteristicProj.class);
    }

    @GetMapping(value = {"categories/{categoryId}/chars/{charId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CharacteristicNoRefProj> charById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        return ResponseEntity.ok(characteristicService.findProjById(charId, CharacteristicNoRefProj.class));
    }

    @PostMapping(value = {"categories/{categoryId}/chars"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> createChar(
            HttpServletRequest req,
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @RequestBody CharacteristicDto dto
    ) {
        var characteristic = characteristicService.createOne(categoryId, modelMapper.map(dto, Characteristic.class));
        return ResponseEntity.ok(buildURI(req, characteristic.getId().toString()));
    }

    @PutMapping(value = {"categories/{categoryId}/chars/{charId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CharacteristicDto> updateCharById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody CharacteristicDto dto
    ) {
        var characteristic = characteristicService.updateOne(charId, modelMapper.map(dto, Characteristic.class));
        return ResponseEntity.ok(modelMapper.map(characteristic, CharacteristicDto.class));
    }

    @PatchMapping(value = {"categories/{categoryId}/chars/{charId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CharacteristicProj> patchCharById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId,
            @RequestBody JsonNode dto
    ) {
        var fetched = characteristicService.findById(charId);
        fetched = characteristicService.patchOne(charId, mergePatch(fetched, Characteristic.class, dto));
        return ResponseEntity.ok(characteristicService.findProjById(fetched.getId(), CharacteristicProj.class));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"categories/{categoryId}/chars/{charId}"})
    public void deleteCharById(
            @PathVariable(name = "categoryId", required = true) Long categoryId,
            @PathVariable(name = "charId", required = true) Long charId
    ) {
        characteristicService.deleteOne(charId);
    }
}
