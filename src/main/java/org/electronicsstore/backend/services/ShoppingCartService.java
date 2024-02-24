package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import org.electronicsstore.backend.dtos.ShoppingCartDto;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.repos.ShoppingCartRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;

    public List<ShoppingCart> findAll() {
        return shoppingCartRepo.findAll();
    }
}
