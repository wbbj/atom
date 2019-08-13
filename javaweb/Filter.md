### Filter
* 概念:javaweb的一个重要组件,可以对发送到servlet的请求进行拦截并对响应也进行拦截  
Filter是实现了Filter接口的java类  
Filter需要在web.xml文件中进行配置和映射  

* 创建Filter方式:  
创建一个Filter类:实现Filter接口:publicclass HelloFilter implements Filter
在web.xml文件中配置注册并映射该Filter  

* Filter相关API:  
filter接口  init()类似于Servlet的init()方法在创建Filter对象时立即被调用,对当前的Filter进行初始化,只被调用一次  
FilterConfig类似于ServletConfig,可以在web.xml文件中配置当前Filter的初始化参数,配置方式也和Servlet类似  

Filterchain:Filter链,多个Filter可以构成一个Filter链  
doFilter(ServletRequest request,ServletResponse response):把请求传给Filter链的下一个Filter若当前Filter是Filter链的最后一个FIlter,将把请求给到目标Servlet(或JSp)

多个FIlter拦截的顺序和<FIlter-mapping>配置的顺序有关,靠前的先调用  

destroy()只被调用一次,释放占用资源,销毁之前被调用  

* 映射Filter  
<Filter-mapping>元素用于设置一个Filter所负责拦截的资源.有两种方式指定拦截的资源:Servlet名称和资源访问的请求路径(url样式)  
子元素:  
<Filter-name>用于设置Filter的注册名称该值必须是在<filter>元素中声明过得过滤器的名字  
<url-pattern>设置Filter所拦截的请求路径  
<servlet-name>指定过滤器所拦截的Servlet名称  
<dispatcher>指定过滤器所拦截的资源被Servlet容器调用的方式默认request,可以设置多个.   

filter应用:  
* 禁止浏览器缓存当前页面的三个响应头:  
response.setDataHeader("Expires",-1);  
response.setHeader("Cache-Contrel","no-cache");  
response.setHeader("Pragma","no-cache");  
* 字符编码过滤器:配置参数encoding.  
request.setCharacterEncoding("UTF-8")  
* 检验用户是否登录的过滤器  
