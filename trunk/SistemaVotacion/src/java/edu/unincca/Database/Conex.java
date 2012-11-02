package edu.unincca.Database;

import java.sql.*;
import java.util.Properties;
/**
*
*
@author
ososorio
*/

public class Conex{
   String driver ="org.postgresql.Driver";
   
public Connection getConnection()throws SQLException, ClassNotFoundException
{

   Connection conn = null;
Properties connectionProps = new Properties();
connectionProps.put("user","postgres");
connectionProps.put("password","postgres");

Class.forName(driver);

conn = DriverManager.getConnection(
        "jdbc:postgresql://localhost/ParcialContexto",
         connectionProps);
		 
System.out.println("Connected to database");
return conn;
  }
}