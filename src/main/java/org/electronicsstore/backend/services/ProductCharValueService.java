package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.model.product.ProductCharValue;
import org.electronicsstore.backend.repos.ProductCharValueRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ProductCharValueService {
    private final ProductCharValueRepo productCharValueRepo;

    public List<ProductCharValue> findAllById(Collection<Long> ids) {
        return productCharValueRepo.findAllById(ids);
    }
}
