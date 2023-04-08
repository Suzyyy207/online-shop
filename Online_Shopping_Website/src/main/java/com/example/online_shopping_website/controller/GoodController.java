package com.example.online_shopping_website.controller;

import com.example.online_shopping_website.entity.Good;
import com.example.online_shopping_website.entity.Shop;
import com.example.online_shopping_website.service.IGoodService;
import com.example.online_shopping_website.service.IUserService;
import com.example.online_shopping_website.service.ex.GoodnameDuplicateException;
import com.example.online_shopping_website.service.ex.ShopnameDuplicateException;
import com.example.online_shopping_website.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static javax.security.auth.callback.ConfirmationCallback.NO;
import static javax.security.auth.callback.ConfirmationCallback.YES;

@RestController
public class GoodController {
    @Autowired
    private IGoodService goodService;

    @RequestMapping("/api/goodsRegister")
    public JsonResult<Integer> goodsRegister(@RequestBody Good good) {
        JsonResult<Integer> result = new JsonResult<>(YES);
        try {
            Good goodResult = goodService.goodsRegister(good.getIntroduction(),good.getGoodsname(),good.getGoodsPrice(),good.getGoodsStock(),good.getGoodsCategory(),good.getShopname());
            result.setData(goodResult.getGoodsId());
        }catch (GoodnameDuplicateException e) { // 店名被占用
            result.setState(NO);
            result.setMessage("商品名已经被占用");
            result.setData(null);
        }
        return result;
    }
    @RequestMapping("/api/setGoodsInfo")
    public JsonResult<Integer> setGoodsInfo(@RequestBody Good good) {
        JsonResult<Integer> result = new JsonResult<>(YES);
        try {
            goodService.setGoodsInformation(good.getIntroduction(),good.getGoodsname(),good.getGoodsPrice(),good.getGoodsStock(),good.getGoodsCategory(),good.getShopname(),good.getGoodsId());
        }catch(GoodnameDuplicateException e){
            result.setState(NO);
            result.setMessage("商品名已经被占用");
            result.setData(null);
        }
        return result;
    }
}
