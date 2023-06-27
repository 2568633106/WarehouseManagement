package com.example.ledger.req;

import lombok.Data;

@Data
public class GetAllReq {
    private String name;
    private int startPage;
    private int pageSize;
}
