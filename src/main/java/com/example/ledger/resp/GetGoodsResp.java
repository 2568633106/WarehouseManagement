package com.example.ledger.resp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetGoodsResp {
    private int number;
    private BigDecimal price;
}
