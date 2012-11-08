/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;

import edu.unincca.database.ConexAmazon;
import edu.unincca.interfaces.IModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ososorio
 */
public class ChangePassword implements IModule{

    @Override
    public String getResponse(JSONObject jobject) {
         Connection connection = null;
          PreparedStatement statement =null;
          PreparedStatement statementLastLogin = null;
        try {

            JSONObject jsonData = jobject.optJSONObject("data");

            String user = jsonData.getString("cedula");
            String oldpass = jsonData.getString("oldpassword");
            String newpass = jsonData.getString("newpassword");
 
                ConexAmazon con = new ConexAmazon();
                connection = con.getConnection();
             
                statement = (PreparedStatement) connection.prepareStatement("update persona set password=? WHERE cedula_persona=? AND password =?");
               
                if (user != null && oldpass != null && newpass != null && !user.equals("") && !oldpass.equals("") && !newpass.equals("")) {

                    statement.setString(1, newpass);
                    statement.setInt(2, Integer.parseInt(user));
                    statement.setString(3, oldpass);

                    int rs= statement.executeUpdate();
                    
                    if(rs==1)
                    {
                     statementLastLogin = (PreparedStatement) connection.prepareStatement("update persona set last_login=? WHERE cedula_persona=? AND password =?");
                     statementLastLogin.setLong(1, new Date().getTime());
                     statementLastLogin.setInt(2, Integer.parseInt(user));
                     statementLastLogin.setString(3, newpass);
                     statementLastLogin.executeUpdate();
                    }
                                                                 
                    return new JSONObject().put("changePassword",(rs==0)?"false":"true").put("cause", (rs==0)?"Review your user and password":"ok").toString();               
       
                }
                 return new JSONObject().put("changePassword","false").put("cause", "Review your json").toString();               
                
        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (JSONException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
         catch (NumberFormatException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
          try{
             return new JSONObject().put("changePassword","false").put("cause", "Review user is only number not text").toString();
          } catch (Exception ex1) {  }
        }
        finally {
            try {
                
                if(connection != null) {
                    connection.close();
                }
                
                if(statement != null) {
                    statement.close();
                }
                
                 if(statement != null) {
                    statementLastLogin.close();
                }
                
                
            } catch (Exception ex) { }
        }
     
        
        try{
          return new JSONObject().put("changePassword","false").put("cause", "Review your user, and password is not empty").toString();
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    
}
