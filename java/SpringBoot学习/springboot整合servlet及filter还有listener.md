### SpringBoot整合Servlet及Filter还有istener

#### 整合Servlet
新建项目就不用说了可以参考前面helloworld的介绍  
[SpringBoot编写HelloWorld]()  
其中目录结构也是相同的  

整合Servlet有两种方式  
* 注解扫描  
新建MyServlet类代码如下:  

```java
package com.wbb.springbootservletdemo.demo.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyServlet",urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().write("整合servlet");

        System.out.println("整合servlet");
    }
}
```

在启动类的`@SpringBootApplication`下加上`@ServletComponentScan`  
然后运行如图:  
同样是在url输入对应信息`localhost:8080/myServlet`
springbootservlet运行.png  
可以看到控制台也对应的输出了`整合servlet`的信息  
至此第一种方案就完成了  

* 通过方法整合  
在servlet包下新建SecondServlet类:  
代码如下:  

```java
package com.wbb.springbootservletdemo.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().append("springboot整合servlet成功");
    }
}
```
这里就不需要写注解了  
在对应的application启动类中增加一个方法方法上用@Bean  
代码如下:

```java
package com.wbb.springbootservletdemo.demo;

import com.wbb.springbootservletdemo.demo.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new SecondServlet());
        bean.addUrlMappings("/secondServlet");
        return bean;
    }

}
```
运行结果如图:  
springboot整合servlet方法2.png  

至此方法二也整合成功  

---
#### 整合Filter  
方法与上面差不多  
* 第一种同样是注解方式  
新建filter包目录结构同上  
新建两个类第一个是第一种方法第二个后面写  
第一个MyFilter.java  
代码如下:   

```java
package com.wbb.springbootservletdemo.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(filterName="MyFilter",urlPatterns="/myFilter")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开filter");
    }

    @Override
    public void destroy() {

    }
}
```
同时在启动类的`@SpringBootApplication`下加上`@ServletComponentScan`  
运行在网页输入url:`localhost:8080/myFilter`  
在控制台输出:  
filter方法一运行png  

* 第二种方法注册  
这里我是接着servletlet写的后面listener也是在这个项目中写  
下面是SecondFilter类的代码:  

```java
package com.wbb.springbootservletdemo.demo.filter;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入第二个Filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开第二个Filter");
    }

    @Override
    public void destroy() {

    }
}

```
然后是启动类代码:  

```java
package com.wbb.springbootservletdemo.demo;

import com.wbb.springbootservletdemo.demo.filter.SecondFilter;
import com.wbb.springbootservletdemo.demo.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new SecondServlet());
        bean.addUrlMappings("/secondServlet");
        return bean;
    }
    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean((new SecondFilter()));
        bean.addUrlPatterns("/secondFilter");
        return bean;
    }

}
```
运行后在网页url输入`localhost:8080/secondFilter`  
运行结果:  
filter方法二运行.png  
至此filter两种方法整合完  

---
### 整合Listener  
同样是两种方法  
* 第一种就不用详细写了  
同样要在在启动类的`@SpringBootApplication`下加上`@ServletComponentScan`
代码:  

```java
package com.wbb.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener-init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
```
* 第二种  
启动类的代码
```java
package com.wbb.demo;

import com.wbb.demo.filter.SecondFilter;
import com.wbb.demo.listener.SecondListener;
import com.wbb.demo.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletListenerRegistrationBean getListener(){
        ServletListenerRegistrationBean<SecondListener> bean=new ServletListenerRegistrationBean<SecondListener>(new SecondListener());
        return bean;
    }

}
```
listener直接运行就行了,在控制台会输出在contextInitialized方法中的东西  
