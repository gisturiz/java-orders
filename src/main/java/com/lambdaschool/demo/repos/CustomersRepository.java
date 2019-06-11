package com.lambdaschool.demo.repos;

import com.lambdaschool.demo.model.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{
    Customers findByCustname(String custname);
}
