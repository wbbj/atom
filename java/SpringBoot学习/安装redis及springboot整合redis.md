### 安装redis及springboot整合redis
### 安装并使用redis
```bash
~ sudo apt-get install redis-server
```
检查Redis服务器系统进程  
```bash
~ ps -aux|grep redis
redis     4162  0.1  0.0  10676  1420 ?        Ss   23:24   0:00 /usr/bin/redis-server /etc/redis/redis.conf
conan     4172  0.0  0.0  11064   924 pts/0    S+   23:26   0:00 grep --color=auto redis
```

通过命令行客户端访问Redis  
```bash
~ redis-cli
redis 127.0.0.1:6379>

# 命令行的帮助

redis 127.0.0.1:6379> help
```

查看所有的key列表  

```bash

redis 127.0.0.1:6379> keys *
(empty list or set)
```
* 修改配置文件使用密码登录redis以及开放远程访问  

用vi打开Redis服务器的配置文件redis.conf  
这里可能内容太多找起来不方便  
可以直接打开配置文件修改  
```bash
~ sudo vi /etc/redis/redis.conf
```
取消注释requirepass  
requirepass 这里修改为想要的密码例如123456  

注释掉bind  
`#bind 127.0.0.1`  

修改后重启redis服务器  
```bash
~ sudo /etc/init.d/redis-server restart
```
登录指令  
```bash
~  redis-cli -a 123456
```

检查Redis服务器占用端口
```bash
~ netstat -nlt|grep 6379
tcp        0      0 0.0.0.0:6379            0.0.0.0:*               LISTEN
```

远程访问  
```bash
~ redis-cli -a 123456 -h ***.***.*.***
```


### idea中配置springboot整合redis
新建项目,或使用原有的项目  
首先添加启动器  
```xml
<!--redis-->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-redis</artifactId>
       </dependency>
```
配置信息:  
```yml
spring:
  redis:
    host: 127.0.0.1
    port: 6379
    password: 367494
    jedis:
      pool:
        max-active: 8
        max-wait: -1
        max-idle: 500
        min-idle: 0
    lettuce:
      shutdown-timeout: 0
```
测试代码:  
```java
package com.wbb.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private RedisTemplate<String ,String>redisTemplate;
    @Test
    public void set() {
        redisTemplate.opsForValue().set("myKey","myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

}
```

配置序列化器  
这里将  `@Autowired
  private RedisTemplate<String ,String>redisTemplate;`中后面的String改为Object后就会报错,需要序列化器  
这里是比较方便的方法  
示例代码:
```java
package com.wbb.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object>template=new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
```
