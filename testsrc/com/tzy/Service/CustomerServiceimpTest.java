package com.tzy.Service;

import com.tzy.DAO.CustomerDAO;
import com.tzy.DAO.imp.CustomerDAOimp;
import com.tzy.Entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceimpTest {
    CustomerService test;


    @BeforeEach
    void setUp() {
        test = new CustomerServiceimp();
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void login() {
        Customer customer = new Customer();
        customer.setId("test");
        customer.setPassword("test");

        assertEquals(test.login(customer),true);

        customer.setPassword("123");
        assertEquals(test.login(customer),false);


    }

    @Test
    void register() {
        CustomerDAO dao = new CustomerDAOimp();
        Customer customer = new Customer();
        customer.setId("kaola");
        customer.setPassword("123");
        customer.setName("asa");
        customer.setAddress("sas");
        customer.setPhone(210102);
        test.register(customer);

        assertEquals(dao.findbyID("kaola").getName(),"asa");
        assertEquals(dao.findbyID("kaola").getAddress(),"sas");


    }
}