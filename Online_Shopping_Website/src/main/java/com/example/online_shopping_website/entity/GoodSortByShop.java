package com.example.online_shopping_website.entity;

import lombok.Data;

import java.util.List;

@Data
public class GoodSortByShop {
    private String shopname;

    private List<GoodReturn> goodReturnList;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public List<GoodReturn> getGoodReturnList() {
        return goodReturnList;
    }

    public void setGoodReturnList(List<GoodReturn> goodReturnList) {
        this.goodReturnList = goodReturnList;
    }

    public GoodSortByShop() {
    }

    public GoodSortByShop(String shopname, List<GoodReturn> goodReturnList) {
        this.shopname = shopname;
        this.goodReturnList = goodReturnList;
    }
}
