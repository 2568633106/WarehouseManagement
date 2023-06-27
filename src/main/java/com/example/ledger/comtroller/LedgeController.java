package com.example.ledger.comtroller;

import com.example.goods.service.GoodsService;
import com.example.ledger.pojo.WareHourse;
import com.example.ledger.req.GetIncome;
import com.example.ledger.resp.GetGoodsResp;
import com.example.ledger.service.WareHouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wareHouse")
public class LedgeController {
    @Autowired
    private WareHouseService wareHouseService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public GetGoodsResp getUser(@PathVariable int id){
        return wareHouseService.getGoodsInfo(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id){
        int result = wareHouseService.delete(id);
        if(result >= 1){
            return "删除成功！";
        }else{
            return "删除失败！";
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public int insert(@RequestBody WareHourse wareHouse){
        return wareHouseService.save(wareHouse);
    }

    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public int delete(@RequestBody WareHourse wareHouse){
        return wareHouseService.update(wareHouse);
    }

    @GetMapping(value = "/selectAll")
    @ResponseBody
    public PageInfo<WareHourse> listGood(@RequestBody GetIncome getIncome){
        List<WareHourse> list = wareHouseService.selectAll(getIncome);
        return new PageInfo<>(list);
    }
}
