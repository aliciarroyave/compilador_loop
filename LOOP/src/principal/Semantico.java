/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Excepciones.ExcepcionArchivo;
import Excepciones.ExcepcionDeclaracion;
import Excepciones.ExcepcionEjecicion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;
import objetos.Arbol;
import objetos.Nodo;

/**
 *
 * @author arroyave
 */
public class Semantico {
    Arbol arbolSemantico;
    ArrayList<Nodo> todasFunciones;
    String dirArchivoSalida = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "LOOP" + File.separator + "salida.asm";
    String dirArchivoClase = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "LOOP" + File.separator + "calsesyfunciones.txt";
    BufferedWriter archivoSalida, archivoClase;
    public Semantico(){
    }

    public Semantico(Arbol arbolSemantico) {

        this.arbolSemantico = arbolSemantico;
        todasFunciones = new ArrayList<>();
    }

    void analizar()  throws ExcepcionArchivo , ExcepcionDeclaracion{
        try {
            archivoClase = new BufferedWriter(new FileWriter(dirArchivoClase));
            Nodo archivos = arbolSemantico.getHijo(0);
            ArrayList<Nodo> hijos = archivos.getHijos();

            for (Nodo hijo : hijos) {
                String arch = (hijo.getContenido()).replace("\"", "");
                archivoClase.write("incluir ->" + hijo.getContenido());
                archivoClase.newLine();
                if(!new File(arch).exists()){
                    throw new ExcepcionArchivo("ERROR: No se puede abrir el achivo. Verifique que la ruta sea correcta y que el nombre no lleve espacios: " + hijo.getContenido());
                }
            }

            System.out.println("*****Librerias verificadas");

            ArrayList<Nodo> clases = arbolSemantico.getHijo(1).getHijos();

            ArrayList<String> nomreClases= new ArrayList<>();

            for(Nodo clase: clases){
                archivoClase.write("Clase ->" + clase.getContenido());
                archivoClase.newLine();
                if(!nomreClases.contains(clase.getContenido())){
                    nomreClases.add(clase.getContenido());
                    
                }else{
                    throw new ExcepcionDeclaracion("Existe mas de una clase con ese nombre: " + clase.getContenido());
                }
                Nodo bloquePropiedades = clase.getHijo(0);
                ArrayList<Nodo> propiedades = bloquePropiedades.getHijo(0).getHijos();
                ArrayList<String> variables= new ArrayList<>(); 

                for(Nodo propiedad: propiedades){
                    archivoClase.write("propiedad ->" + propiedad.getContenido());
                    archivoClase.newLine();
                    if(!variables.contains(propiedad.getContenido())){
                        variables.add(propiedad.getContenido());
                    }else{
                        throw new ExcepcionDeclaracion("Existe mas de una variable con ese nombre: " + propiedad.getContenido());
                    }
                }
                Nodo bloqueFunciones = clase.getHijo(1);
                ArrayList<Nodo> funciones = bloqueFunciones.getHijo(0).getHijos();

                ArrayList<String> nombreFuncion= new ArrayList<>();
                for(Nodo funcion:funciones){
                    archivoClase.write("funcion ->" + funcion.getContenido());
                    archivoClase.newLine();
                    if(!nombreFuncion.contains(funcion.getContenido())){
                        nombreFuncion.add(funcion.getContenido());
                    }else{
                        throw new ExcepcionDeclaracion("Existe mas de una Funcion con ese nombre: " + funcion.getContenido());
                    }
                }
                Nodo aux =new Nodo(clase.getContenido(), 0, 0);
                aux.agregarHijoNI(bloqueFunciones);
                todasFunciones.add(aux);
            }
            archivoClase.close();
        } catch (IOException ex) {
             Logger.getLogger(Semantico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ejecutar() throws ExcepcionEjecicion{
        try {
            archivoSalida= new BufferedWriter(new FileWriter(dirArchivoSalida));
            if (!todasFunciones.isEmpty()) {
                for(Nodo clase:todasFunciones ){
                    
                    ArrayList<Nodo> funciones = clase.getHijo(0).getHijo(0).getHijos();
                    archivoSalida.write("");
                    archivoSalida.newLine();
                }
            }else{
                throw new ExcepcionEjecicion("No hay funciones para exportar");
            }
            archivoSalida.close();
        } catch (IOException ex) {
            Logger.getLogger(Semantico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}