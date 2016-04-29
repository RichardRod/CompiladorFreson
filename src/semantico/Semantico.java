package semantico;


import sintactico.*;

import java.util.List;


public class Semantico {

    //atributos
    private List<String> listaErrores;
    Nodo arbol;

    public void muestraErrores()
    {
        if(existenErrores())
        {
            for (String error : listaErrores){
                System.out.println(error);
            }
        }
    }

    public boolean existenErrores()
    {
        return listaErrores.size() > 0;
    }




}//fin de la clase Semantico
