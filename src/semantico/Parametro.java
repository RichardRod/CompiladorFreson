package semantico;


public class Parametro {


    //atributos
    private String tipo;
    private String identificador;
    private String ambito;

    //constructor
    public Parametro(String tipo, String identificador) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.ambito = "Parametro";
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

}
