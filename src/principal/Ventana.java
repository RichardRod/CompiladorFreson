package principal;

import javafx.scene.control.Tab;
import semantico.TablaSimbolos;
import sintactico.Sintactico;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ventana extends JFrame implements ActionListener {

    //atributos
    private Font fuente;
    private JPanel panelContenedor;

    private JMenuBar barraMenu;
    private JButton botonCompilar;
    private JButton botonAbrir;

    private JTextArea txtFuente;
    public static JTextArea txtArbol;
    private JTable tablaResultados;
    public static JTable tablaSimbolos;
    public static JTable tablaErrores;
    private JScrollPane desplazamientoTabla;
    private JScrollPane desplazamientoTexto;
    private JScrollPane desplazamientoArbol;
    private JScrollPane desplazamientoSimbolos;
    private JScrollPane desplazamientoErrores;

    private JTabbedPane panelFichas;
    private JPanel panelPila;
    private JPanel panelArbol;
    private JPanel panelSemantico;

    public Ventana() {
        super("Compilador");

        fuente = new Font("Comic Sans Ms", Font.CENTER_BASELINE, 16);

        panelContenedor = new JPanel();
        setLayout(null);

        barraMenu = new JMenuBar();

        botonAbrir = new JButton();
        botonAbrir.setPreferredSize(new Dimension(30, 40));
        botonAbrir.setIcon(new ImageIcon("imagenes/openIcon.png"));
        botonAbrir.addActionListener(this);
        barraMenu.add(botonAbrir);

        botonCompilar = new JButton();
        botonCompilar.setPreferredSize(new Dimension(30, 40));
        botonCompilar.setIcon(new ImageIcon("imagenes/buildIcon.png"));
        botonCompilar.addActionListener(this);
        barraMenu.add(botonCompilar);

        setJMenuBar(barraMenu);


        txtFuente = new JTextArea();
        txtFuente.setFont(fuente);
        txtFuente.setBounds(10, 10, 1260, 400);
        panelContenedor.add(txtFuente);

        desplazamientoTexto = new JScrollPane(txtFuente);
        desplazamientoTexto.setBounds(10, 10, 1260, 400);
        add(desplazamientoTexto);

        panelFichas = new JTabbedPane();


        panelPila = new JPanel();
        panelPila.setLayout(new BorderLayout());
        tablaResultados = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{
                        "Elementos en pila", "Entrada", "Accion",}));
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(700);
        panelPila.add(BorderLayout.CENTER, tablaResultados);
        desplazamientoTabla = new JScrollPane(tablaResultados);
        panelPila.add(BorderLayout.CENTER, desplazamientoTabla);
        panelFichas.addTab("Pila", panelPila);

        panelArbol = new JPanel();
        panelArbol.setLayout(new BorderLayout());
        txtArbol = new JTextArea();
        panelArbol.add(BorderLayout.CENTER, txtArbol);
        desplazamientoArbol = new JScrollPane(txtArbol);
        panelArbol.add(BorderLayout.CENTER, desplazamientoArbol);
        panelFichas.addTab("Arbol", panelArbol);

        panelSemantico = new JPanel();
        panelSemantico.setLayout(new BorderLayout());
        tablaSimbolos = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Tipo", "Identificador", "Ambito"}));
        panelSemantico.add(BorderLayout.EAST, tablaSimbolos);
        desplazamientoSimbolos = new JScrollPane(tablaSimbolos);
        panelSemantico.add(BorderLayout.EAST, desplazamientoSimbolos);
        tablaErrores = new JTable(new DefaultTableModel(new Object[][]{},
                new String[]{"Linea", "Error"}));
        panelSemantico.add(BorderLayout.CENTER, tablaErrores);
        desplazamientoErrores = new JScrollPane(tablaErrores);
        panelSemantico.add(BorderLayout.CENTER, desplazamientoErrores);
        panelFichas.addTab("Semantico", panelSemantico);

        panelFichas.setBounds(0, 420, 1280, 250);
        add(panelFichas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);

    }//fin del constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCompilar) {
            limpiar();
            analizar();
        }
        else if(e.getSource() == botonAbrir)
        {
            abrir();
        }
    }//fin del metodo actionPerformed

    public void analizar() {

        DefaultTableModel modeloTabla = (DefaultTableModel)tablaResultados.getModel();
        Object[] filas = new Object[2];

        String fuente = txtFuente.getText();

        /*Lexico lexico = new Lexico();
        lexico.entrada(fuente);
        while (lexico.simbolo.compareTo("$") != 0)
        {
            lexico.siguienteSimbolo();
                filas[0] = lexico.simbolo;
                filas[1] = lexico.simboloCadena(lexico.tipo);
            modeloTabla.addRow(filas);
            tablaResultados.setModel(modeloTabla);
        }//fin de while
        */

        analisisSintactico(fuente, tablaResultados);

    }//fin del metodo analizar


    private void limpiar()
    {
        DefaultTableModel modeloTabla = (DefaultTableModel)tablaResultados.getModel();
        DefaultTableModel modeloTablaDos = (DefaultTableModel) tablaSimbolos.getModel();

        while(modeloTabla.getRowCount() > 0)
        {
            modeloTabla.removeRow(0);
        }

        while(modeloTablaDos.getRowCount() > 0)
        {
            modeloTablaDos.removeRow(0);
        }

        TablaSimbolos.limpiar();
    }

    private void abrir()
    {
        try
        {
            JFileChooser seleccionarArchivo = new JFileChooser();
            seleccionarArchivo.showOpenDialog(null);

            FileReader fr = new FileReader(seleccionarArchivo.getSelectedFile().getPath());
            BufferedReader br = new BufferedReader(fr);
            String linea = "";

            while((linea = br.readLine()) != null)
            {
                txtFuente.append(linea + "\n");
            }

            fr.close();
        }
        catch(Exception e) {}
    }

    private void analisisSintactico(String entrada, JTable tablaResultados)
    {
        Sintactico sintactico = new Sintactico(tablaResultados);
        sintactico.setEntrada(entrada);
        //sintactico.Gramatica_3();
        sintactico.analisisSintactico();
    }


}//fin de la clase Ventana