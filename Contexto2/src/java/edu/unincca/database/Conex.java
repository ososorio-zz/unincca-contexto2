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
                   "jdbc:postgresql://localhost:5432/Contexto2",
                   connectionProps);
    
    System.out.println("Connected to database");
    return conn;
}
   
   
  public ResultSet executeQuery(Connection con,PreparedStatement pre,boolean enableClose) throws SQLException  
  {
  
     ResultSet resultado = pre.executeQuery();
     if(enableClose)
     con.close();
     return resultado;
  } 
   
  public int executeUpdate(Connection con,PreparedStatement pre) throws SQLException  
  {
  
     int resultado = pre.executeUpdate();
     con.close();
     return resultado;
  } 

  public int[] executeBatch(Connection con,PreparedStatement pre) throws SQLException  
  {
  
     int[] resultado = pre.executeBatch();
     con.close();
     return resultado;
  }   
  
    
}
