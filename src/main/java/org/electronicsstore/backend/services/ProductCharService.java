package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.ProductCharCreateRequest;
import org.electronicsstore.backend.dtos.ProductCharDto;
import org.electronicsstore.backend.dtos.ProductCharPatchRequest;
import org.electronicsstore.backend.dtos.ProductCharUpdateRequest;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.ProductCategory;
import org.electronicsstore.backend.model.product.ProductChar;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.electronicsstore.backend.repos.ProductCharRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class ProductCharService {
    private final ProductCharRepo productCharRepo;
    private final ProductCategoryRepo categoryRepo;

    public ProductChar findById(Long id) {
        return productCharRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("ProductChar not found by id = " + id));
    }

    public ProductCharDto findByIdDto(Long charId) {
        return ProductCharDto.modelToDto(findById(charId));
    }

    public List<ProductChar> findAllById(Collection<Long> ids) {
        return productCharRepo.findAllById(ids);
    }

    public List<ProductChar> findAll() {
        return productCharRepo.findAll();
    }

    public List<ProductCharDto> findAllDto() {
        return findAll().stream().map(ProductCharDto::modelToDto).toList();
    }

    public ProductChar createOne(ProductCharCreateRequest dto) {
        if (productCharRepo.existsByName(dto.name())) {
            throw new CustomEntityExistsException("Character is already present, article = " + dto.name());
        }
        var productChar = ProductCharCreateRequest.dtoToModel(dto, (categoryId) -> categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new CustomEntityNotFoundException("Customer not found, id = " + categoryId)));
        return productCharRepo.save(productChar);
    }

    public ProductCharDto createOneDto(ProductCharCreateRequest dto) {
        return ProductCharDto.modelToDto(createOne(dto));
    }

    public ProductChar updateOne(Long charId, ProductCharUpdateRequest dto) {
        throw new NotImplementedException();
    }

    public ProductCharDto updateOneDto(Long charId, ProductCharUpdateRequest dto) {
        return ProductCharDto.modelToDto(updateOne(charId, dto));
    }

    public ProductChar patchOne(Long charId, ProductCharPatchRequest dto) {
        var productChar = findById(charId);
        for (String field : dto.fields()) {
            switch (field) {
                case "name": productChar.setName(dto.name()); break;
                case "dataType": productChar.setDataType(dto.dataType()); break;
                case "categoryId": {
                    var category = categoryRepo
                            .findById(dto.categoryId())
                            .orElseThrow(() -> new CustomEntityNotFoundException("Customer not found, id = " + dto.categoryId()));
                    productChar.setProductCategory(category);
                    break;
                }
                default: log.warn("Such field is not expected {}", field);
            }
        }
        return productCharRepo.save(productChar);
    }

    public ProductCharDto patchOneDto(Long charId, ProductCharPatchRequest dto) {
        return ProductCharDto.modelToDto(patchOne(charId, dto));
    }

    public void deleteOne(Long charId) {
        var product = findById(charId);
        productCharRepo.delete(product);
    }

    ProductChar createRandomCategory(ProductCategory category) {
        String randomText = UUID.randomUUID().toString();
        var productChar = new ProductChar();
        productChar.setName(randomText);
        productChar.setDataType(randomText);
        productChar.setProductCategory(category);
        return productChar;
    }
}
