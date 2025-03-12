package com.xja.blogsystem.mapper;

import com.xja.blogsystem.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据账号和密码查询用户信息，对应登录操作
     * @param account 账号
     * @param password 密码
     * @return 用户对象
     */
    User queryUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 根据账号进行等值查询
     * @param account 账号
     * @return 用户对象
     */
    User queryUserByAccount(@Param("account") String account);

    /**
     * 添加用户信息
     * @param user 用户对象
     * @return 受影响的行数
     */
    int insertUser(User user);

    /**
     * 根据id查询用户信息
     * @param userId 用户编号
     * @return 用户对象
     */
    User queryUserById(@Param("userId") Integer userId);

    /**
     * 修改用户操作
     * @param user 用户对象
     * @return 受影响的行数
     */
    int updateUserInfo(User user);
}