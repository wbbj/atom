### session
session指用来在客户端和服务器端之间保持状态的解决方案,有时也指解决方案的存储结构  
HTTP协议是一种无状态的协议web服务器本身不能识别出那些请求是同一个浏览器发出的，浏览器的每一次请求都是完全孤立的。  
作为web服务器必须能够采用一种机制来唯一的标示一个用户，同时记录该用户的状态。  
借助会话状态，web服务器能够把属于同一会话中的一系列的请求和响应过程关联起来。  
浏览器会对每个请求消息用SeesionID进行标识  

* 获取sessionid的方式:  

```java
<%= session.getId()%>
<%
    Cookie cookie=new Cookie("JSESSIONID",session.getId());
    cookie.setMaxAge(20);
    response.addCookie(cookie);
%>
```

* session="false"表示当前JSP页面禁用session隐含变量,但可以使用其他的显式的httpsession对象  
如`<%HttpSession session=request.getSession(false);%>`  

创建HttpSession:  
若Servlet是客户端访问的第一个WEB应用的资源,则只有调用了request.getSession()或request.getSession(true)才会创建HttpSession对象  
若当前JSP不是客户端访问的当前WEB应用的第一个资源且其他页面已经创建了一个HttpSession对象,则服务器不会为当前创建对象,而会把和当前会话关联的那个对象返回给当前页面

在Servlet中如何获取HttpSession对象:request.getSession(boolean create),若Create为false,若没有和当前JSP也绵绵关联的HttpSession对像则返回false,若有则返回true.  
若为true,一定返回一个HttpSession对象,若没有和当前JSP页面关联的HttpSession对象,则服务器创建一个新的HttpSession对象返回,若有,则返回关联的.  
request.getSession()等同于request.getSession(true)  

销毁HttpSession对象:  
1.直接调用HttpSession的invalidate()方法,该方法使HttpSession失效  
2.服务器卸载当前WEB应用  
3.超出HttpSession的过期时间.  
设置过期时间的方式:  
1).`session.setMaxInactiveInterval(5);`设置过期时间单位为秒  
2).在web.xml中设置过期时间,单位为分钟  
3).<session-config>  
<session-timeout>30</session-timeout>  
</session-config>  

并不是关闭浏览器就销毁了HttpSession.  

---
HttpSession常用方法:  
SessionID:`<%=session.getId()%>`获取sessionID  
IsNew:`<%=session.isNew()%>`是否是新的SessionID  
`MaxInactiveInterval: <%=session.getMaxInactiveInterval()%>`获取最大过期时间  
`LastAccessTime:<%=session.getLastAccessedTime()%>`  
`HttpSession1.invalidate()`销毁HttpSession对象  

利用URL重写实现Session跟踪:  
`<%=response.encodeURL("login.jsp")%>`  
