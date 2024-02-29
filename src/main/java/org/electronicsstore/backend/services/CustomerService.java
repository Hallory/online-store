package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.electronicsstore.backend.dtos.CustomerCreateRequest;
import org.electronicsstore.backend.dtos.CustomerDto;
import org.electronicsstore.backend.exceptions.CustomEntityExistsException;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.Customer;
import org.electronicsstore.backend.model.customer.ShoppingCart;
import org.electronicsstore.backend.repos.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    public List<CustomerDto> findAllDto() {
        return findAll().stream().map(CustomerDto::modelToDto).toList();
    }
    public Customer findById(String id) {
        return customerRepo.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Customer not found id: " + id));
    }

    public CustomerDto findByIdDto(String id) {
        return CustomerDto.modelToDto(findById(id));
    }

    public Customer createOne(CustomerCreateRequest req, String accountId) {
        if (customerRepo.existsByUsername(req.username())) {
            throw new CustomEntityExistsException("Something went wrong. Such email is already registered.");
        }
        return customerRepo.save(initCustomer(req, accountId));
    }

    public CustomerDto createOneDto(CustomerCreateRequest req, String accountId) {
        return CustomerDto.modelToDto(createOne(req, accountId));
    }

    private Customer initCustomer(CustomerCreateRequest dto, String accountId) {
        var customer = new Customer();
        customer.setUsername(dto.username());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setAccountId(accountId);
        customer.setEmail(dto.username());
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
