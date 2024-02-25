package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.CustomerCreateRequest;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.Customer;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.repos.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    public Customer findById(String id) {
        return customerRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Customer not found id: " + id));
    }

    public Customer saveOneInit(CustomerCreateRequest req, String accountId) {
        if (customerRepo.existsByUsername(req.username())) {
            throw new CustomEntityExistsException("Something went wrong. Such email is already registered.");
        }
        return customerRepo.save(initCustomer(req, accountId));
    }

    private Customer initCustomer(CustomerCreateRequest req, String accountId) {
        var customer = CustomerCreateRequest.dtoToModel(req);
        customer.setAccountId(accountId);
        customer.setEmail(req.username());
        customer.setShoppingCart(new ShoppingCart());
        return customer;
    }

    public Customer updateOne(Customer customer) {
        throw new NotImplementedException();
    }
    public void deleteOne(Customer customer) {
        throw new NotImplementedException();
    }
}
