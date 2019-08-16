package com.wbb.mybatis.dao.impl;

import com.wbb.mybatis.dao.UserDao;
import com.wbb.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    @Override
    public User queryUserById(String id) {
        return this.sqlSession.selectOne("UserDao.queryUserById",id);
    }

    @Override
    public List<User> queryUserAll() {
       return this.sqlSession.selectList("UserDao.queryUserAll");
    }

    @Override
    public void insertUser(User user) {
        this.sqlSession.insert("UserDao.insertUser",user);
    }
    @Override
    public void deleteUser(String id) {
        this.sqlSession.delete("UserDao.deleteUser",id);
    }
}
