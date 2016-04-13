package lexico;

public class Lexico {

    //atributos
    private String fuente;
    private int ind;
    private boolean continua;
    private char c;
    private int estado;

    private String simbolo;
    private int tipo;

    //constructor parametrizado
    public Lexico(String fuente) {
        ind = 0;
        simbolo = "";
        this.fuente = fuente;
    }//fin del constructor parametrizado

    //constructor
    public Lexico() {
        ind = 0;
        simbolo = "";
    }//fin del constructor

    public String getSimbolo() {
        return simbolo;
    }

    public int getTipo() {
        return tipo;
    }

    public void entrada(String fuente) {
        ind = 0;
        this.fuente = fuente;
    }//fin del metodo entrada

    public void siguienteSimbolo() {
        int anterior, entrada;

        estado = 0;
        anterior = 0;
        continua = true;
        simbolo = "";

        //inicio automata
        while (continua) {

            c = siguienteCaracter();

            if (esLetra(c)) entrada = 0;
            else if (esDigito(c)) entrada = 1;
            else if (c == '.') entrada = 2;
            else if (c == '"') entrada = 3;
            else if (c == '+' || c == '-') entrada = 4;
            else if (c == '*' || c == '/') entrada = 5;
            else if (c == '<' || c == '>') entrada = 6;
            else if (c == '|') entrada = 7;
            else if (c == '&') entrada = 8;
            else if (c == '!') entrada = 9;
            else if (c == '=') entrada = 10;
            else if (c == ';') entrada = 11;
            else if (c == ',') entrada = 12;
            else if (c == '(') entrada = 13;
            else if (c == ')') entrada = 14;
            else if (c == '{') entrada = 15;
            else if (c == '}') entrada = 16;
            else if (c == '$') entrada = 17;
            else if (esEspacio(c)) entrada = 18;
            else entrada = 19;

            anterior = estado;
            estado = siguienteEstado(entrada);

            if (estado == Estados.ACP) {
                tipo = aceptacion(anterior);

                if (tipo == TipoSimbolo.IDENTIFICADOR)
                    palabraReservada();

                if (esEspacio(c)) {
                    simbolo = simbolo.substring(0, simbolo.length() - 1);
                    break;
                }
                Retroceso();
                break;
            }//fin de if ACP
            else if (estado == Estados.ERR) {

                tipo = TipoSimbolo.ERROR;
                if (anterior == 2 || anterior == 4 || anterior == 11 || anterior == 13)
                    Retroceso();
                break;
            }//fin de else if

        }//fin de while

        //fin automata

        if (estado == Estados.ACP && anterior == 0)
            siguienteSimbolo();
    }//fin del metodo siguienteSimbolo

    private char siguienteCaracter() {
        if (terminado())
            return '$';
        return fuente.charAt(ind++);
    }//fin del metodo siguienteCaracter

    private int siguienteEstado(int entrada) {
        simbolo += c;
        return Estados.tablaEstados[estado][entrada];
    }//fin del metodo siguienteEstado

    private int aceptacion(int estado) {
        int tipo;

        switch (estado) {
            case 1: tipo = TipoSimbolo.ENTERO;
                break;
            case 3: tipo = TipoSimbolo.REAL;
                break;
            case 26: tipo = TipoSimbolo.CADENA;
                break;
            case 6: tipo = TipoSimbolo.IDENTIFICADOR;
                break;
            case 7: tipo = TipoSimbolo.opRELAC;
                break;
            case 8: tipo = TipoSimbolo.opRELAC;
                break;
            case 9: tipo = TipoSimbolo.opSUMA;
                break;
            case 10: tipo = TipoSimbolo.opMULT;
                break;
            case 12: tipo = TipoSimbolo.opOR;
                break;
            case 14: tipo = TipoSimbolo.opAND;
                break;
            case 15: tipo = TipoSimbolo.opNOT;
                break;
            case 16: tipo = TipoSimbolo.opIGUALDAD;
                break;
            case 17: tipo = TipoSimbolo.IGUAL;
                break;
            case 18: tipo = TipoSimbolo.opIGUALDAD;
                break;
            case 19: tipo = TipoSimbolo.PUNTO_COMA;
                break;
            case 20: tipo = TipoSimbolo.COMA;
                break;
            case 21: tipo = TipoSimbolo.PARENTESIS_INICIO;
                break;
            case 22: tipo = TipoSimbolo.PARENTESIS_FIN;
                break;
            case 23: tipo = TipoSimbolo.LLAVE_INICIO;
                break;
            case 24: tipo = TipoSimbolo.LLAVE_FIN;
                break;
            case 25: tipo = TipoSimbolo.PESOS;
                break;
            default: tipo = TipoSimbolo.ERROR;
                break;
        }//fin de switch

        return tipo;
    }//fin del metodo Aceptacion

    private boolean terminado() {
        return ind >= fuente.length();
    }//fin del metodo Terminado

    private void Retroceso() {
        simbolo = simbolo.substring(0, simbolo.length() - 1);
        if (c != '$')
            ind--;
        continua = false;
    }//fin del metodo Retroceso

    private boolean esLetra(char caracter) {
        return Character.isLetter(c);
    }//fin del metodo EsLetra

    private boolean esDigito(char caracter) {
        return Character.isDigit(c);
    }//fin del metodo EsDigito

    private boolean esEspacio(char caracter) {
        return Character.isWhitespace(c);
    }//fin del metodo EsEspacio

    public String tipoACad(int tipo) {
        String cad = "";

        switch (tipo) {
            case TipoSimbolo.IDENTIFICADOR: cad = "Identificador";
                break;
            case TipoSimbolo.ENTERO: cad = "Entero";
                break;
            case TipoSimbolo.REAL: cad = "Real";
                break;
            case TipoSimbolo.CADENA: cad = "Cadena";
                break;
            case TipoSimbolo.TIPO: cad = "Tipo";
                break;
            case TipoSimbolo.opSUMA: cad = "Operador Suma";
                break;
            case TipoSimbolo.opMULT: cad = "Operador Multiplicacion";
                break;
            case TipoSimbolo.opRELAC: cad = "Operador Relacional";
                break;
            case TipoSimbolo.opOR: cad = "Operador OR";
                break;
            case TipoSimbolo.opAND: cad = "Operador AND";
                break;
            case TipoSimbolo.opNOT: cad = "Operador NOT";
                break;
            case TipoSimbolo.opIGUALDAD: cad = "Operador Igualdad";
                break;
            case TipoSimbolo.PUNTO_COMA: cad = "Punto y Coma";
                break;
            case TipoSimbolo.COMA: cad = "Coma";
                break;
            case TipoSimbolo.PARENTESIS_INICIO: cad = "Parentesis Inicio";
                break;
            case TipoSimbolo.PARENTESIS_FIN: cad = "Parentesis Fin";
                break;
            case TipoSimbolo.LLAVE_INICIO: cad = "Llave Inicio";
                break;
            case TipoSimbolo.LLAVE_FIN: cad = "Llave Fin";
                break;
            case TipoSimbolo.IGUAL: cad = "Operador Asignacion";
                break;
            case TipoSimbolo.IF: cad = "Palabra Reservada if";
                break;
            case TipoSimbolo.WHILE: cad = "Palabra Reservada while";
                break;
            case TipoSimbolo.RETURN: cad = "Palabra Reservada return";
                break;
            case TipoSimbolo.ELSE: cad = "Palabra Reservada else";
                break;
            case TipoSimbolo.PESOS: cad = "Fin de la entrada";
                break;
            case TipoSimbolo.ERROR: cad = "Error";
                break;
        }//fin de switch

        return cad;
    }//fin del metodo simboloCadena

    private void palabraReservada() {
        switch (simbolo.replace('$', ' ').trim()) {

            case "if":
                tipo = TipoSimbolo.IF;
                break;
            case "else":
                tipo = TipoSimbolo.ELSE;
                break;
            case "while":
                tipo = TipoSimbolo.WHILE;
                break;
            case "return":
                tipo = TipoSimbolo.RETURN;
                break;
            case "int":
            case "float":
            case "void":
                tipo = TipoSimbolo.TIPO;
                break;
            default:
                break;

        }//fin de switch
    }//fin del metodo PalabraReservada

}//fin de la clase Lexico
