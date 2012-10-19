/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;

import edu.unincca.database.Conex;
import edu.unincca.interfaces.IModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ososorio
 */
public class LostPassword implements IModule{

    @Override
    public String getResponse(JSONObject jobject) {
        
         Connection connection = null;
    try {
            
        
           JSONObject jsonData= jobject.optJSONObject("data");
           
           String user = jsonData.getString("user");
           String pass = jsonData.getString("oldpass"); 
           String newpass = jsonData.getString("newpass"); 
           
           
           if(   user !=null && pass != null 
              && !user.equals("") && !pass.equals("")
              && newpass!=null && !newpass.equals(""))
           {
           /*example  obtain valid user
              SELECT cedula_persona
                FROM persona
                WHERE password='osorio'
                AND cedula_persona=1030550748 
           example change user
                UPDATE persona
                SET password= 'osorio1'
                WHERE cedula_persona = 1030550748       
            */  
               
             Conex con = new Conex();
             connection=con.getConnection();
             PreparedStatement statement= (PreparedStatement) connection.prepareStatement("select cedula_persona FROM persona WHERE cedula_persona=? AND password =?");
               
             statement.setInt(1,Integer.parseInt(user) );                     
             statement.setString(2, pass);    
 
             ResultSet rs= statement.executeQuery();
            
             while(rs.next())
             {
               statement.close();
               statement= (PreparedStatement) connection.prepareStatement("UPDATE persona SET password= ? WHERE cedula_persona = ? ");
               statement.setString(1, newpass);                  
               statement.setInt(2, rs.getInt(1));
            
               int result= statement.executeUpdate();

               statement.close();

              return new JSONObject().put("changepassword", result).toString();            
             }
           }
           
          return new JSONObject().put("changepassword", "fail").toString();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return "";
        
        
    }

    
}
