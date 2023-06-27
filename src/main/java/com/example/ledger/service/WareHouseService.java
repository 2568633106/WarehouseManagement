package com.example.ledger.service;

import com.example.goods.pojo.Goods;
import com.example.goods.service.GoodsService;
import com.example.ledger.mapper.WareHourseMapper;
import com.example.ledger.pojo.WareHourse;
import com.example.ledger.pojo.WareHourseExample;
import com.example.ledger.req.GetIncome;
import com.example.ledger.resp.GetGoodsResp;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WareHouseService {
    @Autowired
    private WareHourseMapper wareHouseMapper;
    private GoodsService goodsService;

    public List<WareHourse> getWareHouse(int id){
        WareHourseExample wareHouseExample = new WareHourseExample();
        wareHouseExample.createCriteria().andGoodsidEqualTo(id);
        return wareHouseMapper.selectByExample(wareHouseExample);
    }

    public int delete(int id){
        return wareHouseMapper.deleteByPrimaryKey(id);
    }

    public int save(WareHourse wareHouse){
        List<WareHourse> origiWareHouse = getWareHouse(wareHouse.getGoodsid());
        WareHourse newWareHouse = new WareHourse();
        newWareHouse.setGoodsname(wareHouse.getGoodsname());
        newWareHouse.setWarehousing(new Date());
        if (origiWareHouse!=null&&origiWareHouse.size()!=0){
            newWareHouse.setNumber(origiWareHouse.get(0).getNumber() + wareHouse.getNumber());
            newWareHouse.setGoodsid(wareHouse.getGoodsid());
            newWareHouse.setId(origiWareHouse.get(0).getId());
            addUpdate(newWareHouse);
            return origiWareHouse.get(0).getNumber() + wareHouse.getNumber();
        }
        newWareHouse.setNumber(wareHouse.getNumber());
        newWareHouse.setGoodsid(wareHouse.getGoodsid());
        newWareHouse.setWarehousing(new Date());
        wareHouseMapper.insert(wareHouse);
        return newWareHouse.getNumber();
    }

    public List<WareHourse>  selectAll(GetIncome getIncome){
        int pageSize = getIncome.getPageSize();
        int startPage = getIncome.getStartPage();
        WareHourseExample wareHouseExample = new WareHourseExample();
        wareHouseExample.createCriteria()
                .andWarehousingGreaterThanOrEqualTo(getIncome.getDate());
        PageHelper.startPage(startPage,pageSize);
        return wareHouseMapper.selectByExample(wareHouseExample);
    }

    public GetGoodsResp getGoodsInfo(int id) {
        GetGoodsResp getGoodsResp = new GetGoodsResp();
        Goods goods = goodsService.getGoods(id);
        BigDecimal price = goods.getPrice();
        int number = getWareHouse(id).get(0).getNumber();
        BigDecimal numberBig = new BigDecimal(number);
        BigDecimal multiply = price.multiply(numberBig);
        getGoodsResp.setNumber(number);
        getGoodsResp.setPrice(multiply);
        return getGoodsResp;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public int update(WareHourse wareHouse){
        List<WareHourse> origiWareHouse = getWareHouse(wareHouse.getGoodsid());
        if (origiWareHouse==null||origiWareHouse.size()==0){
            System.out.println("商品数量不足");
            return 0;
        }
        int number = origiWareHouse.get(0).getNumber() - wareHouse.getNumber();
        if(number < 0){
            System.out.println("商品数量不足");
            return 0;
        }
        WareHourseExample wareHouseExample = new WareHourseExample();
        wareHouseExample.createCriteria().andGoodsidEqualTo(wareHouse.getGoodsid());
        List<WareHourse> wareHouses = wareHouseMapper.selectByExample(wareHouseExample);
        WareHourse newWareHouse = new WareHourse();
        newWareHouse.setNumber(number);
        newWareHouse.setId(wareHouses.get(0).getId());
        newWareHouse.setGoodsid(wareHouse.getGoodsid());
        newWareHouse.setWarehousing(new Date());
        newWareHouse.setGoodsname(wareHouse.getGoodsname());
        wareHouseMapper.updateByPrimaryKey(newWareHouse);
        return number;
    }

    public void addUpdate(WareHourse newWareHouse) {
        wareHouseMapper.updateByPrimaryKey(newWareHouse);
    }
}
