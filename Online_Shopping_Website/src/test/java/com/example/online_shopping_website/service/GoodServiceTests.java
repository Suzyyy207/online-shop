package com.example.online_shopping_website.service;

import com.example.online_shopping_website.entity.Good;
import com.example.online_shopping_website.entity.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
// 启动这个单元测试类 参数是固定的
@RunWith(SpringRunner.class)
public class GoodServiceTests {
    @Autowired
    private IGoodService goodervice;
    @Test
    public void goodsRegister(){
        Good good = goodervice.goodsRegister("好想死18","good188",130,130,"好想死18","shoptest000");
        System.out.println(good);
    }
}
