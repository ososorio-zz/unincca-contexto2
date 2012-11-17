/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.delegator;

import edu.unincca.interfaces.IFactory;
import edu.unincca.mauthentication.MAuthentication;
import edu.unincca.mfunctionsopen.Mfunctionsopen;
import edu.unincca.muser.MUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author ososorio
 */
public class FactoryContexto implements IFactory {

    /*configure services/actions available in proyect vote (suffrage)
     */
    private HttpServletResponse _response;

    public HttpServletResponse getResponse() {
        return _response;
    }

    public void setResponse(HttpServletResponse _response) {
        this._response = _response;
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {


        try {

            setResponse(response);


            StringBuilder jb = new StringBuilder();
            String line = null;

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }

            JSONObject jobject = new JSONObject(jb.toString());

            IFactory action = null;

            //ac:action
            // int actionRequest=Integer.parseInt( request.getParameter("ac") );
            EnumActions actionToCall = EnumActions.fromInteger(jobject.getInt("ac"));

            //JOptionPane.showMessageDialog(null, jobject.getInt("ac"));
            if (actionToCall == null) {
                responseAvailableActions();
                return;
            }

            switch (actionToCall) {

                case _MAuthentication:

                    action = new MAuthentication();

                    break;

                case _MAdmin:

                    break;

                case _MFunctionOpens:
                    action = new Mfunctionsopen();
                    break;

                case _MUser:
                  action = new MUser();
                    break;


            }



            action.processRequest(jobject, request, response);



        } catch (NumberFormatException ex) {
            writeResponse("Action Invalid:".concat(ex.getMessage().toString()));

        } catch (Exception ex) {

            writeResponse("Invalid JSON"+ex.getMessage().toString());
        }

    }

    private void writeResponse(String msg) {

        PrintWriter writer = null;
        try {
            writer = getResponse().getWriter();
            writer.write(msg);

        } catch (IOException ex1) {
            Logger.getLogger(FactoryContexto.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            writer.close();
        }

    }

    private void responseAvailableActions() {

        writeResponse("{\"Error\":\"Pleaser Review the actions and operation\",   \"Mlogin\":[  {\"login\":{\"ac\":1,\"op\":1}},"
                + "{\"lostPassword\":{\"ac\":1, \"op\": 2  }}  ]}");

    }

    @Override
    public void processRequest(JSONObject jobject, HttpServletRequest request, HttpServletResponse response) {
    }
}
