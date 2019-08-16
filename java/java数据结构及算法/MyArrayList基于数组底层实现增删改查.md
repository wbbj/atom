# 基于数组底层实现增删该查
* 先声明数组变量int[] array和数组中元素数量的变量size
* 然后初始化数组长度与元素数量为0
* 在插入操作中要判断元素是否占满所有容量没占满就插入指定位置并将后面所有数据后移，如果满了就用System.arraycopy()将次数组放入一个新数组，或者先对数组进行扩容原理是相同的都是产生一个新数组，然后让原数组指向新数组。
* 删除操作要先找到指定位置然后将后面的数据全部前移
* 查看和修改操作可以直接根据位置获取数据直接修改或查看

### 代码：
* MyArrayList

```java
package MyArryList;

public class MyArrayList {
    private int[] array;
    private int size;
    public void setSize(int i){
        array=new int[i];
        size=0;
    }
    public int getSize(){
        return size;
    }
    public int getRl(){
        return array.length;
    }
    public int getMyArrayLists(int i){
        return array[i];
    }
    public void setMyArrayLists(int i,int data){
        if(i<array.length){
            array[i]=data;
        }
        size++;
    }
    public void insert(int i, int data){
        if(size==array.length){
            int[] array1 = new int[array.length+1];
            array1[array.length] = data;
            System.arraycopy(array, 0, array1, 0, array.length);
            this.array=array1;
            size++;
        }
        if (size>=0&&size<array.length) {
            for (int j = array.length-2; j >= i; j--) {
                array[j + 1] = array[j];
            }
            array[i] = data;
            size++;
        }
    }
    public void delete(int i){
        for (int j = i + 1; j < size; j++) {
            array[j - 1] = array[j];
        }
        size--;
    }
    public void reSize(){
        if(size==array.length) {
            int[] array1 = new int[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, array1, 0, array.length);
            this.array=array1;
        }
    }
}
```
* Main.java
```java
package MyArryList;

import test1.S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyArrayList array=new MyArrayList();
        int m=0;
        array.setSize(20);
        System.out.println("数组容量设置为"+array.getRl());
        for (int i=0;i<10;i++) {
            array.setMyArrayLists(i,i);
        }
        System.out.println("设置数组内部分元素");
        for(int i=0;i<array.getSize();i++){
            System.out.print(array.getMyArrayLists(i)+" ");
        }
        System.out.println();
        array.insert(2,8);
        array.insert(4,9);
        array.delete(5);
        System.out.println("插入和删除操作在第二个元素后面插入8，第四个元素后面插入9然后删除低5个元素，并获取元素数量");
        m=array.getSize();
        System.out.println(m);
        for(int i=0;i<array.getSize();i++){
            System.out.print(array.getMyArrayLists(i)+" ");
        }
        System.out.println();
        System.out.println("将数组填满");
        for (int i=0;i<9;i++){
            array.insert(i+1,15);
        }

        m=array.getSize();
        System.out.println(m);
        for(int i=0;i<array.getSize();i++){
            System.out.print(array.getMyArrayLists(i)+" ");
        }
        System.out.println();
        System.out.println("再插入一个元素");
        array.insert(3,6);
        m=array.getSize();
        System.out.println(m);
        for(int i=0;i<array.getSize();i++){
            System.out.print(array.getMyArrayLists(i)+" ");
        }
        System.out.println();
        array.reSize();
        System.out.println("容量扩充1.5倍");
        System.out.println("数组容量:"+array.getRl());
        for(int i=0;i<array.getSize();i++){
            System.out.print(array.getMyArrayLists(i)+" ");
        }
        System.out.println();
    }
}
```
运行结果：
`数组容量设置为20`  
`设置数组内部分元素`  
`0 1 2 3 4 5 6 7 8 9`   
`插入和删除操作在第二个元素后面插入8，第四个元素后面插入9然后删除低5个元素，并获取元素数量`  
`11`  
`0 1 8 2 9 4 5 6 7 8 9`   
`将数组填满`  
`20`  
`0 15 15 15 15 15 15 15 15 15 1 8 2 9 4 5 6 7 8 9`   
`再插入一个元素`  
`21`  
`0 15 15 15 15 15 15 15 15 15 1 8 2 9 4 5 6 7 8 9 6`   
`容量扩充1.5倍`  
`数组容量:31`  
`0 15 15 15 15 15 15 15 15 15 1 8 2 9 4 5 6 7 8 9 6`   
