### 今天的任务：
1005 继续（3n+1）猜想  
1006 换个格式输出整数  
1007 素数对猜想  
1008 数组元素循环右移问题  
1009 说反话  

任务都已完成，已经能够熟练使用 `Scanner in =new Scanner(System.in);`来声明各种存储用户
输入的变量。  
* 在做1005时对题意理解错误将覆盖数与关键数弄反，在判断如何去除覆盖字同时输出
关键字遇到困难。  
* 1006中没遇到什么困难。  
* 1007中如何判断素数并调用遇到困难。  
* 1008中一开始没想太多，先是调换前后位置再掉换中后位置逻辑混乱。后面在做的过程中忽略了位
移量大于数组长度的问题。后都已解决只是写法有些繁琐，不会使用特殊标量。  
* 1009中如何将间隔的字符串传入数组遇到困难。


###### git的用法：
* pwd命令用于显示当前目录。这个仓库位于/c/Users/Administrator。  
`$ mkdir learngit`  
`$ cd learngit`    
`$ pwd `   
`/c/Users/Administrator`  

* 将目录变为git可管理的目录  
`$ git init `   
`Reinitialized existing Git repository in C:/Users/Administrator/.git/`  
* 添加文件  
`$ git add readme.txt`  可反复多次使用，添加多个文件  
`$ git commit -m "wrote a readme file"`  
* 克隆本地库  
`$ git clone git@github.com:wbbj/gitskills.git` 其中wbbj是自己的地址  

使用`$ git push -u origin master`可以将atom中的笔记上传git，在这之间要右击存储atom文件的文件点击 `git bush hear`
