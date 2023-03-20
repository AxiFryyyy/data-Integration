package GUI.entity;

public class SSSelect {
    private String sno;
    private String cno;
    private String grade;
    public SSSelect() {}
    public SSSelect(String sno, String cno, String grade) {
        this.sno = sno;
        this.cno = cno;
        this.grade = grade;
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
    public void setSno(String sno) {
        this.sno = sno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
