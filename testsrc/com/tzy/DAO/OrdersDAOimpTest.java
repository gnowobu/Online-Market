package com.tzy.DAO;

import com.tzy.DAO.imp.OrdersDAOimp;
import com.tzy.Entity.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class OrdersDAOimpTest {
    OrdersDAO test;

    @BeforeEach
    void setUp() {
        test = new OrdersDAOimp();
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void findbyID() {
        Orders order = test.findbyID(1);
        assertNotNull(order);
        assertEquals(1,order.getId());
        assertEquals(20190209,order.getDate().getTime());

    }

    @Test
    void findAll() {
        List<Orders> ordersList = new ArrayList<>();
        ordersList =  test.findAll();

        assertEquals(1,ordersList.size());
        assertEquals(205.12,ordersList.get(0).getTotal(),0.01);


    }

    @Test
    void insert() {
        Orders order = new Orders();
        order.setDate(new Date(849778179420L));
        order.setId(2);
        order.setStatus("ongoing");
        order.setTotal(123.21);

        test.insert(order);
        order = test.findbyID(2);

        assertEquals(order.getDate().getTime(),849778179420L);



    }

    @Test
    void modify() {
        Orders order = new Orders();
        order.setDate(new Date(84977829420L));
        order.setId(2);
        order.setStatus("finished");
        order.setTotal(112.1);

        test.modify(order);
        order = test.findbyID(2);

        assertEquals(112.1,order.getTotal());

    }

    @Test
    void delete() {
        test.delete(2);
        assertNull(test.findbyID(2));
    }
}