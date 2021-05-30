package com.wxl.sms.dao;

import com.wxl.sms.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于系统登录的Mapper
 *
 * @author wxl on 2021/5/8 22:10
 */
public interface UserMapper {

    /**
     * 用于登录验证, 根据输入的用户名去数据库查询返回该用户用于对比
     *
     * @param username username参数
     * @return User实体类
     */
    User getUserByUsername(String username);

    /**
     * 获取系统所有用户, 用于页面显示
     *
     * @return List集合
     */
    List<User> getAllUser();

    /**
     * 添加用户
     *
     * @param user SpringMVC自动封装表单对象
     * @return 影响的行数
     */
    int addUser(User user);

    /**
     * 重置所有用户的密码
     *
     * @return 影响的行数
     */
    int resetAllPassword();

    /**
     * 按照用户id重置某一用户密码
     *
     * @param id 用户id
     * @return 影响的行数
     */
    int resetPasswordByUserId(Integer id);

    /**
     * 按照用户id删除某一用户
     *
     * @param id 用户id
     * @return 影响的行数
     */
    int deleteUserByUserId(Integer id);

    /**
     * 修改密码
     *
     * @param id 用户id
     * @param password 新密码
     * @return 影响的行数
     */
    int updatePassword(@Param("id") Integer id, @Param("password") String password);

    /**
     * 获取当前系统中所有的管理员账户信息
     *
     * @return List
     */
    List<User> getAllAdmin();

    /**
     * 根据用户id获取用户
     *
     * @return user
     */
    User getUserByUserId(Integer id);
}
