package com.example.online_shopping_website.mapper;

import com.example.online_shopping_website.entity.Good;

import java.util.List;

public interface GoodMapper {
    Integer insertGoodByUser(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,int status,int registerStatus,int modifyStatus,String shopname);

    Good SearchByGoodname(String goodsname);
    void UpdateGoodsStock(int goodsId,int goodsStock);
    void UpdateGoodsPrice(int goodsId,float goodsPrice);
    void UpdateGoodsname(int goodsId,String goodsname);
    void UpdateIntroduction(int goodsId,String introduction);
    void UpdateGoodsCategory(int goodsId,String goodsCategory);
    void UpdateStatus(int goodsId,int status);
    void UpdateRegisterStatus(int goodsId,int registerStatus);
    void UpdateModifyStatus(int goodsId,int modifyStatus);
    Integer picInsert(int goodsId,byte[] pic);
    List<Good> SearchByStatus(String shopname, int status);
    List<Good> SearchByRegisterStatus(String shopname,int registerStatus);
    List<Good> SearchByModifyStatus(String shopname,int modifyStatus);
}
