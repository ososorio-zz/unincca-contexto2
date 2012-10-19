package edu.unincca.muser;

import edu.unincca.delegator.EnumActions;
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

public class MUser implements IFactory {
    /* 
    
    {
    "ac": 3,
    "op": 0,
    "cp": 0
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
        try {
            setResponse(response);
            //op:operation
            // int action=Integer.parseInt( request.getParameter("op") );


            IFactory action = null;

            //ac:action
            // int actionRequest=Integer.parseInt( request.getParameter("ac") );
            EnumOperations actionToCall = EnumOperations.fromInteger(jobject.getInt("op"));

            //JOptionPane.showMessageDialog(null, jobject.getInt("ac"));
            if (actionToCall == null) {
                responseAvailableActions();
                return;
            }

            switch (actionToCall) {

                case votacion:
                  
                    action = new Votacion();
                    break;

                case changePassword:
                    action = new ChangePassword();
                    break;

            }
            action.processRequest(jobject, request, response);
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
            Logger.getLogger(MUser.class.getName()).log(Level.SEVERE, null, ex1);
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
