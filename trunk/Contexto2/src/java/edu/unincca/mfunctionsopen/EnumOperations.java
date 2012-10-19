package edu.unincca.mfunctionsopen;

/**
 *
 * @author wilson
 */
public enum EnumOperations {

    consulta_candidates,
    consulta_cedula,
    consulta_results;

    public static EnumOperations fromInteger(int x) {
        switch (x) {
            case 0:
                return consulta_candidates;
            case 1:
                return consulta_cedula;
            case 2:
                return consulta_results;
        }
        return null;
    }
};
