package edu.unincca.madmin;

public enum EnumOperations {

    Creación_votaciones,
    Inscripción_candidatos,
    Inscripción_votantes;

    public static EnumOperations fromInteger(int x) {
        switch (x) {
            case 0:
                return Creación_votaciones;
            case 1:
                return Inscripción_candidatos;
            case 2:
                return Inscripción_votantes;
        }
        return null;
    }
};
