package main.OV.validator;

public class MisCadenas {
    public static String derecha(String cCadena, int nCuantos)
    {
        int nCar = 0;
        int nPos = 0;

        nCar = nCuantos;
        if (nCar <= 0) nCar = 0;
        if (nCar > cCadena.length()) nCar = cCadena.length();

        nPos = cCadena.length() - nCar;

        return cCadena.substring(nPos);
    }

    public static String izquierda(String _cadena, int _cuantos)
    {
        int nCar = 0;

        nCar = _cuantos;
        if (nCar < 0) nCar = 0;
        if (nCar > _cadena.length()) nCar = _cadena.length();

        return _cadena.substring(0, nCar);
    }
}
