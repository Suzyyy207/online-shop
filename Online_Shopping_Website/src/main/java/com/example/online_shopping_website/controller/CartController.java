package com.example.online_shopping_website.controller;

import com.example.online_shopping_website.service.ICartService;
import com.example.online_shopping_website.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static javax.security.auth.callback.ConfirmationCallback.YES;

public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("/api/setCartGoodsNum")
    public JsonResult setCartGoodsNum(@RequestBody Map<String,Object> map){
        String username = (String)map.get("username");
        int goodsId = (int)map.get("goodsId");
        int num = (int)map.get("num");
        JsonResult result = cartService.setCartGoodsNum(username, goodsId, num);
        return result;
    }

    //废用
    @RequestMapping("/api/addToCart")
    public JsonResult addToCart(@RequestBody Map<String,Object> map){
        String username = (String)map.get("username");
        int goodsId = (int)map.get("goodsId");
        int num = (int)map.get("num");

        JsonResult result = new JsonResult<>(YES);
        result = cartService.addToCart(username, goodsId, num);
        return result;
    }

    @RequestMapping("/api/getValidCart")
    JsonResult getValidCart(@RequestBody Map<String,Object> map){
        String username = (String)map.get("username");
        JsonResult result = cartService.getValidCart(username);
        return result;
    }

    @RequestMapping("/api/getInvalidCart")
    JsonResult getInvalidCart(@RequestBody Map<String,Object> map){
        String username = (String)map.get("username");
        JsonResult result = cartService.getInvalidCart(username);
        return result;
    }
}
