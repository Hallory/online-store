package org.electronicsstore.backend.dtos;

public record UserDto(
   String username,
   String password,
   String email,
   String roleName
) {}
