

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conex {

    String driver = "org.postgresql.Driver";

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "postgres");
        connectionProps.put("password", "admin");
        Class.forName(driver);
        conn = DriverManager.getConnection("jdbc:postgresql://localhost/votaciones", connectionProps);
        System.out.println("Se conecto a postgrEs BUUUUU");
        return conn;
    }
    
     
}
    