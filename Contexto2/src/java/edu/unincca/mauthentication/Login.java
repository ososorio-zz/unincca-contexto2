/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;


import edu.unincca.database.Conex;
import edu.unincca.interfaces.IModule;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.PreparedStatement;
/**
 *
 * @author ososorio
 */
public class Login implements IModule{

    @Override
    public String getResponse(JSONObject jobject) {
        
        try {
            
            
             StringBuilder jb = new StringBuilder();
             String line = null;
            
             
             
            Iterator it = jobject.keys(); //gets all the keys

            while(it.hasNext())
            {
                String key = (String) it.next(); // get key
                Object o = jobject.get(key); // get value
                //session.putValue(key, o); // store in session
                System.out.println(key+" === "+o);
            }
        
            
            
           JSONObject jsonData= jobject.optJSONObject("data");
           
           String user = jsonData.getString("user");
           String pass = jsonData.getString("pass"); 
        
           if(user.equals("test") && pass.equalsIgnoreCase("test"))
           {       
            return "{\"login\":\"authenticated\"}";  
           }
           else{
               
               
               Conex con = new Conex();
               Connection connection=con.getConnection();
               PreparedStatement statement= (PreparedStatement) connection.prepareStatement("");
               
               con.executeQuery(connection, statement);
               
            //  Connection connnection= con.getConnection();
               
               
               
               return "{\"login\":\"failed\"}";
           }
           
         
           
           
           
           
        
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        /*
         * call the methods to validate login 
         * 
         */
        //http://www.json.org/java/
        return "{\"login\":\"failed\"}";
    }

   
    
}
