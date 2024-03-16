package org.electronicsstore.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.electronicsstore.backend.dtos.customer.AddressProj;
import org.electronicsstore.backend.exceptions.CustomEntityNotFoundException;
import org.electronicsstore.backend.model.customer.Address;
import org.electronicsstore.backend.model.customer.Review;
import org.electronicsstore.backend.repos.AddressRepo;
import org.electronicsstore.backend.repos.BaseService;
import org.electronicsstore.backend.repos.ReviewRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AddressService implements BaseService<Address, String> {
    private final AddressRepo addressRepo;

    @Override
    public List<Address> findAll() {
        return addressRepo.findAll();
    }

    @Override
    public <P> List<P> findAllBy(Class<P> clz) {
        return addressRepo.findAllBy();
    }

    @Override
    public Address findById(String id) {
        return addressRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Override
    public <P> P findProjById(String id, Class<P> clz) {
        return addressRepo.findProjById(id, clz);
    }

    @Override
    public List<Address> findAllById(List<String> ids) {
        return addressRepo.findAllById(ids);
    }

    @Override
    public <P> List<P> findAllProjByIds(List<String> ids, Class<P> clz) {
        return addressRepo.findAllProjByIdIn(ids, clz);
    }

    @Override
    public Address createOne(Address entity) {
        return addressRepo.save(entity);
    }

    @Override
    public Address updateOne(String id, Address entity) {
        return addressRepo.save(entity);
    }

    @Override
    public Address patchOne(String id, Address entity) {
        return addressRepo.save(entity);
    }

    @Override
    public void deleteOne(String id) {
        var address = addressRepo.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        addressRepo.delete(address);
    }

    public <P> List<P> findAllByCustomerId(String customerId, Class<P> clz) {
        return addressRepo.findAllByCustomerId(customerId, clz);
    }
}
