## Java 数据结构
#### 枚举（Enumeration）
```java
import java.util.Vector;
import java.util.Enumeration;

public class EnumerationTester {

   public static void main(String args[]) {
      Enumeration<String> days;
      Vector<String> dayNames = new Vector<String>();
      dayNames.add("Sunday");
      dayNames.add("Monday");
      dayNames.add("Tuesday");
      dayNames.add("Wednesday");
      dayNames.add("Thursday");
      dayNames.add("Friday");
      dayNames.add("Saturday");
      days = dayNames.elements();
      while (days.hasMoreElements()){
         System.out.println(days.nextElement());
      }
   }
}
```

以上实例编译运行结果如下：

`Sunday`  
`Monday`  
`Tuesday`  
`Wednesday`  
`Thursday`  
`Friday`  
`Saturday`   

#### 位集合（BitSet）
一个Bitset类创建一种特殊类型的数组来保存位值。BitSet中数组大小会随需要增加。这和位向量（vector of bits）比较类似。  

这是一个传统的类，但它在Java 2中被完全重新设计    

BitSet定义了两个构造方法。  

第一个构造方法创建一个默认的对象：  

`BitSet() `
第二个方法允许用户指定初始大小。所有位初始化为0。  

`BitSet(int size)`   

```java
import java.util.BitSet;

public class BitSetDemo {

  public static void main(String args[]) {
     BitSet bits1 = new BitSet(16);
     BitSet bits2 = new BitSet(16);

     // set some bits
     for(int i=0; i<16; i++) {
        if((i%2) == 0) bits1.set(i);
        if((i%5) != 0) bits2.set(i);
     }
     System.out.println("Initial pattern in bits1: ");
     System.out.println(bits1);
     System.out.println("\nInitial pattern in bits2: ");
     System.out.println(bits2);

     // AND bits
     bits2.and(bits1);
     System.out.println("\nbits2 AND bits1: ");
     System.out.println(bits2);

     // OR bits
     bits2.or(bits1);
     System.out.println("\nbits2 OR bits1: ");
     System.out.println(bits2);

     // XOR bits
     bits2.xor(bits1);
     System.out.println("\nbits2 XOR bits1: ");
     System.out.println(bits2);
  }
}
```
以上实例编译运行结果如下：  
`Initial pattern in bits1:`  
`{0, 2, 4, 6, 8, 10, 12, 14}`  

`Initial pattern in bits2:`  
`{1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14}`  

`bits2 AND bits1:`  
`{2, 4, 6, 8, 12, 14}`  

`bits2 OR bits1:`  
`{0, 2, 4, 6, 8, 10, 12, 14}`  

`bits2 XOR bits1:`  
`{}`

#### 向量（Vector）
Vector 类支持 4 种构造方法。  
第一种构造方法创建一个默认的向量，默认大小为 10：  
`Vector()`  

第二种构造方法创建指定大小的向量。  
`Vector(int size)`  

第三种构造方法创建指定大小的向量，并且增量用 incr 指定。增量表示向量每次增加的元素数目。  
`Vector(int size,int incr)`  

第四种构造方法创建一个包含集合 c 元素的向量：  
`Vector(Collection c)`  

```java
import java.util.*;

public class VectorDemo {

   public static void main(String args[]) {
      // initial size is 3, increment is 2
      Vector v = new Vector(3, 2);
      System.out.println("Initial size: " + v.size());
      System.out.println("Initial capacity: " +
      v.capacity());
      v.addElement(new Integer(1));
      v.addElement(new Integer(2));
      v.addElement(new Integer(3));
      v.addElement(new Integer(4));
      System.out.println("Capacity after four additions: " +
          v.capacity());

      v.addElement(new Double(5.45));
      System.out.println("Current capacity: " +
      v.capacity());
      v.addElement(new Double(6.08));
      v.addElement(new Integer(7));
      System.out.println("Current capacity: " +
      v.capacity());
      v.addElement(new Float(9.4));
      v.addElement(new Integer(10));
      System.out.println("Current capacity: " +
      v.capacity());
      v.addElement(new Integer(11));
      v.addElement(new Integer(12));
      System.out.println("First element: " +
         (Integer)v.firstElement());
      System.out.println("Last element: " +
         (Integer)v.lastElement());
      if(v.contains(new Integer(3)))
         System.out.println("Vector contains 3.");
      // enumerate the elements in the vector.
      Enumeration vEnum = v.elements();
      System.out.println("\nElements in vector:");
      while(vEnum.hasMoreElements())
         System.out.print(vEnum.nextElement() + " ");
      System.out.println();
   }
}
```
以上实例编译运行结果如下：  

`Initial size: 0`  
`Initial capacity: 3`  
`Capacity after four additions: 5`  
`Current capacity: 5`  
`Current capacity: 7`  
`Current capacity: 9`  
`First element: 1`  
`Last element: 12`  
`Vector contains 3.`  

`Elements in vector:`  
`1 2 3 4 5.45 6.08 7 9.4 10 11 12`  

#### 栈（Stack）
```java
import java.util.*;

public class StackDemo {

    static void showpush(Stack<Integer> st, int a) {
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }

    static void showpop(Stack<Integer> st) {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }

    public static void main(String args[]) {
        Stack<Integer> st = new Stack<Integer>();
        System.out.println("stack: " + st);
        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);
        showpop(st);
        showpop(st);
        showpop(st);
        try {
            showpop(st);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}
```
以上实例编译运行结果如下：  

`stack: [ ]`  
`push(42)`  
`stack: [42]`  
`push(66)`  
`stack: [42, 66]`  
`push(99)`  
`stack: [42, 66, 99]`  
`pop -> 99`  
`stack: [42, 66]`  
`pop -> 66`  
`stack: [42]`  
`pop -> 42`  
`stack: [ ]`  
`pop -> empty stack`  
