package com.example.online_shopping_website.service.impl;

import com.example.online_shopping_website.mapper.UserMapper;
import com.example.online_shopping_website.entity.User;
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


/**用户模块业务层的实现类*/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public JsonResult<User> register(User user){

        JsonResult<User> registerResult = new JsonResult<User>(OK,"", null);

        //对注册信息的双重检查
        if(!InfoVerification.checkAllInfoValid(user)){
            throw new RegisterInfoInvalidException("前端传来的信息（检查过）有误，可能是恶意攻击");
        }

        if( userMapper.SearchByUsername(user.getUsername()) != null){
            throw new UsernameDuplicatedException("注册失败：用户名已被占用");
        }
        if( userMapper.SearchByPhone(user.getPhone()) != null){
            throw new PhoneDuplicatedException("注册失败：手机号已被注册");
        }
        if( userMapper.SearchByEmail(user.getEmail()) != null){
            throw new EmailDuplicatedException("注册失败：邮箱已被注册");
        }
        if(userMapper.SearchByIdnum(user.getIdnum()) != null){
            throw new UserIdnumDuplicatedException("注册失败：身份证号已被注册\n");
        }

        if( registerResult.getState() == OK ){
            int row = userMapper.Register(user);
            registerResult.setMessage("注册成功");
            if (row != 1){
                throw new SQLRegisterInsertException("注册时出现未知错误");
            }
        }

        return registerResult;
    }

    @Override
    public User SearchByUsername(String username){
        User user = userMapper.SearchByUsername(username);
        return user;
    }

    @Override
    public JsonResult<User> login(String username, String password) {

        int state =OK;
        String message = "登录成功！";
        User data = null;

        User result = userMapper.SearchByUsername(username);
        if(result == null){
            throw new UserNotFoundException("登录失败：用户名不存在！");
        }else{
            String oldPassword = result.getPassword();
            if(!oldPassword.equals(password)){
                throw new PasswordNotMatchException("登录失败：密码错误！");
            }else{
                data = userMapper.SearchByUsername(username);
            }
        }

        JsonResult<User> loginResult = new JsonResult<>(state,message,data);
        return loginResult;
    }
}
