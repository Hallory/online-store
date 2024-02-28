package org.electronicsstore.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public record ProductCharValuePatchRequest(
        Set<String> fields,
        String data,
        @JsonIgnore Long productCharId, // not required
        @JsonIgnore Set<String> productIds // no sense
) {
}
