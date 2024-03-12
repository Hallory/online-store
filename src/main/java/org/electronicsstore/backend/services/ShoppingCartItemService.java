package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.ShoppingCartItem;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.ShoppingCartItemRepo;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ShoppingCartItemService implements BaseService<ShoppingCartItem, String> {
    private final ShoppingCartItemRepo shoppingCartItemRepo;

    @Override
    public List<ShoppingCartItem> findAll() {
        return shoppingCartItemRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return shoppingCartItemRepo.findAllBy(clz);
    }

    @Override
    public ShoppingCartItem findById(String id) {
        return shoppingCartItemRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public <P> P findProjById(String id, Class<P> clz) {
        return shoppingCartItemRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public List<ShoppingCartItem> findAllById(List<String> ids) {
        return shoppingCartItemRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<String> ids, Class<P> clz) {
        return shoppingCartItemRepo.findAllByIdIn(ids, clz);
    }

    public <P> List<P> findAllProjByCustomerId(String customerId, Class<P> clz) {
        return shoppingCartItemRepo.findAllProjByShoppingCartCustomerId(customerId, clz);
    }

    @Override
    public ShoppingCartItem createOne(ShoppingCartItem entity) {
        return shoppingCartItemRepo.save(entity);
    }

    public List<ShoppingCartItem> createAll(List<ShoppingCartItem> shoppingCartItems) {
        return shoppingCartItemRepo.saveAll(shoppingCartItems);
    }

    @Override
    public ShoppingCartItem updateOne(String id, ShoppingCartItem entity) {
        throw new NotImplementedException();
    }

    @Override
    public ShoppingCartItem patchOne(String id, ShoppingCartItem entity) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteOne(String id) {
        var sci = shoppingCartItemRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        shoppingCartItemRepo.delete(sci);
    }
}
