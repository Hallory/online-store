package org.electronicsstore.backend.configs;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.product.PromoDto;
import org.electronicsstore.backend.dtos.product.PromoPutDto;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.Promo;
import org.electronicsstore.backend.repos.ProductRepo;
import org.electronicsstore.backend.repos.PromoRepo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Configuration
public class MapperConfig {
    private final PromoRepo promoRepo;
    private final ProductRepo productRepo;
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setPreferNestedProperties(false);
        modelMapper.typeMap(PromoPutDto.class, Promo.class).addMappings(mapper -> {
                Converter<List<String>, Set<Product>> colls = c -> new HashSet<>(productRepo.findAllById(c.getSource()));
                mapper.using(colls).map(PromoPutDto::getProductIds, Promo::replaceProduct);
            });
        modelMapper.typeMap(Promo.class, PromoDto.class).addMappings(mapper ->
                    mapper.using(c -> ((Set<Product>)(c.getSource())).stream().map(Product::getId).toList())
                        .map(Promo::getProducts, PromoDto::setProducts));
        return modelMapper;
    }
}
