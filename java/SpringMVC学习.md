### SpringMVC学习
* SpringMVC重要组建:  
  * DispatcherServlet:前端控制器,接收所有请求
  * HandlerMapping:解析请求格式,判断要执行具体哪个方法
  * HandlerAdapter:负责调用具体方法
  * ViewResovler:试图解析器,解析结果,准备跳转到具体物理视图

* SpringMVC运行原理  
用户请求---->DispatcherServlet接收请求--->HandlerMapping解析请求--->HandlerAdapter调用方法--->Controller执行方法--->ViewResovler--->响应用户  

* Spring容器和SpringMVC容器是父子容器  
  * SpringMVC容器中能够调用Spring容器的所有内容

springmvc配置  
环境搭建:  
导入jar包,因为是用maven建的项目所以直接在pom.xml中导入就行了
web.xml中  
```html
<!--  配置前端控制器-->
  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
<!--    自启动-->
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```
其中springmvc.xml配置  
```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <!--扫描注解-->
    <context:component-scan base-package="com.wbb.controller"/>
    <!--    注解驱动-->
    <mvc:annotation-driven/>
</beans>
```

其中xmlns:mvc是手动加上去的,这里视频用的都是eclipse操作的,我用的idea发现有很多问题,上面放出来的也是alt+enter后的,然后找了csdn上idea的例子看起来是一样的  
```html
<!--扫描注解-->
    <context:component-scan base-package="com.wbb">
    <!-- 只扫描 controller-->
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
```
就这一部分有改动.  

然后Controller中类创建之后需要在开始加上`@Controller`  
例如:  
```java
@Controller
public class DemoController {
    @RequestMapping("demo")
    public String demo(){
        System.out.println("执行demo");
        return "main.jsp";
    }
}
```
这里我运行后不知道为什么没达到视频中的效果  
待后面慢慢研究  
配置静态资源
`<!--    静态资源-->
    <mvc:resources mapping="/page/*" location="/WEB-INF/page/"/>`
其中mapping中/后面的*代表目录下的所有文件,可以多个*,每个代表一层目录*所代表的文件会从location后面写的目录查找  

---

看了这些发现好多地方idea都不太一样,写法思路都是一样的,目录结构不太一样,idea结构更清晰,跟着教程走很多地方都有问题,所以就先不管这些,先学概念和思路以及一些语句.

##### SpringMVC环境搭建    
* 导入jar包
这里我用的maven
所以只要到官网找到需要的包就行了  
`<!--    Spring-->
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-beans -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-aop -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-expression -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>5.1.8.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.2</version>
    </dependency>`

这是我找的所有包,  
* 在web.xml中配置前端控制器DispatcherServlet
`<servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>`

* 新建springmvc.xml文件  
`<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 扫描Controller的注解-->
    <context:component-scan base-package="com.wbb.controller"/>

    <!-- 开启对SpringMVC注解的支持 -->
    <mvc:annotation-driven/>
<!--    静态资源-->
    <mvc:resources mapping="/page/*" location="/WEB-INF/page/"/>
</beans>`

* 编写控制类  
里面添加`@Controller`注解以及`@RequestMapping("")`注解,在类上填/类名,方法上填方法名  

---
###### 字符编码过滤器  
在web.xml中配置
<!-- 编码过滤器 -->
`<filter>
  <filter-name>encodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>encodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>`

HandlerMethod中参数是对象类型  
请求参数名和对象中属性名对应(get/set方法)  
`People people`  
请求参数中包含多个同名参数的获取方式  
`@RequestParam("name")List<String> test`  

跳转方式有重定向redirect和转发forword  

---
##### 视图解析器
教程中说Spring会提供默认视图解析器,但是我实际在idea中操作发现一直出现视图解析器问题,看了很多人写的例子里面也都配置了试图解析器  
教程这里也给了自定义试图解析器配置方法  
具体配置在springmvc.xml中,我这里放的是其他人写的idea中的  
与教程中有一丢丢不同,应该效果是一样的  
`<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
   <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
   <property name="prefix" value="/WEB-INF/page/"/>
   <property name="suffix" value=".html"/>
</bean>`  
而且教程用的都是.jsp,我用的是.html  
如果希望不执行自定义视图解析器,在方法返回值前面添加redirect:或forword:  

---
##### `@ReposeBody`
* 在方法上只有@RequestMapping时,无论方法返回值是什么仍为需要跳转  
* 在方法上添加@ResponseBody(恒不跳转):  
  * 如果返回值满足key-value形式(对象或map)
    * 把相应头设置为application/json;charset=utf-8
    * 把转换后的内容以输出流的形式响应给后端  
  * 不满足例如string
    * 把相应头设置为text/html
    * 把方法返回值以流的形式直接输出.

---
### 了解下大体步骤  
* 目录结构
  * src
    * com.xxx
      * mapper
      * pojo
      * service
        * impl
      * controller
* 第一步,jar包  
  * 这里直接在pom.xml中添加之前用的spring,log,mybatis
* 第二步,配置web.xml  
  * 上下文参数
    * name contextConfigLocation
    * value classpath:applicationContext.xml
  * 监听器:ContextLoaderListener  
  * SpringMVC前端控制器:servlet...
    * 初始化参数name与上下文参数对应,value为springmvc.xml
  * 字符编码过滤器
* 第三步applicationContext.xml
  * 注解扫描:com.`***`.service.impl
  * 加载属性文件:jdbc.property
    * * 配置resource目录下的jdbc.properties
  * 数据源:配置与上面数据库对应的相关信息DriverManagerDatasource
  * SqlSessionFactory:SqlSessionFactoryBean
  * 扫描器MapperScannerConfigurer
  * 事务管理器:DataSourceTransactionManager
  * 声明式事物  
  * 配置aop

* 第四步,配置springmvc.xml
  * 扫描注解,只扫描Controller包
  * 注解驱动
  * 设置静态源
  * 试图解析器
* 完善pojo
* 设置mapper以及对应的.xml
* 以上配置文件除了web.xml都放在resource中
* 完善service以及impl
* 完善controller
* 完善前段.html或.jsp
