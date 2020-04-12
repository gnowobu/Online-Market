package com.tzy.Service;

import com.tzy.DAO.GoodsDAO;
import com.tzy.DAO.OrderStatDAO;
import com.tzy.DAO.OrdersDAO;
import com.tzy.DAO.imp.GoodsDAOimp;
import com.tzy.DAO.imp.OrderStatDAOimp;
import com.tzy.DAO.imp.OrdersDAOimp;
import com.tzy.Entity.Goods;
import com.tzy.Entity.OrderStat;
import com.tzy.Entity.Orders;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class OrderServiceimp implements OrderService {

    GoodsDAO goodsimp = new GoodsDAOimp();
    OrdersDAO ordersimp = new OrdersDAOimp();
    OrderStatDAO orderstatimp = new OrderStatDAOimp();


    @Override
    public int submitOrders(List<Map<String, Object>> cart) {//the structure of cart:[Goods id,quantity]

        Orders order = new Orders();
        Date date = new Date();
        Random r = new Random();
        order.setId(r.nextInt(1000));
        order.setDate(date);
        order.setStatus("unpaid");
        order.setTotal(0.0);

        ordersimp.insert(order);
        double total = 0.0;

        for(Map item: cart){
            int id = (Integer)item.get("goodsid");
            int quantity = (Integer)item.get("quantity");

            Goods goods = goodsimp.findbyID(id);
            double price = goods.getPrice();
            double subtotal = quantity * price;

            OrderStat orderStat = new OrderStat();//the OrderStat is like a record for each order
            orderStat.setOrders(order);
            orderStat.setGoods(goods);
            orderStat.setQuantity(quantity);
            orderStat.setSubtotal(subtotal);
            orderStat.setId(r.nextInt(1000));
            orderstatimp.insert(orderStat);


            total += subtotal;

        }
        order.setTotal(total);
        ordersimp.modify(order);

        return order.getId(); //use String instead of int, as in most cases the id will be String
    }
}
