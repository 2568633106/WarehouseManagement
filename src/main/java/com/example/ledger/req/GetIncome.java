package com.example.ledger.req;

import lombok.Data;

import java.util.Date;

@Data
public class GetIncome {
    private Date date;
    private int startPage;
    private int pageSize;
}
