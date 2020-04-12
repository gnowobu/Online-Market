package com.tzy.Service;

import com.tzy.Entity.Goods;

import java.util.List;

public interface GoodsService {
    public List<Goods> queryAll();

    public Goods goodsDetail(int id);
}
