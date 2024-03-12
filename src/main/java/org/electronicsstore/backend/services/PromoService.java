package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.Promo;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.ProductRepo;
import org.electronicsstore.backend.repos.PromoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PromoService implements BaseService<Promo, Long> {
    private final PromoRepo promoRepo;
    private final ProductRepo productRepo;

    @Override
    public List<Promo> findAll() {
        return promoRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return promoRepo.findAllBy(clz);
    }

    @Override
    public Promo findById(Long id) {
        return promoRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Promo not found, id = " + id));
    }

    @Override
    public <P> P findProjById(Long id, Class<P> clz) {
        return promoRepo.findProjById(id, clz).orElseThrow(() -> new CustomEntityNotFoundException("Promo not found, id = " + id));
    }

    @Override
    public List<Promo> findAllById(List<Long> ids) {
        return promoRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<Long> ids, Class<P> clz) {
        return promoRepo.findAllByIdIn(ids, clz);
    }

    @Override
    public Promo createOne(Promo entity) {
        return promoRepo.save(entity);
    }

    @Override
    public Promo updateOne(Long id, Promo entity) {
        return promoRepo.save(entity);
    }

    @Override
    public Promo patchOne(Long id, Promo entity) {
        return promoRepo.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        var promo = findById(id);
        promoRepo.delete(promo);
    }

    public Promo addProducts(Long promoId, List<String> productIds) {
        var promo = findById(promoId);
        var products = productRepo.findAllById(productIds);
        promo.addProduct(products);
        return promoRepo.save(promo);
    }

    public Promo replaceProducts(Long promoId, List<String> productIds) {
        var promo = findById(promoId);
        var products = productRepo.findAllById(productIds);
        promo.replaceProduct(products);
        return promoRepo.save(promo);
    }

    public void removeProduct(Long promoId, String productId) {
        var promo = findById(promoId);
        var product = productRepo.findById(productId).orElseThrow(CustomEntityNotFoundException::new);
        promo.removeProduct(product);
        promoRepo.save(promo);
    }
}
