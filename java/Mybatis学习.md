### mybatis
视频第一集了解了sql的一些基本命令  
* 创建数据库并指定编码  
Create database 数据库名 default character set utf8
* 创建表
`Create table 表名(
  列名 类型 约束 auto_increment comment'备注',
  );`

因为我用的idea,教程用的eclipse,所以一些操作基本都不一样  

就记了些基础知识  

* 命名规范  
 *  项目名:没有要求,不起中文  
 *  包:公司域名倒写
 *  持久层:dao,persist,`mapper`(mabatis推荐使用)
 *  实体:entity,model,bean,javabean,`pojo`
 *  业务逻辑:service,biz
 *  控制器:controller,`servlet`,action,web
 *  过滤器:`filter`
 *  异常:`exception`
 *  监听器:`listener`
 *  注释:
  * 类上和方法上使用文档注释`/**  */`
  * 在方法里面使用`/* */ 或//`
 *  类:大驼峰
 *  方法,属性:小驼峰

* MVC开发模式
 M:dao,V:html,C:servlet
 MVC用于大型项目开发

先设计数据库  
先写实体类  
持久层  
业务逻辑  
控制器  
视图  

---
* 业务层pojo  
  * 创建对象例如User
* 持久层dao
  * 例如查询
    * 实现类implimplements
* service
* servlet
 *  处理请求

前面这么多还没有开始Mybatis  

### Mybatis 介绍
开源免费框架  
* 作用:数据访问层框框
  * 底层是对JDBC的封装
mybatis优点,不需要编写实现类  

mybatis搭建  
* 导入jar包  
  `<!--    mybatis-->
      <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
      </dependency>

      <!-- https://mvnrepository.com/artifact/cglib/cglib -->
      <dependency>
        <groupId>cglib</groupId>
        <artifactId>cglib</artifactId>
        <version>3.2.12</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.ow2.asm/asm -->
      <dependency>
        <groupId>org.ow2.asm</groupId>
        <artifactId>asm</artifactId>
        <version>5.0.4</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.javassist/javassist -->
      <dependency>
        <groupId>org.javassist</groupId>
        <artifactId>javassist</artifactId>
        <version>3.25.0-GA</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/log4j/log4j -->
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
      <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.12.0</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
      <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.12.0</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
      <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.8.0-beta4</version>
      </dependency>
      <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
      <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>1.8.0-beta4</version>
        <scope>test</scope>
      </dependency>`

  这些是跟着教程一个个找的.  
  其中有cglib依赖的包  
  动态代理包  
  日志包  
  mybatis核心包  
* 在src下新建全局配置文件(编写JDBC四个变量)
   内容:
    `<?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    <!--    default引用environment的id,当前所使用的环境-->
    <environments default="default">
    <!--        声明可以使用的环境-->
        <environment id="default">
    <!--            使用原生JDBC事物-->
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver_className" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/refuse_classification"/>
                <property name="username" value="root"/>
                <property name="password" value="367494"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/wbb/mapper/GarbageMapper.xml"/>
    </mappers>
    </configuration>`

  * 没有名称和地址要求
  * 在全局配置文件中引入DTD或schema
  新建以mapper结尾的包,在包下新建实体类名+Mapper.xml
  编写需要执行的sql命令

* 测试结果只有在单独使用mybatis时使用

---
数据库连接池  
在内存中开辟一块空间,存放多个数据库连接对象  
JDBC Tomcat Pool,直接有tomcat产生数据库连接池.
使用数据库连接池可提高效率  

实现JDBC Tomcat Pool  
在web项目中的META-INF中存放context.xml
这里idea好像不一样,没看到有META-INF文件  
这里先了解下  

三种查询方式例:  
`List<Garbage> list=session.selectList("a.b.selAll");
for (Garbage garbage:list){
   System.out.println(garbage.toString());
}

int count=session.selectOne("a.b.selByID");
System.out.println(count);

Map<Object,Object> map=session.selectMap("a.b.c,","gname");
System.out.println(map);`  
这里是test中写的测试内容

相应的xml内容:  
`<select id="selAll" resultType="com.wbb.pojo.Garbage">
    select * from garbage
 </select>
<select id="selById" resultType="int">
    select count(*) from test_login
</select>
<select id="c" resultType="com.wbb.pojo.Garbage"><![CDATA[
    select * from garbage
 ]]></select>`  


 ---
 今天找了个特别好的文章[Mybatis教程-实战看这一篇就够了](https://blog.csdn.net/hellozpc/article/details/80878563#11IDEAmaven_12)

这个教程很有用,前面看的视频教程一直出错,这里讲的很详细.  
看了这个总算把mybatis最基础的部分给实现了.看来还是和eclipse有不少区别.  
