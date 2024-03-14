package org.electronicsstore.backend.configs;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.customer.CustomerRefDto;
import org.electronicsstore.backend.dtos.customer.ReviewDto;
import org.electronicsstore.backend.dtos.customer.ShoppingCartItemDto;
import org.electronicsstore.backend.dtos.customer.ShoppingCartPutDto;
import org.electronicsstore.backend.dtos.order.OrderItemDto;
import org.electronicsstore.backend.dtos.order.ShippingMethodRefDto;
import org.electronicsstore.backend.dtos.order.ShopOrderDto;
import org.electronicsstore.backend.dtos.order.ShopOrderProcessDto;
import org.electronicsstore.backend.dtos.product.ProductRefDto;
import org.electronicsstore.backend.dtos.product.PromoDto;
import org.electronicsstore.backend.dtos.product.PromoPutDto;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.Review;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.model.order.OrderItem;
import org.electronicsstore.backend.model.order.Payment;
import org.electronicsstore.backend.model.order.ShopOrder;
import org.electronicsstore.backend.model.product.Product;
import org.electronicsstore.backend.model.product.Promo;
import org.electronicsstore.backend.repos.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class MapperConfig {
    private final PromoRepo promoRepo;
    private final ProductRepo productRepo;
    private final ShoppingCartItemRepo shoppingCartItemRepo;
    private final CustomerRepo customerRepo;
    private final ShippingMethodRepo shippingMethodRepo;

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setPreferNestedProperties(false)
                .setMatchingStrategy(MatchingStrategies.STRICT);;

        modelMapper.typeMap(PromoPutDto.class, Promo.class).addMappings(mapper -> {
            Converter<List<ProductRefDto>, Set<Product>> colls = c -> new HashSet<>(productRepo.findAllById(c.getSource()
                            .stream()
                            .map(ProductRefDto::getId)
                            .toList()));
            mapper
                    .using(colls)
                    .map(PromoPutDto::getProductIds, Promo::replaceProduct);
            });

        modelMapper.typeMap(Promo.class, PromoDto.class).addMappings(mapper ->
                mapper
                        .using(c -> ((Set<Product>)(c.getSource())).stream().map(Product::getId).toList())
                        .map(Promo::getProducts, PromoDto::setProducts));

        modelMapper.typeMap(ShoppingCartPutDto.class, ShoppingCart.class).addMappings(mapper -> {
            mapper
                    .using(c -> ((List<ShoppingCartItemDto>) c.getSource()).stream().map(sci -> modelMapper.map(sci, ShoppingCartItem.class)).toList())
                    .map(ShoppingCartPutDto::getShoppingCartItems, ShoppingCart::setShoppingCartItems);
            mapper
                    .using(c -> customerRepo.findById(((CustomerRefDto) c.getSource()).getId()).orElseThrow(CustomEntityNotFoundException::new))
                    .map(ShoppingCartPutDto::getCustomerId, ShoppingCart::setCustomer);
            });

        modelMapper.typeMap(ShoppingCartItem.class, ShoppingCartItemDto.class).addMappings(mapper -> {
            mapper
                    .using(c -> new ProductRefDto(((Product) c.getSource()).getId()))
                    .map(ShoppingCartItem::getProduct, ShoppingCartItemDto::setProduct);
            });

        modelMapper.typeMap(ReviewDto.class, Review.class).addMappings(mapper -> {
            mapper
                    .using(c -> customerRepo.findById(((CustomerRefDto) c.getSource()).getId()).orElseThrow(CustomEntityNotFoundException::new))
                    .map(ReviewDto::getCustomerId, Review::setCustomer);
            mapper
                    .using(c -> productRepo.findById(((ProductRefDto) c.getSource()).getId()).orElseThrow(CustomEntityNotFoundException::new))
                    .map(ReviewDto::getProductId, Review::setProduct);
        });

        modelMapper.typeMap(OrderItemDto.class, OrderItem.class).addMappings(mapper -> {
            mapper
                    .using(c -> productRepo.findById(((ProductRefDto) c.getSource()).getId()).orElseThrow(CustomEntityNotFoundException::new))
                    .map(OrderItemDto::getProductId, OrderItem::setProduct);
        });

        modelMapper.typeMap(ShopOrderDto.class, ShopOrder.class).addMappings(mapper -> {
            mapper
                    .using(c -> customerRepo.findById(((CustomerRefDto) c.getSource()).getId()).orElseThrow(CustomEntityNotFoundException::new))
                    .map(ShopOrderDto::getCustomerId, ShopOrder::setCustomer);
            mapper
                    .using(c -> shippingMethodRepo.findById(((ShippingMethodRefDto) c.getSource()).getId()).orElseThrow(CustomEntityNotFoundException::new))
                    .map(ShopOrderDto::getShippingMethodId, ShopOrder::setShippingMethod);
            mapper
                    .using(c -> ((List<OrderItemDto>) c.getSource()).stream().map(dto -> modelMapper.map(dto, OrderItem.class)).collect(Collectors.toSet()))
                    .map(ShopOrderDto::getOrderItems, ShopOrder::setOrderItems);
        });
        return modelMapper;
    }
}
