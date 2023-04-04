package GUI.dao;

import GUI.db.ConnectToSS;
import GUI.entity.SSUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SSUserDaoImpl implements SSUserDao{
    Connection connect;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean addEmp(SSUser emp) {
        boolean flag = false;
        connect = new ConnectToSS().getConnection();
        String sql = "insert into account_t(name,pwd,auth) values(?,?,?)";
        try {
            ps = connect.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPwd());
            ps.setString(3, emp.getAuth());
            if (ps.executeUpdate()>0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int delEmp(String name) {
        return 0;
    }

    @Override
    public int updEmp(SSUser emp) {
        return 0;
    }

    @Override
    public SSUser findOne(String user, String pwd) {
        SSUser user_t = null;
        connect = new ConnectToSS().getConnection();
        String sql = "select name, pwd, auth from account_t";
        try {
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getString("name").equals(user) && rs.getString("pwd").equals(pwd)) {
                    user_t = new SSUser();
                    user_t.setName(rs.getString("name"));
                    user_t.setPwd(rs.getString("pwd"));
                    user_t.setAuth(rs.getString("auth"));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_t;
    }

    @Override
    public List<SSUser> findAll() {
        List<SSUser> ul = new ArrayList<SSUser>();
        SSUser user = null;
        connect = new ConnectToSS().getConnection();
        String sql = "select * from account_t";
        try {
            ps = connect.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new SSUser();
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setAuth(rs.getString("auth"));
                ul.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ul;
    }
}
