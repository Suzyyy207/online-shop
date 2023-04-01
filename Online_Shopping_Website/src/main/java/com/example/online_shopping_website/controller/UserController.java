package com.example.online_shopping_website.controller;

import com.example.online_shopping_website.entity.Shop;
import com.example.online_shopping_website.entity.ShopInfo;
import com.example.online_shopping_website.entity.User;
import com.example.online_shopping_website.mapper.UserMapper;
import com.example.online_shopping_website.service.IUserService;
import com.example.online_shopping_website.service.ex.*;
import com.example.online_shopping_website.service.impl.UserServiceImpl;
import com.example.online_shopping_website.util.JsonResult;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static javax.security.auth.callback.ConfirmationCallback.*;

//@Controller
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/api/userRegister")
    public JsonResult<User> register(@RequestBody User user){
        JsonResult<User> registerResult = new JsonResult<User>();

        try{
            registerResult = userService.register(user);
        }catch(UsernameDuplicatedException e){
            registerResult.setState(NO);
            registerResult.setMessage("注册失败：用户名已被占用&");
        }catch(PhoneDuplicatedException e){
            registerResult.setState(NO);
            registerResult.addMessage("注册失败：手机号已被注册&");
        }catch(EmailDuplicatedException e){
            registerResult.setState(NO);
            registerResult.addMessage("注册失败：邮箱已被注册&");
        }catch(UserIdnumDuplicatedException e){
            registerResult.setState(NO);
            registerResult.addMessage("注册失败：身份证号已被注册&");
        }catch(SQLRegisterInsertException e){
            registerResult.setState(NO);
            registerResult.addMessage("注册失败：身份证号已被注册&");
        }
        return registerResult;
    }

    @RequestMapping("/api/userlogin")
    public JsonResult<User> login(@RequestParam("username") String username, @RequestParam("password") String password){
        JsonResult<User> loginResult = new JsonResult<User>();
        User user = new User(username,password);
        loginResult = userService.login(user.getUsername(), user.getPassword());
        //补充：异常处理
        return loginResult;
    }
}
