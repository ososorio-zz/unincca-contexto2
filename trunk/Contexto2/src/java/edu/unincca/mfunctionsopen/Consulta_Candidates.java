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
import org.json.JSONArray;

/**
 *
 * @author wilson
 */
public class Consulta_Candidates implements IModule {

    public String getResponse(JSONObject jobject) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            JSONObject jsonData = jobject.optJSONObject("data");
            int tipo = jsonData.getInt("tipo");

            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
            
            if (tipo == 0) {
                int partido = jsonData.getInt("partido");
                statement = (PreparedStatement) connection.prepareStatement("select persona.nombre,persona.apellido,persona.url_foto FROM persona,candidato,partido where  partido.id_partido=? and persona.cedula_persona=candidato.cedula_persona and partido.id_partido=candidato.id_partido");
                statement.setInt(1, partido);
            }


            if (tipo == 1) {
                statement = (PreparedStatement) connection.prepareStatement("select persona.nombre,persona.apellido,persona.url_foto FROM persona,candidato where persona.cedula_persona=candidato.cedula_persona");
            }


            rs = statement.executeQuery();
            JSONArray guarda = new JSONArray();
            while (rs.next()) {
                JSONObject responsedb = new JSONObject();
                responsedb.put("name", rs.getString(1));
                responsedb.put("lastname", rs.getString(2));
                responsedb.put("url_picture", rs.getString(3));
                guarda.put(responsedb);
            }
            return new JSONObject().put("", guarda).toString();


        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Candidates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta_Candidates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Consulta_Candidates.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                rs.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consulta_Candidates.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "{\"login\":\"failed\"}";
    }
}
