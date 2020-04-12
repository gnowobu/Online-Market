package com.tzy.DAO;

import com.tzy.Entity.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer findbyID(String id);

    List<Customer> findAll();

    void create(Customer customer);

    void modify(Customer customer);

    void delete(String id);

}
