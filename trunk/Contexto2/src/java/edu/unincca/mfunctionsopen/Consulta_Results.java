/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mfunctionsopen;

import edu.unincca.database.ConexAmazon;
import edu.unincca.interfaces.IModule;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Consulta_Results implements IModule {

    public String getResponse(JSONObject jobject) {
        Connection connection = null;
        try {

                ConexAmazon con = new ConexAmazon();
                connection = con.getConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select nombre,url_logo FROM partido");

                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        JSONObject responsedb = new JSONObject();

                        responsedb.put("name", rs.getString(1));
                        responsedb.put("url_logo", rs.getString(2));
                       
                        rs.close();
                        statement.close();
                        return new JSONObject().put("login", responsedb).toString();
                    }


                
                return "{\"login\":\"failed\"}";
            
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Results.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta_Results.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Consulta_Results.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consulta_Results.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



        /*
         * call the methods to validate login 
         * 
         */
        //http://www.json.org/java/
        return "{\"login\":\"failed\"}";
    }
}
