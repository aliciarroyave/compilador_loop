/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author arroyave
 */
public class token {
    private final String nombre;
    private final int fila, columna, tab;

    public token(String nombre, int fila, int columna, int tab) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.tab = tab;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getTab() {
        return tab;
    }
}
