package sintactico;

import lexico.Lexico;
import lexico.TipoSimbolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Sintactico {

    //atributos
    private Lexico lexico;
    private String entrada;
    private int fila, columna, accion;
    private Stack<ElementoPila> pila;
    private NoTerminal nt;
    private Nodo nodo;

    private JTable tablaResultados;
    private int[][] tabla = new int[95][46];
    public int[] idReglas = new int[52];
    public int[] lonReglas = new int[52];
    public String[] strReglas = new String[52];

    //constructor
    public Sintactico(JTable tablaResultados) {
        cargarArchivo();
        entrada = "";
        lexico = new Lexico("");
        pila = new Stack<ElementoPila>();
        this.tablaResultados = tablaResultados;
    }//fin del constructor

    //constructor parametrizado
    public Sintactico(String entrada) {
        cargarArchivo();
        this.entrada = entrada;
        lexico = new Lexico(entrada);
        pila = new Stack<ElementoPila>();
    }//fin del constructor parametrizado

    public void setEntrada(String entrada) {
        this.entrada = entrada;
        lexico = new Lexico(entrada);
        pila.clear();
    }

    public String getEntrada() {
        return entrada;
    }

    public void analisisSintactico() {
        DefaultTableModel modeloTabla = (DefaultTableModel)tablaResultados.getModel();
        Object[] filas = new Object[3];

        //System.out.println("Cuajo: " + );

        accion = 0;
        pila.push(new Terminal(TipoSimbolo.PESOS, "$"));
        pila.push(new Estado(0));
        lexico.siguienteSimbolo();

        while(true)
        {
            fila = pila.peek ().getId();
            columna = lexico.getTipo();
            accion = tabla[fila] [columna];


            ElementoPila[] elementos = new ElementoPila[pila.size()];
            String elementosEnPila = "";
            for (int i = 0; i < elementos.length; i++) {
                elementos[i] = pila.get(i);
            }

            //reversar
            Collections.reverse(Arrays.asList(elementos));

            for (int i = elementos.length - 1; i >= 0; i--) {
                elementosEnPila += elementos[i].getElemento();
            }



            filas[0] = elementosEnPila;
            filas[1] = lexico.getSimbolo();
            filas[2] = accion;

            modeloTabla.addRow(filas);
            tablaResultados.setModel(modeloTabla);
            //System.out.printf("%50s%10s%10s\n", elementosEnPila, lexico.simbolo, accion);

            if(accion > 0) // desplazamiento
            {
                pila.push(new Terminal(lexico.getTipo(), lexico.getSimbolo()));
                pila.push(new Estado(accion));
                lexico.siguienteSimbolo();

            }//fin de if

            else if(accion < 0) //aceptacion reduccion
            {
                if(accion == -1)
                {
                    fila = pila.peek().getId();
                    columna = lexico.getTipo();
                    accion = tabla[fila][columna];
                    break;
                }//fin de if

                int regla = -(accion + 2);
                int reglaAux = (regla + 1);

                switch(reglaAux)
                {
                    case 1: //<programa> ::= <Definiciones>
                        nodo = new Programa(pila);
                        break;

                    case 2:
                        nodo = new Nodo("<Definiciones>");
                        break;

                    case 3:
                        nodo = new Definiciones(pila);
                        break;

                    case 4:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 5:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 6:
                        nodo = new Variables(pila);
                        break;

                    case 7:
                        nodo = new Nodo("<ListaVar>");
                        break;

                    case 8:
                        nodo = new VariablesDos(pila);
                        break;

                    case 9:
                        nodo = new DefFunc(pila);
                        break;

                    case 10:
                        nodo = new Nodo("<ListaParam>");
                        break;

                    case 11:
                        nodo = new Parametros(pila);
                        break;

                    case 12:
                        nodo = new Nodo("<ListaParam>");
                        break;

                    case 13:
                        nodo = new ParametrosDos(pila);
                        break;

                    case 14:
                        nodo = new BloqueFuncion(pila);
                        break;

                    case 15:
                        nodo = new Nodo("<DefLocales>");
                        break;

                    case 16:
                        nodo = new DefinicionesLocales(pila);
                        break;

                    case 17:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 18:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 19:
                        nodo = new Nodo("<Sentencias>");
                        break;

                    case 20:
                        nodo = new Sentencias(pila);
                        break;

                    case 21:
                        nodo = new SentenciasAsignacion(pila);
                        break;

                    case 22:
                        nodo = new SentenciaIf(pila);
                        break;

                    case 23:
                        nodo = new SentenciaWhile(pila);
                        break;

                    case 24:
                        nodo = new SentenciaValorRegresa(pila);
                        break;

                    case 25:
                        nodo = new SentenciaLlamadaFuncion(pila);
                        break;

                    case 26:
                        nodo = new Nodo("<Otro>");
                        break;

                    case 27:
                        nodo = new Otro(pila);
                        break;

                    case 28:
                        nodo = new Bloque(pila);
                        break;

                    case 29:
                        nodo = new Nodo("Retorno");
                        break;

                    case 30:
                        pila.pop();
                        Nodo retorno = new Nodo("<Retorno>");
                        retorno.hijos.add(pila.pop().getNodo());
                        nodo = retorno;
                        break;

                    case 31:
                        nodo = new Nodo("<ListaArgumentos>");
                        break;

                    case 32:
                        nodo = new ListaArgumentos(pila);
                        break;

                    case 33:
                        nodo = new Nodo("<ListaArgumentos>");
                        break;

                    case 34:
                        nodo = new ListaArgumentosDos(pila);
                        break;

                    case 35:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 36: //<Termino> ::= id
                        nodo = new Identificador(pila);
                        break;

                    case 37: //<Termino> ::= entero
                        nodo = new Entero(pila);
                        break;

                    case 38: //<Termino> ::= real
                        nodo = new Real(pila);
                        break;

                    case 39: //<Termino> ::= cadena
                        nodo = new Cadena(pila);
                        break;

                    case 40:
                        nodo = new LlamadaFuncion(pila);
                        break;

                    case 41:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 42:
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 43:
                        nodo = new ExpresionEntreParentesis(pila);
                        break;

                    case 44:
                        nodo = new OperadorAdicionDos(pila);
                        break;

                    case 45:
                        nodo = new OperadorOr(pila);
                        break;

                    case 46: //<Expresion> ::= <Termino> * <Termino>
                        nodo = new OperadorMultiplicacion(pila);
                        break;

                    case 47:  //<Expresion> ::= <Termino> + <Termino>
                        nodo = new OperadorAdicion(pila);
                        break;

                    case 48:
                        nodo = new OperadorRelacional(pila);
                        break;

                    case 49:
                        nodo = new OperadorIgualdad(pila);
                        break;

                    case 50:
                        nodo = new OperadorAnd(pila);
                        break;

                    case 51:
                        nodo = new OperadorOr(pila);
                        break;

                    case 52: //<Expresion> ::= <Termino>
                        pila.pop();
                        Nodo expresion = new Nodo("<Expresion>");
                        expresion.hijos.add(pila.pop().getNodo());
                        nodo = expresion;
                        break;

                    default:
                        for(int i = 0; i < lonReglas[regla] * 2; i++){
                            pila.pop();
                        }
                        break;
                }//fin de switch

                fila = pila.peek().getId();
                columna = idReglas[regla];
                accion = tabla[fila] [columna];

                nt = new NoTerminal(idReglas[regla], strReglas[regla]);
                nt.setNodo(nodo);

                pila.push(nt);
                pila.push(new Estado(accion));
            }//fin de else if

            if(accion == 0)
                return;



        }//fin de while

    }//fin del metodo analisisSintactico


    private void cargarArchivo() {
        try {
            FileReader fr = new FileReader("compilador.lr");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int contadorLinea = 0;

            while ((linea = br.readLine()) != null) {
                String arreglo[] = linea.split("\\s+");


                for (int i = 0; i < arreglo.length && contadorLinea < 53; i++) {
                    if (contadorLinea > 0) {
                        if (i == 0) {
                            idReglas[contadorLinea - 1] = Integer.valueOf(arreglo[i]);
                        }
                        if (i == 1) {
                            lonReglas[contadorLinea - 1] = Integer.valueOf(arreglo[i]);
                        }
                        if (i == 2) {
                            strReglas[contadorLinea - 1] = arreglo[i];
                        }

                    }//fin de if
                }//fin de for

                for (int i = 0; i < arreglo.length; i++) {
                    if (contadorLinea > 53) {
                        tabla[contadorLinea - 54][i] = Integer.valueOf(arreglo[i]);
                    }
                }
                contadorLinea++;
            }//fin de while

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void Gramatica_3()
    {
        // <Expresion> ::= <Expresion> opMul <Expresion>
        // <Expresion> ::= <Expresion> opSuma <Expresion>
        // <Expresion> ::= <Termino>
        // <Termino> ::= id
        // <Termino> ::= entero
        DefaultTableModel modeloTabla = (DefaultTableModel)tablaResultados.getModel();
        Object[] filas = new Object[3];

        int[] idReglas = { 5, 5, 5, 6, 6 };
        int[] lonReglas = { 3, 3, 1, 1, 1 };
        String[] strReglas = { "<Expresion1>", "<Expresion2>", "<Expresion3>", "<Termino4>", "<Termino5>" };

        //Tabla de la grámatica
        int[][] tablaLR =
                {
                        {3, 4,  0,  0,  0, 1, 2},
                        {0, 0,  5,  6, -1, 0, 0},
                        {0, 0, -4, -4, -4, 0, 0},
                        {0, 0, -5, -5, -5, 0, 0},
                        {0, 0, -6, -6, -6, 0, 0},
                        {3, 4,  0,  0,  0, 7, 2},
                        {3, 4,  0,  0,  0, 8, 2},
                        {0, 0, -2, -2, -2, 0, 0},
                        {0, 0,  5, -3, -3, 0, 0}
                };

        accion = 0;
        pila.push(new Terminal(TipoSimbolo.PESOS, "$"));
        pila.push(new Estado(0));
        lexico.siguienteSimbolo();

        //System.out.printf("%50s%10s%10s\n", "Elementos en pila", "Simbolo", "Accion");

        while(true)
        {
            fila = pila.peek ().getId();
            columna = lexico.getTipo();
            accion = tablaLR [fila] [columna];


            ElementoPila[] elementos = new ElementoPila[pila.size()];
            String elementosEnPila = "";
            for (int i = 0; i < elementos.length; i++) {
                elementos[i] = pila.get(i);
            }

            //reversar
            Collections.reverse(Arrays.asList(elementos));

            for (int i = elementos.length - 1; i >= 0; i--) {
                elementosEnPila += elementos[i].getElemento();
            }



            filas[0] = elementosEnPila;
            filas[1] = lexico.getSimbolo();
            filas[2] = accion;

            modeloTabla.addRow(filas);
            tablaResultados.setModel(modeloTabla);
            //System.out.printf("%50s%10s%10s\n", elementosEnPila, lexico.simbolo, accion);

            if(accion > 0) // desplazamiento
            {
                pila.push(new Terminal(lexico.getTipo(), lexico.getSimbolo()));
                pila.push(new Estado(accion));
                lexico.siguienteSimbolo();

            }//fin de if

            else if(accion < 0) //aceptacion reduccion
            {
                if(accion == -1)
                {
                    fila = pila.peek().getId();
                    columna = lexico.getTipo();
                    accion = tablaLR[fila][columna];

                    //Console.WriteLine("{0, 60}", "Aceptacion");

                    break;
                }//fin de if

                int regla = -(accion + 2);
                int reglaAux = (regla + 1);

                switch(reglaAux)
                {
//                    case 1: //<programa> ::= <Definiciones>
//                        nodo = new Programa(pila);
//                        break;
//                    case 2: //<Definiciones> ::= \e
//                        nodo = new Nodo("<Definiciones>");
//                        break;
//                    case 3: //<Definiciones> ::= <Definicion> <Definiciones>
//                        nodo = new Definiciones(pila);
//                        break;
//                    case 4: //<Definicion> ::= <DefVar>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 5: //<Definicion> ::= <DefFunc>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 6: //<DefVar> ::= tipo identificador <ListaVar> ;
//                        nodo = new Variables(pila);
//                        break;
//                    case 7: //<ListaVar> ::= \e
//                        nodo = new Nodo("<ListaVar>");
//                        break;
//                    case 8: //<ListaVar> ::= , identificador <ListaVar>
//                        nodo = new Variables2(pila);
//                        break;
//                    case 9: //<DefFunc> ::= tipo identificador ( <Parametros> ) <BloqFunc>
//                        nodo = new DefFunc(pila);
//                        break;
//                    case 10: //<Parametros> ::= \e
//                        nodo = new Nodo("<ListaParam>");
//                        break;
//                    case 11: //<Parametros> ::= tipo identificador <ListaParam>
//                        nodo = new Parametros(pila);
//                        break;
//                    case 12: //<ListaParam> ::= \e
//                        nodo = new Nodo("<ListaParam>");
//                        break;
//                    case 13: //<ListaParam> ::= , tipo identificador <ListaParam>
//                        nodo = new Parametros2(pila);
//                        break;
//                    case 14: //<BloqFunc> ::= { <DefLocales> }
//                        nodo = new BloqueFunc(pila);
//                        break;
//                    case 15: //<DefLocales> ::= \e
//                        nodo = new Nodo("<DefLocales>");
//                        break;
//                    case 16: //<DefLocales> ::= <DefLocal> <DefLocales>
//                        nodo = new DefinicionesLocales(pila);
//                        break;
//                    case 17: //<DefLocal> ::= <DefVar>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 18: //<DefLocal> ::= <Sentencia>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 19: //<Sentencias> ::= \e
//                        nodo = new Nodo("<Sentencias>");
//                        break;
//                    case 20: //<Sentencias> ::= <Sentencia> <Sentencias>
//                        nodo = new Sentencias(pila);
//                        break;
//                    case 21: //<Sentencia> ::= identificador = <Expresion> ;
//                        nodo = new SentenciaAsignacion(pila);
//                        break;
//                    case 22: //<Sentencia> ::= if ( <Expresion> ) <SentenciaBloque> <Otro>
//                        nodo = new SentenciaIf(pila);
//                        break;
//                    case 23: //<Sentencia> ::= while ( <Expresion> ) <Bloque>
//                        nodo = new SentenciaWhile(pila);
//                        break;
//                    case 24: //<Sentencia> ::= return <ValorRegresa> ;
//                        nodo = new SentenciaValorRegresa(pila);
//                        break;
//                    case 25: //<Sentencia> ::= <LlamadaFunc> ;
//                        nodo = new SentenciaLlamadaFuncion(pila);
//                        break;
//                    case 26: //<Otro> ::= \e
//                        nodo = new Nodo("<Otro>");
//                        break;
//                    case 27: //<Otro> ::= else <SentenciaBloque>
//                        nodo = new Otro(pila);
//                        break;
//                    case 28: //<Bloque> ::= { <Sentencias> }
//                        nodo = new Bloque(pila);
//                        break;
//                    case 29: //<ValorRegresa> ::= \e
//                        nodo = new Nodo("<ValorRegresa>");
//                        break;
//                    case 30: //<ValorRegresa> ::= <Expresion>
//                        pila.Pop();
//                        Nodo valorRegresar = new Nodo("<ValorRegresa>");
//                        valorRegresar.AñadirHijo(pila.Pop().Nodo);
//                        nodo = valorRegresar;
//                        break;
//                    case 31: //<Argumentos> ::= \e
//                        nodo = new Nodo("<ListaArgumentos>");
//                        break;
//                    case 32: //<Argumentos> ::= <Expresion> <ListaArgumentos>
//                        nodo = new ListaArgumentos(pila);
//                        break;
//                    case 33: //<ListaArgumentos> ::= \e
//                        nodo = new Nodo("<ListaArgumentos>");
//                        break;
//                    case 34: //<ListaArgumentos> ::= , <Expresion> <ListaArgumentos>
//                        nodo = new ListaArgumentos2(pila);
//                        break;
//                    case 35: //<Termino> ::= <LlamadaFunc>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 36: //<Termino> ::= identificador
//                        nodo = new Identificador(pila);
//                        break;
//                    case 37: //<Termino> ::= entero
//                        nodo = new Entero(pila);
//                        break;
//                    case 38: //<Termino> ::= real
//                        nodo = new Real(pila);
//                        break;
//                    case 39: //<Termino> ::= cadena
//                        nodo = new Cadena(pila);
//                        break;
//                    case 40: //<LlamadaFunc> ::= identificador ( <Argumentos> )
//                        nodo = new LlamadaFuncion(pila);
//                        break;
//                    case 41: //<SentenciaBloque> ::= <Sentencia>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 42: //<SentenciaBloque> ::= <Bloque>
//                        pila.Pop();
//                        nodo = pila.Pop().Nodo;
//                        break;
//                    case 43: //<Expresion> ::= ( <Expresion> )
//                        nodo = new ExpresionEntreParentesis(pila);
//                        break;
//                    case 44: //<Expresion> ::= opSuma <Expresion>
//                        nodo = new OperadorAdicion2(pila);
//                        break;
//                    case 45: //<Expresion> ::= opNot <Expresion>
//                        nodo = new OperadorNot(pila);
//                        break;
//                    case 46: //<Expresion> ::= <Expresion> opMul <Expresion>
//                        nodo = new OperadorMultiplicacion(pila);
//                        break;
//                    case 47: //<Expresion> ::= <Expresion> opSuma <Expresion>
//                        nodo = new OperadorAdicion(pila);
//                        break;
//                    case 48: //<Expresion> ::= <Expresion> opRelac <Expresion>
//                        nodo = new OperadorRelacional(pila);
//                        break;
//                    case 49: //<Expresion> ::= <Expresion> opIgualdad <Expresion>
//                        nodo = new OperadorIgualdad(pila);
//                        break;
//                    case 50: //<Expresion> ::= <Expresion> opAnd <Expresion>
//                        nodo = new OperadorAnd(pila);
//                        break;
//                    case 51: //<Expresion> ::= <Expresion> opOr <Expresion>
//                        nodo = new OperadorOr(pila);
//                        break;
//                    case 52: //<Expresion> ::= <Termino>
//                        pila.Pop();
//                        Nodo expresion = new Nodo("<Expresion>");
//                        expresion.AñadirHijo(pila.Pop().Nodo);
//                        nodo = expresion;
//                        break;

                    case 1: //<Expresion> ::= <Termino> * <Termino>
                        nodo = new Multiplicacion(pila);
                        break;

                    case 2:  //<Expresion> ::= <Termino> + <Termino>
                        nodo = new Suma(pila);
                        break;

                    case 3: //<Expresion> ::= <Termino>
                        pila.pop();
                        nodo = pila.pop().getNodo();
                        break;

                    case 4: //<Termino> ::= id
                        nodo = new Identificador(pila);
                        break;

                    case 5: //<Termino> ::= entero
                        nodo = new Entero(pila);
                        break;

                    default:
                        for(int i = 0; i < lonReglas[regla] * 2; i++){
                            pila.pop();
                        }
                        break;
                }//fin de switch

                fila = pila.peek().getId();
                columna = idReglas[regla];
                accion = tablaLR[fila] [columna];

                nt = new NoTerminal(idReglas[regla], strReglas[regla]);
                nt.setNodo(nodo);

                pila.push(nt);
                pila.push(new Estado(accion));
            }//fin de else if

            if(accion == 0)
                return;



        }//fin de while

        //nodo.mostrar();

    }//fin del metodo Gramatica 3


}//fin de la clase Sintactico