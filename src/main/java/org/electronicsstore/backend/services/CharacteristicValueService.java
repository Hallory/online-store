package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.Characteristic;
import org.electronicsstore.backend.model.product.CharacteristicValue;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.CharacteristicRepo;
import org.electronicsstore.backend.repos.CharacteristicValueRepo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CharacteristicValueService implements BaseService<CharacteristicValue, Long> {
    private final CharacteristicValueRepo characteristicValueRepo;
    private final CharacteristicRepo characteristicRepo;
    private final ProductRepo productRepo;

    @Override
    public List<CharacteristicValue> findAll() {
        return characteristicValueRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return characteristicValueRepo.findAllBy(clz);
    }

    @Override
    public CharacteristicValue findById(Long id) {
        return characteristicValueRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public <P> P findProjById(Long id, Class<P> clz) {
        return characteristicValueRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public List<CharacteristicValue> findAllById(List<Long> ids) {
        return characteristicValueRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<Long> ids, Class<P> clz) {
        return characteristicValueRepo.findAllByIdIn(ids, clz);
    }

    @Override
    public CharacteristicValue createOne(CharacteristicValue entity) {
        return characteristicValueRepo.save(entity);
    }

    public CharacteristicValue createOne(Long categoryId, String productId, Long charId, CharacteristicValue charValue) {
        // todo deep validation
        var product = productRepo.findById(productId).orElseThrow(CustomEntityNotFoundException::new);
        var characteristic = characteristicRepo.findById(charId).orElseThrow(CustomEntityNotFoundException::new);
        charValue.setCharacteristic(characteristic);
        charValue.addProducts(product);
        return createOne(charValue);
    }

    @Override
    public CharacteristicValue updateOne(Long id, CharacteristicValue entity) {
        return characteristicValueRepo.save(entity);
    }

    public CharacteristicValue updateOne(Long categoryId, String productId, Long id, CharacteristicValue entity) {
        return updateOne(id, entity);
    }

    @Override
    public CharacteristicValue patchOne(Long id, CharacteristicValue entity) {
        return characteristicValueRepo.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        characteristicValueRepo.delete(findById(id));
    }

    public <P> List<P> findAllProjByCharacteristicCategoryId(Long categoryId, Class<P> clz) {
        return characteristicValueRepo.findAllProjByCharacteristicCategoryId(categoryId, clz);
    }

    public <P> List<P> findAllProjByCharacteristicId(Long charId, Class<P> clz) {
        return characteristicValueRepo.findAllProjByCharacteristicId(charId, clz);
    }

    public <P> List<P> findAllProjByProductsId(String productId, Class<P> clz) {
        return characteristicValueRepo.findAllProjByProductsId(productId, clz);
    }

    CharacteristicValue createRandomProductCharValue(Characteristic characteristic) {
        String randomText = UUID.randomUUID().toString();
        var productCharValue = new CharacteristicValue();
        productCharValue.setData(randomText);
        productCharValue.setCharacteristic(characteristic);
        return productCharValue;
    }
}
