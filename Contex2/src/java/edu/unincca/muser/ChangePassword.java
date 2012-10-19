package edu.unincca.muser;

import edu.unincca.interfaces.IFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.unincca.database.ConexAmazon;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author YAZMIN,DIEGO,YEISON,GIOVANNY
 */
public class ChangePassword implements IFactory {
    /*   
    {
    "ac": 3,
    "op": 1,
    "data": {
    "user":"1026266020",
    "pass":"doom",
    "pass2":"otra"
    }
    }     
     */
    private HttpServletResponse _response;

    public HttpServletResponse getResponse() {
        return _response;
    }

    public void setResponse(HttpServletResponse _response) {
        this._response = _response;
    }

    public void processRequest(JSONObject jobject, HttpServletRequest request, HttpServletResponse response) {

        try {
            setResponse(response);
            Connection connection = null;
            try {


                StringBuilder jb = new StringBuilder();
                String line = null;

                JSONObject jsonData = jobject.optJSONObject("data");

                String user = jsonData.getString("user");
                String pass = jsonData.getString("pass");
                String pass2 = jsonData.getString("pass2");

                ConexAmazon con = new ConexAmazon();
                connection = con.getConnection();
                PreparedStatement statement = (PreparedStatement) connection.prepareStatement("update persona set password=? WHERE cedula_persona=? AND password =?");
                // PreparedStatement statement= (PreparedStatement) connection.prepareStatement("select nombre,apellido,url_foto FROM persona");

                if (user != null && pass != null && pass2 != null && !user.equals("") && !pass.equals("") && !pass2.equals("")) {

                    statement.setString(1, pass2);
                    statement.setInt(2, Integer.parseInt(user));
                    statement.setString(3, pass);

                    ResultSet rs = statement.executeQuery();
                    writeResponse("{\"Update\":\"Exit\"}");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

         
        } catch (NumberFormatException ex) {
            writeResponse("Operation Invalid:".concat(ex.getMessage().toString()));

        } catch (Exception ex) {

            writeResponse(ex.getMessage().toString());
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void writeResponse(String msg) {
        PrintWriter writer = null;
        try {
            writer = getResponse().getWriter();
            writer.write(msg);

        } catch (IOException ex1) {
            Logger.getLogger(MUser.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            writer.close();
        }

    }
}
