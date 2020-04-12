package com.tzy.DAO.imp;

import com.tzy.DAO.OrdersDAO;
import com.tzy.Entity.Goods;
import com.tzy.Entity.Orders;
import com.tzy.db.core.DataAccessException;
import com.tzy.db.core.JdbcTemplate;
import com.tzy.db.core.PreparedStatementCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOimp implements OrdersDAO {
    JdbcTemplate template = new JdbcTemplate();

    @Override
    public Orders findbyID(int id) {

        String sql = "select id,order_date,status,total from Orders where id = ?";
        List<Orders> list = new ArrayList<>();

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            return ps;
        }, rs -> {
            OrdersList(list, rs);
        });
        if(list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Orders> findAll() {
        String sql = "select id,order_date,status,total from Orders";
        List<Orders> list = new ArrayList<>();

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);

            return ps;
        }, rs -> {
            OrdersList(list, rs);
        });

        return list;
    }

    private void OrdersList(List<Orders> list, ResultSet rs) throws SQLException {
        Orders orders = new Orders();
        orders.setId(rs.getInt(1));
        orders.setDate(new Date(rs.getLong(2)));
        orders.setStatus(rs.getString(3));
        orders.setTotal(rs.getDouble(4));
        list.add(orders);
    }

    @Override
    public void insert(Orders order) {
        String sql = "insert into Orders (id,order_date,status,total) values (?,?,?,?)";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,order.getId());
                ps.setLong(2,order.getDate().getTime());
                ps.setString(3,order.getStatus());
                ps.setDouble(4,order.getTotal());

                return ps;

            }
        });

    }

    @Override
    public void modify(Orders order) {
        String sql = "update Orders set order_date=?, status=?, total=? where id=?";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1,order.getDate().getTime());
                ps.setString(2,order.getStatus());
                ps.setDouble(3,order.getTotal());
                ps.setInt(4,order.getId());
                return ps;
            }
        });




    }

    @Override
    public void delete(int id) {
        String sql = "delete from Orders where id=?";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1,id);
                return ps;
            }
        });

    }
}
