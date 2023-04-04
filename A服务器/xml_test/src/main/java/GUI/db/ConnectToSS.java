package GUI.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToSS {

    Connection connection = null;

    public ConnectToSS() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=gui_test", "sa", "18061389075ssh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
