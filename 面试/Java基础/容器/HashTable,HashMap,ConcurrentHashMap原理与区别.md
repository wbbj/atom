
### HashTable
底层:数组+链表  
key和value都不能为null,线程安全,但是实现安全的方式是在修改数据时锁住整个HashTable,效率低  
初始size为11,扩容2*oldsize+1  

### HashMap
底层:数组链表  
可以存储null键和null值,线程不安全:插入元素后才判断是否扩容,可能无效扩容  
初始size:16,扩容2*oldsize  
元素总数超过entry数组的75%,触发扩容操作  

###  ConcurrentHashMap  
底层:分段数组+链表  
线程安全,通过把整个Map分为N个Segment,可以提供相同的线程安全,但是效率提升N倍,默认16倍  
HashTable的synchronized是针对整张Hash表的,每次锁住整张表让线程独占,ConcurrentHashMap允许多个修改操作并发进行,其关键在于使用了锁分离技术  
扩容:段内扩容  
