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

public class Creación_Votaciones implements IModule {

    public String getResponse(JSONObject jobject) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            JSONObject jsonData = jobject.optJSONObject("data");

            String tipo = jsonData.getString("tipo_votacion");
            String descripcion_1 = jsonData.getString("descripcion_1");
            String cedula = jsonData.getString("cedula");
            String fecha = jsonData.getString("fecha");
            String hora_apertura = jsonData.getString("hora_apertura");
            String hora_cierre = jsonData.getString("hora_cierre");

            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
            statement = (PreparedStatement) connection.prepareStatement("insert into votacion(id_tipo_votacion,cedula_persona,descripcion,fecha,hora_apertura,hora_cierre) values(?,?,?,?,?,?)");

            statement.setInt(1, Integer.parseInt(tipo));
            statement.setInt(2, Integer.parseInt(cedula));
            statement.setString(3, descripcion_1);
            statement.setString(4, fecha);
            statement.setString(5, hora_apertura);
            statement.setString(6, hora_cierre);
            statement.executeQuery();

            return "{\"Creación_Votaciones\":\"EXIT\"}";

        } catch (SQLException ex) {
            Logger.getLogger(Creación_Votaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Creación_Votaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Creación_Votaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                rs.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Creación_Votaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "{\"login\":\"failed\"}";
    }
}
