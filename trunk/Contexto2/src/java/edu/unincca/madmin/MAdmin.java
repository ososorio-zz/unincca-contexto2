package edu.unincca.madmin;


import edu.unincca.interfaces.IFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class MAdmin implements IFactory {

    private HttpServletResponse _response;

 
    public void processRequest(JSONObject jobject, HttpServletRequest request, HttpServletResponse response) {
        try {
            setResponse(response);

            IFactory action = null;

            EnumOperations actionToCall = EnumOperations.fromInteger(jobject.getInt("op"));


            if (actionToCall == null) {
                responseAvailableActions();
                return;
            }

            switch (actionToCall) {

                case Creación_votaciones:
                    writeResponse(new Creación_Votaciones().getResponse(jobject));
                    break;
                case Inscripción_candidatos:
                   action=new Inscripción_Candidatos();
                   action.processRequest(jobject, request, response);
                    break;
                case Inscripción_votantes:
                   
                    writeResponse(new Inscripción_Votantes().getResponse(jobject));
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

    private void responseAvailableActions() {

        writeResponse("{\"Error\":\"Pleaser Review the actions and operation\",   \"Mlogin\":[  {\"login\":{\"ac\":1,\"op\":1}},"
                + "{\"lostPassword\":{\"ac\":1, \"op\": 2  }}  ]}");

    }
     public HttpServletResponse getResponse() {
        return _response;
    }

    public void setResponse(HttpServletResponse _response) {
        this._response = _response;
    }
        private void writeResponse(String msg) {
        PrintWriter writer = null;
        try {
            writer = getResponse().getWriter();
            writer.write(msg);

        } catch (IOException ex1) {
            Logger.getLogger(MAdmin.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            writer.close();
        }

    }
}
