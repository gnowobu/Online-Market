package com.tzy.DAO.imp;

import com.tzy.DAO.GoodsDAO;
import com.tzy.Entity.Goods;
import com.tzy.db.core.JdbcTemplate;
import com.tzy.db.core.PreparedStatementCreator;
import com.tzy.db.core.RowCallbackHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOimp implements GoodsDAO {
    JdbcTemplate template = new JdbcTemplate();

    @Override
    public Goods findbyID(int id) {
        String sql = "select id,name,price,description,brand from Goods where id = ?";
        List<Goods> list = new ArrayList<Goods>();

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            return ps;
        }, rs -> {
            GoodsList(list, rs);
        });
        if(list.size() == 1){
            return list.get(0);
        }

        return null;
    }

    private void GoodsList(List<Goods> list, ResultSet rs) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getInt(1));
        goods.setName(rs.getString(2));
        goods.setPrice(rs.getFloat(3));
        goods.setDescription(rs.getString(4));
        goods.setBrand(rs.getString(5));
        list.add(goods);
    }

    @Override
    public List<Goods> findAll() {
        String sql = "select id,name,price,description,brand from Goods";
        List<Goods> list = new ArrayList<Goods>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                return ps;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                GoodsList(list, rs);
            }
        });
        return list;
    }

    @Override
    public void create(Goods goods) {
        String sql = "insert into Goods (id,name,price,description,brand) values (?,?,?,?,?)";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,goods.getId());
                ps.setString(2,goods.getName());
                ps.setDouble(3,goods.getPrice());
                ps.setString(4,goods.getDescription());
                ps.setString(5,goods.getBrand());

                return ps;
            }
        });

    }

    @Override
    public void modify(Goods goods) {
        String sql = "update Goods set name=?,price=?,description=?,brand=? where id=?";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1,goods.getName());
                ps.setDouble(2,goods.getPrice());
                ps.setString(3,goods.getDescription());
                ps.setString(4,goods.getBrand());
                ps.setInt(5,goods.getId());
                return ps;
            }
        });



    }

    @Override
    public void delete(int id) {
        String sql = "delete from Goods where id=?";

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
