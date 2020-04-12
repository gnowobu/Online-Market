package com.tzy.DAO;

import com.tzy.DAO.imp.OrderStatDAOimp;
import com.tzy.Entity.Goods;
import com.tzy.Entity.OrderStat;
import com.tzy.Entity.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatDAOimpTest {
    OrderStatDAO test;

    @BeforeEach
    void setUp() {
        test = new OrderStatDAOimp();
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void findbyID() {
        OrderStat orderStat = test.findbyID(1);
        assertEquals(orderStat.getId(),1);
        assertEquals(orderStat.getGoods().getId(),1);



    }

    @Test
    void findbyGoodsID() {
        Goods goods = new Goods();
        goods.setId(1);
        List<OrderStat> orderStat = test.findbyGoodsID(goods);
        assertEquals(orderStat.get(0).getId(),1);
    }

    @Test
    void findbyOrderID() {
        Orders order = new Orders();
        order.setId(1);
        List<OrderStat> orderStat = test.findbyOrderID(order);
        assertEquals(orderStat.get(0).getId(),1);
    }

    @Test
    void findAll() {
        List<OrderStat> orderStat = test.findAll();
        assertEquals(1,orderStat.size());
    }

    @Test
    void insert() {
        OrderStat os = new OrderStat();
        os.setId(2);
        os.setSubtotal(12.12);
        Goods goods = new Goods();
        goods.setId(2);
        Orders order = new Orders();
        order.setId(2);
        os.setGoods(goods);
        os.setOrders(order);
        os.setQuantity(4);
        test.insert(os);
        os = test.findbyID(2);
        assertEquals(os.getSubtotal(),12.12,0.01);
    }

    @Test
    void modify() {
        OrderStat os = new OrderStat();
        os.setId(1);
        os.setSubtotal(12.12);
        Goods goods = new Goods();
        goods.setId(2);
        Orders order = new Orders();
        order.setId(2);
        os.setGoods(goods);
        os.setOrders(order);
        os.setQuantity(4);

        test.modify(os);
        os = test.findbyID(1);
        assertEquals(os.getGoods().getId(),2);
    }

    @Test
    void delete() {
        test.delete(2);
        assertNull(test.findbyID(2));
    }
}