/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.interfaces;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

/**
 *
 * @author ososorio
 */
public interface IModule {
    
    
    String getResponse( JSONObject jobject);
    
}
