package com.example.online_shopping_website.service.impl;

import com.example.online_shopping_website.entity.Good;
import com.example.online_shopping_website.entity.Shop;
import com.example.online_shopping_website.mapper.GoodMapper;
import com.example.online_shopping_website.mapper.ShopMapper;
import com.example.online_shopping_website.service.IGoodService;
import com.example.online_shopping_website.service.ex.GoodnameDuplicateException;
import com.example.online_shopping_website.service.ex.ShopnameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements IGoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Override
    public Good goodsRegister(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,String shopname){
        Good good = goodMapper.SearchByGoodname(goodsname);

        if (good != null) {
            throw new GoodnameDuplicateException("尝试注册的商品名[" + goodsname + "]已经被占用");
        }
        goodMapper.insertGoodByUser(introduction,goodsname,goodsPrice,goodsStock,goodsCategory,0,0,0,shopname);
        Good good1 = goodMapper.SearchByGoodname(goodsname);
        return good1;
    }
    @Override
    public void setGoodsInformation(String introduction,String goodsname,float goodsPrice,int goodsStock,String goodsCategory,String shopname,int goodsId){
        if(goodsname!=null) {
            Good good = goodMapper.SearchByGoodname(goodsname);
            if (good != null){
                throw new GoodnameDuplicateException("尝试注册的商品名[" + goodsname + "]已经被占用");
            }else {
                goodMapper.UpdateGoodsname(goodsId,goodsname);
            }
        }
        if(introduction!=null){
            goodMapper.UpdateIntroduction(goodsId,goodsname);
        }
        if(goodsPrice!=0){
            goodMapper.UpdateGoodsPrice(goodsId,goodsPrice);
        }
        if(goodsCategory!=null){
            goodMapper.UpdateGoodsCategory(goodsId,goodsCategory);
        }
        if(goodsStock!=0){
            goodMapper.UpdateGoodsStock(goodsId,goodsStock);
        }
    }
    @Override
    public void picsUpload(int goodsId,byte[] pic){
        goodMapper.picInsert(goodsId,pic);
    }
    @Override
    public List<Good> searchByStatus(String shopname, int status){
        List<Good> goodslist = goodMapper.SearchByStatus(shopname,status);
        return goodslist;
    }
    @Override
    public List<Good> searchByRegisterStatus(String shopname, int registerStatus){
        List<Good> goodslist = goodMapper.SearchByStatus(shopname,registerStatus);
        return goodslist;
    }
    @Override
    public List<Good> searchByModifyStatus(String shopname, int modifyStatus){
        List<Good> goodslist = goodMapper.SearchByStatus(shopname,modifyStatus);
        return goodslist;
    }
}
