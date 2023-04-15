package com.example.online_shopping_website.service.impl;

import com.example.online_shopping_website.mapper.CartMapper;
import com.example.online_shopping_website.mapper.GoodMapper;
import com.example.online_shopping_website.mapper.ShopMapper;
import com.example.online_shopping_website.service.ICartService;
import com.example.online_shopping_website.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.NO;
import static javax.security.auth.callback.ConfirmationCallback.YES;

public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public JsonResult setCartGoodsNum(String username, int goodsId, int num){
        JsonResult result =new JsonResult<>(YES);
        int goodsStock = goodMapper.GetGoodsStockByGoodsId(goodsId);
        if(num > goodsStock)    //异常1：添加到购物车的数量大于库存数量
            result.setState(NO);
        else{
            if(cartMapper.IsGoodsInCart(username,goodsId))  //异常2：用户名下的购物车已经有相应的商品了
                cartMapper.updateCartGoodsNum(username, goodsId, num);  //购物车已有，更新数据库
            else
                cartMapper.insertCartGoodsNum(username, goodsId, num);  //购物车没有，插入数据库
        }
        return result;
    }

    @Override
    public JsonResult getValidCart(String username){
        JsonResult result = new JsonResult<>(YES);

        List<Integer> allGoodsId = cartMapper.GetAllGoodsIdInCartByUsername(username);
        for(Integer id : allGoodsId){
            String shopname = goodMapper.GetShopnamByGoodsId(id);
            int shopStaus = shopMapper.GetShopStatusByShopname(shopname);
        }

        return result;
    }

    @Override
    public JsonResult getInvalidCart(String username){
        JsonResult result = new JsonResult<>(YES);


        return result;
    }

    @Override
    public JsonResult addToCart(String username, int goodsId, int addNum){
        JsonResult result = new JsonResult<>(YES);

        int originalNum;
        Integer oNum = cartMapper.getGoodsNumberInCart(username, goodsId);
        if(oNum == null)
            originalNum = 0;
        else
            originalNum = oNum;
        //取出购物车中该商品的数量。判断
        if(originalNum == 0 && addNum > 0){   //购物车中没有相应商品，插入
            int newNum = addNum;
            cartMapper.InsertNewGoodsIntoCart(username, goodsId, newNum);
        }else if ( addNum > 0 || (addNum < 0 && (originalNum + addNum) >= 0)){   //增加购物车中商品数量，或者减少的数量小于等于购物车中已有的数量
            int newNum = originalNum + addNum;
            cartMapper.UpdateGoodsNumInCart(username, goodsId, newNum);
        }else if( (originalNum + addNum) < 0 || addNum == 0){  //商品减少的数量大于购物车中原有的数量
            result.setState(NO);
        }
        //移除购物车中数量为0的商品
        cartMapper.DeleteZeroGoodsInCart(username);
        return result;
    }

}
