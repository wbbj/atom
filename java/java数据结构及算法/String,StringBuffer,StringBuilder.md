###### 基本介绍：
* String是一个类，是一个应用数据类型。String里面几乎所有的属性都是final，由于它的不可变性导致一系列对字符串进行的拼接，剪裁动作时产生大量无用的中间对象。  
* StringBuffer是为了解决String拼接产生多余对象的问题而提供的一个类，StringBuffer采用了synchronized关键字保证了线程的安全，但开销也大。  
* StringBuilder和StringBuffer功能一样但减少了开销。在单线程下StringBuilder效率更快因为它不需要加锁,所以安全性低。  
* StringBuffer一般在多线程的环境下使用  StringBuilder一般在单线程下使用。  
String一般在操作少量数据时使用

* String的操作会生成一个新的字符串而不是在原有的字符串上进行修改，而StringBuffer和StringBuilder类的对象能被多次的修改并且不产生新的未使用对象。
---
StringBuffer与StringBuilder中常用方法：  
append()在末尾追加字符。  
reserse()字符反转。    
delete()删除。  
insert()插入。  
replace()替换。  
与String类的方法似的方法：  
capacity()返回容量   
indexOf()返回第一次出现的指定字符串的索引  
toString()返回此序列中数据的字符串形式  
