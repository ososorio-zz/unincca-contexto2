/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;

import edu.unincca.interfaces.IFactory;
import edu.unincca.interfaces.IModule;
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
public class MAuthentication  implements IFactory{
    
    /*
     * 1.login
     * 2.lostPassword
     * 3.ChangePassword 
     * 
     * 
     */
            /*
        {
          "ac": 0,
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

    @Override
    public void processRequest(JSONObject jobject,HttpServletRequest request, HttpServletResponse response) {
        
               
        try{
                setResponse(response);
            //op:operation
          // int action=Integer.parseInt( request.getParameter("op") );
        
        
           EnumOperations actionToCall = EnumOperations.fromInteger(jobject.getInt("op"));
        
        
            switch(actionToCall)
            {

                case login:

                  //new MAuthentication().processRequest(request, response);
                  writeResponse(new Login().getResponse(jobject));
                    

                break;

                case changePassword:

                break;

                case lostPassword:

                break;



            }
        
        
        
        
        
        }catch(NumberFormatException ex)
        {
            writeResponse("Operation Invalid:".concat( ex.getMessage().toString() ) );
        
        }
        catch(Exception ex)
        {
        
           writeResponse(ex.getMessage().toString());
        }
               
    }
    
    
     private void writeResponse(String msg)
    {
        PrintWriter writer = null;
        try {
                writer = getResponse().getWriter();
                writer.write(msg);
                
            } catch (IOException ex1) {
                Logger.getLogger(MAuthentication.class.getName()).log(Level.SEVERE, null, ex1);
            }
            finally {            
                writer.close();
            }
    
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
    
}
