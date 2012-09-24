package edu.unincca.database;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 *
 * @author ososorio
 */
public class Conex {
    
   String driver = "org.postgresql.Driver";
    
   public  Connection getConnection() throws SQLException, ClassNotFoundException {
    
       
   
    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "postgres");
    connectionProps.put("password", "lanc525");

    Class.forName(driver);
    
    conn = DriverManager.getConnection(
                   "jdbc:postgresql://localhost/ParcialContexto",
                   connectionProps);
    
    System.out.println("Connected to database");
    return conn;
}
    
}
