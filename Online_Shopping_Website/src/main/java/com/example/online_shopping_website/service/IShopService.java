package com.example.online_shopping_website.service;
import com.example.online_shopping_website.entity.Shop;
import com.example.online_shopping_website.entity.ShopInfo;
import com.example.online_shopping_website.util.JsonResult;

import java.util.List;

public interface IShopService {
    void open(Shop shop);
    Shop shop_admitted(String shopname);
    List<Shop> GetAllShop();
    List<Shop> GetAllAdmittedShop();
    ShopInfo ShopAdmitAdmittanceCheck(String shopname);
    List<Shop> GetAllNotAdmittedShop();
    Shop GetShopInformationFromUser(String username);
}
