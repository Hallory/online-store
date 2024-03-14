package org.electronicsstore.backend.services;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.CustomerRepo;
import org.electronicsstore.backend.repos.ShoppingCartRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShoppingCartService implements BaseService<ShoppingCart, String> {
    private final ShoppingCartRepo shoppingCartRepo;
    private final CustomerRepo customerRepo;

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return shoppingCartRepo.findAllBy(clz);
    }

    @Override
    public ShoppingCart findById(String id) {
        return shoppingCartRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public <P> P findProjById(String id, Class<P> clz) {
        return shoppingCartRepo.findProjById(id, clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    public <P> P findProjByCustomerId(String customerId, Class<P> clz) {
        return shoppingCartRepo.findProjByCustomerId(customerId, clz).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public List<ShoppingCart> findAllById(List<String> ids) {
        return shoppingCartRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<String> ids, Class<P> clz) {
        return shoppingCartRepo.findAllByIdIn(ids, clz);
    }

    @Override
    public ShoppingCart createOne(ShoppingCart entity) {
        return shoppingCartRepo.save(entity);
    }

    public ShoppingCart createOne(String customerId, ShoppingCart entity) {
        if (shoppingCartRepo.existsByCustomerId(customerId)) {
            throw new CustomEntityExistsException();
        }
        var customer = customerRepo.findById(customerId).orElseThrow(CustomEntityNotFoundException::new);
        entity.setCustomer(customer);
        return createOne(entity);
    }

    @Override
    public ShoppingCart updateOne(String id, ShoppingCart entity) {
        return shoppingCartRepo.save(entity);
    }

    @Override
    public ShoppingCart patchOne(String id, ShoppingCart entity) {
        return shoppingCartRepo.save(entity);
    }

    @Override
    public void deleteOne(String id) {
        var cart = shoppingCartRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        shoppingCartRepo.delete(cart);
    }
}
