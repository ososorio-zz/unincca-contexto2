package edu.unincca.madmin;

public enum Enum_Inscripcion {

    Ins_Cadidatos,
    Ins_Grupos;


    public static Enum_Inscripcion fromInteger(int x) {
        switch (x) {
            case 0:
                return  Ins_Cadidatos;
            case 1:
                return Ins_Grupos;

        }
        return null;
    }
};
