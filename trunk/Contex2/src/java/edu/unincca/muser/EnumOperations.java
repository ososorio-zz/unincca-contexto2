
package edu.unincca.muser;

/**
 *
 * @author YAZMIN,DIEGO,YEISON,GIOVANNY
 */
public enum EnumOperations{ 
 
   votacion,
   changePassword;
   
  public static EnumOperations fromInteger(int x) {
        switch(x) {
        case 0:
            return    votacion;
        case 1:
            return    changePassword;
        }
        return null;
    }
};
