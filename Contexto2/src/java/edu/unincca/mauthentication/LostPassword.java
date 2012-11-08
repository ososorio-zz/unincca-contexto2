/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;

import edu.unincca.database.ConexAmazon;
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

            JSONObject jsonData = jobject.optJSONObject("data");

            String cedula = jsonData.getString("cedula");
            String nombre = jsonData.getString("nombre");
            String apellido = jsonData.getString("apellido");

            
            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select password FROM persona WHERE cedula_persona=? AND nombre=? AND apellido=?");

                if (cedula != null && nombre != null && apellido != null && !cedula.equals("") && !nombre.equals("") && !apellido.equals("")) {

                    statement.setInt(1, Integer.parseInt(cedula));
                    statement.setString(2, nombre);
                    statement.setString(3, apellido);
                    
                    ResultSet rs = statement.executeQuery();

                    JSONObject response=new JSONObject();
                         
                    while (rs.next()) {
                        response.put("password", rs.getString(1));
                    }
                    rs.close();
                    statement.close();
                    
                    statement = (PreparedStatement) connection.prepareStatement("UPDATE persona set last_login=0 WHERE  cedula_persona=? AND nombre=? AND apellido=?");
                    statement.setInt(1, Integer.parseInt(cedula));
                    statement.setString(2, nombre);
                    statement.setString(3, apellido);
                    
                    statement.executeUpdate();
                    statement.close();
                    
                    System.out.println(response.length());
                    return new JSONObject().put("LostPassword", (response.length() >= 1)?"true":"false").put("password",(response.length() >= 1)?response.get("password"):"").toString();                    
                }
                return new JSONObject().put("LostPassword","false").put("cause", "review json").toString();
                
        } catch (SQLException ex) {
            Logger.getLogger(LostPassword.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LostPassword.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (JSONException ex) {
            Logger.getLogger(LostPassword.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
         catch (NumberFormatException ex) {
            Logger.getLogger(LostPassword.class.getName()).log(Level.SEVERE, null, ex);
           //   return new JSONObject().put("login","false").put("cause", "user or password incorrect").toString();
           try{
          return new JSONObject().put("LostPassword","false").put("cause", "Review user is only number not text").toString();
            } catch (Exception ex1) {  }
        }
        finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
               // Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
        
        
        try{
          return new JSONObject().put("LostPassword","false").put("cause", "Review your json").toString();
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }

    
}
