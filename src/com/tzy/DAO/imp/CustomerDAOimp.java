package com.tzy.DAO.imp;

import com.tzy.DAO.CustomerDAO;
import com.tzy.Entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tzy.db.core.JdbcTemplate;

public class CustomerDAOimp implements CustomerDAO {

    JdbcTemplate template = new JdbcTemplate();

    @Override
    public Customer findbyID(String id) {
        List<Customer> list = new ArrayList<Customer>();
        String sql = "select id, name, address, password, phone from Customer where id = ?";

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps;
        }, rs -> CustomerList(list, rs));
        if(list.size() == 1){
            return(list.get(0));
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "select id, name, address, password, phone from Customer";

        template.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> {
            CustomerList(list, rs);
        });

        return list;
    }

    private void CustomerList(List<Customer> list, ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getString("id"));
        customer.setName(rs.getString("name"));
        customer.setAddress(rs.getString("address"));
        customer.setPassword(rs.getString("password"));
        customer.setPhone(rs.getInt("phone"));

        list.add(customer);
    }

    @Override
    public void create(Customer customer) {
        String sql = "insert into Customer (id,name,password,address,phone) values (?,?,?,?,?)";
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,customer.getId());
            ps.setString(2,customer.getName());
            ps.setString(3,customer.getPassword());
            ps.setString(4,customer.getAddress());
            ps.setInt(5,customer.getPhone());
            return ps;
        });

    }

    @Override
    public void modify(Customer customer) {
        String sql = "update Customer set name=?,password=?,address=?,phone=? where id=?";
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getPassword());
            ps.setString(3,customer.getAddress());
            ps.setInt(4,customer.getPhone());
            ps.setString(5,customer.getId());

            return ps;
        });

    }

    @Override
    public void delete(String id) {
        String sql = "delete from Customer where id = ?";

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            return ps;
        });

    }
}
