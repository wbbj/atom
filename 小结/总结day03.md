### 今天的任务：
1016 部分A+B  
1017 A除以B  
1018 锤子剪刀布  
1019 数字黑洞  
1020 月饼  

* 在做1016时本来是想用for循环往数组中输入要求的A，B，PA，PB但是这样做影响了后续操作。  
首先将A B字符传入数组中以便于比较但存储的方法要选好这里使用了`char-----.toCharArray()` ，根据相同字符个数利用`Math.pow()`计算PA,PB。   
还有一种就是直接用long型输入四个数，然后用while循环求余比较两个数然后由此通过`Math.pow()`得到PA，PB  

* 1017题没什么特别之处因为求得数比较大只要注意变量的类型这里我使用了`BigInteger`来声明

* 1018题做的时候在比较字符的时候使用`==`出现错误，比较字符要用`A.equals(B)`不能用`==`，后面输出胜利使用最多手势比较繁琐  
我是将不同手势胜利计数输出最大的那个手势，但这种方法使得同时比较两个人的胜利使用手势次数很烦。  

* 1019题我的思路是设置两个函数分别表示非递增排列和非递减排列  
其中用到了`Arrays.sort(a);`来将数组中的数据排序，然后将两函数代入公式中利用`do while`循环输出题目要求的计算过程  
其中还用了`System.out.printf("%04d - %04d = %04d\n",FZ(N),FJ(N),HD);`来使输出的数据保持`xxxx-xxxx=xxxx`的形式  

* 1020题计算最大收益完全没头绪，看了别人的思路是计算每种月饼每吨的价格然后最大限度卖出最贵的月饼，将月饼的数据单独分离出来然后计算每种月饼的每吨的价格  
然后取最大的价格的月饼，如果需求量大于月饼库存量，用最大需求量减去最大价格月饼库存量，然后计算收益。  
然后对比剩余需求与剩余最贵的月饼，方法同上，知道需求量用完。

###  写一个JDBC实现CRUD
###### 连接数据库：  
`url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";`  
`user = "root";`   
`password = "Wbb100809136";`  

`Class.forName("com.mysql.cj.jdbc.Driver");`  

###### 增删改查操作：

```java
//增加为例
public static void addData() {
        try {
            pStmt = con.prepareStatement("insert into db_user (name,phone) values(?,?)");
            pStmt.setString(1, "李四");
            pStmt.setString(2, "0000000");
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

###### 输出操作：  
```java
public String toString() {
        return "student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +'\''+'\''+
                '}';
    }
    ```
