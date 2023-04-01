package com.example.online_shopping_website.service;

import com.example.online_shopping_website.entity.User;
import com.example.online_shopping_website.util.JsonResult;

public interface IUserService {
    /**
     * 用户注册功能
     * @param user 用户的信息，封装在类中
     * @return 错误信息
     */
    JsonResult<User> register(User user);

    /**
     * 用户登陆功能
     * @param username 用户名
     * @param password 用户密码
     * @return 返回用户数据
     */
    JsonResult<User> login(String username, String password);

    /**
     * 根据用户名查找用户
     * @param username
     * @return 成功返回用户信息，失败返回空
     */
    User SearchByUsername(String username);
    void UpdateAvatar(String username,byte[] avatar);
}
