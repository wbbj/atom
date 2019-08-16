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
自动注入@Autowired在使用自动注入的时候会出现多个实现类IoC不知道该注入哪个这时候使用@Primary优先注入和@Qualifier写在@Autowired之后根据名称注入  
`@Primary`例子:  

```java
@Component
public class RoleController{
@Autowired
private RoleService roleService=null;
}
```

```java
@Component("roleService3")
@Primary
public class RoleServiceImpl3 implements RoleService{
}
```
上面是优先注入RoleServiceImpl3  
下面是根据别名roleService3注入RoleServiceImpl3  
`@Qualifier`例子:  

```java
@Component
public class RoleController{
  @Autowired
  @Qualifier("roleService3")
  private RoleService roleService=null;
}
```
可以在方法上使用@Bean装配Bean  
其中包含四个配置项  
1.name允许配置多个BeanName  
2.autowire标志是否是一个引用的Bean对象,默认值是Autowire.NO  
3.initMethod自定义初始化方法  
4.destroyMethod自定义销毁方法  


扫描包`@ComponentScan(basePackages={"com.ssm.dao.*"}) `
或者xml的形式`<context:component-scan base-packe="com.ssm.dao.*"/>`  

使用@Profile定义多个数据库连接池或者XML方式  
激活profile需要自行激活  

属性配置文件properties  
1.使用注解方式加载属性文件@PropertySource  
如@PropertySource(Value={"classpath:database-config.properties"})  
使用注解@Value和占位符如@Value("${jdbc.database.driver}")引用已经定义好的配置  

2.使用XML方式加载属性文件  
<context:property-placeholder ignore-resource-not-found="true" location="classpath:database-config.properties"/>上面为true的属性代表允许文件不存在  
配置多个属性文件  

```html
<property name="location">
  <array>
    <value>classpath:database-config.properties</value>
    <value>classpath:log4j.properties</value>
  </array>
</property>
```  
### Spring表达式

EL的使用例:  
```java
package wbb.test;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class EL {
    public static void main(String[] args) {
        //表达式解析器
        ExpressionParser parser=new SpelExpressionParser();
        //设置表达式
        Expression exp=parser.parseExpression("'hello world'");
        String str= (String) exp.getValue();
        System.out.println(str);
        //通过EL访问普通方法
        exp=parser.parseExpression("'hello world'.charAt(0)");
        char ch=(Character) exp.getValue();
        System.out.println(ch);
        //通过EL访问的getter方法
        exp=parser.parseExpression("'hello world'.bytes");
        byte[] bytes=(byte[])exp.getValue();
        System.out.println(bytes);
        //通过EL访问属性,相当于"hello world".getBytes().length
        exp=parser.parseExpression("'hello world'.bytes.length");
        int length=(Integer)exp.getValue();
        System.out.println(length);
        exp=parser.parseExpression("new String('abc')");
        String abc=(String)exp.getValue();
        System.out.println(abc);
    }
}
```
EL可以用来创建对象  
EL中注解@Value("#{}")  
类的静态常量@Value("#(T(Math).PI)")  
EL的各种运算  

### AOP面向切面编程  
AOP是Spring的一个重要方法

AOP主要内容就是动态代理  
* 代理的意思就是我只管做我要做的事,其他的详细实现内容由代理来做,比如旅游你只管吃喝玩,实现旅游的门票安排等等都由代理来做  
* 代理有静态代理和动态代理  
静态代理是在程序运行前就已经存在代理类的字节码文件.代理类和原始类在运行前就已经确定  
动态代理采用反射技术在运行时才确认代理的方法  

动态代理的例子:  
先写接口类及实现类  
如Human:  

```java
package com.wbb.aop;

public interface Human {
    public void eat() throws InterruptedException;
    public void sleep() throws InterruptedException;
    public void study() throws InterruptedException;
    public void work() throws InterruptedException;
}
```

然后是实现类:  
Student:  
Teacher:  
```java
package com.wbb.aop;

public class Student implements Human{
    public void eat() throws InterruptedException {
        System.out.println("eat");
        Thread.sleep(200);
    }

    public void sleep() throws InterruptedException {
        System.out.println("sleep");
        Thread.sleep(3000);
    }

    public void study() throws InterruptedException {
        System.out.println("study");
        Thread.sleep(1000);
    }

    public void work() throws InterruptedException {
        System.out.println("work");
        Thread.sleep(2500);
    }
}
```
```java
package com.wbb.aop;

public class Teacher implements Human{

    public void eat() throws InterruptedException {
        System.out.println("teacher eat");
        Thread.sleep(1500);
    }

    public void sleep() throws InterruptedException {
        System.out.println("teacher sleep");
        Thread.sleep(1500);
    }

    public void study() throws InterruptedException {
        System.out.println("teacher study");
        Thread.sleep(2000);
    }

    public void work() throws InterruptedException {
        System.out.println("teacher work");
        Thread.sleep(3000);
    }
}
```

然后是代理类:  
```java
package com.wbb.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler {
    private Object target=null;
    //绑定需要代理的对象
    public Object bind(Object target){
        this.target=target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    //实现具体逻辑
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start=System.currentTimeMillis();
        Object obj=method.invoke(target,args);
        System.out.println("============you spend time is: "+(System.currentTimeMillis()-start));
        return obj;
    }
}
```

最后测试:  
```java
package wbb.test;

import com.wbb.aop.Human;
import com.wbb.aop.MyProxy;
import com.wbb.aop.Student;
import com.wbb.aop.Teacher;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyProxy myProxy=new MyProxy();
        Human student=(Human)myProxy.bind(new Student());
        student.eat();
        student.sleep();
        student.study();
        student.work();
        Human teacher=(Human) myProxy.bind(new Teacher());
        teacher.eat();
        teacher.sleep();
        teacher.study();
        teacher.work();
    }
}
```

运行结果:
```
eat
============you spend time is: 201
sleep
============you spend time is: 3000
study
============you spend time is: 1001
work
============you spend time is: 2500
teacher eat
============you spend time is: 1500
teacher sleep
============you spend time is: 1501
teacher study
============you spend time is: 2000
teacher work
============you spend time is: 3000
```
