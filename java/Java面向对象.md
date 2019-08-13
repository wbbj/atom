## Java 继承  
#### 类的继承格式  
在 Java 中通过 extends 关键字可以申明一个类是从另外一个类继承而来的，一般形式如下：  

类的继承格式  
`class 父类 {`  
`}`  
`class 子类 extends 父类 {`  
`}`  

继承方法实例：　　
公共父类：　
```java
public class Animal {
    private String name;  
    private int id;
    public Animal(String myName, int myid) {
        name = myName;
        id = myid;
    }
    public void eat(){
        System.out.println(name+"正在吃");
    }
    public void sleep(){
        System.out.println(name+"正在睡");
    }
    public void introduction() {
        System.out.println("大家好！我是"         + id + "号" + name + ".");
    }
}
```

这个Animal类就可以作为一个父类，然后企鹅类和老鼠类继承这个类之后，就具有父类当中的属性和方法，子类就不会存在重复的代码，维护性也提高，代码也更加简洁，提高代码的复用性（复用性主要是可以多次使用，不用再多次写同样的代码）   
继承之后的代码：  

企鹅类：

```java
public class Penguin extends Animal {
    public Penguin(String myName, int myid) {
        super(myName, myid);
    }
}
```

老鼠类：  

```java
public class Mouse extends Animal {
    public Mouse(String myName, int myid) {
        super(myName, myid);
    }
}
```

继承支持单继承，多重继承，不同类继承同一个类。不支持多继承。

#### 关键字:  

* extends关键字  
在 Java 中，类的继承是单一继承，也就是说，一个子类只能拥有一个父类，所以 extends 只能继承一个类。  

* implements关键字  
使用 implements 关键字可以变相的使java具有多继承的特性，使用范围为类继承接口的情况，可以同时继承多个接口（接口跟接口之间采用逗号分隔）。  


例子
```java
public class Animal {
    private String name;   
    private int id;
    public Animal(String myName, String myid) {
        //初始化属性值
    }
    public void eat() {  //吃东西方法的具体实现
     }
    public void sleep() { //睡觉方法的具体实现  
    }
}
public class Penguin  extends  Animal{
}

```
implements 关键字  

```java
public interface A {
    public void eat();
    public void sleep();
}

public interface B {
    public void show();
}

public class C implements A,B {
}
```

* super 与 this 关键字

super关键字：我们可以通过super关键字来实现对父类成员的访问，用来引用当前对象的父类。  

this关键字：指向自己的引用。

实例:

```java
class Animal {
  void eat() {
    System.out.println("animal : eat");
  }
}

class Dog extends Animal {
  void eat() {
    System.out.println("dog : eat");
  }
  void eatTest() {
    this.eat();   // this 调用自己的方法
    super.eat();  // super 调用父类方法
  }
}

public class Test {
  public static void main(String[] args) {
    Animal a = new Animal();
    a.eat();
    Dog d = new Dog();
    d.eatTest();
  }
}
```

* final关键字   
final 关键字声明类可以把类定义为不能继承的，即最终类；或者用于修饰方法，该方法不能被子类重写：  

---

## Java 重写(Override)与重载(Overload)
### 重写(Override)  
在面向对象原则里，重写意味着可以重写任何现有方法。实例如下：  

TestDog.java 文件代码：  
```java
class Animal{
   public void move(){
      System.out.println("动物可以移动");
   }
}

class Dog extends Animal{
   public void move(){
      System.out.println("狗可以跑和走");
   }
}

public class TestDog{
   public static void main(String args[]){
      Animal a = new Animal(); // Animal 对象
      Animal b = new Dog(); // Dog 对象

      a.move();// 执行 Animal 类的方法

      b.move();//执行 Dog 类的方法
   }
}
```
以上实例编译运行结果如下：  
`动物可以移动`  
`狗可以跑和走`  

Super关键字的使用  
当需要在子类中调用父类的被重写方法时，要使用super关键字。  

TestDog.java 文件代码：  
```java
class Animal{
   public void move(){
      System.out.println("动物可以移动");
   }
}

class Dog extends Animal{
   public void move(){
      super.move(); // 应用super类的方法
      System.out.println("狗可以跑和走");
   }
}

public class TestDog{
   public static void main(String args[]){

      Animal b = new Dog(); // Dog 对象
      b.move(); //执行 Dog类的方法

   }
}
```
以上实例编译运行结果如下：  

`动物可以移动`  
`狗可以跑和走`  

### 重载(Overload)  
重载规则:  

被重载的方法必须改变参数列表(参数个数或类型不一样)；  
被重载的方法可以改变返回类型；  
被重载的方法可以改变访问修饰符；  
被重载的方法可以声明新的或更广的检查异常；  
方法能够在同一个类中或者在一个子类中被重载。  
无法以返回值类型作为重载函数的区分标准。  

```java
public class Overloading {
    public int test(){
        System.out.println("test1");
        return 1;
    }

    public void test(int a){
        System.out.println("test2");
    }   

    //以下两个参数类型顺序不同
    public String test(int a,String s){
        System.out.println("test3");
        return "returntest3";
    }   

    public String test(String s,int a){
        System.out.println("test4");
        return "returntest4";
    }   

    public static void main(String[] args){
        Overloading o = new Overloading();
        System.out.println(o.test());
        o.test(1);
        System.out.println(o.test(1,"test3"));
        System.out.println(o.test("test4",1));
    }
}
```
---

## Java 多态  
多态的实现方式  
方式一：重写：  
这个内容已经在上一章节详细讲过，就不再阐述，详细可访问：Java 重写(Override)与重载(Overload)。  
方式二：接口  
1. 生活中的接口最具代表性的就是插座，例如一个三接头的插头都能接在三孔插座中，因为这个是每个国家都有各自规定的接口规则，有可能到国外就不行，那是因为国外自己定义的接口类型。  

2. java中的接口类似于生活中的接口，就是一些方法特征的集合，但没有方法的实现。具体可以看 java接口 这一章节的内容。  

方式三：抽象类和抽象方法  

---
## Java 抽象类
抽象类总结规定  
1. 抽象类不能被实例化(初学者很容易犯的错)，如果被实例化，就会报错，编译无法通过。只有抽象类的非抽象子类可以创建对象。  

2. 抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类。  

3. 抽象类中的抽象方法只是声明，不包含方法体，就是不给出方法的具体实现也就是方法的具体功能。  

4. 构造方法，类方法（用 static 修饰的方法）不能声明为抽象方法。  

5. 抽象类的子类必须给出抽象类中的抽象方法的具体实现，除非该子类也是抽象类。  

---

## Java 封装
实例
让我们来看一个java封装类的例子：

EncapTest.java 文件代码：
```java
/* 文件名: EncapTest.java */
public class EncapTest{

   private String name;
   private String idNum;
   private int age;

   public int getAge(){
      return age;
   }

   public String getName(){
      return name;
   }

   public String getIdNum(){
      return idNum;
   }

   public void setAge( int newAge){
      age = newAge;
   }

   public void setName(String newName){
      name = newName;
   }

   public void setIdNum( String newId){
      idNum = newId;
   }
}
```
以上实例中public方法是外部类访问该类成员变量的入口。  

通常情况下，这些方法被称为getter和setter方法。  

因此，任何要访问类中私有成员变量的类都要通过这些getter和setter方法。  

通过如下的例子说明EncapTest类的变量怎样被访问：  
```java
/* F文件名 : RunEncap.java */
public class RunEncap{
   public static void main(String args[]){
      EncapTest encap = new EncapTest();
      encap.setName("James");
      encap.setAge(20);
      encap.setIdNum("12343ms");

      System.out.print("Name : " + encap.getName()+
                             " Age : "+ encap.getAge());
    }
}
```
运行结果：  
`Name : James Age : 20`  

---
## 接口  

* 接口的实现　  　
当类实现接口的时候，类要实现接口中所有的方法。否则，类必须声明为抽象的类。

```java
interface Animal {
   public void eat();
   public void travel();
}
```

类使用implements关键字实现接口。在类声明中，Implements关键字放在class声明后面。    
实例  
MammalInt.java 文件代码：  

```java
/* 文件名 : MammalInt.java */
public class MammalInt implements Animal{

   public void eat(){
      System.out.println("Mammal eats");
   }

   public void travel(){
      System.out.println("Mammal travels");
   }

   public int noOfLegs(){
      return 0;
   }

   public static void main(String args[]){
      MammalInt m = new MammalInt();
      m.eat();
      m.travel();
   }
}
```

以上实例编译运行结果如下:  
`Mammal eats`  
`Mammal travels`  

* 口的多继承  
在Java中，类的多继承是不合法，但接口允许多继承。  

在接口的多继承中extends关键字只需要使用一次，在其后跟着继承接口。 如下所示：  

`public interface Hockey extends Sports, Event`  
