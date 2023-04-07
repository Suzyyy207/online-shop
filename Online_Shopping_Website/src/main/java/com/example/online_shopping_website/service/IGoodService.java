package com.example.online_shopping_website.service;

import com.example.online_shopping_website.entity.Good;

public interface IGoodService {
    Good goodsRegister(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,String shopname);
    void setGoodsInformation(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,String shopname,int goodsId);
}
