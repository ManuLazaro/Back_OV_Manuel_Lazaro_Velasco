package main.OV.validator;

public class CifNif {
    public static void validarNIF(String _codigo) throws Exception
    {
        validarNIF(_codigo, "El NIF " + _codigo);
    }
    public static void validarNIF(String _codigo, String _literalNIF) throws Exception
    {
        String cCodigo = _codigo.trim().toUpperCase();
        char cCarIni = cCodigo.charAt(0);
        char cCarFin = cCodigo.charAt(cCodigo.length() - 1);
        String cNumeros = null;
        String[] args = { _literalNIF };
        String cFormatoAlt = "El NIF " + _codigo + " es incorrecto";

        if ((!(Character.isDigit(cCarIni))) && (cCarIni != 'K') && (cCarIni != 'L') && (cCarIni != 'M')) {
            throw new Exception();
        }

        if ((_codigo.length() != 9) && (_codigo.length() != 10)) {
            throw new Exception();
        }

        cNumeros = MisCadenas.izquierda(cCodigo, cCodigo.length() - 1);

        if (!(Character.isDigit(cCarIni))) {
            cNumeros = cNumeros.substring(1);

            if (cNumeros.length() != 7) {
                throw new Exception();
            }
        }

        for (int i = 0; i < cNumeros.length(); ++i) {
            if (!(Character.isDigit(cNumeros.charAt(i)))) {
                throw new Exception();
            }

        }

        if (cCarFin != getLetraNIF(cCodigo))
            throw new Exception();
    }
    public static char getLetraNIF(String _codigo)
    {
        String cLetras = "TRWAGMYFPDXBNJZSQVHLCKE";
        String cNumeros = _codigo.trim().toUpperCase();
        char cLetra = '\0';
        try
        {
            if (!(Character.isDigit(cNumeros.charAt(0)))) {
                cNumeros = cNumeros.substring(1);
            }

            if (!(Character.isDigit(cNumeros.charAt(cNumeros.length() - 1)))) {
                cNumeros = MisCadenas.izquierda(cNumeros, cNumeros.length() - 1);
            }

            if (cNumeros != null) {
                int nNumeros = 0;
                int nResto = 0;

                nNumeros = Integer.valueOf(cNumeros).intValue();
                nResto = nNumeros % 23;

                cLetra = cLetras.charAt(nResto);
            }
        }
        catch (Exception e) {
            cLetra = '\0';
        }

        return cLetra;
    }
    public static void validarCIF(String _codigo) throws Exception
    {
        validarCIF(_codigo, "El CIF");
    }

    public static void validarCIF(String _codigo, String _literalCIF) throws Exception
    {
        String cCodigo = _codigo.trim().toUpperCase();
        char cCarIni = cCodigo.charAt(0);
        char cCarFin = cCodigo.charAt(cCodigo.length() - 1);
        String cNumeros = cCodigo.substring(1, cCodigo.length() - 1);
        String[] args = { _literalCIF };
        String cFormatoAlt = "El CIF " + _codigo + " es incorrecto";
        String cTipoOrg = "ABCDEFGHJNPQRSUVW";

        if (cTipoOrg.indexOf(cCarIni) < 0) {
            throw new Exception();
        }

        if ((_codigo.length() != 9) && (_codigo.length() != 10)) {
            throw new Exception();
        }

        if (cNumeros.length() != 7) {
            throw new Exception();
        }

        for (int i = 0; i < cNumeros.length(); ++i) {
            if (!(Character.isDigit(cNumeros.charAt(i)))) {
                throw new Exception();
            }

        }

        int nControl = 0;
        int nPares = 0;
        int nImpares = 0;

        for (int i = 1; i < cNumeros.length(); i += 2) {
            nPares += Character.getNumericValue(cNumeros.charAt(i));
        }

        for (int i = 0; i < cNumeros.length(); i += 2) {
            int tmp = 2 * Integer.parseInt(cNumeros.substring(i, i + 1));
            if (tmp > 9) {
                tmp = 1 + tmp - 10;
            }
            nImpares += tmp;
        }

        nControl = 10 - ((nImpares + nPares) % 10);
        if (nControl == 10) {
            nControl = 0;
        }
        String numeros = "0123456789";
        String letras = "JABCDEFGHI";

        if ((((cCarFin != numeros.charAt(nControl)) ? 1 : 0) & ((cCarFin != letras.charAt(nControl)) ? 1 : 0)) != 0)
            throw new Exception();
    }
}
