package wbb.ssm.entity;

public class Manage {
    //自增的id
    private String id;
    //管理员账号
    private String username;
    //管理员密码
    private String password;

    public Manage() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Manage(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
