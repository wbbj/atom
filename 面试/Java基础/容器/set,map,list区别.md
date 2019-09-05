### List
list是一个有序容器  
允许存储重复的对象  
可以插入多个null元素  
常用实现类有ArrayList,LinkedList  

### Set  
Set是无序容器  
不允许有重复对象  
只允许一个null元素  
Set接口比较流行的实现类有HashSet,TreeSet,后者是通过compara()和comparaTo()定义进行排序的有序容器  

### Map
Map不是Collection的子接口或者实现类,本身就是一个接口,而list和set是Collection的子接口  
Map的每个entry都持有(键)key和(值)value两个对象,其中键必须是唯一的  
Map中可以有多个null值但只能有一个null键  
比较流行的实现类HashMap,TreeMap,Hashtable  
