### Elasticsearch的安装与配置  
据网上了解  
Elasticsearch是一个时时分布式搜索和分析数据的平台.它的流行是因为其强大的功能及易用可扩展  
Elasticsearch支持RESTful操作  


看了教程建议不装最新版.  
* 下载  
进入官网:https://www.elastic.co/cn/downloads/elasticsearch  
![整合Elastisearch官网.png](https://img-blog.csdnimg.cn/20190907210941453.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)  
如上图选择以前的版本,这里我是看的他人的教程,所以下的是他博客教程用的版本  
参考博客:  
[Elasticsearch-SpringBoot整合Elasticsearch
](https://blog.csdn.net/qq_36781505/article/details/89862894)  
选择6.4.1版本  
如图  
![Elastisearch版本.png](https://img-blog.csdnimg.cn/20190907211002402.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)

下载完后解压  

* 在根目录下新建`/data/es/data`和`/data/es/log`
然后打开配置文件  
```bash
vim elasticsearch-6.1.4/config/elasticsearch.yml
```
修改里面的内容  
如图:  
![elasticsearchyml配置.png](https://img-blog.csdnimg.cn/20190907211015178.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)  

## `这里出现问题,port忘记改了后面才发现...  `

* es只能在非root用户下启动可以添加权限,或者添加用户  
在添加用户的时候出了点问题,在写法上与云服务器有点不一样  
于是我就查了下  

```bash
groupadd groupname
useradd -g groupname username
```

```bash
chown -R groupname:username /data
chown -R gourpname:username /usr/local/elasticsearch
```

这是云服务器上的写法  
下面是本地的写法  

```bash
sudo groupadd es
sudo useradd es -g es -p es
```

```bash
sudo chown -R es:es data/
sudo chown -R es:es /usr/local/elasticsearch/
```
进入es目录查看效果  
这里不知道为什么我用不了缩写的`ls -l`也就是`ll`  
结果如下:  

```bash
arthas@arthas-PC:/usr/local/elasticsearch$ ls -l
总用量 448
drwxr-xr-x  3 es es   4096 9月  14  2018 bin
drwxr-xr-x  2 es es   4096 9月   6 20:06 config
drwxr-xr-x  3 es es   4096 9月  14  2018 lib
-rw-r--r--  1 es es  13675 9月  14  2018 LICENSE.txt
drwxr-xr-x  2 es es   4096 9月   6 20:06 logs
drwxr-xr-x 27 es es   4096 9月  14  2018 modules
-rw-r--r--  1 es es 401465 9月  14  2018 NOTICE.txt
drwxr-xr-x  2 es es   4096 9月  14  2018 plugins
-rw-r--r--  1 es es   8511 9月  14  2018 README.textile
```

* 然后就是切换到非root用户下启动es  
这里又出现了问题  

```bash
su es

su:鉴定故障   
```
又折腾了半天
后来找到资料使用:  

```bash
sudo su es
```
切换至普通用户  
但是启动es还是有权限问题错误  

找了半天还是不行  
明天再想办法搞下  

目前的错误:  
![Elasticsearch错误1.png](https://img-blog.csdnimg.cn/20190907211052437.png)  

这个问题是没有给es访问文件的权限,但是前面用`chown -R es:es data/`给过权限了  
不知道是什么原因,然后我换了个方法,直接  

```bash
arthas@arthas-PC:~$ sudo chown -R arthas /data
[sudo] arthas 的密码：
arthas@arthas-PC:~$ sudo chown -R arthas /usr/local/elasticsearch/
```
然后直接进入Elasticsearch目录启动  

```bash
./bin/elasticsearch
```
中间出现了几个无法指定被请求地址的异常  
`Caused by: java.net.BindException: 无法指定被请求的地址`
但是后面有  

```bash
[2019-09-07T15:46:45,209][INFO ][o.e.n.Node               ] [lixue-node-1] stopping ...
[2019-09-07T15:46:45,212][INFO ][o.e.n.Node               ] [lixue-node-1] stopped
[2019-09-07T15:46:45,212][INFO ][o.e.n.Node               ] [lixue-node-1] closing ...
[2019-09-07T15:46:45,226][INFO ][o.e.n.Node               ] [lixue-node-1] closed
[2019-09-07T15:46:45,229][INFO ][o.e.x.m.j.p.NativeController] Native controller process has stopped - no new native processes can be started
```
不知道是否启动成功  
测试一下  
这里我没安装curl,昨天装了后一直有错,还得搞下  
就直接在浏览器输入localhost:9200测试  
发现还是失败  
后来查明原因是前面yml配置文件漏改port了  

如图:  
![Elasticsearch配置改.png](https://img-blog.csdnimg.cn/20190907211125119.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)  

然后再启动  
就成功了  
访问`http://localhost:9200/`  
如图:  
![Elasticsearch启动成功.png](https://img-blog.csdnimg.cn/20190907211134230.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMDM5MDk3,size_16,color_FFFFFF,t_70)  

前面也可以用curl测试  
deepin安装curl

```bash
sudo apt-get install curl
```
然后查看curl版本  

```bash
curl --version
```

到这里es就安装成功了  
