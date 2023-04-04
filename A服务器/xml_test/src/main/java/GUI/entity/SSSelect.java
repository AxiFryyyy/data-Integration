package GUI.entity;

public class SSSelect {
    private String sno;
    private String cno;
    private String grade;
    private String sname;
    private String cname;
    public SSSelect() {}
    public SSSelect(String sno, String cno, String grade, String sname, String cname) {
        this.sno = sno;
        this.cno = cno;
        this.grade = grade;
        this.sname = sname;
        this.cname = cname;
    }
    public String getSno() {
        return this.sno;
    }
    public String getCno() {
        return this.cno;
    }
    public String getGrade() {
        return this.grade;
    }
    public String getSname() {
        return this.sname;
    }
    public String getCname() {
        return this.cname;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
}
