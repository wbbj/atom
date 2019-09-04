##### Web应用类加载器默认的加载顺序是:  
* 先从缓存中加载;
* 如果没有则从JVM的Bootstrap类加载器加载;
* 否则从当前类加载器加载
  * 按照WEB-INF/classes,WEB-INF/lib的顺序
* 否则从父类加载器加载
  * 加载顺序AppClassLoader,Common,shared.
