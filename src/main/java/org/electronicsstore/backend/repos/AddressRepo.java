package org.electronicsstore.backend.repos;

import org.electronicsstore.backend.model.customer.Address;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AddressRepo extends ListCrudRepository<Address, String> {
    <P> List<P> findAllBy();

    <P> P findProjById(String id, Class<P> clz);

    <P> List<P> findAllProjByIdIn(List<String> ids, Class<P> clz);

    <P> List<P> findAllByCustomerId(String customerId, Class<P> clz);
}
