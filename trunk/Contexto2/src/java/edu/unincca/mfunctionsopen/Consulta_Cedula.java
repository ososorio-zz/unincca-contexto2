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
public class Consulta_Cedula implements IModule {

    public String getResponse(JSONObject jobject) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            JSONObject jsonData = jobject.optJSONObject("data");
            int cedula = jsonData.getInt("cedula");
            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();

            statement = (PreparedStatement) connection.prepareStatement("select id_votacion FROM votacion_persona where cedula_persona=?");

            statement.setInt(1, cedula);

            rs = statement.executeQuery();


            JSONArray guarda = new JSONArray();
            while (rs.next()) {
                JSONObject responsedb = new JSONObject();
                responsedb.put("", rs.getString(1));

                guarda.put(responsedb);
            }
            if (guarda.length() > 0) {
                return new JSONObject().put("Si esta Inscrito", guarda).toString();
            } else {
                return new JSONObject().put("No esta Inscrito", guarda).toString();
            }




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
