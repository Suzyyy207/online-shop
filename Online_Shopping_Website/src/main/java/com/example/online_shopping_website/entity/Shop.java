package com.example.online_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Shop {
    int shopid;
    int uid;
    private String shopname;
    private String goodstype;
    private List<String> goodstypeList;
    private String IDnum;
    private String introduction;
    private String address;
    private String capital;
    private String date;
    private int is_admitted;
    public String getShopname() {
        return shopname;
    }
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    public int getIsAdmitted() {
        return is_admitted;
    }
    public void setIsAdmitted(int is_admitted) {
        this.is_admitted = is_admitted;
    }

    public Shop(String shopname) {
        this.shopname = shopname;
    }


//    public Shop( int uid,String shopname,String goodstype, String IDnum, String introduction, String address, String capital, Date date,int is_admitted) {
//        this.uid = uid;
//        this.shopname = shopname;
//        this.goodstype = goodstype;
//        this.goodstypeList =  Arrays.asList(goodstype.split(";"));
//        this.IDnum = IDnum;
//        this.introduction = introduction;
//        this.address = address;
//        this.capital = capital;
//        this.date = date;
//        this.is_admitted = 0;
//    }
    public Shop(String shopname,String goodstype,String idnum,String introduction,String address,String capital,String date,int Uid){
        this.uid = Uid;
        this.shopname = shopname;
        this.goodstype = goodstype;
        this.IDnum = idnum;
        this.introduction = introduction;
        this.address = address;
        this.capital = capital;
        this.date = date;
        this.is_admitted = 0;
    }
    public Shop(String shopname,int Uid){
        this.uid = Uid;
        this.shopname = shopname;
    }

    public Shop(int shopid, int uid, String shopname, String goodstype, List<String> goodstypeList, String IDnum, String introduction, String address, String capital, String date, int is_admitted) {
        this.shopid = shopid;
        this.uid = uid;
        this.shopname = shopname;
        this.goodstype = goodstype;
        this.goodstypeList =  Arrays.asList(goodstype.split(";"));
        this.IDnum = IDnum;
        this.introduction = introduction;
        this.address = address;
        this.capital = capital;
        this.date = date;
        this.is_admitted = is_admitted;
    }

    public Shop(String shopname, String goodstype, String IDnum, String introduction, String address, String capital, int uid) {
        this.uid = uid;
        this.shopname = shopname;
        this.goodstype = goodstype;
        //this.goodstypeList = goodstypeList;
        this.IDnum = IDnum;
        this.introduction = introduction;
        this.address = address;
        this.capital = capital;
        //this.is_admitted = is_admitted;
    }

    public Shop(int shopid, int uid, String shopname, String goodstype, String IDnum, String introduction, String address, String capital, String date, int is_admitted) {
        this.shopid = shopid;
        this.uid = uid;
        this.shopname = shopname;
        this.goodstype = goodstype;
        this.goodstypeList =  Arrays.asList(goodstype.split(";"));
        this.IDnum = IDnum;
        this.introduction = introduction;
        this.address = address;
        this.capital = capital;
        this.date = date;
        this.is_admitted = is_admitted;
    }

    public Shop(String shopname, String goodstype) {
        this.shopname = shopname;
        this.goodstype = goodstype;
    }

}
