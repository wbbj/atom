## 第一部分

### Spring框架
Spring本身的理念包括IoC(Inversion of Control,控制反转)和AOP(Aspect Oriented Programming,面向切面编程)  

##### IoC
IoC是一个容器,用来装载及管理java的各种资源,包括JavaBean的创建,事件行为等.  
IoC的理念是:不需要去找资源,只要向Spring IoC容器描述所需资源,Spring IoC自己会找到你所需要的资源．  
此外Spring IoC还提供对Java Bean生命周期的管理.  

##### AOP
IoC的目的是为了管理Bean,而Bean是Java面向对象(OOP)的基础设计  
而有些情况是面向对象没法处理的  
Spring AOP常用于数据库事务的编程  
在Spring AOP编程中省去了很多数据库代码和一些复杂的语句,这些都被Spring封装起来了  

### MyBatis
* 是一个基于java的持久层框架,Mybatis提供了接口编程,使用灵活  
Mybatis的数据访问层DAO不需要实现类,只需要一个接口和XML或者注解  
Mybatis与Hibernate不同之处在于,后者是完全面向POJO的,基本不需要编写SQL就可以通过映射关系来操作数据库,是一种全表映射的体现  
而前者需要提供SQL去运行  
Mybatis的优势在于,程序员可以自己制定SQL规则,使用起来更加精准从而优化性能  
* Mybatis的映射中resultMap元素用于定义映射规则  
其中mapper元素中的namespace属性要和一个接口的全限定名保持一致  
里面的SQL的id也要和接口定义的方法完全保持一致  

###SpringMVC
MVC模式把应用程序分成不同方面并提供这些元素之间的松耦合  
松耦合的概念:松耦合系统通常是基于消息的系统，此时客户端和远程服务并不知道对方是如何实现的。客户端和服务之间的通讯由消息的架构支配。只要消息符合协商的架构，则客户端或服务的实现就可以根据需要进行更改，而不必担心会破坏对方。  
MVC分为:  
模型:封装了应用程序的数据和由他们组成的POJO  
视图:负责把模型渲染到视图上,将数据以一定形式展现给用户  
控制器:负责处理用户请求,并建立适当的模型把它传给视图渲染  

## 第二部分Java设计模式  
##### java反射技术
反射之前特别了解了一下  
相关链接:
[类的加载过程及反射的相关知识](https://blog.csdn.net/qq_30039097/article/details/98394983)  
下面是我参考的链接:  
[java反射（特别通俗易懂）](https://blog.csdn.net/lwl20140904/article/details/80163880)   
后面没看  

## 第三部分 MyBatis  
### MyBatis的核心组件:  
1.SqlSessionFactoryBuilder(构造器)根据配置或代码生成SqlSessionFactory  
2.SqlSessionFactory(工厂接口)使用工厂模式生产SqlSession  
3.SqlSession(会话)发送SQL执行返回结果,获取Mapper的接口  
4.SQL Mapper(映射器)由一个Java接口和XML文件或注解构成,需要给出对应的SQL和映射规则,负责发送SQL去执行并返回结果  

#### SqlSessionFactory
MyBatis-config.xml文件的相关配置:  

```html
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
<!--    设置别名:定义了一个别名user,代表了下面type里面的类,这样就可以在mybatis上下文中使用别名代替全限定名了-->
    <typeAliases>
        <typeAlias type="com.wbb.mybatis.pojo.User" alias="user"/>
    </typeAliases>
<!--    数据库配置-->
<!--    数据环境-->
    <environments default="development">
        <environment id="development">
<!--            配置事务管理器:这里采用Mybatis的JDBC管理方式-->
            <transactionManager type="JDBC"/>
<!--            配置数据库:POOLED代表采用Mybatis内部提供的连接池方式-->
            <dataSource type="POOLED">
<!--                JDBC属性信息-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/数据库名称"/>
                <property name="username" value="root"/>
                <property name="password" value="自己数据库的密码"/>
            </dataSource>
        </environment>
    </environments>
<!--    映射器-->
    <mappers>
<!--        这里因为书上用的eclipse,这里直接在idea的main目录下新建resource文件夹右击选择mark Directory as->>Resources root作为配置文件的资源目录就行了-->
        <mapper resource="mappers/UserDaoMapper.xml"/>
    </mappers>
</configuration>
```

以上是使用xml构建SqlSessionFactory的基础配置文件  
配置完基础配置文件就可以用一小段代码生成SqlSessionFactory了  
例:

```java
SqlSessionFactory SqlSessionFactory=null;
String resource="mybatis-config.xml";
InputStream inputStream;
try {
    //读取配置文件
    inputStream=Resources.getResourceAsStream(resource);
    //构建SQLSessionFactory
    SqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
} catch (IOException e) {
    e.printStackTrace();
}
```
#### SqlSession
是MyBatis中的核心接口,代表着一个连接资源的启用  
可以获取Mapper接口,发送SQL给数据库,控制数据库事务  

接着前面的构建SqlSessionFactory  

```java
SqlSession sqlSession=null;
try {
   //打开SqlSession会话
   sqlSession=SqlSessionFactory.openSession();
   //提交事务
   sqlSession.commit();
} catch (Exception e) {
   //回滚事务
   sqlSession.rollback();
}finally {
   //确保资源被顺利关闭
   if (sqlSession!=null){
       sqlSession.close();
   }
}
```
今天重装系统没备份装完发现github上面昨天写的忘记存了。。

## Spring
自动注入  
@Autowired
