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

public class Inscripción_Person implements IModule {

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    public String getResponse(JSONObject jobject) {

        try {
            JSONObject jsonData = jobject.optJSONObject("data");

            String cedula = jsonData.getString("cedula");
            String nombre = jsonData.getString("nombre");
            String apellido = jsonData.getString("apellido");
            String url = jsonData.getString("url");
            String partido = jsonData.getString("partido");

            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
          
            insertar_partido(partido);
            insertar_persona(cedula, nombre, apellido, url);
            int id = extraer_partido();
            insertar_candidato(cedula, id);

               return "{\"Inscripción_Candidato\":\"exit\"}";
        } catch (SQLException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                rs.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "{\"Inscripción_Candidate\":\"failed\"}";
    }

    public void insertar_partido(String partido) {
        try {
            statement = (PreparedStatement) connection.prepareStatement("insert into partido(nombre) values(?)");
            statement.setString(1, partido);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertar_persona(String cedula, String nombre, String apellido, String url) {
        try {
            statement = (PreparedStatement) connection.prepareStatement("insert into persona(cedula_persona,nombre,apellido,url_foto) values(?,?,?,?)");
            statement.setInt(1, Integer.parseInt(cedula));
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setString(4, url);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int extraer_partido() {
        int container = 0;
        try {
            statement = (PreparedStatement) connection.prepareStatement("SELECT id_partido FROM partido ORDER BY id_partido DESC LIMIT 1");
            rs = statement.executeQuery();

            while (rs.next()) {
                container = rs.getInt(1);
            }
            return container;
        } catch (SQLException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return container;
    }

    public void insertar_candidato(String cedula, int id) {
        try {
            statement = (PreparedStatement) connection.prepareStatement("insert into candidato values(?,?,?)");
            statement.setInt(1, Integer.parseInt(cedula));
            statement.setInt(2, Integer.parseInt(cedula));
            statement.setInt(3, id);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Inscripción_Person.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
