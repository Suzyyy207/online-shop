package com.example.online_shopping_website.mapper;

import com.example.online_shopping_website.entity.Good;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodMapperTests {
    @Autowired
    private GoodMapper goodMapper;

    @Test
    public void Insert() {
        goodMapper.insertGoodByUser("好想死","good1",130,130,"好想死",0,0,0,"shoptest000");

    }
    @Test
    public void Select() {
    }
    @Test
    public void Update(){
        goodMapper.UpdateGoodsStock(4,1300);
    }
}
