package sintactico;

public class ElementoPila {

    //atributos
    protected int id;
    protected String elemento;
    private int tipo;
    private int subTipo;
    private Nodo nodo;

    public int getId() {
        return id;
    }

    public String getElemento() {
        return elemento;
    }

    public int getTipo() {
        return tipo;
    }

    public int getSubTipo() {
        return subTipo;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public boolean esEstado() {
        return false;
    }

    public boolean esTerminal() {
        return false;
    }

    public boolean esNoTerminal() {
        return false;
    }

}//fin de la clase ElementoPila

class Estado extends ElementoPila {

    //constructor
    public Estado(int id){
        super();
        this.id = id;
    }//fin del constructor

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getElemento(){
        return String.valueOf(id);
    }

    @Override
    public boolean esEstado() {
        return true;
    }
}//fin de la clase Estado

class Terminal extends ElementoPila
{
    //constructor
    public Terminal(int id){
        super();
        this.id = id;
    }//fin del constructor

    public Terminal(int id, String elemento){
        super();
        this.id = id;
        this.elemento = elemento;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getElemento() {
        return elemento;
    }

    @Override
    public boolean esTerminal() {
        return true;
    }
}//fin de la clase Terminal

class NoTerminal extends ElementoPila
{
    //constructor
    public NoTerminal(int id, String elemento){
        super();
        this.id = id;
        this.elemento = elemento;
    }//fin del constructor

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getElemento() {
        return elemento;
    }

    @Override
    public boolean esNoTerminal() {
        return true;
    }
}//fin de la clase NoTerminal
