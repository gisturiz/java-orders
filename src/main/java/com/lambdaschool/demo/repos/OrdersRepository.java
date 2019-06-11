package com.lambdaschool.demo.repos;

import com.lambdaschool.demo.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long>
{
}
