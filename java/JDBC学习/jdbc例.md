```java

package com.hu.dao;

import com.hu.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    boolean add(Student s);
    boolean delete(int id);
    boolean update(Student s);
    /**
     * 根据 id  查询学生信息
     *  Map 是一个key value 的键值映射
     *
     * 将一个学生的信息进行返回
     * 数据库：
     *    id    name    age
     *     1    张三     18
     *  java类型接收 ： 一个返回值类型 返回id name age 三个数据
     *    Student s  =  new Student()；
     *    s.setId(1);
     *    s.setName(""张三);
     *    s.setAge(18);
     *
     *    Map map = new HashMap()
     *    map.put("id",1);
     *    map.put("name","张三");
     *    map.put("age",18);
     *
     *  Student   id = 1  name  age
     *  Map     id: 1  name:zhangsan  age 18
     */
    Map<String,Object> getStudent(int id);
    // 查询所有学生
    List<Map<String , Object>> getAllStudent();
}
package com.hu.dao.impl;

import com.hu.dao.StudentDao;
import com.hu.entity.Student;
import com.hu.util.DButil;

import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean add(Student s) {
        String sql = "insert into student (name,age) values(?,?)";
        return DButil.executeUpdate(sql,s.getName(),s.getAge());
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from student where id=?";
        return DButil.executeUpdate(sql,id);
    }

    @Override
    public boolean update(Student s) {
        String sql = "update student set name=? ,age=? where id=? ";
        return DButil.executeUpdate(sql,s.getName(),s.getAge(),s.getId());
    }

    @Override
    public Map<String, Object> getStudent(int id) {
        String sql = "select * from student where id = ?";
        List<Map<String, Object>> lms = DButil.executeQuery(sql,id);
        if (lms.size()>0) {
            return lms.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getAllStudent() {
        String sql = "select * from student";
        return DButil.executeQuery(sql);
    }
}
package com.hu.servlet;

import com.hu.dao.Login;
import com.hu.dao.StudentDao;
import com.hu.dao.impl.LoginImpl;
import com.hu.dao.impl.StudentDaoImpl;
import com.hu.entity.Student;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class LoginTest {
    @Test
    public void fun1(){
        Student s = new Student(3,"白骨精",26);
        StudentDao sd = new StudentDaoImpl();
//   增     boolean add = sd.add(s);
//   删     boolean delete = sd.delete(5);
//   改     boolean update = sd.update(s);
//   根据id查     Map<String, Object> student = sd.getStudent(4);
//   全部查询
        List<Map<String, Object>> allStudent = sd.getAllStudent();
        System.out.println(allStudent);
    }
package com.hu.util;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC工具类
 */
public class DButil{

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/shabi?characterEnconding=UTF-8", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 增删改的通用方法
     * @paramString sql  要执行的sql
     * @paramObject[] obj    对象类型的数组  里面存放着 sql执行的占位符参数
     *              【name，age，id】
     *                【id】
     *               【name，age】
     *         Object... 可变参数
     * */
    public static boolean executeUpdate(String sql,Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            int i = ps.executeUpdate();
            if (i>0)return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            close(conn,ps,null);
        }
        return false;
    }

    /**
     * c查询的通用方法
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String,Object>> executeQuery(String sql,Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            conn = DButil.getConnection();
            ps = conn.prepareStatement(sql);
            /* 有可能有参数 */
            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            /*执行*/
            set = ps.executeQuery();
            /*需要将所有数据都存放到 List中    每一行 用一个 map存放*/
            List<Map<String,Object>> list = new ArrayList<>();
            /*获取本次查询结果集有多少列*/
            int count = set.getMetaData().getColumnCount();

            while(set.next()){
                Map<String, Object> map = new HashMap<>();//一行数据 用一个map 接收

                for(int i=0;i<count;i++){
                    String name = set.getMetaData().getColumnLabel(i+1);
                    map.put(name,set.getObject(name));
                }
                /*将每行的map存放到 List中*/
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(conn,ps,set);
        }
        return null;
    }

    /** 关闭的通用方法
     *
     * */
    private static void close(Connection conn,PreparedStatement st,ResultSet set){
        try {
            if(set!=null){
                set.close();
            }
            if(st!=null){
                st.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
```
