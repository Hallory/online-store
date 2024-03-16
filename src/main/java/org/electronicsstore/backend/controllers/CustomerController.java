package org.electronicsstore.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.CustomerDto;
import org.electronicsstore.backend.dtos.customer.AddressDto;
import org.electronicsstore.backend.dtos.customer.AddressProj;
import org.electronicsstore.backend.model.customer.Address;
import org.electronicsstore.backend.services.AddressService;
import org.electronicsstore.backend.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class CustomerController extends AbstractController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> customers() {
        return customerService.findAllDto();
    }

    @GetMapping(value = {"{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerDto> customerById(@PathVariable(name = "customerId", required = true) String customerId) {
        return ResponseEntity.ok(customerService.findByIdDto(customerId));
    }

    @GetMapping(value = {"{customerId}/addresses"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AddressProj>> customerAllAddresses(
            @PathVariable(name = "customerId", required = true) String customerId
    ) {
        return ResponseEntity.ok(addressService.findAllByCustomerId(customerId, AddressProj.class));
    }

    @PostMapping(value = {"{customerId}/addresses"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> addAddressToCustomer(
            HttpServletRequest req,
            @PathVariable(name = "customerId", required = true) String customerId,
            @RequestBody AddressDto dto
    ) {
        var customer = customerService.findById(customerId);
        var address = modelMapper.map(dto, Address.class);
        address.setCustomer(customer);
        address = addressService.createOne(address);
        return ResponseEntity.created(buildURI(req, address.getId())).build();
    }

    @PatchMapping(value = {"{customerId}/addresses/{addressId}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAddressToCustomer(
            @PathVariable(name = "customerId", required = true) String customerId,
            @PathVariable(name = "addressId", required = true) String addressId,
            @RequestBody AddressDto dto
    ) {
        var address = addressService.findById(addressId);
        address = addressService.patchOne(addressId, mergePatch(address, Address.class, dto));
    }

    @DeleteMapping(value = {"{customerId}/addresses/{addressId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerAddressById(
            @PathVariable(name = "customerId", required = true) String customerId,
            @PathVariable(name = "addressId", required = true) String addressId
    ) {
        addressService.deleteOne(addressId);
    }
}
