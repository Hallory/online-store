package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.product.CategoryProj;
import org.electronicsstore.backend.dtos.product.CharacteristicNoRefProj;
import org.electronicsstore.backend.dtos.product.CharacteristicProj;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.exceptions.EntityBadRequestException;
import org.electronicsstore.backend.model.product.Category;
import org.electronicsstore.backend.model.product.Characteristic;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.CategoryRepo;
import org.electronicsstore.backend.repos.CharacteristicRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Stream;

@Transactional
@Slf4j
@AllArgsConstructor
@Service
public class CharacteristicService implements BaseService<Characteristic, Long> {
    private final CharacteristicRepo characteristicRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public List<Characteristic> findAll() {
        return characteristicRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return characteristicRepo.findAllBy(clz);
    }

    @Override
    public Characteristic findById(Long id) {
        return characteristicRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    public <P> List<P> findAllProjByCategoryId(Long id, Class<P> clz) {
        return characteristicRepo.findAllProjByCategoryId(id, clz);
    }

    public List<CategoryProj.CharacteristicProjEmb> findAllProjByCategoryIdInherited(Long id, Class<CategoryProj> clz) {
        final var category = categoryRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
        List<CategoryProj.CharacteristicProjEmb> inherited =
                new ArrayList<>(category.getCharacteristics());
        CategoryProj.CategoryProjEmb tmp = category.getParent();
        while (tmp != null) {
            inherited.addAll(tmp.getCharacteristics());
            tmp = tmp.getParent();
        }
        return inherited.stream().filter(Objects::nonNull).toList();
    }

    public List<CharacteristicNoRefProj> findAllProjByCategoryIdInheritedCTE(Long id) {
        return characteristicRepo.findAllProjByCategoryIdCTE(id);
    }

    @Override
    public <P> P findProjById(Long id, Class<P> clz) {
        return characteristicRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
    }
    public <P> P findProjById(Long categoryId, Long id, Class<P> clz) {
        if (!categoryRepo.existsById(categoryId)) {
            throw new EntityBadRequestException();
        }
        return findProjById(id, clz);
    }


    @Override
    public List<Characteristic> findAllById(List<Long> ids) {
        return characteristicRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<Long> ids, Class<P> clz) {
        return characteristicRepo.findAllByIdIn(ids, clz);
    }

    @Override
    public Characteristic createOne(Characteristic entity) {
        if (characteristicRepo.existsByName(entity.getName())) {
            throw new CustomEntityExistsException();
        }
        return characteristicRepo.save(entity);
    }

    public Characteristic createOne(Long categoryId, Characteristic entity) {
        var category = categoryRepo.findById(categoryId).orElseThrow(EntityBadRequestException::new);
        entity.setCategory(category);
        return createOne(entity);
    }

    @Override
    public Characteristic updateOne(Long id, Characteristic entity) {
        return characteristicRepo.save(entity);
    }

    public Characteristic updateOne(Long categoryId, Long id, Characteristic entity) {
        // todo handle deep char value creation
        if (!categoryRepo.existsById(categoryId)) {
            throw new EntityBadRequestException();
        }
        return updateOne(id, entity);
    }

    @Override
    public Characteristic patchOne(Long id, Characteristic entity) {
        return characteristicRepo.save(entity);
    }

    public Characteristic patchOne(Long categoryId, Long id, Characteristic entity) {
        if (!categoryRepo.existsById(categoryId)) {
            throw new EntityBadRequestException();
        }
        return patchOne(id, entity);
    }

    @Override
    public void deleteOne(Long id) {
        characteristicRepo.delete(findById(id));
    }

    public void deleteOne(Long categoryId, Long id) {
        if (!categoryRepo.existsById(categoryId)) {
            throw new EntityBadRequestException();
        }
        characteristicRepo.delete(findById(id));
    }

    Characteristic createRandomProductChar(Category category) {
        String randomText = UUID.randomUUID().toString();
        var productChar = new Characteristic();
        productChar.setName(randomText);
        productChar.setDataType(randomText);
        productChar.setCategory(category);
        return productChar;
    }
}
