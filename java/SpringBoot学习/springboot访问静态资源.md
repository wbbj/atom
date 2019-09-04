### springboot访问静态资源
springboot访问静态资源有两种方式  
1.从classpath/static的目录  
目录名称必须是static  
目录结构:  
springboot访问静态资源方式1.png  

html代码示例:  
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>静态资源访问方式1</title>
</head>
<body>
静态资源访问方式1
<hr/>
<img src="images/AllHeroes_4K.jpg">
</body>
</html>
```
2.ServletContext根目录下  
此方法就是在src目录下main目录下的webapp目录  
没有的话新建一个  
src/main/webapp  
名称必须为webapp  
目录结构:  
springboot访问静态资源方式2.png  
