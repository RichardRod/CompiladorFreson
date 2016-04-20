package sintactico;

import principal.Ventana;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Arbol
{
    private Nodo raiz;

    public void insertar(Nodo elementoNuevo)
    {
        //System.out.println("INSERCION");
        List<Nodo> aux = new LinkedList<>();
        List<Nodo> hijos1 = new LinkedList<>();
        List<Nodo> hijos2 = new LinkedList<>();
        System.out.println(elementoNuevo.simbolo);

        for(int i = 0; i < elementoNuevo.hijos.size(); i++){
            System.out.println(elementoNuevo.hijos.get(i).simbolo);
            aux = elementoNuevo.hijos.get(i).hijos;

            if(elementoNuevo.hijos.get(i).hijos.size() > 0)
            {
                hijos1 = aux;
            }
        }

        System.out.println("Hijos: " + aux.size());

        for(int i = 0; i < aux.size(); i++){
            System.out.println(aux.get(i).simbolo);
        }

        System.out.println("------------");


        elementoNuevo.nodoSiguiente = raiz;
        raiz = elementoNuevo;
    }

    public void imprimir(Nodo recorrido)
    {
        if(recorrido != null)
        {
            imprimir(recorrido.nodoSiguiente);
            System.out.println(recorrido.simbolo + " - ");
        }
    }

    public void imprimir()
    {
        imprimir(raiz);
    }
}//fin de la clase Arbol



class Programa extends Nodo {
    public Programa(Stack<ElementoPila> pila) {
        super();
        simbolo = "<Programa>";
        pila.pop();
        hijos.add(pila.pop().getNodo());
    }//fin del constructor
}//fin de la clase Programa

class Identificador extends Nodo {
    //constructor
    public Identificador(Stack<ElementoPila> pila) {
        super();
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Identificador> " + simbolo;

        //muestra();
    }//fin del constructor

    @Override
    public void muestra() {
        sangria();
        System.out.println("<Chuchita> " + simbolo);
    }
}//fin de la clase Identificador

class Entero extends Nodo {
    //constructor
    public Entero(Stack<ElementoPila> pila) {
        super();
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Entero> " + simbolo;
    }//fin del constrcutor

}//fin de la clase Entero

class Real extends Nodo {
    //constructor
    public Real(Stack<ElementoPila> pila) {
        super();
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Real> " + simbolo;
    }//fin del constrcutor
}//fin de la clase Real

class Cadena extends Nodo {
    //constructor
    public Cadena(Stack<ElementoPila> pila) {
        super();
        pila.pop();
        simbolo = ((Terminal) pila.pop()).getElemento();
        simbolo = "<Cadena> " + simbolo;
    }//fin del constrcutor

}//fin de la clase Cadena

class Definiciones extends Nodo {
    //constructor
    public Definiciones(Stack<ElementoPila> pila) {

        super();

        simbolo = "<Definiciones>";

        pila.pop();
        Nodo definiciones = pila.pop().getNodo();
        pila.pop();
        Nodo definicion = pila.pop().getNodo();

        hijos.add(definicion);
        for (int i = 0; i < definiciones.hijos.size(); i++) {
            hijos.add(definiciones.hijos.get(i));
        }//fin de for
    }//fin del constructor
}//fin de la clase Definiciones

class DefinicionesLocales extends Nodo {
    //constructor
    public DefinicionesLocales(Stack<ElementoPila> pila) {
        super();

        simbolo = "<DefLocales>";

        pila.pop();
        Nodo definicionesLocales = pila.pop().getNodo();
        pila.pop();
        Nodo definicionLocal = pila.pop().getNodo();

        hijos.add(definicionLocal);
        for (int i = 0; i < definicionesLocales.hijos.size(); i++) {
            hijos.add(definicionesLocales.hijos.get(i));
        }//fin de for
    }//fin del constructor
}//fin de la clase DefinicionesLocales

class ExpresionOperadoresBinarios extends Nodo {

    Nodo expresionDerecha;
    String operador;
    Nodo expresionIzquierda;


    public ExpresionOperadoresBinarios(Stack<ElementoPila> pila) {
        super();
        simbolo = "<Expresion>";

        pila.pop();
        expresionDerecha = pila.pop().getNodo();
        pila.pop();
        operador = pila.pop().getElemento();
        pila.pop();
        expresionIzquierda = pila.pop().getNodo();

        Nodo expresion = new Nodo(operador);
        expresion.hijos.add(expresionIzquierda);
        expresion.hijos.add(expresionDerecha);

        hijos.add(expresion);

        muestra();



    }

    @Override
    public void muestra() {
        //super.muestra();

        sangria();

        System.out.println("<Expresion>");
        if(expresionDerecha != null)
        {
            System.out.println("Entro");
            expresionDerecha.tamSangria += 1;
            expresionDerecha.muestra();
        }

        sangria();

        System.out.println("<OpSuma> " + simbolo);
        sangria();

        if(expresionIzquierda != null)
        {
            expresionIzquierda.tamSangria += 1;
            expresionIzquierda.muestra();
        }


    }
}//fin de la clase ExpresionOperadoresBinarios

class OperadorAdicion extends ExpresionOperadoresBinarios {
    public OperadorAdicion(Stack<ElementoPila> pila) {
        super(pila);
    }


}//fin de la clase OperadorAdicion

class OperadorAdicionDos extends Nodo {
    public OperadorAdicionDos(Stack<ElementoPila> pila) {
        super();
        simbolo = "<Expresion>";

        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        String operador = pila.pop().getElemento();

        hijos.add(new Nodo(operador));
        hijos.add(expresion);
    }//fin del constructor
}//fin de la clase OperadorAdicionDos

class OperadorMultiplicacion extends ExpresionOperadoresBinarios {
    public OperadorMultiplicacion(Stack<ElementoPila> pila) {
        super(pila);

    }
}//fin de la clase OperadorMultiplicacion

class OperadorRelacional extends ExpresionOperadoresBinarios {

    public OperadorRelacional(Stack<ElementoPila> pila) {
        super(pila);


    }


}//fin de la clase OperadorRelacional

class OperadorOr extends ExpresionOperadoresBinarios {
    public OperadorOr(Stack<ElementoPila> pila) {
        super(pila);
    }
}//fin de la clase OperadorOr

class OperadorAnd extends ExpresionOperadoresBinarios {
    public OperadorAnd(Stack<ElementoPila> pila) {
        super(pila);
    }
}//fin de la clase OperadorAnd

class OperadorIgualdad extends ExpresionOperadoresBinarios {
    public OperadorIgualdad(Stack<ElementoPila> pila) {
        super(pila);
    }
}//fin de la clase

class OperadorNot extends Nodo {
    public OperadorNot(Stack<ElementoPila> pila) {
        super();
        simbolo = "<Expresion>";

        pila.pop();
        Nodo expresion = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("!"));
        hijos.add(expresion);
    }//fin del constructor
}//fin de la clase OperadorAdicionDos

class Variables extends Nodo {
    //constructor
    public Variables(Stack<ElementoPila> pila) {

        super();
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



        Ventana.txtArbol.append(variable.simbolo + "\n");
        Ventana.txtArbol.append("\t<Tipo> " + tipo + "\n");
        Ventana.txtArbol.append("\t<Identificador> " + identificador + "\n");

        TablaSimbolos.agregarElemento(tipo, identificador);



        System.out.println(variable.simbolo);
        System.out.println("\t<Tipo> " + tipo);
        System.out.println("\t<Identificador> " + identificador);

        for (int i = 0; i < listaVariables.size(); i++) {
            hijos.add(listaVariables.get(i));
            Ventana.txtArbol.append("\t<Identificador> " + listaVariables.get(i).hijos.get(i).simbolo + "\n");
            System.out.println("\t<Identificador> " + listaVariables.get(i).hijos.get(i).simbolo);
            TablaSimbolos.agregarElemento(tipo, listaVariables.get(i).hijos.get(i).simbolo);
        }//fin de for


    }//fin del constructor

}//fin de la clase Variables

class VariablesDos extends Nodo {
    //constructor
    public VariablesDos(Stack<ElementoPila> pila) {

        super();
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

        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            hijos.add(listaVariables.get(i));
        }//fin de for
    }//fin del constructor
}//fin de la clase VariablesDos


class DefFunc extends Nodo {
    //constructor
    public DefFunc(Stack<ElementoPila> pila) {

        super();
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

        Ventana.txtArbol.append(simbolo + "\n");
        Ventana.txtArbol.append("\t<Tipo> " + tipo + "\n");
        Ventana.txtArbol.append("\t<Identificador> " + identificador + "\n");

        System.out.println(simbolo);
        System.out.println("\t<Tipo> " + tipo);
        System.out.println("\t<Identificador> " + identificador);
        System.out.println();

    }//fin del constructor
}//fin de la clase DefFunc

class Parametros extends Nodo {
    //constructor
    public Parametros(Stack<ElementoPila> pila) {
        super();

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
    }//fin del constructor
}//fin de la clase Parametros

class ParametrosDos extends Nodo {
    //constructor
    public ParametrosDos(Stack<ElementoPila> pila) {

        super();

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

        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            hijos.add(listaVariables.get(i));
        }//fin de for
    }//fin del constructor
}//fin de la clase ParametrosDos

class BloqueFuncion extends Nodo {
    //constructor
    public BloqueFuncion(Stack<ElementoPila> pila) {

        super();
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

class LlamadaFuncion extends Nodo {
    //constructor
    public LlamadaFuncion(Stack<ElementoPila> pila) {
        super();

        simbolo = "<LlamadaFunc>";

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo argumentos = pila.pop().getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        String identificador = pila.pop().getElemento();

        Ventana.txtArbol.append("<LlamadaFuncion>\n");
        Ventana.txtArbol.append("\t<Identificador> " + identificador + "\n");
        Ventana.txtArbol.append("\t"+argumentos.hijos.get(0).hijos.get(0).simbolo + "\n");

        System.out.println("<LlamadaFuncion>");
        System.out.println("\t<Identificador> " + identificador);
        System.out.println("\t"+argumentos.hijos.get(0).hijos.get(0).simbolo);



        hijos.add(new Nodo(identificador));
        hijos.add(new Nodo("("));
        hijos.add(argumentos);
        hijos.add(new Nodo(")"));
    }//fin del constructor
}//fin de la clase LlamadaFuncion

class ListaArgumentos extends Nodo {
    //constructor
    public ListaArgumentos(Stack<ElementoPila> pila) {
        super();

        simbolo = "<ListaArgumentos>";

        pila.pop();
        Nodo listaArgumentos = pila.pop().getNodo();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();

        hijos.add(expresion);
        for (Nodo nodo : listaArgumentos.hijos) {
            hijos.add(nodo);
            System.out.println(nodo.simbolo);
        }//fin de for
        //System.out.println("Hijos: " + hijos.get(0).simbolo);
    }//fin del constructor
}//fin de la clase ListaArgumentos

class ListaArgumentosDos extends Nodo {
    //constructor
    public ListaArgumentosDos(Stack<ElementoPila> pila) {
        super();

        simbolo = "<ListaArgumentos>";

        pila.pop();
        Nodo listaArgumentos = pila.pop().getNodo();
        pila.pop();
        Nodo expresion = pila.pop().getNodo();

        hijos.add(expresion);
        for (Nodo nodo : listaArgumentos.hijos) {
            hijos.add(nodo);
        }//fin de for
    }//fin del constructor
}//fin de la clase ListaArgumentos

class Sentencias extends Nodo {
    public Sentencias(Stack<ElementoPila> pila) {
        super();

        simbolo = "<Sentencias>";

        pila.pop();
        Nodo sentencias = pila.pop().getNodo();
        pila.pop();
        Nodo sentencia = pila.pop().getNodo();

        hijos.add(sentencia);
        for (Nodo nodo : sentencias.hijos) {
            hijos.add(nodo);
        }//fin de for
    }//fin del constructor
}//fin de la clase Sentencias

class SentenciasAsignacion extends Nodo {
    //constructor
    public SentenciasAsignacion(Stack<ElementoPila> pila) {
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

        Ventana.txtArbol.append("<Asignacion>\n");
        Ventana.txtArbol.append("\t<Identificador> " + identificador + "\n");
        Ventana.txtArbol.append("\t" + expresion.hijos.get(0).simbolo + "\n");

        System.out.println("<Asignacion>");
        System.out.println("\t<Identificador> " + identificador);
        System.out.println("\t" + expresion.hijos.get(0).simbolo);
    }//fin del constructor
}//fin de la clase SentenciasAsignacion

class SentenciaLlamadaFuncion extends Nodo {
    //constructor
    public SentenciaLlamadaFuncion(Stack<ElementoPila> pila) {
        super();

        simbolo = "<Sentencia>";

        pila.pop();
        pila.pop();
        pila.pop();
        hijos.add(pila.pop().getNodo());
    }//fin del constructor
}//fin de la clase SentenciaLlamadaFuncion

class SentenciaValorRegresa extends Nodo {
    public SentenciaValorRegresa(Stack<ElementoPila> pila) {
        super();

        pila.pop();
        pila.pop();
        pila.pop();
        Nodo valorRegresa = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("return"));
        hijos.add(valorRegresa);

    }//fin del constructor
}//fin de la clase SentenciaValorRegresa

class ExpresionEntreParentesis extends Nodo {
    //constructor
    public ExpresionEntreParentesis(Stack<ElementoPila> pila) {
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
    }//fin del constructor
}//fin de la clase ExpresionEntreParentesis

class SentenciaIf extends Nodo {
    //constructor
    public SentenciaIf(Stack<ElementoPila> pila) {
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

        System.out.println("<if>");
        Ventana.txtArbol.append("<if>\n");
        for(int i = 0; i < hijos.size(); i++){
            //System.out.println("\t" + hijos.get(i).simbolo);
            if(i == 2)
            {
                System.out.println("\t" + hijos.get(2).hijos.get(0).simbolo);
                Ventana.txtArbol.append("\t" + hijos.get(2).hijos.get(0).simbolo + "\n");
            }
            /*if(i == 5)
            {
                System.out.println("\t" + hijos.get(5).hijos.get(0).simbolo);
                System.out.println("\t" + hijos.get(5).hijos.get(1).hijos.size());

            }*/
        }
    }//fin del constructor
}//fin de la clase SentenciaIf

class SentenciaWhile extends Nodo {
    //constructor
    public SentenciaWhile(Stack<ElementoPila> pila) {
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

        Ventana.txtArbol.append("<while>\n");


        System.out.println("<while>");
        for(int i = 0; i < hijos.size(); i++) {
            //System.out.println("\t" + hijos.get(i).simbolo);
            if (i == 2) {
                Ventana.txtArbol.append("\t" + hijos.get(2).hijos.get(0).simbolo + "\n");
                System.out.println("\t" + hijos.get(2).hijos.get(0).simbolo);
            }

        }
    }//fin del constructor
}//fin de la clase SentenciaWhile

class Bloque extends Nodo {
    //constructor
    public Bloque(Stack<ElementoPila> pila) {
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
    }//fin del constructor
}//fin de la clase Bloque

class Otro extends Nodo {
    //constructor
    public Otro(Stack<ElementoPila> pila) {
        super();

        simbolo = "<Otro>";

        pila.pop();
        Nodo bloque = pila.pop().getNodo();
        pila.pop();
        pila.pop();

        hijos.add(new Nodo("else"));
        hijos.add(bloque);

    }//fin del constructor
}//fin de la clase Otro

class TablaSimbolos
{
    public static void agregarElemento(String tipo, String identificador)
    {
        DefaultTableModel modeloTabla = (DefaultTableModel) Ventana.tablaSimbolos.getModel();
        Object[] fila = new Object[3];

        fila[0] = tipo;
        fila[1] = identificador;
        fila[2] = "Global";

        modeloTabla.addRow(fila);
        Ventana.tablaSimbolos.setModel(modeloTabla);
    }
}