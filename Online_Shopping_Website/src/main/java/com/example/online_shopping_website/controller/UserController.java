package com.example.online_shopping_website.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.example.online_shopping_website.entity.AvatarLoader;
import com.example.online_shopping_website.entity.User;
import com.example.online_shopping_website.service.IUserService;
import com.example.online_shopping_website.service.ex.*;
import com.example.online_shopping_website.service.impl.UserServiceImpl;
import com.example.online_shopping_website.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
            registerResult.addMessage("注册失败：出现未知错误&");
        }catch(RegisterInfoInvalidException e){
            registerResult.setState(NO);
            registerResult.addMessage("注册失败：出现未知错误&");
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

    @RequestMapping("/api/getUserInfo")
    public JsonResult getUserInfo(@RequestParam String username){

        JsonResult getUserInfoResult = new JsonResult<>();
        try{
            getUserInfoResult = userService.getUserInfo(username);
        }catch(UserNotFoundException e){    //正常是不会出现的 用户找不到的情况
            getUserInfoResult.setState(NO);
            System.out.println("用户未找到");
        }catch(UsernameInvalidException e){
            getUserInfoResult.setState(NO);
            System.out.println("用户名异常");
        }
        return  getUserInfoResult;
    }

    @RequestMapping("/api/userModified")
    public JsonResult setUserInfo(@RequestParam String username,
                                  @RequestParam String newusername,
                                  @RequestParam String phone,
                                  @RequestParam String idnum,
                                  @RequestParam String email){
        JsonResult setUserInfoResult = new JsonResult<>();
        User NewUserInfo = new User(newusername,phone,email,idnum);


        try{
            setUserInfoResult = userService.setUserInfo(username, NewUserInfo);
        }catch(UsernameDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.setMessage("修改失败：用户名已被占用&");
        }catch(PhoneDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：手机号已被注册&");
        }catch(EmailDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：邮箱已被注册&");
        }catch(UserIdnumDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：身份证号已被注册&");
        }catch(SQLRegisterInsertException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：出现未知错误&");
        }catch(RegisterInfoInvalidException e){
            setUserInfoResult.setState(NO);
            System.out.println("前端传来的信息（检查过）有误，可能是恶意攻击");
        }

        return  setUserInfoResult;
    }



//    public JsonResult<User> uploadAvatar(@RequestBody AvatarLoader avatarLoader /*@RequestParam("avatar") MultipartFile avatarFile, @RequestParam("username") String username*/) throws IOException {
//            User user = new User();
//            MultipartFile avatarFile = avatarLoader.getAvatarFile();
//            String username = avatarLoader.getUsername();
//            byte[] avatarData = avatarFile.getBytes();
//            userService.UpdateAvatar(username,avatarData);
//            JsonResult result = new JsonResult<User>(OK,"用户头像上传成功");
//            user.setAvatar(avatarData);
//            return result;
//    }
//
    @PostMapping("/api/setUserAvatar")
    public JsonResult<User> uploadAvatar(@RequestParam("avatar") MultipartFile avatarFile, @RequestParam("username") String username) throws IOException {
        User user = new User();
        byte[] avatarData = avatarFile.getBytes();
        userService.UpdateAvatar(username,avatarData);
        JsonResult result = new JsonResult<User>(OK,"用户头像上传成功");
        user.setAvatar(avatarData);
        return result;
    }

}


