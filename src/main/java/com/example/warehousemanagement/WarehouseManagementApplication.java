package com.example.warehousemanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
@MapperScan("com.example.*.mapper")
public class WarehouseManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseManagementApplication.class, args);
    }

}
