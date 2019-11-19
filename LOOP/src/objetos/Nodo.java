/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Excepciones.ExcepcionTablaSimbolos;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Elemento tipo nodo, el cual forma la estructura para crear el arbol.Estos elementos tienen la caracteristica
 * de tener muchos hijos por nodo de tal manera que cada nodo puede tener su propia expresion.
 * @author arroyave
 */
public class Nodo {
    private ArrayList<Nodo> hijos;
    private String contenido;
    private int tab, tipo, fila, columna;
    
    public static final int TIPO_RAIZ = 0;
    public static final int TIPO_OPERADOR = 1;
    public static final int TIPO_FUNCION = 2;
    public static final int TIPO_ENTERO = 3;
    public static final int TIPO_CADENA = 4;
    public static final int TIPO_BOLEANO = 5;
    public static final int TIPO_SI = 6;
    public static final int TIPO_IDENTIFCADOR = 7;
    public static final int TIPO_BLOQUE = 8;
    public static final int TIPO_PARAMETROS = 9;
    public static final int TIPO_RETORNAR = 10;
    public static final int TIPO_ARCHIVO = 11;
    public static final int TIPO_ARCHIVOS = 12;//cada archivo dentro de un archivo 
    public static final int TIPO_NULO = 13; 
    public static final int TIPO_ASIGNACION = 14; 
    public static final int TIPO_LLAMADA_FUNCION = 15; 
    public static final int TIPO_SINO = 16; 
    public static final int TIPO_SINO_COND = 17; 
    public static final int TIPO_DESDE = 18; 
    public static final int TIPO_PARACADA = 19; 
    public static final int TIPO_INSTRUCCION = 20; 
    public static final int TIPO_INTERROGANTE = 21; 
    public static final int TIPO_CONTENIDO_ARCHIVO = 22; 
    public static final int TIPO_OPERACION_ARCHIVO = 23; 

    public static final int TIPO_CLASE = 24;
    public static final int TIPO_REAL = 25;
    public static final int TIPO_PROPIEDADES = 26;
    public static final int TIPO_METODOS = 27;
    /**
     * 
     */
    public Nodo() {
    }
    
    /**
     * Constructor principal del nodo
     * @param contenido el contenido del nodo
     * @param tab la cantidad de tabs que tiene el nodo
     * @param tipo tipo de nodo que es
     */
    public Nodo(String contenido, int tab, int tipo) {
        this.hijos = new ArrayList<>();
        this.contenido = contenido;
        this.tab = tab;
        this.tipo = tipo;
    }

    public Nodo(String contenido, int tab, int tipo, int fila, int columna) {
        this.hijos = new ArrayList<>();
        this.contenido = contenido;
        this.tab = tab;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }
    
    
    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    /**
     * metodo para obtener el numero de tabs del nodo
     * @return 
     */
    public int getTab(){
        return this.tab;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public Nodo getHijo(int index){
        return this.hijos.get(index);
    }
    public String getContenido() {
        return contenido;
    }

    public int getTipo() {
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    
    /**
     * Metodo que inserta hijos al nodo padre comprobando la identacion
     * @param nodo recibe el nodo a insertar
     * @return (1) si la inserción se puede hacer debido al numero de tabs del padre <br> (0) si no se pude hacer la inserción
     */
    public int agregarHijo(Nodo nodo){
        if ((nodo.getTab()-1) == this.getTab()){
            this.hijos.add(nodo);
            return 1;
        }else return 0;
    }
    /**
     * Metodo que inserta hijos al nodo padre sin comrpobar la identacion
     * @param nodo recibe el nodo a insertar
     */
    public void agregarHijoNI(Nodo nodo){
        this.hijos.add(nodo);
    }
    
    public boolean validarExp(Nodo n){
        
        return true;
    }
}
