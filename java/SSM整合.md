### SSM整合简单步骤
* 目录结构
目录结构.png  
* 第一步导入jar包  
下面是pom.xml中的配置:  

```html
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.wbb</groupId>
  <artifactId>Ssm</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>Ssm Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
<!--数据库-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.37</version>
    </dependency>

    <dependency>
      <groupId>c3p0</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.1.2</version>
    </dependency>
    <!-- DAO: MyBatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.3</version>
    </dependency>
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
  </dependency>
    <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.5.4</version>
  </dependency>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.5.0</version>
  </dependency>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.4</version>
  </dependency>
  <!-- 4.Spring -->
  <!-- 1)Spring核心 -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-beans</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
  <!-- 2)Spring DAO层 -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
  <!-- 3)Spring web -->
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.1.7.RELEASE</version>
  </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>compile</scope>
    </dependency>
  </dependencies>

  <build>
    <finalName>Ssm</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- seehttp://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```
注意其中:  
`<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>3.1.0</version>
</dependency>`
`这里servlet-api是3.1.0版本对应的web.xml也要修改  
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"version="3.1" metadata-complete="true">`  
其中webapp后面就要用3.1
* 然后先根据目录结构将要建立的包建好
* 首先在main下新建一个java文件夹右击mark Directory as -> source root  
如图:  
marksource.png  
这里的java文件夹是存放java代码,  
* 在java文件内新建包wbb.entity , wbb.dao , wbb.service , wbb.service.impl , wbb.web  
* 在main下新建文件夹resources存放xml文件,同样要右击mark Directory as->resource root
* 在resources文件夹下新建mapper和spring文件夹存放对数据库操作的配置和springmvc的配置
* 然后编写配置文件web.xml
  ```html
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
  http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1" metadata-complete="true">
    <display-name>Archetype Created Web Application</display-name>

    <!-- 如果是用mvn命令生成的xml，需要修改servlet版本为3.1 -->
    <!-- 配置DispatcherServlet -->
    <servlet>
      <servlet-name>DispatcherServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <!-- 配置springMVC需要加载的配置文件
          spring-dao.xml,spring-service.xml,spring-web.xml
          Mybatis - > spring -> springmvc
       -->
      <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring/spring-*.xml</param-value>
      </init-param>
    </servlet>
    <servlet-mapping>
      <servlet-name>DispatcherServlet</servlet-name>
      <!-- 默认匹配所有的请求 -->
      <url-pattern>/</url-pattern>
    </servlet-mapping>

  </web-app>
  ```
* 在resources下新建mybatis-config.xml并配置以下内容  
  ```html
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!-- 配置全局属性 -->
      <settings>
        <!-- 这里是控制打印输出数据库内容的配置 -->
          <setting name="logImpl" value="STDOUT_LOGGING" />
          <setting name="lazyLoadingEnabled" value="true" />
          <setting name="aggressiveLazyLoading" value="false" />

          <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->
          <setting name="useGeneratedKeys" value="true" />

          <!-- 使用列别名替换列名 默认:true -->
          <setting name="useColumnLabel" value="true" />

          <!-- 开启驼峰命名转换:Table{create_time} -> Entity{createTime} -->
          <setting name="mapUnderscoreToCamelCase" value="true" />
      </settings>
  </configuration>
  ```
* 然后在spring目录下新建spring-web.xml和spring-dao.xml  
spring-web.xml的内容:  
  ```html
  <beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:context="http://www.springframework.org/schema/context"
  xmlns:mvc="http://www.springframework.org/schema/mvc"
  xmlns:contex="http://www.springframework.org/schema/context"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
  http://www.springframework.org/schema/beans/spring-beans.xsd
  http://www.springframework.org/schema/context
  http://www.springframework.org/schema/context/spring-context.xsd
  http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
      <!-- 配置SpringMVC -->
      <!-- 1.开启SpringMVC注解模式 -->
      <!-- 简化配置：
          (1)自动注册DefaultAnootationHandlerMapping,AnotationMethodHandlerAdapter
          (2)提供一些列：数据绑定，数字和日期的format @NumberFormat, @DateTimeFormat, xml,json默认读写支持
      -->
      <mvc:annotation-driven></mvc:annotation-driven>
      <mvc:default-servlet-handler></mvc:default-servlet-handler>
      <!-- 4.扫描web相关的bean -->
      <!-- 扫描service层所有@注解 -->
      <context:component-scan base-package="wbb.service"/>
      <!-- 扫描dao层所有注解 -->
      <context:component-scan base-package="wbb.dao"/>
      <!-- 扫描service中impl实现类中的所有注解 -->
      <context:component-scan base-package="wbb.service.impl"/>
      <!-- 扫描web层也就是Controller层所有注解 -->
      <context:component-scan base-package="wbb.web"/>

  </beans>
  ```
spring-dao.xml内容:  
  ```html
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
  http://www.springframework.org/schema/beans/spring-beans.xsd
  http://www.springframework.org/schema/context
  http://www.springframework.org/schema/context/spring-context.xsd">
      <!-- 配置整合mybatis过程 -->
      <!-- 1.配置数据库相关参数properties的属性：${url} -->
      <context:property-placeholder location="classpath:jdbc.properties" />

      <!-- 2.数据库连接池 -->
      <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
          <!-- 配置连接池属性 -->
          <!-- 下面${jdbc.***}会自动匹配jdbc.properties中的信息 -->
          <property name="driverClass" value="${jdbc.driver}" />
          <property name="jdbcUrl" value="${jdbc.url}" />
          <property name="user" value="${jdbc.username}" />
          <property name="password" value="${jdbc.password}" />

          <!-- c3p0连接池的私有属性 -->
          <property name="maxPoolSize" value="30" />
          <property name="minPoolSize" value="10" />
          <!-- 关闭连接后不自动commit -->
          <property name="autoCommitOnClose" value="false" />
          <!-- 获取连接超时时间 -->
          <property name="checkoutTimeout" value="10000" />
          <!-- 当获取连接失败重试次数 -->
          <property name="acquireRetryAttempts" value="2" />
      </bean>

  <!-- spring整合mybatis -->
      <!-- 3.配置SqlSessionFactory对象 -->
      <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <!-- 注入数据库连接池 -->
          <property name="dataSource" ref="dataSource" />
          <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
          <property name="configLocation" value="classpath:mybatis-config.xml" />
          <!-- 扫描sql配置文件:mapper需要的xml文件 -->
          <!-- mapper中配置的是dao层对应的sql操作 -->
          <property name="mapperLocations" value="classpath:mapper/*.xml" />
      </bean>

      <!-- 4.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->
      <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
          <!-- 注入sqlSessionFactory -->
          <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
          <!-- 给出需要扫描Dao接口包 -->
          <property name="basePackage" value="wbb.dao" />
  </bean>
  </beans>
  ```
其中可以配置jdbc.properties也可以写死在此配置中,这里在resources目录下新建jdbc.properties文件并配置一下内容  
`jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=utf8
jdbc.username=root
jdbc.password=你的密码`

* 然后在entity中新建类例如Book类
下面的内容只需要写好数据库中对应的属性然后alt+insert快捷生成构造方法就行了  
  ```java
  package wbb.entity;

  public class Book {
    //Book中的信息,跟数据库表中信息对应
      private String id;
      private String name;
      private String author;
      private String publisher;
  //下面可以通过idea自带的alt+insert选择getter+setter快捷生成
      public String getId() {
          return id;
      }

      public void setId(String id) {
          this.id = id;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getAuthor() {
          return author;
      }

      public void setAuthor(String author) {
          this.author = author;
      }

      public String getPublisher() {
          return publisher;
      }

      public void setPublisher(String publisher) {
          this.publisher = publisher;
      }
  }
  ```
* 然后写dao层,在dao目录下新建Bookdao接口类  
    ```java
    package wbb.dao;

    import org.springframework.stereotype.Component;
    import wbb.entity.Book;

    import java.util.List;
    //通过Component将BookDao内容注入spring容器
    @Component
    public interface BookDao {
      //先随便写个方法,获取全部信息要用List
        public List<Book> getAll();
    }
    ```
  * 然后写dao对应的mapper,在resources目录下的mapper目录下新建BookMapper.xml文件:  
    ```html
    <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
          <!-- 命名空间要用全限定名 -->
  <mapper namespace="wbb.dao.BookDao">
    <!-- 因为是查询表中所有信息所以返回类型是下面的内容 -->
      <select id="getAll" resultType="wbb.entity.Book">
        <!-- 查询book表中的所有信息 -->
          select * from book
      </select>
  </mapper>
  ```
* 在service目录下新建BookService接口类对应BookDao的方法  
  ```java
  package wbb.service;


  import wbb.entity.Book;

  import java.util.List;

  public interface BookService {
      /**
       * 获取所有的书
       * @return
       */
     public List<Book> getAllb();
  }
  ```
* 然后在service.impl目录下新建service接口的实现类BookServiceImpl.xml  
  ```java
  package wbb.service.impl;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;
  import wbb.dao.BookDao;
  import wbb.entity.Book;
  import wbb.service.BookService;

  import java.util.List;

  //通过下面注解自动注入spring容器
  @Service("bookService")
  public class BookServiceImpl implements BookService {
    //注入bookDao
      @Autowired
      private BookDao bookDao;

      @Override
      public List<Book> getAllb() {
        //用于测试service层是否注入成功
          System.out.println("查询");
         return bookDao.getAll();
      }
  }
  ```
* 最后写web层或Controller,在web目录下新建BookController类  
  ```java
  package wbb.web;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RestController;
  import wbb.entity.Book;
  import wbb.service.BookService;

  import java.util.List;

//下面注解也可以使用Controller,@RestController注解是@Controller和@ResponseBody注解的结合。表明这是一个控制器类
  @RestController
  public class BookController {
    //注入BookService
      @Autowired
      private BookService bookService;
//RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
      @RequestMapping("/getAll")
      public List<Book> getAll(){
          return bookService.getAllb();
      }

  }
  ```

运行测试:  
这里可以用maven也可以用tomcat  
maven配置tomcat只需要像配置tomcat一样新建一个maven然后在Parameters栏里面的Command line:中输入`tomcat:run`  
然后运行等自动下载配置完成就行  
如图:  
测试ssm.png
其中输出的是json类型的,如果没有以下jar包可能会报错  
`<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-core</artifactId>
<version>2.5.4</version>
</dependency>
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-annotations</artifactId>
<version>2.5.0</version>
</dependency>
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-databind</artifactId>
<version>2.5.4</version>
</dependency>`  
