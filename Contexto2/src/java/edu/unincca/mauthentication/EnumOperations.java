/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.mauthentication;

/**
 *
 * @author ososorio
 */
public enum EnumOperations{ 
 
   login,
   lostPassword,
   changePassword;
   
  public static EnumOperations fromInteger(int x) {
        switch(x) {
        case 0:
            return    login;
        case 1:
            return    lostPassword;
        case 2:
            return    changePassword;
        }
        return null;
    }
};
