#### 用java爬取zol壁纸
* 首先找到壁纸页面
得到ur：http://desk.zol.com.cn/showpic/1920x1080_4000_10.html
然后查看源码找到图片的信息  
<img src="https://desk-fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/01/0E/ChMkJ1bKwfWIK7FbAAiTgt3qkBkAALGggICrlgACJOa906.jpg" width="100" height="75">  
```html
<img src="https://desk-fd.zol-img.com.cn/t_s1920x1080c5/g5/M00/01/0E/ChMkJ1bKwfWIK7FbAAiTgt3qkBkAALGggICrlgACJOa906.jpg" width="100" height="75">
```  

分析url可以发现4000这个数字是这张图片的id ，所以只需要将url分为三部分，中间的数字用循环改变然后用线程下载  

* 创建一个获取图片url的方法
```java
private String getImgUrl(String u) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(u);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 " +
                "(KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1");
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"gb2312"));
        String string = "";
        while (null != (string = reader.readLine())) {
            sb.append(string);
        }
        string = sb.toString();
        //从获取的源码中截取图片的URL
        Matcher matcher = Pattern.compile(imgReg).matcher(string);
        if (matcher.find()) {
            string = matcher.group();
            string = string.substring(10, string.length() - 2);
        }
        in.close();
        return string;
    }
```  
* 然后创建一个从上面获取的url下载图片的方法  
```java
private void getImg(String imgUrl) throws IOException {
        URL url = new URL(imgUrl);
        InputStream in = url.openStream();
        FileOutputStream fo = new FileOutputStream(new File("/image/" + this.number+".jpg"));
        byte[] by = new byte[1024];
        int length = 0;
        System.out.println("开始下载:" +this.number);
        while ((length = in.read(by, 0, by.length)) != -1) {
            fo.write(by, 0, length);
        }
        in.close();
        fo.close();
    }
```  
