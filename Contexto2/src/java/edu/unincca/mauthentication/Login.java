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
public class Login implements IModule {

    @Override
    public String getResponse(JSONObject jobject) {

        Connection connection = null;
        try {

            JSONObject jsonData = jobject.optJSONObject("data");

            String user = jsonData.getString("cedula");
            String pass = jsonData.getString("password");

                /*example
                 * 
                 *   select nombre,apellido,url_foto 
                 *   FROM persona
                 *   WHERE cedula_persona=1030550748
                 *   AND password ='osorio'
                 */
                ConexAmazon con = new ConexAmazon();
                connection = con.getConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select nombre,apellido,url_foto,tipo,last_login FROM persona WHERE cedula_persona=? AND password =?");

                if (user != null && pass != null && !user.equals("") && !pass.equals("")) {

                    statement.setInt(1, Integer.parseInt(user));
                    statement.setString(2, pass);

                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        JSONObject responsedb = new JSONObject();

                        responsedb.put("name", rs.getString(1));
                        responsedb.put("lastname", rs.getString(2));
                        responsedb.put("url_picture", rs.getString(3));
                        responsedb.put("type", rs.getString(4));
                        responsedb.put("last_login", rs.getString(5));
                        
                        /*esto solo aplica para este caso
                         * este codigo debe ir por fuera del while
                         */
                        rs.close();
                        statement.close();
                        return new JSONObject().put("login","true").put("userInfo", responsedb).toString();
                    }

                }
                return new JSONObject().put("login","false").put("cause", "user or password incorrect").toString();
                
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
         catch (NumberFormatException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
           //   return new JSONObject().put("login","false").put("cause", "user or password incorrect").toString();
           try{
          return new JSONObject().put("login","false").put("cause", "Review user is only number not text").toString();
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
          return new JSONObject().put("login","false").put("cause", "Review your json").toString();
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
