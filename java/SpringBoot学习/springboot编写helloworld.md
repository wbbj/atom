### SpringBoot编写HelloWorld
* 首先新建一个项目  
如图:  
springboot新建项目.png  
* 然后点击next  
如图:  
springboot新建项目1.png  
填写项目包和名称  
jdk版本保持一致  
* 继续next:  
如图选择web选项-->勾选Spring Web Starter  
springboot新建项目2.png  
* 最后next填写项目文件名然后Finish  

###### 然后开始写helloworld  
* 首先新建一个controller  

  其中要注意启动类可以和controller位于同一个包下，或者位于controller的上一级包中，但是不能放到controller的平级以及子包下。  
  第一次写这个都会受之前写的项目的影响把目录写成如下形式  
  * java
    * com.wbb.springboot
      * controller
        * HelloWorld.java
      * application
        * SpringbootApplication  

  这时候就会报404错误这样写是扫描不到类的  

目录结构如图  
springboot目录结构.png  
在包controller下新建Helloworld类  
编写代码如下:  
```java
package com.wbb.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    public Map<String ,String >hello(){
        Map map=new HashMap();
        map.put("msg","hello");
        return map;
    }
}
```
* 下面的xxxApplication类是项目自动创建好的启动类  
打开可以看到  

  ```java
  package com.wbb.springboot;

  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;

  @SpringBootApplication
  public class SpringbootApplication {

      public static void main(String[] args) {
          SpringApplication.run(SpringbootApplication.class, args);
      }

  }
  ```
* 这里就可以直接运行了  
Springboot是自带tomcat的  
* 运行结果如图:  
springboothelloworld运行1.png  
可以看到项目是基于tomcat的  
* 然后在浏览器中输入`@RequestMapping("/hello")`中对应的url:`localhost:8080/hello`  
可以看到如图的信息与 `map.put("msg","hello");`对应  
springboothelloworld运行2.png  

* 至此helloworld项目已经完成了  
