package com.tzy.DAO.imp;

import com.tzy.DAO.OrderStatDAO;

import com.tzy.Entity.Customer;
import com.tzy.Entity.Goods;
import com.tzy.Entity.OrderStat;
import com.tzy.Entity.Orders;
import com.tzy.db.core.JdbcTemplate;
import com.tzy.db.core.PreparedStatementCreator;
import com.tzy.db.core.RowCallbackHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatDAOimp implements OrderStatDAO {
    JdbcTemplate template = new JdbcTemplate();


    @Override
    public OrderStat findbyID(int id) {
        String sql = "select id,goodsid,orderid,quantity,subtotal from OrderStat where id = ?";
        List<OrderStat> list = new ArrayList<>();

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            return ps;
        }, rs -> {
            OSList(list, rs);
        });
        if(list.size() == 1){
            return list.get(0);
        }

        return null;
    }

    private void OSList(List<OrderStat> list, ResultSet rs) throws SQLException {
        OrderStat os = new OrderStat();
        os.setId(rs.getInt("id"));

        Goods goods = new Goods();
        goods.setId(rs.getInt("goodsid"));
        os.setGoods(goods);

        Orders orders = new Orders();
        orders.setId(rs.getInt("orderid"));
        os.setQuantity(rs.getInt("quantity"));
        os.setSubtotal(rs.getFloat("subtotal"));
        list.add(os);
    }

    @Override
    public List<OrderStat> findbyGoodsID(Goods goods) {
        String sql = "select id,goodsid,orderid,quantity,subtotal from OrderStat where goodsid = ?";
        List<OrderStat> list = new ArrayList<>();
        template.query((PreparedStatementCreator) conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,goods.getId());
            return ps;
        }, rs -> OSList(list,rs));

        return list;
    }

    @Override
    public List<OrderStat> findbyOrderID (Orders orders) {
        String sql = "select id,goodsid,orderid,quantity,subtotal from OrderStat where orderid = ?";
        List<OrderStat> list = new ArrayList<>();

        template.query((PreparedStatementCreator) conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,orders.getId());
            return ps;
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                OSList(list,rs);
            }
        });
        return list;
    }

    @Override
    public List<OrderStat> findAll() {
        String sql = "select id,goodsid,orderid,quantity,subtotal from OrderStat";
        List<OrderStat> list = new ArrayList<>();

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> {
            OSList(list, rs);
        });
        return list;

    }

    @Override
    public void insert(OrderStat orderStat) {
        String sql = "insert into OrderStat (id,goodsid,orderid,quantity,subtotal) values (?,?,?,?,?)";
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderStat.getId());
            ps.setInt(2, orderStat.getGoods().getId());
            ps.setInt(3, orderStat.getOrders().getId());
            ps.setInt(4, orderStat.getQuantity());
            ps.setDouble(5, orderStat.getSubtotal());
            return ps;

        });
    }

    @Override
    public void modify(OrderStat orderStat) {

        String sql = "update OrderStat set goodsid=?,orderid=?,quantity=?,subtotal=? where id = ?";
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(5, orderStat.getId());
            ps.setInt(1, orderStat.getGoods().getId());
            ps.setInt(2, orderStat.getOrders().getId());
            ps.setInt(3, orderStat.getQuantity());
            ps.setDouble(4, orderStat.getSubtotal());
            return ps;

        });
    }

    @Override
    public void delete(int id) {
        String sql = "delete from OrderStat where id=?";

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            return ps;
        });

    }
}
