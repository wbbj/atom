### springboot整合mybatis
这个创建项目的时候与前面创建项目步骤相同  
参考:https://blog.csdn.net/qq_30039097/article/details/99687346
其中在选择Dependencies时不光要选择web还要选择如下图所示的内容mybatis和mysql
springboot整合mybatis.png

另外在pom.xml中还要加上  

```html
<!--MySQL数据库驱动-->
 <dependency>
     <groupId>mysql</groupId>
     <artifactId>mysql-connector-java</artifactId>
     <version>5.0.2</version>
 </dependency>
 <!--数据库连接池-->
 <dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>druid</artifactId>
     <version>1.0.15</version>
 </dependency>
```
将application.properties改为application.yml  
修改其中内容为  
```java
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/refuse_classification
    username: root
    password: ******
    type: com.alibaba.druid.pool.DruidDataSource
mybatis:
  type-aliases-package: com.wbb.mybatis.pojo
```
