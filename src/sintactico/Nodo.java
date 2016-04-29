package sintactico;

import principal.Ventana;

import java.util.LinkedList;
import java.util.List;

public class Nodo
{
    //atributos
    protected String simbolo;
    public static int tamSangria;
    Nodo nodoSiguiente;
    private char tipoDato;
    //public static TablaSimbolos tablaSimbolos;
    //public static String ambito;


    public List<Nodo> hijos = new LinkedList<>();

    //constructor sin parametros
    public Nodo() {
        nodoSiguiente = null;
    }//fin del constructor sin parametros

    //constructor parametrizado
    public Nodo(String simbolo) {
        this.simbolo = simbolo;
    }//fin del constrcutor parametrizado

    public static void sangria()
    {

        for(int i = 0; i < tamSangria; i++){
            System.out.print(" ");
        }

    }

    public void muestra()
    {

    }

    public void validaTipos()
    {
        tipoDato = 'v';

    }


}//fin de la clase Nodo