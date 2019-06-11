package com.lambdaschool.demo.controller;

import com.lambdaschool.demo.model.Customers;
import com.lambdaschool.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping ("/customers")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/orders",
                produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        ArrayList<Customers> myList = customerService.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{custname}",
            produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(
            @PathVariable
                    String custname)
    {
        Customers r = customerService.findCustomerByName(custname);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping(value = "/customers",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid
                                              @RequestBody Customers newCustomer)
    {
        newCustomer = customerService.save(newCustomer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customers/{custcode}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateCustomerById(
            @RequestBody
                    Customers updateCustomer,
            @PathVariable
                    long custcode)
    {
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/customers/{custcode}")
    public ResponseEntity<?> deleteCustomerById(
            @PathVariable
                    long custcode)
    {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
