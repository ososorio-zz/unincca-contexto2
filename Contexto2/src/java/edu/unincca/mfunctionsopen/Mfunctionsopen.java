package edu.unincca.mfunctionsopen;

import edu.unincca.interfaces.IFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class Mfunctionsopen implements IFactory {
  
    private HttpServletResponse _response;

    public HttpServletResponse getResponse() {
        return _response;
    }

    public void setResponse(HttpServletResponse _response) {
        this._response = _response;
    }

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

                case consulta_candidates:
                    writeResponse(new Consulta_Candidates().getResponse(jobject));
                    break;
                case consulta_cedula:
                    writeResponse(new Consulta_Cedula().getResponse(jobject));
                    break;
                case consulta_results:
                    writeResponse(new Consulta_Results().getResponse(jobject));
                    break;

            }
         
        } catch (NumberFormatException ex) {
            writeResponse("Operation Invalid:".concat(ex.getMessage().toString()));

        } catch (Exception ex) {

            writeResponse(ex.getMessage().toString());
        }
    }

    private void writeResponse(String msg) {
        PrintWriter writer = null;
        try {
            writer = getResponse().getWriter();
            writer.write(msg);

        } catch (IOException ex1) {
            Logger.getLogger(Mfunctionsopen.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            writer.close();
        }

    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    private void responseAvailableActions() {

        writeResponse("{\"Error\":\"Pleaser Review the actions and operation\",   \"Mlogin\":[  {\"login\":{\"ac\":1,\"op\":1}},"
                + "{\"lostPassword\":{\"ac\":1, \"op\": 2  }}  ]}");

    }
}
