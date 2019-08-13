### Maven的安装  
本人使用的是deepin的环境，所以就写下linux的安装与配置方法  

* 首先下载maven，可以到官网下载  
或者使用指令：  
```bash
$ sudo apt install maven
```
* 然后使用:  
```bash
mvn -version
```
查看下载到的目录  
* 在用户目录下建立repostory存放各种jar包和插件  
```bash
$ mkdir repostory
```
* 然后在maven目录下的conf目录中的setting.xml中配置  
具体配置可以参考此博客[maven](https://blog.csdn.net/qq_36781505/article/category/9144615)

* 然后就是maven中添加依赖的方法  
这是maven的目录结构  
maven1.png  
在pom.xml中进行配置  
如图:  
maven2.png  
其中  
```html
<!-- https://mvnrepository.com/artifact/org.json/json -->
      <dependency>
          <groupId>org.json</groupId>
          <artifactId>json</artifactId>
          <version>20160810</version>
      </dependency>
```
可以到官网获取  
如图:  
maven3.png  
maven4.png  
maven5.png  
