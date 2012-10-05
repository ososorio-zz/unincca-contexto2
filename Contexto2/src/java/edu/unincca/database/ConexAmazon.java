/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author ososorio
 */
public class ConexAmazon {
    
     String driver = "org.postgresql.Driver";
    
   public  Connection getConnection() throws SQLException, ClassNotFoundException {
    
       
   
    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "wvckcwbvseqptr");
    connectionProps.put("password", "_R0BM2KU6PbNMqrMOBS2dLwI8E");

    Class.forName(driver);
    
    conn = DriverManager.getConnection(
                   "jdbc:postgresql://ec2-54-243-190-72.compute-1.amazonaws.com:5432/d1pol1dkj36ib?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
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
