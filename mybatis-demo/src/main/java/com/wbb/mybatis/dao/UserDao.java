package com.wbb.mybatis.dao;

import com.wbb.mybatis.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据id查询用户信息信息
     *
     * @param id
     * @return
     */
    public User queryUserById(String id);

    /**
     * 查询所有用户信息
     *
     * @return
     */

    public List<User> queryUserAll();

    /**
     * 新增用户
     *
     * @param user
     */
    public void insertUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    public void deleteUser(String id);
}
