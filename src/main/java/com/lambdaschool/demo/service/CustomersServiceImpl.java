package com.lambdaschool.demo.service;

import com.lambdaschool.demo.model.Customers;
import com.lambdaschool.demo.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "customerService")
public class CustomersServiceImpl implements CustomerService
{
    @Autowired
    private CustomersRepository customerrepo;

    @Override
    public ArrayList<Customers> findAll()
    {
        ArrayList<Customers> list = new ArrayList<>();
        customerrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerById(long id)
    {
        return customerrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Customers findCustomerByName(String custname)
    {
        Customers customers = customerrepo.findByCustname(custname);

        if (customers == null)
        {
            throw new EntityNotFoundException("Agent " + custname + " not found!");
        }

        return customers;
    }

    @Override
    public void delete(long id)
    {
        if (customerrepo.findById(id).isPresent())
        {
            customerrepo.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Customers save(Customers customers)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customers.getCustname());
        newCustomer.setWorkingarea(customers.getWorkingarea());
        newCustomer.setCustcity(customers.getCustcity());
        newCustomer.setCustcountry(customers.getCustcountry());
        newCustomer.setGrade(customers.getGrade());
        newCustomer.setOpeningamt(customers.getOpeningamt());
        newCustomer.setReceiveamt(customers.getReceiveamt());
        newCustomer.setPaymentamt(customers.getPaymentamt());
        newCustomer.setOutstandingamt(customers.getOutstandingamt());
        newCustomer.setPhone(customers.getPhone());

        return customerrepo.save(newCustomer);
    }

    @Transactional
    @Override
    public Customers update(Customers customers, long id)
    {
        Customers currentAgents = customerrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customers.getCustname() != null)
        {
            currentAgents.setCustname(customers.getCustname());
        }

        if (customers.getWorkingarea() != null)
        {
            currentAgents.setWorkingarea(customers.getWorkingarea());
        }

        if (customers.getGrade() != null)
        {
            currentAgents.setGrade(customers.getGrade());
        }

        if (customers.getOpeningamt() != 0)
        {
            currentAgents.setOpeningamt(customers.getOpeningamt());
        }

        if (customers.getReceiveamt() != 0)
        {
            currentAgents.setReceiveamt(customers.getReceiveamt());
        }

        if (customers.getPaymentamt() != 0)
        {
            currentAgents.setPaymentamt(customers.getPaymentamt());
        }

        if (customers.getOutstandingamt() != 0)
        {
            currentAgents.setOutstandingamt(customers.getOutstandingamt());
        }

        if (customers.getCustcity() != null)
        {
            currentAgents.setCustcity(customers.getCustcity());
        }

        if (customers.getPhone() != null)
        {
            currentAgents.setPhone(customers.getPhone());
        }

        if (customers.getCustcountry() != null)
        {

            currentAgents.setCustcountry(customers.getCustcountry());
        }

        return customerrepo.save(currentAgents);
    }

}
