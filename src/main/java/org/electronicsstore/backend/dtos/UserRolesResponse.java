package org.electronicsstore.backend.dtos;

import java.util.List;

public record UserRolesResponse(
        List<String> userRoles
) {
}
