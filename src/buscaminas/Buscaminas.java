/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 * Esta es la clase principal
 *
 * @author Easyklk
 * @version 1.0
 * @since 1.0
 */
public class Buscaminas extends JFrame {

    public Buscaminas() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Buscaminas bm = new Buscaminas();
            bm.initComponents();
            bm.setVisible(true);
        });
    }

    /**
     * Este metodo iniciala los componentes del JFrame.
     *
     * @since 1.0
     */
    public void initComponents() {
        setTitle("Buscaminas Isaac Romanillos Deza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Juego juego = new Juego();
        juego.initComponentsArriba();
        setSize(700, 700);
        setLocationRelativeTo(null);
        add(juego);
        setResizable(false);
        setVisible(true);
    }
}

/**
 * Esta clase es el panel donde se muestra el juego.
 *
 * @author Easyklk
 * @version 1.0
 * @since 1.0
 */
class Juego extends JPanel {

    /**
     * Array bidimensional de cajas.
     */
    protected Casilla[][] cuadricula;

    /**
     * Opcion de dificultad.
     */
    protected JComboBox jcDificultad;

    /**
     * Panel donde muestro el {@link Juego#cuadricula Array}.
     *
     * @see buscaminas.Juego#cuadricula Array
     */
    protected JPanel mid = new JPanel();

    /**
     * Panel donde muestro la seleccion de dificultad.
     *
     * @see buscaminas.Juego#cuadricula Array
     */
    protected JPanel arriba = new JPanel();

    /**
     * Imagenes de recursos.
     *
     * @since 1.0
     */
    protected ImageIcon bandera = new ImageIcon("bandera.png");
    protected ImageIcon mina = new ImageIcon("mina.png");
    protected ImageIcon uno = new ImageIcon("1.png");
    protected ImageIcon dos = new ImageIcon("2.png");
    protected ImageIcon tres = new ImageIcon("3.png");
    protected ImageIcon cuatro = new ImageIcon("4.png");
    protected ImageIcon cinco = new ImageIcon("5.png");
    protected ImageIcon seis = new ImageIcon("6.png");
    protected ImageIcon siete = new ImageIcon("7.png");
    protected ImageIcon ocho = new ImageIcon("8.png");
    protected ImageIcon retryIcon = new ImageIcon("retry_icon.png");
    /**
     * Contador de clicks para la comprobacion de ganador.
     */
    protected int pulsar = 0;

    /**
     * Construye el juego.
     *
     * @since 1.0
     */
    public Juego() {
    }

    /**
     * Este metodo inicializa los comoponentes del Juego.
     *
     * @since 1.0
     */
    public void initComponentsArriba() {
        arriba.setLayout(new FlowLayout());
        setLayout(new BorderLayout());

        JLabel jlTitulo = new JLabel("Bienvenido al Buscaminas");
        jlTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        arriba.add(jlTitulo);

        String opcionesDificultad[] = {"Principiante", "Intermedio", "Experto"};
        jcDificultad = new JComboBox(opcionesDificultad);
        jcDificultad.setFont(new Font("Arial", Font.PLAIN, 20));
        jcDificultad.setPreferredSize(new Dimension(150, 30));
        arriba.add(jcDificultad);

        JButton jbComenzar = new JButton("Comenzar");
        jbComenzar.setFont(new Font("Arial", Font.BOLD, 20));
        arriba.add(jbComenzar);
        jbComenzar.addActionListener((ActionEvent e) -> {
            crearBotones();
        });

        Abajo abajo = new Abajo();
        abajo.initComponents();

        setLayout(new BorderLayout());
        add(arriba, BorderLayout.NORTH);
        add(mid, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);
    }

    /**
     * Esta subclase controla el comportamieto de los clicks izquierdo y
     * derechos.
     *
     * @since 1.0
     */
    class ClicksEventos extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Casilla c = (Casilla) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (!c.isClickDrc() && !c.isMina()) {
                    descubrirCajas(c);
                } else if (!c.isClickDrc() && c.isMina()) {
                    morir(c);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (!c.isClickIzq() && c.isClickDrc()) {
                    c.setClickDrc(false);
                    c.setIcon(null);
                } else if (!c.isClickIzq() && !c.isClickDrc()) {
                    c.setClickDrc(true);
                    c.setIcon(bandera);
                }
            }
        }
    }

    /**
     * Este metodo me crea el tablero segun la dificultada seleccionada en el
     * jcombobox y le da la probabilidad de mina a cada casilla de este.
     *
     * @since 1.0
     */
    public void crearBotones() {
        mid.removeAll();
        int dificultadNumero = jcDificultad.getSelectedIndex();
        System.out.println("Dificultad " + dificultadNumero);
        double probabilidadMina = 0;
        switch (dificultadNumero) {
            case 0:
                cuadricula = new Casilla[10][10];
                mid.setLayout(new GridLayout(10, 10));
                probabilidadMina = 0.10;
                break;
            case 1:
                cuadricula = new Casilla[15][15];
                mid.setLayout(new GridLayout(15, 15));
                probabilidadMina = 0.15;
                break;
            case 2:
                cuadricula = new Casilla[20][20];
                mid.setLayout(new GridLayout(20, 20));
                probabilidadMina = 0.20;
                break;
        }

        for (int i = 0; i < cuadricula.length; i++) {
            for (int j = 0; j < cuadricula.length; j++) {
                double probabilidad = Math.random();
                Casilla c = new Casilla(i, j, false, false, false);
                cuadricula[i][j] = c;
                colocarMina(probabilidad, probabilidadMina, i, j);
                mid.add(c);
                cuadricula[i][j].addMouseListener(new ClicksEventos());
            }
        }
        mid.updateUI();
    }

    /**
     * Este metodo coloca una mina segun la probabilidad del
     * {@link Juego#jcDificultad JComboBox}.
     *
     * @param probabilidad Probabilidad para comprobar si se pone mina o no
     * @param probabilidadMina Probabildiad de que la
     * {@link buscaminas.Casilla casilla} sea mina
     * @param x Posicion X del {@link Juego#cuadricula Array}
     * @param y Posicion Y del {@link Juego#cuadricula Array}
     *
     * @since 1.0
     */
    public void colocarMina(double probabilidad, double probabilidadMina, int x, int y) {
        if (probabilidad < probabilidadMina) {
            cuadricula[x][y].setMina(true);
            cuadricula[x][y].setIcon(mina);
        }
    }

    /**
     * Este metodo me calcula las minas totales del tablero.
     *
     * @return Numero minas total del tablero
     * @since 1.0
     */
    public int calcularMinasTotales() {
        int minas = 0;
        for (Casilla[] cuadricula1 : cuadricula) {
            for (int j = 0; j < cuadricula.length; j++) {
                if (cuadricula1[j].isMina()) {
                    minas++;
                }
            }
        }
        return minas;
    }

    /**
     * Este metodo cuenta las minas adyacentes a la casilla que yo pulse.
     *
     * @param c Casilla pulsada
     * @return El numero de minas adyacentes
     * @since 1.0
     */
    public int contarMinasAdyacentes(Casilla c) {
        int x = c.getxH();
        int y = c.getyV();
        int nMinas = 0;
        for (int i = Math.max(0, x - 1); i <= Math.min(cuadricula.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(cuadricula.length - 1, y + 1); j++) {
                if (cuadricula[i][j].isMina()) {
                    nMinas++;
                }
            }
        }
        return nMinas;
    }

    /**
     * Este metodo coloca los numeros segun las minas adyacentes o desvela la
     * casilla si no tiene ni minas ni numeros.
     *
     * @param c Casilla pulsada
     * @see buscaminas.Casilla
     * @throws ArrayIndexOutOfBoundsException es un ejemplo no da excepciones xd
     * @since 1.0
     */
    public void descubrirCajas(Casilla c) {
        int nMinas = contarMinasAdyacentes(c);
        int x = c.getxH();
        int y = c.getyV();
        if (nMinas > 0) {
            ImageIcon a = new ImageIcon(String.valueOf(nMinas) + ".png");
            cuadricula[x][y].setDisabledIcon(a);
            cuadricula[x][y].setIcon(a);
            cuadricula[x][y].setEnabled(false);
            c.setClickIzq(true);
            pulsar++;
            comprobarGanar();
        } else if (nMinas == 0) {
            cuadricula[x][y].setEnabled(false);
            c.setClickIzq(true);
            for (int i = Math.max(0, x - 1); i <= Math.min(cuadricula.length - 1, x + 1); i++) {
                for (int j = Math.max(0, y - 1); j <= Math.min(cuadricula.length - 1, y + 1); j++) {
                    if (cuadricula[i][j].isEnabled() && !cuadricula[i][j].isClickDrc()) {
                        descubrirCajas(cuadricula[i][j]);
                    }
                }
            }
            pulsar++;
            comprobarGanar();
        }
    }

    /**
     * Este metodo se ejecuta al pulsar una mina, desvela el tablero entero y te
     * muestra un mensaje como el perdedor que eres.
     *
     * @param c Casilla pulsada
     * @see buscaminas.Casilla
     * @since 1.0
     */
    public void morir(Casilla c) {
        for (Casilla[] cuadricula1 : cuadricula) {
            for (int j = 0; j < cuadricula.length; j++) {
                c.setBackground(Color.red);
                c.setIcon(mina);
                if (cuadricula1[j].isMina()) {
                    cuadricula1[j].setIcon(mina);
                    cuadricula1[j].setBackground(Color.red);
                    pulsar = 0;
                }
                cuadricula1[j].setEnabled(false);
            }
        }
        mensaje("¡¡PERDISTE NOOB :P!!");
    }

    /**
     * Este metodo comprueba si ganas la partida calculando el tamaño de tablero
     * y comprobandolo con las minas que este tenga.
     *
     * @since 1.0
     */
    public void comprobarGanar() {
        int tamanioCuadricula = cuadricula.length * cuadricula.length;
        int numCajas = (tamanioCuadricula - calcularMinasTotales());
        System.out.println("numCajas =" + numCajas);
        System.out.println("pulsar=" + pulsar);
        if (numCajas == pulsar) {
            pulsar = 0;
            mensaje("¡¡☺☺☺ FELICIDADES GANASTE ☺☺☺!!");
        }
    }

    /**
     * Este metodo es para mostrar el mensaje si ganas o pierdes.
     *
     * @param texto texto a mostrar si ganas o pierdes
     * @deprecated use {@link Juego#mensaje} instead.
     * @since 1.0
     */
    public void mensaje(String texto) {
        int confirmado = JOptionPane.showConfirmDialog(mid, "¿Volver a jugar?", texto, JOptionPane.YES_NO_OPTION, 1, retryIcon);
        if (JOptionPane.OK_OPTION == confirmado) {
            mid.removeAll();
            mid.repaint();
        } else {
            System.exit(0);
        }
    }
}

/**
 * Esta clase contiene el nombre de autor.
 *
 * @version 1.0
 * @since 1.0
 */
class Abajo extends JPanel {

    /**
     * Construye la parte de abajo.
     *
     * @since 1.0
     */
    public Abajo() {
    }

    /**
     * Este metodo inicializa los comoponentes de la parte de abajo.
     *
     * @since 1.0
     */
    public void initComponents() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.BLACK);
        JLabel jlNombre = new JLabel("Autor: Isaac Romanillos Deza");
        jlNombre.setFont(new Font("Arial", Font.ITALIC, 12));
        jlNombre.setForeground(Color.WHITE);
        add(jlNombre);
    }
}
