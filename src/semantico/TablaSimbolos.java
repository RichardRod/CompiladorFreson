package semantico;


import java.util.List;

public class TablaSimbolos {

    protected List<ElementoTabla> tabla;
    public List<String> listaErrores;
    public Variable variableLocal;
    public Variable variableGlobal;
    public Funcion funcion;

    //constructor
    public TablaSimbolos(List<String> listaErrores){
        this.listaErrores = listaErrores;
    }//fin del constructor

    public int dispersion(String simbolo)
    {
        int h = 0, g;
        char c;
        char alfa = 4;

        for(int i = 0; i < simbolo.length(); i++){
            c = simbolo.charAt(i);
            h += alfa * c;
            h = h % 221;
        }

        return h % 221;
    }

    public void agrega(ElementoTabla elemento)
    {
        int indice = dispersion(elemento.getSimbolo());

    }







}//fin de la clase TablaSimbolos

class ElementoTabla
{
    //atributos
    private String simbolo;
    private char tipo;

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getTipo() {
        return tipo;
    }

    public boolean esVariable()
    {
        return false;
    }

    public boolean esVariableLocal()
    {
        return false;
    }

    public boolean esFuncion()
    {
        return false;
    }

    public void muestra()
    {

    }
}//fin de la clase ElementoTabla

class Variable extends ElementoTabla
{
    protected boolean local;
    private String ambito;

    public Variable(char tipo, String simbolo, String ambito)
    {
        this.setTipo(tipo);
        this.setSimbolo(simbolo);
        this.ambito = ambito;
        this.local = (this.getAmbito().compareTo("") != 0);

    }//fin del constructor

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getAmbito() {
        return ambito;
    }

    @Override
    public boolean esVariableLocal() {
        return local;
    }

    @Override
    public boolean esVariable() {
        return true;
    }

    @Override
    public void muestra() {
        System.out.println("Variable: " + getSimbolo() + " Tipo: " + getTipo());

        if(local)
            System.out.println("Local");
        else
            System.out.println("Global");
    }
}//fin de la clase Variable

class Funcion extends ElementoTabla
{
    private String parametros;

    public Funcion(char tipo, String simbolo, String parametros)
    {
        this.setSimbolo(simbolo);
        this.setTipo(tipo);
        this.parametros = parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getParametros() {
        return parametros;
    }

    public boolean esFuncion()
    {
        return true;
    }

    @Override
    public void muestra() {
        System.out.println("Funcion: " + getSimbolo() + " Tipo: " + getTipo() + " Parametros: " + parametros);
    }
}//fin de la clase Funcion

class DefVar
{

}

class DefFunc
{

}

class Parametro
{

}
