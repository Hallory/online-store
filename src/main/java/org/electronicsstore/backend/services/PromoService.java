package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.Promo;
import org.electronicsstore.backend.repos.PromoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PromoService {
    private final PromoRepo promoRepo;

    public Promo findById(Long id) {
        return promoRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Promo not found, id = " + id));
    }
}
