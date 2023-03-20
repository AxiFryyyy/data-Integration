package GUI.entity;

public class SSCourse {
    private String cno;
    private String cname;
    private String sch;
    private String tch;
    private String utc;
    private String share;
    public SSCourse() {}
    public SSCourse(String cno, String cname, String sch, String tch, String utc, String share) {
        this.cno = cno;
        this.cname = cname;
        this.sch = sch;
        this.tch = tch;
        this.utc = utc;
        this.share = share;
    }
    public String getCno() {
        return this.cno;
    }
    public String getCname() {
        return this.cname;
    }
    public String getSch() {
        return this.sch;
    }
    public String getTch() {
        return this.tch;
    }
    public String getUtc() {
        return this.utc;
    }
    public String getShare() {
        return this.share;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public void setSch(String sch) {
        this.sch = sch;
    }
    public void setTch(String tch) {
        this.tch = tch;
    }
    public void setUtc(String utc) {
        this.utc = utc;
    }
    public void setShare(String share) {
        this.share = share;
    }
}
