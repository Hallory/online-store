package org.electronicsstore.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.electronicsstore.backend.dtos.CustomerDto;
import org.electronicsstore.backend.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(
        origins = {"http://frontend:4200", "http://localhost:4200"},
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH }
)
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> customers() {
        return customerService.findAll().stream().map(CustomerDto::customerModelToDto).toList();
    }

    @GetMapping(value = {"{productId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto customerById(@PathVariable(name = "productId", required = true) String productId) {
        return CustomerDto.customerModelToDto(customerService.findById(productId));
    }
}
