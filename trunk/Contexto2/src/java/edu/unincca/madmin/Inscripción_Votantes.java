package edu.unincca.madmin;

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
import javax.swing.JOptionPane;

public class Inscripción_Votantes implements IModule {

    public String getResponse(JSONObject jobject) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
    
        try {
            JSONObject jsonData = jobject.optJSONObject("data");

            String cedula = jsonData.getString("cedula");
            String nombre = jsonData.getString("nombre");
            String apellido = jsonData.getString("apellido");
            String password = jsonData.getString("password");
            String url = jsonData.getString("url_opcional");

            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
            statement = (PreparedStatement) connection.prepareStatement("insert into persona values(?,?,?,?,?)");
            statement.setInt(1, Integer.parseInt(cedula));
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setString(4, password);
            statement.setString(5, url);
            statement.executeQuery();
            return "{\"Inscripción_Votantes\":\"exit\"}";
        } catch (SQLException ex) {
            Logger.getLogger(Inscripción_Votantes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inscripción_Votantes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Inscripción_Votantes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                rs.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Inscripción_Votantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "{\"Inscripción_Votantes\":\"failed\"}";

    }
}
