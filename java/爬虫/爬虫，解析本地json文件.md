## 任务
解析指定json文件。  
要求：通过文件流读取，解析出影院及优惠券，并存入数据库中。  
需要建两张表：  
电影院，主键 Id  
优惠券，主键 优惠券Id，外键电影院Id  

* 首先在本地找好要解析的文件
json1.png

* 然后用文件流读取文件
```java
BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("/myFile/json.json"), StandardCharsets.UTF_8));
       String s=null;
       String us="";
       while ((s=br.readLine())!=null){
           us=new String(s.getBytes("UTF-8"),"UTF-8");
       }
```
* 再将数据解析出来  
其中在解析之前先观察要解析的代码，现将前面的数据输出然后放到在线json视图查看器中  
如图所示：
json2.png  
json3.png  
图中找到我们需要的数据进行解析  
格式化后的视图可以看出这个json的结构对象包裹对象在包裹数组在包裹对象然后数组对象  

json{  
  data{  
    searchResult[  
                 0{数据  
                       deals[  
                               0{数据  


解析过程中发现一个错误，id等数据不是string类型没仔细看看不出来
如图所示：  
json4.png  

id后面的值是没加双引号的所以提取数据的部分使用了 `getLong("id")+"";` 这种形式
```java
String[] srx=new String[11];
            JSONObject jb=sr.getJSONObject(i);
            srx[0]=jb.getLong("id")+"";
            srx[1]=jb.getString("imageUrl");
            srx[2]=jb.getString("title");
            srx[3]=jb.getString("address");
            srx[5]=jb.getLong("lowestprice")+"";
            srx[6]=jb.getLong("avgprice")+"";
            srx[7]=jb.getLong("avgscore")+"";
            srx[8]=jb.getString("backCateName");
            srx[9]=jb.getString("areaname");
            srx[10]=jb.getString("city");
```
* 然后解析deals优惠券的部分  

```java
JSONArray yh=jb.getJSONArray("deals");
            for(int j=0;j<yh.length();j++){
                JSONObject de=yh.getJSONObject(j);
                String[] deals=new String[5];
                deals[0]=de.getLong("id")+"";
                deals[1]=de.getString("title");
                deals[2]=de.getLong("price")+"";
                deals[3]=de.getLong("value")+"";
                deals[4]=de.getLong("sales")+"";
```

* 最后插入数据库还是和之前一样的操作

```java
String sql0="insert into film values(" +
                    srx[0] +
                    ",'" +
                    srx[1] +
                    "','" +
                    srx[2] +
                    "','" +
                    srx[3] +
                    "'," +
                    srx[4] +
                    "," +
                    srx[5] +
                    "," +
                    srx[6] +
                    ",'" +
                    srx[7] +
                    "','" +
                    srx[8] +
                    "','" +
                    srx[9] +
                    "')";
```
在这之前还是要先连接数据库  
在建表的时候外键问题困扰到了我，一直没找到方法解决于是就删了外键  
运行后的结果：  
表film  
filmbiao.png

表deals  
deals.png
