package com.tzy.Service;

import com.tzy.DAO.OrderStatDAO;
import com.tzy.DAO.OrdersDAO;
import com.tzy.DAO.imp.OrderStatDAOimp;
import com.tzy.DAO.imp.OrdersDAOimp;
import com.tzy.Entity.OrderStat;
import com.tzy.Entity.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceimpTest {
    OrderService test;
    OrdersDAO od = new OrdersDAOimp();



    @BeforeEach
    void setUp() {
        test = new OrderServiceimp();

    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void submitOrders() {

        List<Map<String, Object>> cart = new ArrayList<Map<String, Object>>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("id",1);
        item1.put("quantity",1);
        cart.add(item1);

        Orders order = od.findbyID(test.submitOrders(cart));

        assertNotNull(order);

        assertEquals(order.getTotal(), 100,0.01);

    }
}