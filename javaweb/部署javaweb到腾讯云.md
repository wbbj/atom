### 在腾讯云上部署javaweb并连接数据库
这里我用的环境是idea,用的maven工程建立的javaweb项目  
第一步打开要部署的项目然后如图  
maven部署1.png  
打开后如图
maven部署2.png  
点击install后可能会报错,这里我已经解决过所以就直接放解决方法  
这里的错误是由于找不到ServletAPI  
到官网搜索ServletAPI获取  
这里可以参考我之前写的:  
[maven](https://blog.csdn.net/qq_30039097/article/details/96733740)  
下面是大佬写的
[maven详细内容](https://blog.csdn.net/qq_36781505/article/category/9144615)  
如果有maven没配置好的也可以参考以上链接  

成功后如图:  
maven部署3.png  
之后就可以将war包上传云服务器了,上传方法有很多种,这我用的是filezila软件  
具体方法我就不详述了,下载与使用方法参考:  
[ubuntu下搭建FTP服务器并使用FileZilla上传下载](https://www.jianshu.com/p/87d28ba6772d)  

`注意:war包要上传到tomcat目录下的webapps中去,本地部署也是一样的.`

连接端口我用的是22,教程上面用的时21.在这之前应该云服务器都配置好了,没配置好的话记得在安全组里面开放端口  
这里附上我总结的腾讯云的一些配置,如果有问题可以联系我:  
[腾讯云配置JDK,Mysql,Tomcat以及maven](https://blog.csdn.net/qq_30039097/article/details/97325446)  

然后就是在浏览器上测试了  
`***.***.*.**:8080/war包名/`你的公网IP+:8080+/上传的war包名/  
例如我最近不熟的的是`129.211.1.53:8080/test/`  

注意部署之前要把相关数据链接改成云服务的链接,比如数据库的url,user和password等等.

---
然后就是数据库部署的问题:  
这里我用了网上的使用navicat  
详情参考:https://blog.csdn.net/kkfd1002/article/details/80247882  

mysql开启远程访问权限  
`mysql -uroot -p`
`use mysql;`
//Mysql默认不允许远程登录，所以需要开启远程访问权限
`select user,authentication_string,host from user;`  
`update user set host = '%' where user = 'root';`  
`FLUSH PRIVILEGES;`  
//navicat 连接 mysql 出现`Client does not support authentication protocol requested by server`  
`alter user 'root'@'%' identified with mysql_native_password by '密码';`  

腾讯云放通防火墙3306  
`firewall-cmd --zone=public --add-port=3306/tcp --permanent`  
重启防火墙并查看是否生效  
`firewall-cmd --reload`	#重启firewall  
`firewall-cmd --list-ports`	#查看已经开放的端口  

腾讯云放通端口有点慢,请耐心等待,可以到网上找在线端口扫描检测下.  
反正我是等了好久...  
