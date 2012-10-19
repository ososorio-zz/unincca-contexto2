package edu.unincca.muser;

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

/**
 *
 * @author YAZMIN,DIEGO,YEISON,GIOVANNY
 */
public class Candidate implements IModule {

    public String getResponse(JSONObject jobject) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            ConexAmazon con = new ConexAmazon();
            connection = con.getConnection();
            statement = (PreparedStatement) connection.prepareStatement("select persona.nombre,persona.apellido,persona.url_foto FROM persona,candidato where persona.cedula_persona=candidato.cedula_persona");



            rs = statement.executeQuery();

            JSONObject store = new JSONObject();
            JSONArray container = new JSONArray();
            while (rs.next()) {
                JSONObject responsedb = new JSONObject();
                responsedb.put("name", rs.getString(1));
                responsedb.put("lastname", rs.getString(2));
                responsedb.put("url_picture", rs.getString(3));
                container.put(responsedb);
            }
            store.put("results", container);
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
