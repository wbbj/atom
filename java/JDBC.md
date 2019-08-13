### 连接数据库及增删改查操作  

* 首先声明连接Connection，预处理PreparedStatement以及结果ResultSet对象  

* 然后设置url地址，用户user，密码password，其中url我这边一直出现一个错误所以加上了`serverTimezone=UTC`  

* 然后建立Connection方法加载数据库驱动获取连接数据库对象
其中使用了`Class.forName("com.mysql.cj.jdbc.Driver")`加载驱动
`DriverManager.getConnection(url,user,password);`连接数据库
然后设置主方法连接数据库，主方法中还包含了数据库的增删改查的调用

###### 增删改查：  
* 查看操作：  
```java
 public static void queryData() {
      try {
          pStmt=con.prepareStatement("select * from student");
          res = pStmt.executeQuery();
          while (res.next()) {
              int id = res.getInt("id");
              String name = res.getString("name");
              String phone = res.getString("bianhao");
              System.out.println("id：" + id + "  " + "姓名：" + name + "  " + "电话：" + phone);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  ```
* 增加操作：  
```java
public static void addData() {
    try {
        pStmt = con.prepareStatement("insert into student (name,bianhao) values(?,?)");
        pStmt.setString(1, "益达");
        pStmt.setString(2, "0000000");
        pStmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```
* 更新操作：  
```java
public static void updateData() {
    try {
        pStmt = con.prepareStatement("update student set name = ? where id = 6");
        pStmt.setString(1, "王八");
        pStmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }package sql;

public class HandleSql {
}

}
```
* 删除操作：  
```java
public static void deleteData() {
    try {
        Statement stmt = con.createStatement();//创建Statement对象
        stmt.executeUpdate("delete from student where id=4");
        stmt.executeUpdate("delete from student where id=7");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```
* 其中数据库要连接自己的数据库并建表，如果连接出现错误就用命令行连接并创建数据库
---
具体代码：  
handlesql.java:

```java
package sql;

import java.sql.*;

public class HandleSql { //创建 HandleSql 类

    static Connection con; //声明 Connection 对象
    static PreparedStatement pStmt;//声明预处理 PreparedStatement 对象
    static ResultSet res;//声明结果 ResultSet 对象

    static String url = "jdbc:mysql://localhost:3306/student?serverTimezone=UTC";
    static String user = "root";
    static String password = "367494";

    public Connection getConnection() {//建立返回值为 Connection 的方法

        //加载数据库驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //通过访问数据库的URL获取数据库连接对象
        try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
    public static void main(String[] args) {//主方法
        HandleSql h = new HandleSql();//创建本类对象
        con = h.getConnection();//与数据库建立连接
        addData();
        updateData();
        deleteData();
        queryData();
    }

    //创建查询数据方法
    public static void queryData() {
        try {
            pStmt=con.prepareStatement("select * from student");
            res = pStmt.executeQuery();
            while (res.next()) {//如果当前语句不是最后一条，则进入循环
                int id = res.getInt("id");
                String name = res.getString("name");
                String phone = res.getString("bianhao");
                System.out.println("id：" + id + "  " + "姓名：" + name + "  " + "电话：" + phone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addData() {
        try {
            pStmt = con.prepareStatement("insert into student (name,bianhao) values(?,?)");
            pStmt.setString(1, "益达");
            pStmt.setString(2, "0000000");
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData() {
        try {
            pStmt = con.prepareStatement("update student set name = ? where id = 6");
            pStmt.setString(1, "王八");
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteData() {
        try {
            Statement stmt = con.createStatement();//创建Statement对象
            stmt.executeUpdate("delete from student where id=4");
            stmt.executeUpdate("delete from student where id=7");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

```
