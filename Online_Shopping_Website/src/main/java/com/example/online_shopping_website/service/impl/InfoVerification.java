package com.example.online_shopping_website.service.impl;

import com.example.online_shopping_website.entity.User;

import java.util.regex.*;
public class InfoVerification {

    public static Boolean checkAllInfoValid(User user){
        Boolean result = Boolean.TRUE;
        if(!InfoVerification.isUsernameValid(user.getUsername())
                ||!InfoVerification.isPhoneValid(user.getPhone())
                ||!InfoVerification.isIdnumValid(user.getIdnum())
                ||!InfoVerification.isEmailValid(user.getEmail())
          ) result = Boolean.FALSE;

        return result;
    }

    public static Boolean isUsernameValid(String username){
        Boolean result = Boolean.FALSE;
        if(username.matches("^[a-zA-Z0-9_]{3,10}$")){
            result = Boolean.TRUE;
        }
        return result;
    }

    /*只检查了手机号是否为数字，因为格式我还不清楚
    * */
    public static Boolean isPhoneValid(String phone){
        Boolean result = Boolean.FALSE;
        if(phone.matches("^[0-9]$")){
            result = Boolean.TRUE;
        }
        return result;
    }


    public static Boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //暂时不使用，以免测试太复杂
    public static Boolean isIdnumValid(String idnum) {
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}(19|20)\\d{2}(0\\d|1[0-2])([0-2]\\d|3[0-1])\\d{3}[\\dX]$");
        Matcher matcher = pattern.matcher(idnum);
        return matcher.matches();
    }



}
