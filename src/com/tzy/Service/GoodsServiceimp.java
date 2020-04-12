package com.tzy.Service;

import com.tzy.DAO.GoodsDAO;
import com.tzy.DAO.imp.GoodsDAOimp;
import com.tzy.Entity.Goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceimp implements GoodsService {
    GoodsDAO goodsDAO= new GoodsDAOimp();

    @Override
    public List<Goods> queryAll() {

       return goodsDAO.findAll();

    }

    public Goods goodsDetail(int id){
        return goodsDAO.findbyID(id);
    }
}
