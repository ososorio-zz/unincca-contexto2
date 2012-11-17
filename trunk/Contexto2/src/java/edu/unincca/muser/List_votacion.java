
package edu.unincca.muser;

/**
 *
 * @author YAZMIN,DIEGO,YEISON,GIOVANNY
 */
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
import org.json.JSONArray;


public class List_votacion implements IModule {

    public String getResponse(JSONObject jobject) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            JSONObject jsonData = jobject.optJSONObject("data");
            String user = jsonData.getString("user");
            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
            statement = (PreparedStatement) connection.prepareStatement("SELECT votacion.descripcion from persona,votacion_persona,votacion where persona.cedula_persona=? and votacion_PERSONA.cedula_persona=? and votacion_persona.id_votacion=votacion.id_votacion");

            statement.setInt(1, Integer.parseInt(user));
            statement.setInt(2, Integer.parseInt(user));

            rs = statement.executeQuery();

            JSONObject store = new JSONObject();
            JSONArray container = new JSONArray();
            while (rs.next()) {
                JSONObject responsedb = new JSONObject();
                responsedb.put("votacion", rs.getString(1));
                container.put(responsedb);
            }
            store.put("", container);
            return new JSONObject().put("", store).toString();


        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
                rs.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "{\"login\":\"failed\"}";
    }
}
