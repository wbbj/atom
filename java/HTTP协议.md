### HTTP协议　　
Hyper Text Transfer Protocol（超文本传输协议）的缩写`
HTTP默认端口号为80，但是你也可以改为8080或者其他端口。

###### HTTP 协议的 8 种请求类型介绍:

* `GET`	请求指定的页面信息，并返回实体主体。

* `HEAD`	类似于 GET 请求，只不过返回的响应中没有具体的内容，用于获取报头

* `POST`	向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST 请求可能会导致新的资源的建立和/或已有资源的修改。

* `PUT`	从客户端向服务器传送的数据取代指定的文档的内容。

* `DELETE`	请求服务器删除指定的页面。

* `CONNECT`	HTTP/1.1 协议中预留给能够将连接改为管道方式的代理服务器。

* `OPTIONS`	允许客户端查看服务器的性能。

* `TRACE`	回显服务器收到的请求，主要用于测试或诊断。

* `PATCH`	是对 PUT 方法的补充，用来对已知资源进行局部更新 。

###### 使用GET来传递数据的实例：
客户端请求：  

`GET` /hello.txt HTTP/1.1  
`User-Agent:` curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3  
`Host:` www.example.com  
`Accept-Language:` en, mi  

服务端响应:  
`HTTP/1.1` 200 OK  
`Date:` Mon, 27 Jul 2009 12:28:53 GMT  
`Server:` Apache  
`Last-Modified:` Wed, 22 Jul 2009 19:15:56 GMT  
`ETag:` "34aa387-d-1568eb00"  
`Accept-Ranges:` bytes  
`Content-Length:` 51  
`Vary:` Accept-Encoding  
`Content-Type:` text/plain  

输出结果：  
`Hello World! My payload includes a trailing CRLF.  `

###### HTTP 响应头信息:
HTTP请求头提供了关于请求，响应或者其他的发送实体的信息。  

* `Allow`
服务器支持哪些请求方法（如GET、POST等）。

* `Content-Encoding`
文档的编码（Encode）方法。只有在解码之后才可以得到Content-Type头指定的内容类型。

* `Content-Length`
表示内容长度。只有当浏览器使用持久HTTP连接时才需要这个数据。通过byteArrayStream.writeTo(response.getOutputStream()发送内容。

* `Content-Type`
表示后面的文档属于什么MIME类型。Servlet默认为text/plain，但通常需要显式地指定为text/html。

* `Date`
当前的GMT时间。你可以用setDateHeader来设置这个头以避免转换时间格式的麻烦。

* `Expires`
应该在什么时候认为文档已经过期，从而不再缓存它？

* `Last-Modified`
文档的最后改动时间。客户可以通过If-Modified-Since请求头提供一个日期，该请求将被视为一个条件GET，只有改动时间迟于指定时间的文档才会返回，否则返回一个304（Not Modified）状态。Last-Modified也可用setDateHeader方法来设置。

* `Location`
表示客户应当到哪里去提取文档。Location通常不是直接设置的，而是通过HttpServletResponse的sendRedirect方法，该方法同时设置状态代码为302。

* `Refresh`
表示浏览器应该在多少时间之后刷新文档，以秒计。除了刷新当前文档之外，你还可以通过setHeader("Refresh", "5; URL=http://host/path")让浏览器读取指定的页面。  
Refresh的意义是"N秒之后刷新本页面或访问指定页面"而发送204状态代码则可以阻止浏览器继续刷新，

* `Server`
服务器名字。Servlet一般不设置这个值，而是由Web服务器自己设置。

* `Set-Cookie`
设置和页面关联的Cookie。应使用HttpServletResponse提供的专用方法addCookie。

* `WWW-Authenticate`
在包含401（Unauthorized）状态行的应答中这个头是必需的。  
注意Servlet一般不进行这方面的处理，而是让Web服务器的专门机制来控制受密码保护页面的访问。


###### 下面是常见的HTTP状态码：

* `200 `- 请求成功  
* `301` - 资源（网页等）被永久转移到其它URL  
* `404 `- 请求的资源（网页等）不存在  
* `500` - 内部服务器错误  

###### HTTP content-type:

* .asp	`text/asp`
* .cml	`text/xml`
* .dcd	`text/xml`
* .gif	`image/gif`
* .html	`text/html`
* .htx	`text/html`
* .img	`application/x-img`
* .java	`java/*`
* .jpg	`application/x-jpg`
* .jsp	`text/html`
* .jsp	`text/html`
* .net	`image/pnetvue`
* .png	`application/x-png`
* .ppt	`application/x-ppt`
* .apk	`application/vnd.android.package-archive`
* .xml	`text/xml`
* .txt	`text/plain`
* .png	`image/png`
* .mp3	`audio/mp3`
* .math	`text/xml`
* .js	`application/x-javascript`
