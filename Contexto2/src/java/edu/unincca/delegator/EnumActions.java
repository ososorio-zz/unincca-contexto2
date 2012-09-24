/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.delegator;

/**
 *
 * @author ososorio
 * http://javarevisited.blogspot.com/2011/08/enum-in-java-example-tutorial.html
 */
public enum EnumActions{ 
   _MAuthentication,
   _MAdmin,
   _MFunctionOpens,
   _MUser;
     
     public static EnumActions fromInteger(int x) {
        switch(x) {
        case 0:
            return _MAuthentication;
        case 1:
            return    _MAdmin;
        case 2:
            return    _MFunctionOpens;
        case 3:
            return    _MUser;

        }
        return null;
    }

   
};
 
