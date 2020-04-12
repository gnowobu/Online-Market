package com.tzy.DAO;

import com.tzy.Entity.Customer;
import com.tzy.Entity.Goods;
import com.tzy.Entity.OrderStat;
import com.tzy.Entity.Orders;

import java.util.List;

public interface OrderStatDAO {

    OrderStat findbyID(int id);

    List<OrderStat> findbyGoodsID(Goods goods);

    List<OrderStat> findbyOrderID(Orders orders);

    List<OrderStat> findAll();

    void insert(OrderStat orderStat);

    void modify(OrderStat orderStat);

    void delete(int id);

}
