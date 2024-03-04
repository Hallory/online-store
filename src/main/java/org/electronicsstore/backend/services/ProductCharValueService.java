package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.ProductCharValueCreateRequest;
import org.electronicsstore.backend.dtos.ProductCharValueDto;
import org.electronicsstore.backend.dtos.ProductCharValuePatchRequest;
import org.electronicsstore.backend.dtos.ProductCharValueUpdateRequest;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.model.product.ProductCharValue;
import org.electronicsstore.backend.repos.ProductCharRepo;
import org.electronicsstore.backend.repos.ProductCharValueRepo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ProductCharValueService {
    private final ProductCharValueRepo productCharValueRepo;
    private final ProductCharRepo productCharRepo;
    private final ProductRepo productRepo;

    public List<ProductCharValue> findAllById(Collection<Long> ids) {
        return productCharValueRepo.findAllById(ids);
    }
    public List<ProductCharValueDto> findAllByIdDto(Collection<Long> ids) {
        return findAllById(ids).stream().map(ProductCharValueDto::modelToDto).toList();
    }

    public List<ProductCharValue> findAll() {
        return productCharValueRepo.findAll();
    }

    public List<ProductCharValueDto> findAllDto() {
        return findAll().stream().map(ProductCharValueDto::modelToDto).toList();
    }

    public ProductCharValue findById(Long charId) {
        return productCharValueRepo.findById(charId).orElseThrow(() -> new CustomEntityNotFoundException("Characteristic value not found by id = " + charId));
    }

    public ProductCharValueDto findByIdDto(Long charId) {
        return ProductCharValueDto.modelToDto(findById(charId));
    }

    public ProductCharValue createOne(ProductCharValueCreateRequest dto) {
        var productChar = productCharRepo.findById(dto.productCharId()).orElseThrow(() -> new CustomEntityNotFoundException("Characteristic not found, id = " + dto.productCharId()));
        var products = productRepo.findAllById(dto.productIds());
//        if (productChar.getProductCategory().getId().equals(dto.))
        var productCharValue = new ProductCharValue();
        productCharValue.setData(dto.data());
        productCharValue.setProductChar(productChar);
        productCharValue.setProducts(new HashSet<>(products));
        return productCharValueRepo.save(productCharValue);
    }

    public ProductCharValueDto createOneDto(ProductCharValueCreateRequest dto) {
        return ProductCharValueDto.modelToDto(createOne(dto));
    }

    public ProductCharValue updateOne(Long charValueId, ProductCharValueUpdateRequest dto) {
        throw new NotImplementedException();
    }

    public ProductCharValueDto updateOneDto(Long charValueId, ProductCharValueUpdateRequest dto) {
        return ProductCharValueDto.modelToDto(updateOne(charValueId, dto));
    }

    public ProductCharValue patchOne(Long charValueId, ProductCharValuePatchRequest dto) {
        var charValue = findById(charValueId);
        for (String field : dto.fields()) {
            switch (field) {
                case "data": charValue.setData(dto.data()); break;
                case "productCharId": { // not required
                    var productChar = productCharRepo
                            .findById(dto.productCharId())
                            .orElseThrow(() -> new CustomEntityNotFoundException("Product char not found, id = " + dto.productCharId()));
                    charValue.setProductChar(productChar);
                    break;
                }
                case "productIds": { // no sense
                    var products = new HashSet<>(productRepo.findAllById(dto.productIds()));
                    charValue.setProducts(products);
                    break;
                }
                default: log.warn("Such field is not expected {}", field);
            }
        }
        return productCharValueRepo.save(charValue);
    }

    public ProductCharValueDto patchOneDto(Long charId, ProductCharValuePatchRequest dto) {
        return ProductCharValueDto.modelToDto(patchOne(charId, dto));
    }

    public void deleteOne(Long charId) {
        productCharValueRepo.delete(findById(charId));
    }

    ProductCharValue createRandomProductCharValue(ProductChar productChar) {
        String randomText = UUID.randomUUID().toString();
        var productCharValue = new ProductCharValue();
        productCharValue.setData(randomText);
        productCharValue.setProductChar(productChar);
        return productCharValue;
    }
}
