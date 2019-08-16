package wbb.ssm.entity;

import org.springframework.web.multipart.MultipartFile;

public class Garbage {
    //垃圾名称
    private String gname;
    //垃圾种类
    private String variety;
    //垃圾描述
    private String describe;
    //处理方法
    private String handle;
    //垃圾图片
    private MultipartFile file;

    public MultipartFile getFile() {
        return this.file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Garbage() {
    }

    public String getGname() {
        return this.gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getVariety() {
        return this.variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getHandle() {
        return this.handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Garbage(String gname, String variety, String describe, String handle, MultipartFile file) {
        this.gname = gname;
        this.variety = variety;
        this.describe = describe;
        this.handle = handle;
        this.file = file;
    }
}
