package com.example.online_shopping_website.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.example.online_shopping_website.entity.User;
import com.example.online_shopping_website.service.IUserService;
import com.example.online_shopping_website.service.ex.*;
import com.example.online_shopping_website.service.impl.UserServiceImpl;
import com.example.online_shopping_website.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        }catch(RegisterInfoInvalidException e){
            registerResult.setState(NO);
            System.out.println("前端传来的信息（检查过）有误，可能是恶意攻击");
        }
        return registerResult;
    }

    @RequestMapping("/api/userlogin")
    public JsonResult<User> login(@RequestParam("username") String username, @RequestParam("password") String password){
        JsonResult<User> loginResult = new JsonResult<User>();
        User user = new User(username,password);

        try{
            loginResult = userService.login(user.getUsername(), user.getPassword());
        }catch(UserNotFoundException e){
            loginResult.setState(NO);
            loginResult.setMessage("登录失败：用户名不存在！");
        }catch(PasswordNotMatchException e){
            loginResult.setState(NO);
            loginResult.setMessage("登录失败：密码错误！");
        }

        return loginResult;
    }

    @RequestMapping("/api/setUserAvatar")
    public JsonResult setUserAvatar(){
        JsonResult result = new JsonResult<>(OK,"用户头像上传成功");



        return  result;
    }
}
