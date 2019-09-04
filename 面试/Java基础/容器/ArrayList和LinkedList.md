* ArrayList和LinkedList是迭代器（Iterator)对象的connection接口下的List接口的实现类。

* ArrayLis和LinkedList底层数据结构不同  
ArrayList底层数据结构是动态数组，所以它可以使用get()方法访问列表中的任意元素。  
而Linkedlist底层数据结构是双向列表，get方法是从一端检查到另一端。所以ArrayList访问速度比LinkedList访问某个元素更快

ArrayList起始容量是10，当数组需要增长时每次容量大概会增长50%，最终会有很大空间被浪费掉。  

当我们需要对一个列表进行大量的插入和删除操作时，当一个元素被加到ArrayList的最开端时，所有元素都会后移，而且ArrayList对于添加操作需要在数组快要装满的时候将所有的数据重新装入一个新的数组，数据移动和复制的开销很大，而LinkedList只是简单的为这个元素分配一个记录，开销是固定的。

因此ArrayList对于随机访问元素比LinkedList有优势，但对于添加和删除操作LinkedList更有优势。

---
四种遍历方式
* 普通遍历： 效率最低     
普通的for循环
* 提取list长度： ArrayList时效率最高Linked时极低  
先定义n=list.size();再用普通for循环  
* for Each遍历： Array时遍历次数多效率低，Linked时遍历多效率极高  
for（Object s：list)输出s
* 迭代器iterator遍历：效率高  
Iterator<string> iterator=list.iterator();  
while(iterator.hasnext())输出iterator.next();

---
##### ArrayList中常用方法  
* ensureCapacity(int minCapacity):设置ArrayList的容量，只有给定的容量大于List当前容量才会执行扩容。
* trimToSize():设置容量大小为size属性对应的大小
* indexOf(Object o):返回第一次出现给定值的数组下标。如果不存在返回-1.
* contains(Object o):判断该ArrayList是否包含指定的内容。
* lastIndexOf(Object):返回查找元素最后一次的位置
* toArray():返回ArrayList中存储的数据元素  
格式：  
Object[] array=list.toArray();  
System.out.println(Arrays.toString(array));  
* remove(Object o):删除ArrayList中给定的元素  
删除的是第一次出现的元素。  

##### LinkedList基本用法
* .getFirst()和.getLast()获取链表的第一个和最后一个元素  
* for(String str:list){  
  System.out,print(str);  
}获取链表元素  
* contains()判断是否存在某元素
* set(,)替换元素，前一个参数被后一个替换
