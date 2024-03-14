package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import org.electronicsstore.backend.dtos.order.ShopOrderProcessDto;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.model.order.OrderItem;
import org.electronicsstore.backend.model.order.ShopOrder;
import org.electronicsstore.backend.repos.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class ShopOrderService implements BaseService<ShopOrder, String> {
    private final ShopOrderRepo shopOrderRepo;
    private final ShoppingCartRepo shoppingCartRepo;
    private final ShippingMethodRepo shippingMethodRepo;
    private final CustomerRepo customerRepo;

    @Override
    public List<ShopOrder> findAll() {
        return shopOrderRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return shopOrderRepo.findAllBy(clz);
    }

    @Override
    public ShopOrder findById(String id) {
        return shopOrderRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public <P> P findProjById(String id, Class<P> clz) {
        return shopOrderRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public List<ShopOrder> findAllById(List<String> ids) {
        return shopOrderRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<String> ids, Class<P> clz) {
        return shopOrderRepo.findAllProjByIdIn(ids, clz);
    }

    @Override
    public ShopOrder createOne(ShopOrder entity) {
        return shopOrderRepo.save(entity);
    }

    @Override
    public ShopOrder updateOne(String id, ShopOrder entity) {
        return shopOrderRepo.save(entity);
    }

    @Override
    public ShopOrder patchOne(String id, ShopOrder entity) {
        return shopOrderRepo.save(entity);
    }

    @Override
    public void deleteOne(String id) {
        var order = shopOrderRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        shopOrderRepo.delete(order);
    }

    public ShopOrder processOrder(ShopOrderProcessDto dto) {
        var cart = shoppingCartRepo.findProjByCustomerId(dto.getCustomerId().getId(), ShoppingCart.class).orElseThrow(CustomEntityNotFoundException::new);
        var customer = customerRepo.findById(dto.getCustomerId().getId()).orElseThrow(CustomEntityNotFoundException::new);
        var shippingMethod = shippingMethodRepo.findById(dto.getShippingMethodId().getId()).orElseThrow(CustomEntityNotFoundException::new);
        var address = customer.getAddresses()
                .stream()
                .filter(a -> a.getId().equals(dto.getAddressId()))
                .findFirst().orElseThrow(RuntimeException::new);
        var orderItems = cart.getShoppingCartItems()
                .stream()
                .map(OrderItem::prepareOrderItemFromShoppingCartItem)
                .collect(Collectors.toSet());

        var order = new ShopOrder();
        order.setTotalAmount(cart.getTotal());
        order.setShippingAddress(address.prepareShippingAddress());
        order.setStatus(ShopOrder.ORDER_STATUS.PENDING.name());
        order.setCustomer(customer);
        order.setShippingMethod(shippingMethod);
        order.addOrderItem(orderItems);

        return shopOrderRepo.save(order);
    }
}
