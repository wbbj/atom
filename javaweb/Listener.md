### Listener
Servlet监听器简介:  
Servlet监听器,用于监听Web应用程序中的ServletContext,HttpSession和ServletRequest等域对象的创建与销毁事件,以及监听这些域对象中的属性发生修改的事件.  

监听器分类:  
监听域对象自身的创建和销毁事件监听器  
监听域对象中的属性增加和删除事件监听器  
监听绑定到HttpSession域中的某个对象状态的事件监听器  

第一二种需要在web.xml中进行注册  

---
##### 监听域对象自身事件监听器:ServletContextListener,HttpSessionListener,ServletRequestListener   
ServletContextListener:  
监听ServletContext对象创建或销毁  
创建一个实现了ServletContextListener的类并实现其中的两个方法:  
对象被创建即当前web应用被加载时调用  
`public void contextInitialized(ServletContextEvent sce)`  
对象被销毁之前即当前web被卸载时调用  
`public void contextDestroyed(ServletContextEvent sce)`  
然后在web.xml中配置  

```html
<listener>  
        <listener-class>com.wbb.javaweb.HelloServletContextListener</listener-class>  
</listener>  

HttpSessionListener与ServletContextListener类似  

request,session,application的生命周期:  
* request:当发起一个请求时时创建,当一个响应返回时销毁,请求转发的过程是一个对象    
重定向是两个请求  
* session:当第一次访问WEB应用的一个JSP或者Servlet时,且该JSP或Servlet还需要创建session对象,此时会创建一个session对象,
session销毁:session过期,或者调用invalidate方法,或当前web应用被卸载  
关闭浏览器不意味着session被销毁,还可以通过SessionID找到session.  
* application:web应用被加载时创建,web应用被卸载时被销毁,存在于整个web应用的生命周期中.

---
#####  域对象属性变更事件监听器:`XxxAttributeListener`  
监听ServletContext,HttpSession,ServletRequest中属性的增删改.  

---
##### 感知Session绑定的事件监听器: 不需要在web.xml中配置    
两个接口:  
* HttpSessionBindingListener接口:  
valueBound()和valueUnbound()  

* HttpSessionActivationListener接口;  
sessionDidActivate()活化之后被调用  
sessionWillPassivate()钝化之前被调用  

活化:从磁盘中读取session对象  
钝化:向磁盘中写入session对象  
