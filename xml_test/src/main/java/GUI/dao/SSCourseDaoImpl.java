package GUI.dao;

import GUI.db.ConnectToSS;
import GUI.entity.SSCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SSCourseDaoImpl implements SSCourseDao{
    Connection connect;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public SSCourse findOne(String cno) {
        SSCourse course = null;
        connect = new ConnectToSS().getConnection();
        String sql = "select * from course_t";
        try {
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("cno").equals(cno)) {
                    course = new SSCourse();
                    course.setCno(rs.getString("cno"));
                    course.setCname(rs.getString("cname"));
                    course.setSch(rs.getString("sch"));
                    course.setTch(rs.getString("tch"));
                    course.setUtc(rs.getString("utc"));
                    course.setShare(rs.getString("share"));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<SSCourse> findAll() {
        List<SSCourse> cl = new ArrayList<SSCourse>();
        SSCourse course = null;
        connect = new ConnectToSS().getConnection();
        String sql = "select * from course_t";
        try {
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                course = new SSCourse();
                course.setCno(rs.getString("cno"));
                course.setCname(rs.getString("cname"));
                course.setSch(rs.getString("sch"));
                course.setTch(rs.getString("tch"));
                course.setUtc(rs.getString("utc"));
                course.setShare(rs.getString("share"));
                cl.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }
}
