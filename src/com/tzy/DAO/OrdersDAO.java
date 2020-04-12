package com.tzy.DAO;

import com.tzy.Entity.Orders;

import java.util.List;

public interface OrdersDAO {

    Orders findbyID(int id);

    List<Orders> findAll();

    void insert(Orders order);

    void modify(Orders order);

    void delete(int id);
}
