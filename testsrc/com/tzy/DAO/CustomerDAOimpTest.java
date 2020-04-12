package com.tzy.DAO;

import com.tzy.DAO.imp.CustomerDAOimp;
import com.tzy.Entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerDAOimpTest {
    CustomerDAO test;

    @BeforeEach
    void setUp() {
        test = new CustomerDAOimp();

    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void findbyID() {

        Customer customer = test.findbyID("test");
        assertNotNull(customer);
        assertEquals("test",customer.getId());
        assertEquals("test",customer.getName());
        assertEquals("test",customer.getAddress());
        assertEquals("test",customer.getPassword());
        assertEquals(2022948121,customer.getPhone());

    }

    @Test
    void findAll() {
        List<Customer> list = test.findAll();

        assertEquals(list.size(),1);
    }

    @Test
    void create() {
        Customer customer = new Customer();
        customer.setId("tt166");
        customer.setName("tzy");
        customer.setPassword("1234");
        customer.setAddress("eads");
        customer.setPhone(202294);

        test.create(customer);

        Customer customer1 = test.findbyID("tt166");

        assertEquals(customer.getAddress(),customer1.getAddress());
        assertEquals(customer.getName(),customer1.getName());
        assertEquals(customer.getPhone(),customer1.getPhone());
        assertEquals(customer.getId(),customer1.getId());
        assertEquals(customer.getPassword(),customer1.getPassword());


    }

    @Test
    void modify() {
        Customer customer = new Customer();
        customer.setId("tt166");
        customer.setPhone(12324);
        customer.setAddress("Ros");
        customer.setPassword("421");
        customer.setName("nxing");

        test.modify(customer);

        customer = test.findbyID("tt166");

        assertEquals(customer.getAddress(),"Ros");
        assertEquals(customer.getName(),"nxing");
        assertEquals(customer.getPhone(),12324);
        assertEquals(customer.getPassword(),"421");

    }

    @Test
    void delete() {
        test.delete("tt166");

        assertNull(test.findbyID("tt166"));


    }
}