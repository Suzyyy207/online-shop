package com.example.online_shopping_website.service;

import com.example.online_shopping_website.entity.Good;

import java.util.List;

public interface IGoodService {
    Good goodsRegister(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,String shopname);
    void setGoodsInformation(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,String shopname,int goodsId);
    void picsUpload(int goodsId,byte[] pic);
    List<Good> searchByStatus(String shopname,int status);
    List<Good> searchByRegisterStatus(String shopname,int registerStatus);
    List<Good> searchByModifyStatus(String shopname,int modifyStatus);

}
