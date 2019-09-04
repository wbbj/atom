### lombok
前段时间看别人项目的时候,发现项目里面的pojo用到了@Data,并且里面没有构造方法,只有属性,  
这时候我已经发现应该是@Data的效果就是自动生成这些构造方法  
然而我自己用的时候发现一直报错  
后来才知道要用这个注解要先添加Lombok插件  

添加方法:  
在idea中的setting中打开plugins选项,然后搜索插件Lombok下载
如图:  
lombok.png
