### springboot整合rabbitMQ

###rabbitmq介绍  
1. 什么是RabbitMQ:  
采用AMQP高级消息队列协议的一种消息系列技术,最大的特点就是消费并不需要确保提供方存在,实现了服务之间的高度解耦  

2. 为什么要使用RabbitMQ:  
  * 解耦:系统A在代码中直接调用系统B和系统C的代码,如果将来D系统接入,系统A还需要修改代码,过于麻烦  
  * 异步:将消息写入队列,非必要的业务逻辑以异步的方式运行,加快相应速度   
  * 削峰,并发量大的时候,所有的请求直接发送的数据库,造成数据库连接异常  

使用rabbitmq 中间件，将一些无需即时返回且耗时的操作提取出来，进行了异步处理，而这种异步处理的方式大大的节省了服务器的请求响应时间，从而提高了系统的吞吐量。  



#### 安装
* 首先安装相关配置  
```bash
$ sudo apt install erlang
```
```bash
$ sudo apt install rabbitmq-server
```
* 启动、停止、重启、状态rabbitMq命令  
```bash
sudo rabbitmq-server start
sudo rabbitmq-server stop
sudo rabbitmq-server restart
sudo rabbitmqctl status
```
* 添加用户，并赋予administrator权限  
```bash
sudo rabbitmqctl add_user  wangbeibei  123456
```
```bash
sudo rabbitmqctl set_user_tags wangbeibei administrator
```

* 然后在浏览器输入http://localhost:15672 登录后查看服务器情况  
默认用户名密码是guest/guest  


#### 整合
然后是整合  
* 首先添加依赖  
```xml
<!--rabbitmq-->
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-amqp</artifactId>
     </dependency>
```
* 配置连接信息  
```yml
spring:
  # redis:
  #   host: 127.0.0.1
  #   port: 6379
  #   password: 367494
  #   jedis:
  #     pool:
  #       max-active: 8
  #       max-wait: -1
  #       max-idle: 500
  #       min-idle: 0
  #   lettuce:
  #     shutdown-timeout: 0
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: wangbeibei
    password: 123456
    listener:
      direct:
        retry:
          enabled: true
          max-attempts: 5
```

* 添加配置类  
后面遇到一堆bug为找到解决办法...  
待研究
目前问题已解决,是导入的包的问题  
配置类config包目录下新建RabbitConfig类  
```java
package com.wbb.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue createQueue1(){
        return new Queue(RabbitConfig.queue1);
    }
    @Bean
    public Queue createQueue2(){
        return new Queue(RabbitConfig.queue2);
    }
    //交换器
    public static final String exchange1="exchange1";

    //队列
    public static final String queue1="queue1";
    public static final String queue2="queue2";

    //路由键
    public static final String routingKey1="routingKey1";
    public static final String routingKey2="routingKey2";

    /**
     * 交换机类型
     */
    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(exchange1);
    }

    /**
     * 一个交换机可以绑定多个消息队列
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(createQueue1()).to(defaultExchange()).with(RabbitConfig.routingKey1);

    }
    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(createQueue2()).to(defaultExchange()).with(RabbitConfig.routingKey2);

    }
}
```
* 消息生产者与接受者  
新建rabbitmq包  
包下新建Sender类与Receiver类  
Sender类:  
```java
package com.wbb.demo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wbb.demo.config.RabbitConfig;

@Component
@Slf4j
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String content){
        rabbitTemplate.convertAndSend(RabbitConfig.exchange1,RabbitConfig.routingKey1, content);
    }
}
```
这里rabbitTemplate在注入的时候出现了错误  
找了半天找到一个解决方案  
参考链接:https://baijiahao.baidu.com/s?id=1641259463977037153&wfr=spider&for=pc  

主要就是在配置类中加上rabbitTemplate的声明这里加上@Bean后又出现了新的错误connectionFactory无法注入  
目前还未找到解决办法   
无法注入.png  

```javarabbitTemplate
/**
 * 声明rabbitTemplate
 * @param connectionFactory
 * @return
 */
@Bean
public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate=new RabbitTemplate((connectionFactory));
    return rabbitTemplate;
}
```
Receiver类:  
```java
package com.wbb.demo.rabbitmq;

import com.wbb.demo.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    /**
     * 接收消息的方法,采用消息队列监听机制
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.queue1)
    public void process(String msg) throws InterruptedException{
        log.info("队列1接收到消息"+msg);
    }
}
```
