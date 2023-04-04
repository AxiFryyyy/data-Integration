package GUI.dao;

import GUI.db.ConnectToSS;
import GUI.entity.SSSelect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SSSelectDaoImpl implements SSSelectDao{
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<SSSelect> findCourse(String cno) {
        List<SSSelect> sl = new ArrayList<SSSelect>();
        SSSelect select = null;
        connection = new ConnectToSS().getConnection();
        String sql = "select sc.cno, sc.sno, sc.grade, s.sname from selcourse_t sc join student_t s on s.sno = sc.sno where cno="+cno;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                select = new SSSelect();
                select.setCno(rs.getString("cno"));
                select.setSno(rs.getString("sno"));
                select.setGrade(rs.getString("grade"));
                select.setSname(rs.getString("sname"));
                sl.add(select);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sl;
    }

    @Override
    public List<SSSelect> findStudent(String sno) {
        List<SSSelect> sl = new ArrayList<SSSelect>();
        SSSelect select = null;
        connection = new ConnectToSS().getConnection();
        String sql = "select sc.cno, sc.sno, sc.grade, c.cname from selcourse_t sc join course_t c on c.cno = sc.cno where sno="+sno;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                select = new SSSelect();
                select.setCno(rs.getString("cno"));
                select.setSno(rs.getString("sno"));
                select.setGrade(rs.getString("grade"));
                select.setCname(rs.getString("cname"));
                sl.add(select);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sl;
    }

    @Override
    public void addCourse(String cno, String sno) {
        System.out.println(cno+" "+sno);
        connection = new ConnectToSS().getConnection();
        String sql = "insert into selcourse_t values('"+cno+"','"+sno+"',null)";
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(String cno, String sno) {
        connection = new ConnectToSS().getConnection();
        String sql = "delete from selcourse_t where cno='"+cno+"' and sno='"+sno+"'";
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
