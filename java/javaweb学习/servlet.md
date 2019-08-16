
### Servlet
* Servlet介绍：
java servlet和平台无关是运行在servlet容器中的服务器端组件  
处于 web浏览器或Http客户端请求 和 Http服务器数据库或应用程序 之间的中间层  
servlet用于读取和发送客户端发送的显式数据如网页上的HTML表单或隐式的Http请求数据如cookies，  
然后处理数据并生成结果，期间可能需要访问数据库。

客户——>请求Servlet容器——>servlet  
客户<——响应servlet容器<——servlet  
servlet容器创建serevlet调用生命周期方法  

* Servlet生命周期:  
构造器 只有第一次请求时调用  
通过init()方法进行初始化 只被调用一次  
通过service()执行实际任务，调用service()方法来处理客户端请求，并把格式化的响应写回给客户端  
调用destroy()方法终止 只被调用一次  

---
##### 步骤：
* 创建一个servlet接口实现类
public class HelloServlet implements Servlet  
* 在wweb。xml文件中配置和映射这个Servlet
```html
1<servlet>...  
2<servlet-name>...       <!--注册的名字 -->   
3<servlet-class>...      <!--要写全类名 -->    
——
4<servlet-mapping>...  
5<servlet-name>...       <!--与上面的name对应-->  
6<url-pattern>/...        <!--/代表当前weib根目录，其中*.扩展名和/不能一起使用-->  
```
一个servlet可以有多个servlet-mapping与之对应  

<load-on-startup>1</load-on-startup> 指定servlet被创建的时机配置在servlet节点中  
值越小越先被调用，但是当其中的值为负数时在第一次请求时被创建。   

```html
<!--配置Servlet的初始化参数-->
        <init-param>  
            <param-name>user</param-name>  
            <param-value>root</param-value>  
        </init-param>  
        <init-param>  
            <param-name>password</param-name>  
            <param-value>123456</param-value>  
        </init-param>  
```
注意：必须放在load-on-startup之前

---
*  ServletConfig方法：  
getInitParamenterNames  
getInitParamenter  
获取当前web应用初始化参数信息  
```java
init(){
String user=servletConfig.getInitParameter("user");
        System.out.println("user:"+user);
        Enumeration<String> names=servletConfig.getInitParameterNames();
        while (names.hasMoreElements()){
            String name=names.nextElement();
            String value=servletConfig.getInitParameter(name);
            System.out.println(name+":"+value);
}
```
* ServletContext:可以从中获取到当前web应用的各个方面的信息

```html
<!--配置当前web应用的初始化参数-->
    <context-param>
        <param-name>driver</param-name>
        <param-value>com.mysql.jdbc.Driver</param-value>
    </context-param>
```
getRealPath(String path)获取当前应用的某一个文件在服务器上的绝对路径  

* http请求  


先写一个表单请求数据  
```html
<form action="loginServlet" method="post">
    user:<input type="text" name="user"/>
    password<input type="password" name="password"/>
    <input type="submit" value="Submit"/>
  </form>
```

其中请求方法一般用get和post，其区别就是get传递的数据量有限制一般在1kb以下，而post传递的数据量要比前者大的多  

如果在浏览器中点击一个超链接或输入某个url地址，请求方式一定是get。  
form表单method属性设置位get时，浏览器生成额http请求消息的请求方式位get  
form表单的method置为post时请求方式才位post  

##### 如何在servlet中获取请求信息  
Servlet的service()方法  
获取请求的数据。
```java
Map<String,String[]> map=servletRequest.getParameterMap();
        for(Map.Entry<String ,String[]> entry:map.entrySet()){
            System.out.println(" **"+entry.getKey()+":"+ Arrays.asList(entry.getValue()));
        }
```
请求的URI，请求的方式。
```java
String requestURI=httpServletRequest.getRequestURI();
String method=httpServletRequest.getMethod();
```
getWriter() 返回PrintWriter对象，调用该对象的print()方法把其中的参数打印到客户的浏览器上  
setContentType可以设置响应的内容类型  
```java
servletResponse.setContentType("application/x-abiword");
        PrintWriter out=servletResponse.getWriter();
        out.println("helloworld");
```

void sendRedirect(String location):请求的重定向。  
在web.xml文件中设置两个初始化参数：user，password  

---
### 小结;  
实现一个简单的servlet  
* 先创建servlet接口实现类

```java
package com.myorg.javaweb;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {  
    }
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
    }
    @Override
    public String getServletInfo() {
        return null;
    }
    @Override
    public void destroy() {
    }
}
```
* 写一个登录的表单  
在idea中因为某些导致只能访问jsp而不能访问html  
在web.xml配置文件中加上以下内容即可  

```html
<welcome-file-list>
        <welcome-file>要加载的html文件名称</welcome-file>
    </welcome-file-list>
```

```html
<form action="loginServlet" method="post">
    user:<input type="text" name="username"/>
    password:<input type="password" name="password"/>
    <input type="submit" value="Submit"/>
  </form>
```
* 然后在web.xml文件中配置和映射这个servlet

```html
<servlet>
       <servlet-name>loginServlet</servlet-name>
       <servlet-class>com.myorg.javaweb.LoginServlet</servlet-class>
   </servlet>
   <servlet-mapping>
       <servlet-name>loginServlet</servlet-name>
       <url-pattern>/loginServlet</url-pattern>
   </servlet-mapping>
```
* 先定义一个成员变量方便service方法调用init中的servletContext对象  
这里就创建了servletConfig  
`private ServletConfig servletConfig;`
* 获取请求参数：username，password  
后面步骤都是在service中配置  
`String username=servletRequest.getParameter("username");`  
`String password=servletRequest.getParameter("password");`  

* 获取当前web应用的初始化参数username,password,其中使用了servletContext对象  

```java
ServletContext servletContext=servletConfig.getServletContext();
       String initUser=servletContext.getInitParameter("user");
       String initPassword=servletContext.getInitParameter("password");
       PrintWriter out=servletResponse.getWriter();
```
* 然后进行比对打定响应字符串  

```java
if(initUser.equals(username)&&initPassword.equals(password)){
            out.println("Hello:"+username);
        }else {
            out.println("Sorry:"+username);
        }
```
运行效果：  
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716133446483.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716133454552.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716133502685.png)

---

* GenericServlet:是一个servlet，是servlet借口和servletConfig接口的实现类，但是一个抽行类，其中service位抽象方法，使新建的servlet直接继承GenericServlet是开发变得更简洁。  
具体实现：在其中声明一个ServletConfig类型的成员变量在init()方法中初始化

* HttpServlet：是一个Serv，继承自GenericServlet，针对于HTTP协议所定制。
在service()方法中直接把ServletRequest和ServletResponse转为HttpServletReques和HttpServletResponse

实际开发中直接继承HttpServlet，并根据请求方式复写doXxx()方法接口

---

在MySQL数据库中创建一个数据表添加id,user,password字段并添加一条记录  
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716133524652.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716133530880.png)
利用JDBC从表中查询有没有页面输入的user,password对应的记录  
SELECT count(id) FROM test_login where user=? AND password=?  

具体代码：  

```java
package com.myorg.javaweb;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        PrintWriter out=response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/student?characterEncoding=UTF-8&useSSL=false";
            String user="root";
            String password2="367494";
            connection= DriverManager.getConnection(url,user,password2);

            String sql="SELECT count(id) FROM test_login WHERE username=?" +
                    "AND password=?";

            statement=connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);

            resultSet=statement.executeQuery();
            if(resultSet.next()){
                int count=resultSet.getInt(1);
                if(count>0){
                    out.println("Hello:"+username);
                }else {
                    out.println("Sorry");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(statement!=null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

```

---

#### 配置tomcat  
在deepin下配置tomcat  
* 复制要下载的tomcat的链接
[外链图片转存失败(img-c9PVbsn5-1563255249552)(/home/arthas/atom/java/image)]
* 打开要下载的目录位置   
[外链图片转存失败(img-RjghCe1X-1563255249553)(/home/arthas/atom/java/image)]  
* 输入
```bash
wget空格+下载链接
```
* 解压
```bash
$ tar xzvf tomcat文件名.tar.gz`
```
* 打开etc下的profile
```bash
$ sudo vim /etc/profile
```
*	打开后添加  
`export CATALINA_HOME=tomcat下载目录`  

* 进入tomcat的bin目录使用`chmod 777 *.sh`修改权限，在目录中使用`./startup.sh`和`./shutdown.sh`分别打开和关闭tomcat  
* 注意如果文件里面全被限制了使用`chmod -R 777 目录名/`
可以使目录内文件全生效
*	打开后然后查看`localhost:8080`能正常加载则配置成功  
