package sintactico;

import java.util.LinkedList;
import java.util.List;

public class Nodo
{
    //atributos
    Nodo nodoSiguiente;
    public String simbolo;
    int tamSangria;

    public List<Nodo> hijos = new LinkedList<>();

    //constructor sin parametros
    public Nodo() {

    }//fin del constructor sin parametros

    //constructor parametrizado
    public Nodo(String simbolo) {
        this.simbolo = simbolo;
    }//fin del constrcutor parametrizado

    public void sangria()
    {
        for(int i = 0; i < tamSangria; i++){
            System.out.print(" ");
        }
    }

    public void muestra()
    {
        System.out.println("Metodo muestra");
    }


}//fin de la clase Nodo