package com.example.goods.service;

import com.example.goods.mapper.GoodsMapper;
import com.example.goods.pojo.Goods;
import com.example.goods.pojo.GoodsExample;
import com.example.ledger.req.GetAllReq;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public Goods getGoods(int id){
        return goodsMapper.selectByPrimaryKey(id);
    }

    public int delete(int id){
        return goodsMapper.deleteByPrimaryKey(id);
    }

    public int update(Goods goods){
        return goodsMapper.updateByPrimaryKey(goods);
    }

    public int save(Goods goods){
        Date date = new Date();
        goods.setDate(date);
        return goodsMapper.insert(goods);
    }

    public List<Goods>  selectAll(GetAllReq getAllReq){
        String name = getAllReq.getName();
        int pageSize = getAllReq.getPageSize();
        int startPage = getAllReq.getStartPage();
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause("date asc");
        if (name!=null)
        goodsExample.createCriteria().andNameEqualTo(name);
        PageHelper.startPage(startPage,pageSize);
        return goodsMapper.selectByExample(goodsExample);
    }
}
