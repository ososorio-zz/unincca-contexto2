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
    lista,
    listar_votacion_candidatos;

    public static EnumOperations2 fromInteger(int x) {
        switch (x) {
            case 0:
                return candidato;
            case 1:
                return lista;
            case 2:
                return lista_candidato;
            case 3:
                return listar_votacion_candidatos;
        }
        return null;
    }
};