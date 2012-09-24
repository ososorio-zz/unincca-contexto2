/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;

import edu.unincca.interfaces.IModule;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ososorio
 */
public class Login implements IModule{

    @Override
    public String getResponse(HttpServletRequest request) {
        
        /*
         * call the methods to validate login 
         * 
         */
        //http://www.json.org/java/
        return "{\"login\":\"Test\"}";
    }
    
}
