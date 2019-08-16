### cookie
在Servlet中常用Cookie和Seesion完成会话跟踪  

cookie原理:浏览器访问web服务器时，web服务器会在http响应消息头中附带一个小文本文件传送给浏览器，然后浏览器每次访问都会将cookie传回web服务器  

---

在JavaWEB规范中使用Cookie类代表cookie  
* 创建一个Cookie对象并调用response的一个方法把Cookie传给客户端
```java
<%
    //获取Cookie
    Cookie[] cookies=request.getCookies();
    if(cookies!=null&&cookies.length>1){
        for(Cookie cookie:cookies){
            //获取cookie的name和value
            out.print(cookie.getName()+":"+cookie.getValue());
            out.print("<br>");
        }
    }else {
        out.print("没有Cookie正在创建并返回");
        //1.创建一个Cookie对象
        Cookie cookie = new Cookie("name", "wbb");

        //2.调用response的一个方法吧Cookie传给客户端
        response.addCookie(cookie);
    }
%>
```

默认情况下是一个会话级别的Cookie存储在浏览器内存中,可以使用MaxAge给出Cookie的存活时间,为0则立即删除Cookie  

setMaxAge:设置Cookie的最大时效,以秒为单位,为零则立即删除Cookie,为负数则不存储  
`cookie.setMaxAge(10);`

使用Cookie可以实现自动登录  

* 从浏览器读取Cookie  
获取Cookie  
```java
Cookie[] Cookies=request.getCookies();
if(cookies!=null&&cookies.length>0){
  for(Cookie cookie:cookies){
    out.print(cookie.getName()+":"+cookie.getValue);
    out.print("<br>");
  }
}
```
