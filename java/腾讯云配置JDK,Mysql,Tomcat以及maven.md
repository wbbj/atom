<h3 id='mulu'> 目录</h3>  

* [在本地终端使用ssh远程连接腾讯云](#1)  
* [腾讯云安装并配置jdk](#2)  
* [安装并配置tomcat](#3)  
* [安装并配置mysql](#4)  
* [安装并配置maven](#5)  

---

<h3 id='1'>在本地终端使用ssh远程连接腾讯云</h3>
首先用过github的在本地应该都创建过ssh秘钥了  
没创建的话使用指令  
```bash
$ ssh-keygen -t rsa
```
其中rsa是创建的文件名,然后使用  
```bash
$ cd .ssh
```
进入.ssh目录下输入ls可以看到里面有个`id_rsa.pub`文件打开这个文件  
```bash
~/.ssh$ vim id_rsa.pub
```
复制里面的内容   
打开腾讯云选择ssh密钥,创建密钥  
如图:  
![tenxunyun1.png](https://img-blog.csdnimg.cn/20190726004945371.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)
然后选择已有公钥如图:  
![tenxunyun2.png](https://img-blog.csdnimg.cn/20190726004951941.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)  
选择创建的密钥,点击上面的绑定实例如图:  
![tenxunyun3.png](https://img-blog.csdnimg.cn/20190726004957322.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)  

实例开机  
然后在终端中输入  
```bash
$ ssh root@***.***.*.**
```
其中@后面是你的公网ip  
[返回目录](#mulu)  

---
<h3 id='2'>腾讯云安装并配置jdk</h3>
首先下载jdk8,[下载链接](https://www.oracle.com/technetwork/cn/java/javase/downloads/index.html)

用wget 加上面进入后获取的下载链接,可能要登录,或者另外找资源下载到本地用filezilla上传到服务器中  

我是直接在放的目录下下载的
```bash
# cd /usr/local/
```  
然后解压
```bash
# tar -zxvf jdk-8u212-linux-x64.tar.gz
```
然后打开profile在末尾添加环境变量  
```bash
# vim /etc/profile
```
下面是要添加的环境变量,地址要写自己解压的地址
`JAVA_HOME=/usr/local/jdk1.8.0_212
JRE_HOME=/usr/local/jdk1.8.0_212/jre
CLASS_PATH=.:$JAVA_HOME/lib
PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
export JAVA_HOME JRE_HOME PATH CLASS_PATH`  

然后按`esc`输入`:x`保存退出  
然后输入查看是否配置成功  
```bash
# java -version
```
下面是输入后显示成功的效果  
`# java -version
java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b10)
Java HotSpot(TM) 64-Bit Server VM (build 25.212-b10, mixed mode)`

[返回目录](#mulu)  

---
<h3 id='3'>安装并配置tomcat</h3>
下载tomcat,这里我下载的是8.5.43  
[下载链接](https://tomcat.apache.org/download-80.cgi)  
记得要选`Core:`后面的`tar.gz`  

然后解压
```bash
# tar -zxvf apache-tomcat-8.5.43.tar.gz
```
然后配置环境变量  
打开tomcat目录下bin下的startup.sh同时shutdown目录下也要放
```bash
# vim /usr/local/apache-tomcat-8.5.43/bin/startup.sh
```
`export JAVA_HOME=/usr/local/jdk1.8.0_212
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:%{JAVA_HOME}/lib:%{JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$path
export TOMCAT_HOME=/usr/local/apache-tomcat-8.5.43
`  
然后就是测试
进入tomcat目录下bin目录  
```bash
# cd /usr/local//apache-tomcat-8.5.43/bin/
```
输入
```bash
# ./startup.sh
```
显示  
`Using CATALINA_BASE:   /usr/local/apache-tomcat-8.5.43
Using CATALINA_HOME:   /usr/local/apache-tomcat-8.5.43
Using CATALINA_TMPDIR: /usr/local/apache-tomcat-8.5.43/temp
Using JRE_HOME:        /usr/local/jdk1.8.0_212/jre
Using CLASSPATH:       /usr/local/apache-tomcat-8.5.43/bin/bootstrap.jar:/usr/local/apache-tomcat-8.5.43/bin/tomcat-juli.jar
Tomcat started.`  
表明tomcat启动成功  
然后在浏览器输入你腾讯云的公网ip:8080也就是`***.***.*.**:8080`如果能进入tomcat启动的页面则表示配置成功不行有两个可能一种是你环境变量地址写错,一种是腾讯云没有放通8080端口  

放通端口的方法打开安全组新建一个放通全部端口的然后点击修改规则点即关联实例,关联你的实例,
如果还不行那就在修改规则里面再添加一个规则TCP:8080,要记得入站出站都添加.  

[返回目录](#mulu)  

---
<h3 id='4'>安装并配置mysql</h3>
先安装MySQL Yum Repository
[下载地址](https://dev.mysql.com/downloads/file/?id=484922)  
这是我下的版本也可以选择其他版本  
右击No thanks, just start my download.获取下载链接  
然后  
```bash
# wget https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
```
下载完成获得下载的文件然后输入 rpm -ivh +刚刚下载的文件名
```bash

```
然后安装mysql   
```bash
# yum install mysql-community-server  
```
然后启动mysql服务  
```bash
# systemctl start mysqld
```
设置开机启动  
```bash
# systemctl enable mysqld
# systemctl daemon-reload
```
启动之后可以在/var/log/mysqld.log中找到初始密码  
使用指令  
```bash
# grep 'temporary password' /var/log/mysqld.log
```
获取初始密码  
`2019-07-26T13:00:21.018859Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: s9w6P;._u#Ys`
其中`s9w6P;._u#Ys`就是初始密码  

然后用这个密码登录mysql  
```bash
# mysql -u root -p
```
修改登录密码  
MySQL 5.7.6版本之后使用:  
`ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass';`
MySQL 5.7.5之前使用:  
`SET PASSWORD FOR 'root'@'localhost' = PASSWORD('MyNewPass');`

其中密码不能设置太简单密码必须要包含大小写字符和数字，特殊字符也可以用，太简单会提示错误：Your password does not satisfy the current policy requirements.  

最后修改/etc/my.cnf配置文件，在[mysqld]下添加编码配置，如下所示：  
`[mysqld]
character_set_server=utf8
init_connect=’SET NAMES utf8’`  

最后重启mysql  
```bash
# systemctl restart mysqld
```
[返回目录](#mulu)  

---
<h3 id='5'>安装并配置maven</h3>
安装官网:http://maven.apache.org/download.cgi

使用wget命令  
```bash  
# wget http://mirror.bit.edu.cn/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz
```
解压
```bash
# tar -zxvf apache-maven-3.6.1-bin.tar.gz
```

移动到usr/local/目录下
```bash
# mv apache-maven-3.5.2 /usr/local/
```
配置环境变量  

```bash
# vim /etc/profile
```
在最后加上
`export PATH=/usr/local/apache-maven-3.6.1/bin:$PATH`  

最重要的一步使信息生效  
```bash
# source /etc/profile
```
输入 mvn -v查看版本信息
如下:  
`Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T03:00:29+08:00)
Maven home: /usr/local/apache-maven-3.6.1
Java version: 1.8.0_212, vendor: Oracle Corporation, runtime: /usr/local/jdk1.8.0_212/jre
Default locale: zh_CN, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-693.el7.x86_64", arch: "amd64", family: "unix"`

得到配置信息证明安装成功  

[返回目录](#mulu)  
