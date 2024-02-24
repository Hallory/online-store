package org.electronicsstore.backend.dtos;

import org.electronicsstore.backend.model.customer.ShoppingCart;

import java.time.LocalDateTime;

public record ShoppingCartDto(
        String id,
        Double total,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static ShoppingCartDto modelToDto(ShoppingCart shoppingCart) {
        return new ShoppingCartDto(
                shoppingCart.getId(),
                shoppingCart.getTotal(),
                shoppingCart.getCreatedAt(),
                shoppingCart.getModifiedAt()
        );
    }
}
