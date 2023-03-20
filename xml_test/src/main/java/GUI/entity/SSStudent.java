package GUI.entity;

public class SSStudent {
    private String sno;
    private String sname;
    private String sex;
    private String inst;
    private String rel_acct;
    public SSStudent() {}
    public SSStudent(String sno, String sname, String sex, String inst, String rel_acct) {
        this.sno = sno;
        this.sname = sname;
        this.sex = sex;
        this.inst = inst;
        this.rel_acct = rel_acct;
    }
    public String getSno() {
        return this.sno;
    }
    public String getSname() {
        return this.sname;
    }
    public String getSex() {
        return this.sex;
    }
    public String getInst() {
        return this.inst;
    }
    public String getRel_acct() {
        return this.rel_acct;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setInst(String inst) {
        this.inst = inst;
    }
    public void setRel_acct(String rel_acct) {
        this.rel_acct = rel_acct;
    }
}
