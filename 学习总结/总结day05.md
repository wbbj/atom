## 今日任务：
### ArrayList和LinkedList的区别
* ArrayList和LinkedList是迭代器（Iterator)对象的connection接口下的List接口的实现类。

* ArrayLis和LinkedList底层数据结构不同  
ArrayList底层数据结构是动态数组，所以它可以使用get()方法访问列表中的任意元素。  
而Linkedlist底层数据结构是双向列表，get方法是从一端检查到另一端。所以ArrayList访问速度比LinkedList访问某个元素更快

ArrayList起始容量是10，当数组需要增长时每次容量大概会增长50%，最终会有很大空间被浪费掉。  

当我们需要对一个列表进行大量的插入和删除操作时，当一个元素被加到ArrayList的最开端时，所有元素都会后移，而且ArrayList对于添加操作需要在数组快要装满的时候将所有的数据重新装入一个新的数组，数据移动和复制的开销很大，而LinkedList只是简单的为这个元素分配一个记录，开销是固定的。

因此ArrayList对于随机访问元素比LinkedList有优势，但对于田家河删除操作LinkedList更有优势。
---
### String,StringBuilder和StringBuffer的区别
* String是一个类，是一个应用数据类型。String里面几乎所有的属性都是final，由于它的不可变性导致一系列对字符串进行的拼接，剪裁动作时产生大量无用的中间对象。  
* StringBuffer是为了解决String拼接产生多余对象的问题而提供的一个类，StringBuffer采用了synchronized关键字保证了线程的安全，但开销也大。  
* StringBuilder和StringBuffer功能一样但减少了开销。在单线程下StringBuilder效率更快因为它不需要加锁,所以安全性低。  
* StringBuffer一般在多线程的环境下使用  StringBuilder一般在单线程下使用。  
String一般在操作少量数据时使用

* String的操作会生成一个新的字符串而不是在原有的字符串上进行修改，而StringBuffer和StringBuilder类的对象能被多次的修改并且不产生新的未使用对象。
