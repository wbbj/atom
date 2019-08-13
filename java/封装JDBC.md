### 封装JDBC
这里我把url,user,password都写死了  
JdbcUtil.java
```java
package com.bb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {

    private Connection connection = null;
    public PreparedStatement statement = null;

    public JdbcUtil(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8","root","367494");
            statement = connection.prepareStatement(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        if (this.statement != null) {
            try {
                this.statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
```
调用测试这里我是在我写的登录功能中测试,经测试没有问题
```java
String sql="SELECT count(id) FROM test_login WHERE username=? AND password=?";
            JdbcUtil jdbcUtil=new JdbcUtil(sql);
            jdbcUtil.statement.setString(1,username);
            jdbcUtil.statement.setString(2,password);
            ResultSet resultSet=jdbcUtil.statement.executeQuery();
```
