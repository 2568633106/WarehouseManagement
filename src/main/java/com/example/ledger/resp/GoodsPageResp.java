package com.example.ledger.resp;

import com.example.ledger.pojo.WareHourse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsPageResp {
    private int startPage;
    private int pageSize;
    private List<WareHourse> wareHouses;
}
