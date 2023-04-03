package com.example.online_shopping_website.controller;

import com.example.online_shopping_website.entity.User;
import com.example.online_shopping_website.service.IUserService;
import com.example.online_shopping_website.service.ex.*;
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
        // 不用 try catch
        JsonResult<User> registerResult = userService.register(user);
        return registerResult;
    }

    @RequestMapping("/api/userlogin")
    public JsonResult<User> login(@RequestBody User user){
        JsonResult<User> loginResult = new JsonResult<>();
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
    public JsonResult<User> getUserInfo(@RequestParam("username") String username){

        JsonResult<User> getUserInfoResult = new JsonResult<>(YES);
        try{
            getUserInfoResult = userService.getUserInfo(username);
        }catch(UserNotFoundException e){    //正常是不会出现的用户找不到的情况
            getUserInfoResult.setState(NO);
            System.out.println("用户未找到");
        }catch(UsernameInvalidException e){ //前端检查过了，正常是不会出现的用户名违法的的情况
            getUserInfoResult.setState(NO);
            System.out.println("用户名违法");
        }
        return  getUserInfoResult;
    }

    @RequestMapping("/api/setUserInfo")
    public JsonResult<User> setUserInfo(@RequestParam("username") String oldusername,
                                        @RequestParam("newusername") String newusername,
                                        @RequestParam("newusername") String newpassword,
                                        @RequestParam("phone") String newphone,
                                        @RequestParam("email") String newemail){
        JsonResult<User> setUserInfoResult = new JsonResult<>(YES);
        User NewUserInfo = new User(newusername,newpassword,newphone,newemail);

        try{
            setUserInfoResult = userService.setUserInfo(oldusername, NewUserInfo);
        }catch(UserNotFoundException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：用户未找到;");
        } catch(UsernameDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：用户名已存在;");
        }catch(PhoneDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：手机号已存在;");
        }catch(EmailDuplicatedException e){
            setUserInfoResult.setState(NO);
            setUserInfoResult.addMessage("修改失败：邮箱已被存在;");
        }catch(SQLException e){             //操作数据库时出错
            setUserInfoResult.setState(NO);
            setUserInfoResult.setMessage("修改失败：出现未知错误;");
        }catch(InfoInvalidException e){     //信息违法
            setUserInfoResult.setState(NO);
            setUserInfoResult.setMessage("修改失败：出现未知错误;");
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
//            JsonResult result = new JsonResult<User>(YES,"用户头像上传成功");
//            user.setAvatar(avatarData);
//            return result;
//    }
    @PostMapping("/api/setUserAvatar")
    public JsonResult<User> uploadAvatar(@RequestParam("avatar") MultipartFile avatarFile, @RequestParam("username") String username) throws IOException {
        User user = new User();
        byte[] avatarData = avatarFile.getBytes();
        userService.UpdateAvatar(username,avatarData);
        JsonResult<User> result = new JsonResult<>(YES, "用户头像上传成功");
        user.setAvatar(avatarData);
        return result;
    }
    public JsonResult<String> DownloadAvatar(@RequestParam("username") String username){
        String image = userService.GetAvatar(username);
        JsonResult<String> result = new JsonResult<>(YES, "用户头像返回成功");
        result.setData(image);
        return result;
    }



}


