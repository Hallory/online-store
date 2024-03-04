package org.electronicsstore.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.electronicsstore.backend.exceptions.HttpPatchException;

public class AbstractController {
    protected ObjectMapper objectMapper;

    protected <T> T mergePatch(T entity, Class<T> clz, JsonNode mergePatchDto) {
        JsonNode original = objectMapper.valueToTree(entity);
        try {
            JsonMergePatch patch = JsonMergePatch.fromJson(mergePatchDto);
            JsonNode patched = patch.apply(original);
            return objectMapper.convertValue(patched, clz);
        } catch (JsonPatchException e) {
            throw new HttpPatchException(e);
        }
    }
}
