package GUI.dao;

import GUI.db.ConnectToSS;
import GUI.entity.SSStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SSStudentDaoImpl implements SSStudentDao{
    Connection connect;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public SSStudent findOne(String sno) {
        SSStudent student = null;
        connect = new ConnectToSS().getConnection();
        String sql = "select * from student_t";
        try {
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("sno").equals(sno)) {
                    student = new SSStudent();
                    student.setSno(rs.getString("sno"));
                    student.setSname(rs.getString("sname"));
                    student.setSex(rs.getString("sex"));
                    student.setInst(rs.getString("inst"));
                    student.setRel_acct(rs.getString("rel_acct"));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<SSStudent> findAll() {
        List<SSStudent> sl = new ArrayList<SSStudent>();
        SSStudent student = null;
        connect = new ConnectToSS().getConnection();
        String sql = "select * from student_t";
        try {
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                student = new SSStudent();
                student.setSno(rs.getString("sno"));
                student.setSname(rs.getString("sname"));
                student.setSex(rs.getString("sex"));
                student.setInst(rs.getString("inst"));
                student.setRel_acct(rs.getString("rel_acct"));
                sl.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sl;
    }
}
