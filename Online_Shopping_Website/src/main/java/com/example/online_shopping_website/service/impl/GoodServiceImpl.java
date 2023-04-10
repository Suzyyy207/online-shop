package com.example.online_shopping_website.service.impl;

import com.example.online_shopping_website.entity.*;
import com.example.online_shopping_website.mapper.GoodMapper;
import com.example.online_shopping_website.mapper.ShopMapper;
import com.example.online_shopping_website.service.IGoodService;
import com.example.online_shopping_website.service.ex.GoodnameDuplicateException;
import com.example.online_shopping_website.service.ex.ShopnameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
        Good good = goodMapper.SearchByGoodname(goodsname);
        if (good != null) {
            throw new GoodnameDuplicateException("尝试注册的商品名[" + goodsname + "]已经被占用");
        }
        goodMapper.insertGoodByUser(introduction,goodsname,goodsPrice,goodsStock,goodsCategory, -goodsId,-goodsId,-goodsId,shopname);
        goodMapper.UpdateStatus(goodsId,2);
        goodMapper.UpdateModifyStatus(goodsId,1);
    }
    @Override
    public void picsUpload(int goodsId,byte[] pic){
        goodMapper.picInsert(goodsId,pic);
    }
    @Override
    public List<GoodReturn> searchByStatus(String shopname, int status){
        List<Good> goodslist = goodMapper.SearchByStatus(shopname,status);
        List<GoodReturn> goodReturnList = new ArrayList<>();
        for(Good good : goodslist){
            GoodReturn goodReturn = new GoodReturn();
            goodReturn.setGoodsPrice(good.getGoodsPrice());
            goodReturn.setGoodsStock(good.getGoodsStock());
            goodReturn.setGoodsId(good.getGoodsId());
            goodReturn.setGoodsname(good.getGoodsname());
            goodReturn.setIntroduction(good.getIntroduction());
            goodReturn.setShopname(good.getShopname());
            goodReturn.setStatus(good.getStatus());
            goodReturn.setRegisterStatus(good.getRegisterStatus());
            goodReturn.setModifyStatus(good.getModifyStatus());
            goodReturn.setGoodsCategory(Arrays.asList(good.getGoodsCategory().split(";")));
            List<String> piclist = new ArrayList<>();
            for(pic pics : goodMapper.searchPicByGoodsId(good.getGoodsId())){
                byte[] imageData = pics.getPic();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                piclist.add(base64Image);
            }
            goodReturn.setGoodsAvatar(piclist);
            goodReturnList.add(goodReturn);
        }
        return goodReturnList;
    }
    @Override
    public List<GoodReturn> searchByRegisterStatus(String shopname, int registerStatus){
        List<Good> goodslist = goodMapper.SearchByRegisterStatus(shopname,registerStatus);

        List<GoodReturn> goodReturnList = new ArrayList<>();
        for(Good good : goodslist){
            GoodReturn goodReturn = new GoodReturn();
            goodReturn.setGoodsPrice(good.getGoodsPrice());
            goodReturn.setGoodsStock(good.getGoodsStock());
            goodReturn.setGoodsId(good.getGoodsId());
            goodReturn.setGoodsname(good.getGoodsname());
            goodReturn.setIntroduction(good.getIntroduction());
            goodReturn.setShopname(good.getShopname());
            goodReturn.setStatus(good.getStatus());
            goodReturn.setRegisterStatus(good.getRegisterStatus());
            goodReturn.setModifyStatus(good.getModifyStatus());
            goodReturn.setGoodsCategory(Arrays.asList(good.getGoodsCategory().split(";")));
            List<String> piclist = new ArrayList<>();
            for(pic pics : goodMapper.searchPicByGoodsId(good.getGoodsId())){
                byte[] imageData = pics.getPic();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                piclist.add(base64Image);
            }
            goodReturn.setGoodsAvatar(piclist);
            goodReturnList.add(goodReturn);
        }
        return goodReturnList;
    }
    @Override
    public List<GoodReturn> searchByModifyStatus(String shopname, int modifyStatus){
        List<Good> goodslist = goodMapper.SearchByModifyStatus(shopname,modifyStatus);
        List<GoodReturn> goodReturnList = new ArrayList<>();
        for(Good good : goodslist){
            GoodReturn goodReturn = new GoodReturn();
            goodReturn.setGoodsPrice(good.getGoodsPrice());
            goodReturn.setGoodsStock(good.getGoodsStock());
            goodReturn.setGoodsId(good.getGoodsId());
            goodReturn.setGoodsname(good.getGoodsname());
            goodReturn.setIntroduction(good.getIntroduction());
            goodReturn.setShopname(good.getShopname());
            goodReturn.setStatus(good.getStatus());
            goodReturn.setRegisterStatus(good.getRegisterStatus());
            goodReturn.setModifyStatus(good.getModifyStatus());
            goodReturn.setGoodsCategory(Arrays.asList(good.getGoodsCategory().split(";")));
            List<String> piclist = new ArrayList<>();
            for(pic pics : goodMapper.searchPicByGoodsId(good.getGoodsId())){
                byte[] imageData = pics.getPic();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                piclist.add(base64Image);
            }
            goodReturn.setGoodsAvatar(piclist);
            goodReturnList.add(goodReturn);
        }
        return goodReturnList;
    }
    @Override
    public List<pic> picsSearchByGoodsId(int goodsId){
        List<pic> piclist = goodMapper.searchPicByGoodsId(goodsId);
        return piclist;
    }
    @Override
    public List<Good> getApprovingGoods(){
        List<Good> goodListA = goodMapper.SearchByRegisterStatusRegisterStatusOnly(0);
        List<Good> goodListB = goodMapper.SearchByModifyStatusModifyStatusOnly(1);
        List<Good> goodList = new ArrayList<>();
        goodList.addAll(goodListA);
        goodList.addAll(goodListB);
        return goodList;
    }
    @Override
    public int goodsOffShelve(int goodsId){
        Good good = goodMapper.SearchByGoodsId(goodsId);
        if(good.getStatus()==1) {
            goodMapper.UpdateStatus(goodsId,3);
            return 0;
        }
        else return 1;
    }
    @Override
    public int goodsOnShelve(int goodsId){
        Good good = goodMapper.SearchByGoodsId(goodsId);
        if(good.getStatus()==3) {
            goodMapper.UpdateStatus(goodsId,1);
            return 0;
        }
        else return 1;
    }
    @Override
    public int goodsApplicationApproved(int goodsId,int status){
        if(status == 0 ){
            goodMapper.UpdateStatus(goodsId,1);
            goodMapper.UpdateRegisterStatus(goodsId,1);
            return 0;
        }
        if(status == 2 ){
            List<Good> goodList =goodMapper.SearchByStatusStatusOnly(-goodsId);
            Good good = goodList.get(0);
            goodMapper.setDelete(-goodsId);
            goodMapper.UpdateIntroduction(goodsId,good.getIntroduction());
            goodMapper.UpdateGoodsStock(goodsId,good.getGoodsStock());
            goodMapper.UpdateGoodsname(goodsId,good.getGoodsname());
            goodMapper.UpdateGoodsCategory(goodsId,good.getGoodsCategory());
            goodMapper.UpdateGoodsPrice(goodsId,good.getGoodsPrice());
            goodMapper.UpdateStatus(goodsId,1);
            goodMapper.UpdateRegisterStatus(goodsId,3);
            return 0;
        }else {
            return 1;
        }
    }
    @Override
    public int goodsApplicationRejected(int goodsId,int status){
        if(status == 0 ){
            goodMapper.UpdateRegisterStatus(goodsId,2);
            return 0;
        }
        if(status == 2 ){
            goodMapper.UpdateModifyStatus(goodsId,2);
            /*
             * TODO*/

            return 0;
        }else {
            return 1;
        }
    }
    @Override
    public int cancelApplication(int goodsId){
        Good good = goodMapper.SearchByGoodsId(goodsId);
        if(good.getStatus() == 0 ){
            goodMapper.UpdateStatus(goodsId,4);

            return 0;
        }
        if(good.getStatus() == 2 ){
            goodMapper.UpdateStatus(goodsId,1);
            goodMapper.UpdateModifyStatus(goodsId,0);
            return 0;
        }else {
            return 1;
        }
    }
    @Override
    public GoodReturn getEditingGoodsInfo(int goodsId){
        Good good = goodMapper.SearchByGoodsId(goodsId);
        GoodReturn goodReturn = new GoodReturn();
        if(good.getStatus() == 0){
            goodReturn.setGoodsPrice(good.getGoodsPrice());
            goodReturn.setGoodsStock(good.getGoodsStock());
            goodReturn.setGoodsId(good.getGoodsId());
            goodReturn.setGoodsname(good.getGoodsname());
            goodReturn.setIntroduction(good.getIntroduction());
            goodReturn.setShopname(good.getShopname());
            goodReturn.setStatus(good.getStatus());
            goodReturn.setRegisterStatus(good.getRegisterStatus());
            goodReturn.setModifyStatus(good.getModifyStatus());
            goodReturn.setGoodsCategory(Arrays.asList(good.getGoodsCategory().split(";")));
            List<String> piclist = new ArrayList<>();
            for(pic pics : goodMapper.searchPicByGoodsId(good.getGoodsId())){
                byte[] imageData = pics.getPic();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                piclist.add(base64Image);
            }
            goodReturn.setGoodsAvatar(piclist);
        }else if(good.getStatus() == 2){
            goodReturn.setGoodsPrice(good.getGoodsPrice());
            goodReturn.setGoodsStock(good.getGoodsStock());
            goodReturn.setGoodsId(good.getGoodsId());
            goodReturn.setGoodsname(good.getGoodsname());
            goodReturn.setIntroduction(good.getIntroduction());
            goodReturn.setShopname(good.getShopname());
            goodReturn.setStatus(good.getStatus());
            goodReturn.setRegisterStatus(good.getRegisterStatus());
            goodReturn.setModifyStatus(good.getModifyStatus());
            goodReturn.setGoodsCategory(Arrays.asList(good.getGoodsCategory().split(";")));
            List<String> piclist = new ArrayList<>();
            for(pic pics : goodMapper.searchPicByGoodsId(good.getGoodsId())){
                byte[] imageData = pics.getPic();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                piclist.add(base64Image);
            }
            goodReturn.setGoodsAvatar(piclist);
        }
        return goodReturn;
    }



}
