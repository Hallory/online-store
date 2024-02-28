package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.ProductCreateRequest;
import org.electronicsstore.backend.dtos.ProductDto;
import org.electronicsstore.backend.dtos.ProductPatchRequest;
import org.electronicsstore.backend.dtos.ProductUpdateRequest;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.repos.ProductCategoryRepo;
import org.electronicsstore.backend.repos.ProductCharValueRepo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.electronicsstore.backend.repos.PromoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductCategoryRepo categoryRepo;
    private final PromoRepo promoRepo;
    private final ProductCharValueRepo productCharValueRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public List<ProductDto> findAllDto() {
        return findAll().stream().map(ProductDto::modelToDto).toList();
    }

    public Product findById(String productId) {
        return productRepo.findById(productId).orElseThrow(() -> new CustomEntityNotFoundException("Product not found, id = " + productId));
    }

    public ProductDto findByIdDto(String productId) {
        return ProductDto.modelToDto(findById(productId));
    }

    public List<Product> findAllById(Collection<String> ids) {
        return productRepo.findAllById(ids);
    }

    public List<ProductDto> findAllByIdDto(Collection<String> ids) {
        return findAllById(ids).stream().map(ProductDto::modelToDto).toList();
    }

    public Product createOne(ProductCreateRequest dto) {
        if (productRepo.existsByArticle(dto.article())) {
            throw new CustomEntityExistsException("Product is already present, article = " + dto.article());
        }
        var product = ProductCreateRequest.dtoToModel(dto);
        var category = categoryRepo.findById(dto.categoryId()).orElseThrow(() -> new CustomEntityNotFoundException("Customer not found, id = " + dto.categoryId()));
        product.setProductCategory(category);
        return productRepo.save(product);
    }

    public ProductDto createOneDto(ProductCreateRequest dto) {
        return ProductDto.modelToDto(createOne(dto));
    }

    public Product updateOne(String productId, ProductUpdateRequest dto) {
        throw new NotImplementedException();
//        var product = productRepo.findById(productId).orElse(new Product());
//        product.setArticle(dto.article());
//        product.setSKU(dto.SKU());
//        product.setBarcode(dto.barcode());
//        product.setQtyInStock(dto.qtyInStock());
//        product.setPrice(dto.price());
//        product.setDescription(dto.description());
//        product.setName(dto.name());
//        product.setPromo(promoService.findById(dto.promoId()));
//        product.setProductImages(dto.productImages());
//        product.setProductIcon(dto.productIcon());
//        product.addProductCharValue(productCharValueService.findAllById(dto.productCharValues())); // todo create
//        product.setProductCategory(categoryService.findById(dto.categoryId()));
//        return productRepo.save(product);
    }

    public ProductDto updateOneDto(String productId, ProductUpdateRequest dto) {
        return ProductDto.modelToDto(updateOne(productId, dto));
    }

    public Product patchOne(String productId, ProductPatchRequest dto) {
        var product = findById(productId);
        for (String field : dto.fields()) {
            switch (field) {
                case "article": product.setName(dto.article()); break;
                case "SKU": product.setSKU(dto.SKU()); break;
                case "barcode": product.setBarcode(dto.barcode()); break;
                case "qtyInStock": product.setQtyInStock(dto.qtyInStock()); break;
                case "price": product.setPrice(dto.price()); break;
                case "description": product.setDescription(dto.description()); break;
                case "name": product.setName(dto.name()); break;
                case "promoId": {
                    product.setPromo(promoRepo.findById(dto.promoId()).orElseThrow(() -> new CustomEntityNotFoundException("Promo not found, id = " + dto.promoId())));
                } break;
                default: log.warn("Such field is not expected {}", field);
            }
        }
        return productRepo.save(product);
    }

    public ProductDto patchOneDto(String productId, ProductPatchRequest dto) {
        return ProductDto.modelToDto(patchOne(productId, dto));
    }

    public void deleteOne(String productId) {
        var product = findById(productId);
        productRepo.delete(product);
    }
}
