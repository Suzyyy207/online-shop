package com.example.online_shopping_website.mapper;

import com.example.online_shopping_website.entity.User;

import java.util.List;

//@Mapper
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user
     * @return 受影响的行数
     */
    Integer Insert(User user);

    /**
     * 注册用户
     *
     * @param user
     * @return 插入信息时，影响的行数
     */
    Integer Register(User user);

    /**
     * 根据 用户名 查询用户
     *
     * @param Username
     * @return 找到时返回用户数据；没有找到时返回null
     */
    User SearchByUsername(String Username);
    /**
     * 根据 电话号码 查询用户
     *
     * @param phone
     * @return 找到时返回用户数据；没有找到时返回null
     */
    User SearchByPhone(String phone);

    /**
     * 根据email查询用户
     *
     * @param email
     * @return 找到时返回用户数据；没有找到时返回null
     */
    User SearchByEmail(String email);

    /**
     * 根据 身份证号 查询用户
     *
     * @param idnum
     * @return 找到时返回用户数据；没有找到时返回null
     */
    User SearchByIdnum(String idnum);

    /**
     * 用户上传头像
     *
     * @param username avatar
     * @return 找到时返回用户数据；没有找到时返回null
     */
    void AvatarUpdate(String username,byte[] avatar);

}

