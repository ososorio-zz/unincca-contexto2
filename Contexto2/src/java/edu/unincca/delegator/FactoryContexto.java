/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.delegator;

import edu.unincca.interfaces.IFactory;
import edu.unincca.mauthentication.MAuthentication;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
       
        
       
        
        try{
           
           setResponse(response);             
            
           IFactory action=null; 
            
            //ac:action
           int actionRequest=Integer.parseInt( request.getParameter("ac") );
      
           EnumActions  actionToCall =EnumActions.fromInteger(actionRequest);
        
        
            if(actionToCall==null)
            {
              responseAvailableActions();
              return;
            }
           
            switch(actionToCall)
            {

                case _MAuthentication:

                 action= new MAuthentication();

                break;

                case _MAdmin:

                break;

                case _MFunctionOpens:

                break;

                case _MUser:

                break;


            }
        
         
            
            action.processRequest(request, response);
        
        
        
        }catch(NumberFormatException ex)
        {
            writeResponse("Action Invalid:".concat( ex.getMessage().toString() )  );
        
        }
        catch(Exception ex)
        {
        
           writeResponse(ex.getMessage().toString() );
        }
        
    }
    
    
    
    private void writeResponse(String msg)
    {
    
        PrintWriter writer = null;
        try {
                writer = getResponse().getWriter();
                writer.write(msg);
                
            } catch (IOException ex1) {
                Logger.getLogger(FactoryContexto.class.getName()).log(Level.SEVERE, null, ex1);
            }
            finally {            
                writer.close();
            }
    
    }

    private void responseAvailableActions() {
       
        writeResponse("{\"Error\":\"Pleaser Review the actions and operation\",   \"Mlogin\":[  {\"login\":{\"ac\":1,\"op\":1}},"
                + "{\"lostPassword\":{\"ac\":1, \"op\": 2  }}  ]}");
        
    }


    
    
}
