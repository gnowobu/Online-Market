package com.tzy.DAO;

import com.tzy.DAO.imp.GoodsDAOimp;
import com.tzy.Entity.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDAOimpTest {
    GoodsDAO test;

    @BeforeEach
    void setUp() {
        test = new GoodsDAOimp();
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void findbyID() {
    Goods goods = test.findbyID(1);
    assertNotNull(goods);
    assertEquals("test",goods.getBrand());
    assertEquals("test",goods.getDescription());
    assertEquals("test",goods.getName());
    assertEquals(100.0,goods.getPrice());

    }

    @Test
    void findAll() {
        List<Goods> list = new ArrayList<>();
        list = test.findAll();
        assertEquals(list.size(),1);
    }

    @Test
    void create() {
        Goods goods = new Goods();
        goods.setId(2);
        goods.setName("gw");
        goods.setPrice(25.68);
        goods.setBrand("gwu");
        goods.setDescription("test");

        test.create(goods);

        Goods goods1 = test.findbyID(2);

        assertNotNull(goods1);
        assertEquals("test",goods1.getDescription());
        assertEquals("gwu",goods1.getBrand());
        assertEquals("gw",goods1.getName());
        assertEquals(25.68,goods1.getPrice(),0.001);//double will not be exactly the same
    }

    @Test
    void modify() {
        Goods goods = new Goods();
        goods.setId(2);
        goods.setName("gu");
        goods.setPrice(19.96);
        goods.setBrand("gu");
        goods.setDescription("test1");

        test.modify(goods);
        goods = test.findbyID(2);

        assertNotNull(goods);
        assertEquals("test1",goods.getDescription());
        assertEquals("gu",goods.getBrand());
        assertEquals("gu",goods.getName());
        assertEquals(19.96,goods.getPrice(),0.001);//double will not be exactly the same
    }

    @Test
    void delete() {
        test.delete(2);
        assertNull(test.findbyID(2));

    }
}