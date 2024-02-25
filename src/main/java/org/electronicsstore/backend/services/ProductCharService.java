package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.repos.ProductCharRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductCharService {
    private final ProductCharRepo productCharRepo;

    public ProductChar findById(Long id) {
        return productCharRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("ProductChar not found by id = " + id));
    }

    public List<ProductChar> findAllById(Collection<Long> ids) {
        return productCharRepo.findAllById(ids);
    }
}
