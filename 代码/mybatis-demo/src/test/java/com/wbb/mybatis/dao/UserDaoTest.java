package com.wbb.mybatis.dao;

import com.wbb.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    private UserDao userDao;
    private SqlSession sqlSession;
    @Before
    public void setUp() throws Exception {
//        SqlSessionFactory SqlSessionFactory=null;
//        String resource="mybatis-config.xml";
//        InputStream inputStream;
//        try {
//            //读取配置文件
//            inputStream=Resources.getResourceAsStream(resource);
//            //构建SQLSessionFactory
//            SqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SqlSession sqlSession=null;
//        try {
//            //打开SqlSession会话
//            sqlSession=SqlSessionFactory.openSession();
//            //提交事务
//            sqlSession.commit();
//        } catch (Exception e) {
//            //回滚事务
//            sqlSession.rollback();
//        }finally {
//            //确保资源被顺利关闭
//            if (sqlSession!=null){
//                sqlSession.close();
//            }
//        }


        //mybatis-config.xml
        String resource="mybatis-config.xml";
        //读取配置文件
        InputStream is= Resources.getResourceAsStream(resource);
        //构建SQLSessionFactory
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
        //获取sqlSession
        sqlSession=sqlSessionFactory.openSession();
//        this.userDao=new UserDaoImpl(sqlSession);
        this.userDao=sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void queryUserById() {
        System.out.println(this.userDao.queryUserById("1"));
    }

    @Test
    public void queryUserAll() {
        List<User> userList=this.userDao.queryUserAll();
        for (User user :userList) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        User user=new User();
        user.setId("3");
        user.setUsername("Tom");
        user.setPassword("123456");
        this.userDao.insertUser(user);
        this.sqlSession.commit();
    }

    @Test
    public void deleteUser() {
        this.userDao.deleteUser("4");
        this.sqlSession.commit();
    }
}