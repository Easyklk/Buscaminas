/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.JButton;

/**
 * Esta clase representa cada casilla del
 * {@link buscaminas.Buscaminas Buscaminas}
 *
 * @author Easyklk
 * @version 1.0
 * @since 1.0
 */
public class Casilla extends JButton {

    /**
     * Posicon X de la casilla dentro de la
     * {@link buscaminas.Juego
     * #cuadricula cuadricula}
     *
     * @since 1.0
     */
    private int xH;
    /**
     * Posicon Y de la casilla dentro del
     * {@link buscaminas.Juego
     * #cuadricula cuadricula}
     *
     * @since 1.0
     */
    private int yV;

    /**
     * Indica si la casilla ha sido pulsada con click izquierdo.
     *
     * @since 1.0
     */
    private boolean clickIzq;
    /**
     * Indica si la casilla ha sido pulsada con click derecho.
     *
     * @since 1.0
     */
    private boolean clickDrc;
    /**
     * Indica si la casilla es una mina.
     *
     * @since 1.0
     */
    private boolean mina;

    /**
     * Construye una casilla con los valores
     *
     * @since 1.0
     */
    public Casilla() {
        xH = 0;
        yV = 0;
        clickIzq = false;
        clickDrc = false;
        mina = false;
    }

    /**
     * Construye una casilla con los parametros indicados
     *
     * @param xH posicion X
     * @param yV posicion Y
     * @param clickIzq Click Izquierdo pulsado
     * @param clickDrc Click Derecho pulsado
     * @param mina Tiene mina
     * @since 1.0
     */
    public Casilla(int xH, int yV, boolean clickIzq, boolean clickDrc, boolean mina) {
        this.xH = xH;
        this.yV = yV;
        this.clickIzq = clickIzq;
        this.clickDrc = clickDrc;
        this.mina = mina;
    }

    /**
     * Devuelve la posicion del eje X de la casilla
     *
     * @return posicion del eje X sobre
     * {@link buscaminas.Juego
     * #cuadricula cuadricula}
     * @see buscaminas.Casilla#xH
     * @since 1.0
     */
    public int getxH() {
        return xH;
    }

    /**
     * Establece la posicion del eje X de la casilla en la
     * {@link buscaminas.Juego
     * #cuadricula cuadricula}
     *
     * @param xH Posicion del eje X de la casilla
     * @see buscaminas.Casilla#xH
     * @since 1.0
     *
     */
    public void setxH(int xH) {
        this.xH = xH;
    }

    /**
     * Devuelve la posicion del eje Y de la casilla
     *
     * @return posicion del eje Y sobre
     * {@link buscaminas.Juego
     * #cuadricula cuadricula}
     * @see buscaminas.Casilla#yV
     * @since 1.0
     */
    public int getyV() {
        return yV;
    }

    /**
     * Establece la posicion del eje Y de la casilla en la
     * {@link buscaminas.Juego
     * #cuadricula cuadricula}
     *
     * @param yV Posicion del eje Y de la casilla
     * @see buscaminas.Casilla#yV
     * @since 1.0
     */
    public void setyV(int yV) {
        this.yV = yV;
    }

    /**
     * Devuelve true/false si se ha pulsado el click Izquierdo o no
     *
     * @return si el click Izquierdo ha sido pulsado
     * @see buscaminas.Casilla#clickIzq
     * @since 1.0
     */
    public boolean isClickIzq() {
        return clickIzq;
    }

    /**
     * Establece si se ha pulsado click Izquierdo o no
     *
     * @param clickIzq Click Izquierdo
     * @see buscaminas.Casilla#clickIzq
     * @since 1.0
     */
    public void setClickIzq(boolean clickIzq) {
        this.clickIzq = clickIzq;
    }

    /**
     * Devuelve true/false si se ha pulsado el click Derecho o no
     *
     * @return Si el click Derecho ha sido pulsado
     * @see buscaminas.Casilla#clickDrc
     * @since 1.0
     */
    public boolean isClickDrc() {
        return clickDrc;
    }

    /**
     * Establece si se ha pulsado click Derecho o no
     *
     * @param clickDrc Click Derecho
     * @see buscaminas.Casilla#clickIzq
     * @since 1.0
     */
    public void setClickDrc(boolean clickDrc) {
        this.clickDrc = clickDrc;
    }

    /**
     * Devuelve true/false si la casilla es mina
     *
     * @return true/false si es mina la casilla
     * @see buscaminas.Casilla#mina
     * @since 1.0
     */
    public boolean isMina() {
        return mina;
    }

    /**
     * Establece si la casilla tiene mina o no
     *
     * @param mina Condicion de mina
     * @see buscaminas.Casilla#mina
     */
    public void setMina(boolean mina) {
        this.mina = mina;
    }
}
