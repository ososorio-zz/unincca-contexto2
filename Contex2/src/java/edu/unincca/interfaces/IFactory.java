/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author ososorio
 */
public interface IFactory {
   
    /*
     * Receive and process parameters
     */
  abstract void processRequest(JSONObject jobject, HttpServletRequest request, HttpServletResponse response);
  abstract void processRequest( HttpServletRequest request, HttpServletResponse response);
  
}
