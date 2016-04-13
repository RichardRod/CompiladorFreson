package sintactico;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Nodo
{
    //atributos
    Nodo nodoIzquierdo;
    public String simbolo;
    Nodo nodoDerecho;
    public List<Nodo> hijos = new LinkedList<>();

    //constructor sin parametros
    public Nodo() {

    }//fin del constructor sin parametros

    //constructor parametrizado
    public Nodo(String simbolo) {
        this.simbolo = simbolo;
    }//fin del constrcutor parametrizado



}//fin de la clase Nodo

class Programa extends Nodo
{
    public Programa(Stack<ElementoPila> pila){
        simbolo = "<Programa>";
        pila.pop();
        hijos.add(pila.pop().getNodo());
    }//fin del constructor
}//fin de la clase Programa

class Identificador extends Nodo {

    //constructor
    public Identificador(Stack<ElementoPila> pila) {
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Identificador> " + simbolo;
    }//fin del constructor

}//fin de la clase Identificador

class Entero extends Nodo {

    //constructor
    public Entero(Stack<ElementoPila> pila) {
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Entero> " + simbolo;
    }//fin del constrcutor

}//fin de la clase Entero

class Real extends Nodo {

    //constructor
    public Real(Stack<ElementoPila> pila) {
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Real> " + simbolo;
    }//fin del constrcutor

}//fin de la clase Real

class Cadena extends Nodo {

    //constructor
    public Cadena(Stack<ElementoPila> pila) {
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Cadena> " + simbolo;
    }//fin del constrcutor

}//fin de la clase Cadena

class Definiciones extends Nodo
{

    public Definiciones(Stack<ElementoPila> pila){

        simbolo = "<Definiciones>";

        pila.pop();
        Nodo definiciones = pila.pop().getNodo();
        pila.pop();
        Nodo definicion = pila.pop().getNodo();

        hijos.add(definicion);
        for(int i = 0; i < definiciones.hijos.size(); i++){
            hijos.add(definiciones.hijos.get(i));
        }//fin de for
    }//fin del constructor
}//fin de la clase Definiciones

class DefinicionesLocales extends Nodo
{
    public DefinicionesLocales(Stack<ElementoPila> pila){
        simbolo = "<DefLocales>";

        pila.pop();
        Nodo definicionesLocales = pila.pop().getNodo();
        pila.pop();
        Nodo definicionLocal = pila.pop().getNodo();

        hijos.add(definicionLocal);
        for(int i = 0; i < definicionesLocales.hijos.size(); i++){
            hijos.add(definicionesLocales.hijos.get(i));
        }//fin de for
    }
}

class Suma extends Nodo {

    String operador;

    //constructor
    public Suma(Stack<ElementoPila> pila) {

        simbolo = "<Suma>";
        pila.pop();
        nodoDerecho = (pila.pop().getNodo());
        pila.pop();
        operador = pila.pop().getElemento();
        pila.pop();
        nodoIzquierdo = (pila.pop().getNodo());

        System.out.println(simbolo + " " + operador);
        System.out.println("\t" + nodoIzquierdo.simbolo);
        System.out.println("\t" + nodoDerecho.simbolo);

    }//fin del constructor

}//fin de la clase Suma

class Multiplicacion extends Nodo {

    String operador;

    //constructor
    public Multiplicacion(Stack<ElementoPila> pila) {
        simbolo = "<Multiplicacion>";
        pila.pop();
        nodoDerecho = (pila.pop().getNodo());
        pila.pop();
        operador = pila.pop().getElemento();
        pila.pop();
        nodoIzquierdo = (pila.pop().getNodo());

        System.out.println(simbolo + " " + operador);
        System.out.println("\t" + nodoIzquierdo.simbolo);
        System.out.println("\t" + nodoDerecho.simbolo);

    }//fin del constructor
}//fin de la clase Multiplicacion

class DefVar extends Nodo
{

}//fin de la clase DefVar

class ExpresionOperadoresBinarios extends Nodo
{
    public ExpresionOperadoresBinarios(Stack<ElementoPila> pila)
    {
        simbolo = "<Expresion>";

        pila.pop();
        Nodo expresionDerecha = pila.pop().getNodo();
        pila.pop();
        String operador = pila.pop().getElemento();
        pila.pop();
        Nodo expresionIzquierda = pila.pop().getNodo();

        Nodo expresion = new Nodo(operador);
        expresion.hijos.add(expresionIzquierda);
        expresion.hijos.add(expresionDerecha);

        hijos.add(expresion);
    }
}

class OperadorAdicion extends ExpresionOperadoresBinarios
{
    public OperadorAdicion(Stack<ElementoPila> pila){
        super(pila);
    }
}//fin de la clase OperadorAdicion

class OperadorAdicionDos extends Nodo
{
    public OperadorAdicionDos(Stack<ElementoPila> pila){
        simbolo = "<Expresion>";

        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        String operador = pila.pop().getElemento();

        hijos.add(new Nodo(operador));
        hijos.add(expresion);
    }//fin del constructor
}//fin de la clase OperadorAdicionDos

class OperadorMultiplicacion extends ExpresionOperadoresBinarios
{
    public OperadorMultiplicacion(Stack<ElementoPila> pila){
        super(pila);

    }
}//fin de la clase OperadorMultiplicacion

class OperadorRelacional extends ExpresionOperadoresBinarios
{
    public OperadorRelacional(Stack<ElementoPila> pila){
        super(pila);
    }
}//fin de la clase OperadorRelacional

class OperadorOr extends ExpresionOperadoresBinarios
{
    public OperadorOr(Stack<ElementoPila> pila){
        super(pila);
    }
}//fin de la clase OperadorOr

class OperadorAnd extends ExpresionOperadoresBinarios
{
    public OperadorAnd(Stack<ElementoPila> pila){
        super(pila);
    }
}//fin de la clase OperadorAnd

class OperadorIgualdad extends ExpresionOperadoresBinarios
{
    public OperadorIgualdad(Stack<ElementoPila> pila){
        super(pila);
    }
}//fin de la clase

class OperadorNot extends Nodo
{
    public OperadorNot(Stack<ElementoPila> pila){
        simbolo = "<Expresion>";

        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("!"));
        hijos.add(expresion);
    }//fin del constructor
}//fin de la clase OperadorAdicionDos

class Variables extends Nodo
{

    public Variables(Stack<ElementoPila> pila){
        simbolo = "<ListaVar>";

        pila.pop();
        pila.pop();
        pila.pop();
        List<Nodo> listaVariables = pila.pop().getNodo().hijos;
        pila.pop();
        String identificador = pila.pop().getElemento();
        pila.pop();
        String tipo = pila.pop().getElemento();

        Nodo variable = new Nodo("<DefVar>");
        variable.hijos.add(new Nodo(tipo));
        variable.hijos.add(new Nodo(identificador));

        for(int i = 0; i < listaVariables.size(); i++){
            hijos.add(listaVariables.get(i));
        }//fin de for
    }

}//fin de la clase Variables

class VariablesDos extends Nodo
{
    public VariablesDos(Stack<ElementoPila> pila){
        simbolo = "<ListaVar>";

        pila.pop();
        List<Nodo> listaVariables = pila.pop().getNodo().hijos;
        pila.pop();
        String identificador = pila.pop().getElemento();
        pila.pop();
        pila.pop();

        Nodo variable = new Nodo("<DefVar>");
        variable.hijos.add(new Nodo(identificador));

        hijos.add(variable);

        for(int i = listaVariables.size() - 1; i >= 0; i--){
            hijos.add(listaVariables.get(i));
        }//fin de for
    }
}//fin de la clase VariablesDos


class DefFunc extends Nodo
{
    public DefFunc(Stack<ElementoPila> pila){
        simbolo = "<DefFunc>";

        pila.pop();
        Nodo bloqueFuncion = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo parametros = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        String identificador = pila.pop().getElemento();
        pila.pop();
        String tipo = pila.pop().getElemento();

        hijos.add(new Nodo(tipo));
        hijos.add(new Nodo(identificador));
        hijos.add(new Nodo("("));
        hijos.add(parametros);
        hijos.add(new Nodo(")"));
        hijos.add(bloqueFuncion);

    }
}//fin de la clase DefFunc

class Parametros extends Nodo
{
    public Parametros(Stack<ElementoPila> pila){
        simbolo = "<ListaParam>";

        pila.pop();
        List<Nodo> listaParametros = pila.pop().getNodo().hijos;
        pila.pop();
        String identificador = pila.pop().getElemento();
        pila.pop();
        String tipo = pila.pop().getElemento();

        Nodo parametro = new Nodo("<Parametro>");
        parametro.hijos.add(new Nodo(tipo));
        parametro.hijos.add(new Nodo(identificador));

        hijos.add(parametro);
    }
}

class ParametrosDos extends Nodo
{
    public ParametrosDos(Stack<ElementoPila> pila){

        pila.pop();
        List<Nodo> listaVariables = pila.pop().getNodo().hijos;
        pila.pop();
        String identificador = pila.pop().getElemento();
        pila.pop();
        String tipo = pila.pop().getElemento();
        pila.pop();
        pila.pop();

        Nodo parametro = new Nodo("<Parametro>");
        parametro.hijos.add(new Nodo(tipo));
        parametro.hijos.add(new Nodo(identificador));

        hijos.add(parametro);

        for(int i = listaVariables.size() - 1; i >= 0; i--){
            hijos.add(listaVariables.get(i));
        }
    }
}//fin de la clase ParametrosDos

class BloqueFuncion extends Nodo
{
    public BloqueFuncion(Stack<ElementoPila> pila){
        simbolo = "<BloqueFunc>";

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo definicionesLocales = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("{"));
        hijos.add(definicionesLocales);
        hijos.add(new Nodo("}"));
    }//fin del constructor
}//fin de la clase BloqueFuncion

class LlamadaFuncion extends Nodo
{
    public LlamadaFuncion(Stack<ElementoPila> pila)
    {
        simbolo = "<LlamadaFunc>";

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo argumentos = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        String identificador = pila.pop().getElemento();

        hijos.add(new Nodo(identificador));
        hijos.add(new Nodo("("));
        hijos.add(argumentos);
        hijos.add(new Nodo(")"));
    }
}//fin de la clase LlamadaFuncion

class ListaArgumentos extends Nodo
{
    public ListaArgumentos(Stack<ElementoPila> pila)
    {
        simbolo = "<ListaArgumentos>";

        pila.pop();
        Nodo listaArgumentos = pila.pop().getNodo();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();

        hijos.add(expresion);
        for(Nodo nodo : listaArgumentos.hijos){
            hijos.add(nodo);
        }
    }
}

class ListaArgumentosDos extends Nodo
{
    public ListaArgumentosDos(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<ListaArgumentos>";

        pila.pop();
        Nodo listaArgumentos =  pila.pop().getNodo();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();

        hijos.add(expresion);
        for(Nodo nodo : listaArgumentos.hijos){
            hijos.add(nodo);
        }
    }
}//fin de la clase ListaArgumentos

class Sentencias extends Nodo
{
    public Sentencias(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<Sentencias>";

        pila.pop();
        Nodo sentencias =  pila.pop().getNodo();
        pila.pop();
        Nodo sentencia = pila.pop().getNodo();

        hijos.add(sentencia);
        for(Nodo nodo : sentencias.hijos){
            hijos.add(nodo);
        }
    }
}

class SentenciasAsignacion extends Nodo
{
    public SentenciasAsignacion(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<Sentencia>";

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        String identificador = pila.pop().getElemento();

        hijos.add(new Nodo(identificador));
        hijos.add(new Nodo("="));
        hijos.add(expresion);
    }
}

class SentenciaLlamadaFuncion extends Nodo
{
    public SentenciaLlamadaFuncion(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<Sentencia>";

        pila.pop();
        pila.pop();
        pila.pop();
        hijos.add(pila.pop().getNodo());
    }
}//fin de la clase

class SentenciaValorRegresa extends Nodo
{
    public SentenciaValorRegresa(Stack<ElementoPila> pila)
    {
        super();

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo valorRegresa = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("return"));
        hijos.add(valorRegresa);

    }
}

class ExpresionEntreParentesis extends Nodo
{
    public ExpresionEntreParentesis(Stack<ElementoPila> pila)
    {
        super();

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("("));
        hijos.add(expresion);
        hijos.add(new Nodo(")"));
    }
}//fin de la clase ExpresionEntreParentesis

class SentenciaIf extends Nodo
{
    public SentenciaIf(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<Sentencia>";

        pila.pop();
        Nodo otros = pila.pop().getNodo();
        pila.pop();
        Nodo bloque = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("if"));
        hijos.add(new Nodo("("));
        hijos.add(expresion);
        hijos.add(new Nodo(")"));
        hijos.add(bloque);
        hijos.add(otros);
    }
}

class SentenciaWhile extends Nodo
{
    public SentenciaWhile(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<Sentencia>";

        pila.pop();
        Nodo bloque = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("while"));
        hijos.add(new Nodo("("));
        hijos.add(expresion);
        hijos.add(new Nodo(")"));
        hijos.add(bloque);
    }
}//fin de la clase SentenciaWhile

class Bloque extends Nodo
{
    public Bloque(Stack<ElementoPila> pila)
    {
        super();

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo sentencias = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("{"));
        hijos.add(sentencias);
        hijos.add(new Nodo("}"));
    }
}//fin de la clase Bloque

class Otro extends Nodo
{
    public Otro(Stack<ElementoPila> pila)
    {
        super();

        simbolo = "<Otro>";

        pila.pop();
        Nodo bloque = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("else"));
        hijos.add(bloque);

    }
}