package semantico;

/**
 * Created by Ricardo on 2016-05-02.
 */
public class DefinicionVariable
{
    //atributos
    private String tipo;
    private String identificador;
    private String ambito;

    public DefinicionVariable(String tipo, String identificador, String ambito){
        this.tipo = tipo;
        this.identificador = identificador;
        this.ambito = ambito;
    }//fin del constructor

    public String getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getAmbito() {
        return ambito;
    }
}//fin de la clase DefinicionVaiable