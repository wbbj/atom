### Servlet介绍
* Servlet主要负责接收用户请求HttpServletRequest,在doGet(),doPost()中做相应处理,
并回应HttpServletResponse反馈给用户.`**Servlet`需要在web.xml中进行配置
,一个Servlet可以设置多个URL访问.Servlet不是线程安全的.

#### Servlet生命周期:  
* void init()方法:Web容器加载Servlet并将其实例化后,Servlet生命周期开始,容器运行init()方法初始化
* void service()方法:请求到达时调用Servlet的service()方法service()方法会根据需要调用与请求对应的doGet或doPost等方法
* void destroy()方法:当服务器关闭或项目被卸载时服务器会将Servlet实例销毁,此时调用destroy()方法  

get与post的区别:  
* get请求用来从服务器上获得资源,而post是用来向服务器提交数据  
* get将表单中数据按照name=value的形式,添加到action所指向的URL后面,并且两者使用"?"连接,而各个变量之间使用"&"连接
post是将表单中的数据放在HTTP协议的请求头或者消息体中,或者action所指向URL  
* get传输的数据受URL长度限制,最大2048个字符;而post可以传输大量的数据,上传文件通常要使用post方式
* 使用get时参数会显示在地址栏上,如果这些数据不是敏感数据,那么可以使用get,对于敏感数据可以使用post
* get使用MIME类型application/x-www-form-urlencoded的URL编码,文本的格式传递参数,保证被传送的参数由遵循规范的文本组成
