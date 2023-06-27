package com.example.goods.comtroller;

import com.example.goods.pojo.Goods;
import com.example.goods.service.GoodsService;
import com.example.ledger.req.GetAllReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class UserController {
    @Autowired
    private GoodsService goodService;

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public Goods getUser(@PathVariable int id){
        return goodService.getGoods(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id){
        int result = goodService.delete(id);
        if(result >= 1){
            return "删除成功！";
        }else{
            return "删除失败！";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestBody Goods good){
        int result = goodService.update(good);
        if(result >= 1){
            return "更新成功！";
        }else{
            return "更新失败！";
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public int insert(@RequestBody Goods good){
        return goodService.save(good);
    }

    @GetMapping("/selectAll")
    @ResponseBody
    public List<Goods> listGood(@RequestBody GetAllReq getAllReq){
        return goodService.selectAll(getAllReq);
    }
}
