package com.example.online_shopping_website.service;

import com.example.online_shopping_website.util.JsonResult;

public interface ICartService {
    JsonResult setCartGoodsNum(String username, int goodsId, int num);

    JsonResult getValidCart(String username);

    JsonResult getInvalidCart(String username);

    JsonResult addToCart(String username, int goodsId, int num);
}
