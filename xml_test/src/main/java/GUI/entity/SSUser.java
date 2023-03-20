package GUI.entity;

public class SSUser {
    private String name;
    private String pwd;
    private String auth;
    public SSUser() {}
    public SSUser(String name, String pwd, String auth) {
        this.name = name;
        this.pwd = pwd;
        this.auth = auth;
    }
    public String getName() {
        return name;
    }
    public String getPwd() {
        return pwd;
    }
    public String getAuth() {
        return auth;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void setAuth(String auth) {
        this.auth = auth;
    }
}
