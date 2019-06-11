package com.lambdaschool.demo.service;

import com.lambdaschool.demo.model.Customers;

import java.util.ArrayList;

public interface CustomerService
{
    ArrayList<Customers> findAll();

    Customers findCustomerById(long id);

    Customers findCustomerByName(String name);

    void delete(long id);

    Customers save(Customers customer);

    Customers update(Customers customer, long id);
}
