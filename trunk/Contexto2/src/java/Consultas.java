
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consultas {

    public Consultas() {
    }

    public String usuarioValidoVotaciones(int numerocedula) {
        try {
            String salida = "";
            Conex conex = new Conex();
            Connection con = conex.getConnection();

            StringBuilder respuestaaservlet = new StringBuilder();
           // String sg = "select nombre from persona where cedula_persona='" + numerocedula + "'";
            PreparedStatement stamen =con.prepareStatement("select nombre from persona where cedula_persona=?");
               stamen.setInt(1, numerocedula);
          //  Statement stamen = con.createStatement();
            ResultSet resultado =stamen.executeQuery();//stamen.executeQuery(sg);


            while (resultado.next()) {
                respuestaaservlet.append("La cedula ingresada corresponde a: ").append(resultado.getString(1));
                System.out.print(resultado.getString(1));
            }
            if (respuestaaservlet.length() == 0) {
                respuestaaservlet.append("La cedula ingresada no esta Registrada");
            }
            return respuestaaservlet.toString();

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Ocurrio un Error verifique la cedula";
    }

  
}
