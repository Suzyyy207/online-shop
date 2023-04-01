package com.example.online_shopping_website.service.impl;

import com.example.online_shopping_website.entity.Shop;
import com.example.online_shopping_website.entity.ShopInfo;
import com.example.online_shopping_website.mapper.ShopMapper;
import com.example.online_shopping_website.entity.User;
import com.example.online_shopping_website.mapper.UserMapper;
import com.example.online_shopping_website.service.IShopService;
import com.example.online_shopping_website.service.IUserService;
import com.example.online_shopping_website.service.ex.*;
import com.example.online_shopping_website.util.JsonResult;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.online_shopping_website.service.impl.InfoVerification;


import java.sql.SQLException;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.*;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private ShopMapper shopMapper;
    private UserMapper userMapper;
    @Override
    public void open(Shop shop){
        String shopname = shop.getShopname();
        Shop nameresult = shopMapper.SearchByShopname(shopname);
        if (nameresult != null) {
            throw new ShopnameDuplicateException("尝试注册的店名[" + shopname + "]已经被占用");
        }
        Shop idresult = shopMapper.ShopSearchByIdnum(shop.getIDnum());

        if (idresult !=null){
            throw new idDuplicateException("该身份证号已经注册");
        }
        shopMapper.shopOpening(shop);
    }
    @Override
    public Shop shop_admitted(String shopname){
        Shop shop = shopMapper.SearchByShopname(shopname);
        shopMapper.updateIsAdmitted(shopname);
        return shop;
    }

    @Override
    public List<Shop> GetAllShop(){
        List<Shop> ShopList = shopMapper.ShowAllShop();
        return ShopList;
    }

    @Override
    public List<Shop> GetAllAdmittedShop(){
        List<Shop> ShopList = shopMapper.ShowAllAdmittedShop();
        return ShopList;
    }
    @Override
    public ShopInfo ShopAdmitAdmittanceCheck(String shopname){
        Shop shop = shopMapper.SearchByShopname(shopname);
        if(shop.getIs_admitted()==0) return null;
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopname(shopname);
        shopInfo.setIntroduction(shop.getIntroduction());
        shopInfo.setGoodstype(shop.getGoodstype());
        return shopInfo;
    }
    @Override
    public List<Shop> GetAllNotAdmittedShop(){
        List<Shop> ShopList = shopMapper.ShowAllNotAdmittedShop();
        return ShopList;
    }
    @Override
    public Shop GetShopInformationFromUser(String username){
        User user = userMapper.SearchByUsername(username);
        if(user ==null){
            throw new UserNotFoundException("用户名不存在！");
        }
        Shop shop = shopMapper.ShopSearchByUid(user.getUid());
        if(shop ==null){
            throw new ShopNotExistException("用户没有商店！");
        }
        return shop;
    }

}
