package com.tzy.DAO;

import com.tzy.Entity.Goods;

import java.util.List;

public interface GoodsDAO {

    Goods findbyID(int id);

    List<Goods> findAll();

    //List<Goods> findStartEnd(int start, int end);// for multiple pages

    void create(Goods goods);

    void modify(Goods goods);

    void delete(int id);
}
