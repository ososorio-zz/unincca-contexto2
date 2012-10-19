/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unincca.muser;

/**
 *
 * @author YAZMIN,DIEGO,YEISON,GIOVANNY
 */
public enum EnumOperations2 {

    candidato,
    lista_candidato,
    lista;

    public static EnumOperations2 fromInteger(int x) {
        switch (x) {
            case 0:
                return candidato;
            case 1:
                return lista;
            case 2:
                return lista_candidato;
        }
        return null;
    }
};