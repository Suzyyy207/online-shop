package com.example.online_shopping_website.controller;

import com.example.online_shopping_website.entity.Good;
import com.example.online_shopping_website.entity.Shop;
import com.example.online_shopping_website.service.IGoodService;
import com.example.online_shopping_website.service.ex.GoodnameDuplicateException;
import com.example.online_shopping_website.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    @RequestMapping("/api/setGoodsPicture")
    public JsonResult<Integer> setGoodsPicture(@RequestParam("goodsId") int goodsId, @RequestParam("file") List<MultipartFile> file) throws IOException {
        JsonResult<Integer> result = new JsonResult<>(YES);
        for (MultipartFile f : file) {
            byte[] pic = f.getBytes();
            goodService.picsUpload(goodsId,pic);
        }
        return result;
    }
    @RequestMapping("/api/getRegisteringGoodsByShopname")
    public JsonResult<List<Good>> getRegisteringGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByRegisterStatus(shopname,0);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getRegisteredGoodsByShopname")
    public JsonResult<List<Good>> getRegisteredGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByRegisterStatus(shopname,1);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getRegistrationDeniedGoodsByShopname")
    public JsonResult<List<Good>> getRegistrationDeniedGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByRegisterStatus(shopname,2);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getEditingGoodsByShopname")
    public JsonResult<List<Good>> getEditingGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByModifyStatus(shopname,1);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getEditedGoodsByShopname")
    public JsonResult<List<Good>> getEditedGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByModifyStatus(shopname,3);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getEditDeniedGoodsByShopname")
    public JsonResult<List<Good>> getEditDeniedGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByModifyStatus(shopname,2);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getInvalidGoodsByShopname")
    public JsonResult<List<Good>> getInvalidGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByStatus(shopname,3);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }
    @RequestMapping("/api/getValidGoodsByShopname")
    public JsonResult<List<Good>> getValidGoodsByShopname(@RequestBody Shop shop){
        JsonResult<List<Good>> result = new JsonResult<>();
        String shopname = shop.getShopname();
        List<Good> goodList= goodService.searchByStatus(shopname,1);
        if (goodList == null){
            result.setState(NO);
        }else {
            result.setState(YES);
            result.setData(goodList);
        }
        return result;
    }

}
