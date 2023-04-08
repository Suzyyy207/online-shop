package com.example.online_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    int goodId;
    String goodsname;
    double goodsPrice;
    int status;
    int registerStatus;
    int modifyStatus;
    String goodsCategory;
    int goodsStock;
    String introduction;
    String shopname;
}
