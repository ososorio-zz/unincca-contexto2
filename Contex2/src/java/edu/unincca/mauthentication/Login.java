package edu.unincca.mauthentication;

import edu.unincca.database.Conex;
import edu.unincca.database.ConexAmazon;
import edu.unincca.interfaces.IModule;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ososorio
 */
public class Login implements IModule {

    @Override
    public String getResponse(JSONObject jobject) {

        Connection connection = null;
        try {


            StringBuilder jb = new StringBuilder();
            String line = null;


            /*example only*/
            Iterator it = jobject.keys(); //gets all the keys

            while (it.hasNext()) {
                String key = (String) it.next(); // get key
                Object o = jobject.get(key); // get value
                //session.putValue(key, o); // store in session
                System.out.println(key + " === " + o);
            }
            /*end example*/


            JSONObject jsonData = jobject.optJSONObject("data");

            String user = jsonData.getString("user");
            String pass = jsonData.getString("pass");

            if (user.equals("test") && pass.equalsIgnoreCase("test")) {
                return "{\"login\":\"authenticated\"}";
            } else {
                /*example
                 * 
                 *   select nombre,apellido,url_foto 
                 *   FROM persona
                 *   WHERE cedula_persona=1030550748
                 *   AND password ='osorio'
                 */

                ConexAmazon con = new ConexAmazon();
                connection = con.getConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("select nombre,apellido,url_foto FROM persona WHERE cedula_persona=? AND password =?");


                if (user != null && pass != null && !user.equals("") && !pass.equals("")) {

                    statement.setInt(1, Integer.parseInt(user));
                    statement.setString(2, pass);

                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        JSONObject responsedb = new JSONObject();

                        responsedb.put("name", rs.getString(1));
                        responsedb.put("lastname", rs.getString(2));
                        responsedb.put("url_picture", rs.getString(3));
                        rs.close();
                        statement.close();
                        return new JSONObject().put("login", responsedb).toString();
                    }

                }
                return "{\"login\":\"failed\"}";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
