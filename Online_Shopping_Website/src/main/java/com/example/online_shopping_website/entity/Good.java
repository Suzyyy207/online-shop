package com.example.online_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    int status;
    int registerStatus;
    int modifyStatus;
    int goodsId;
    String introduction;
    String goodsname;
    float goodsPrice;
    int goodsStock;
    String goodsCategory;
    String shopname;
}
