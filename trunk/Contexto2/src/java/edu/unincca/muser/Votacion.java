package edu.unincca.muser;

import edu.unincca.interfaces.IFactory;
import edu.unincca.interfaces.IModule;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Giovanny
 */
public class Votacion implements IFactory {

    /* 
    
     {
     "ac": 3,
     "op": 0,
     "data": {
     "user": "test",
     "pass": "test"
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
        JOptionPane.showMessageDialog(null, "llego");
        try {
            setResponse(response);
            //op:operation
            // int action=Integer.parseInt( request.getParameter("op") );


            EnumOperations2 actionToCall = EnumOperations2.fromInteger(jobject.getInt("cp"));


            switch (actionToCall) {

                case candidato:
                    writeResponse(new Candidate().getResponse(jobject));
                    break;
                case lista:
                    writeResponse(new Party_Candidates().getResponse(jobject));
                    break;
                case lista_candidato:
                    writeResponse(new List_Candidates().getResponse(jobject));
                    break;
                case listar_votacion_candidatos:
                    JOptionPane.showMessageDialog(null, "llego al otro");
                    writeResponse(new List_votacion().getResponse(jobject));
                    break;

            }

        } catch (NumberFormatException ex) {
            writeResponse("Operation Invalid:".concat(ex.getMessage().toString()));

        } catch (Exception ex) {

            writeResponse(ex.getMessage().toString());
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    private void writeResponse(String msg) {
        PrintWriter writer = null;
        try {
            writer = getResponse().getWriter();
            writer.write(msg);

        } catch (IOException ex1) {
            Logger.getLogger(Votacion.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            writer.close();
        }

    }
}