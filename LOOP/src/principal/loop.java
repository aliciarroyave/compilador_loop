/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import Excepciones.ExcepcionArchivo;
import Excepciones.ExcepcionDeclaracion;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;
import objetos.Arbol;

/**
 *
 * @author arroyave
 */
public class loop {
    
    static BufferedReader bf;
    
    public static void main (String[] args) throws ExcepcionArchivo{
        try {
            new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "LOOP" + File.separator).mkdir();
            System.out.println("directorio de salida: " + FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "LOOP" + File.separator);
            InputStreamReader isr = new InputStreamReader(System.in);
            
            bf = new BufferedReader(isr);
            String archivoProcesado;
            /*switch (args.length) {
                case 1:*/
                    //String archivoEntrada =args[0];
                    String archivoEntrada = "./src/principal/loop.txt";
                    if (archivoEntrada.equals("")) {
                        System.out.println("ERROR: No se recibio el archivo de entrada.");
                       // break;
                    }
                    System.out.println("Archivo recibido: " + archivoEntrada);
                    if (!archivoEntrada.endsWith(".txt")) {
                        System.out.println("ERROR: La extension del archivo es invalida.");
                        //break;
                    }
                    if(!new File(archivoEntrada).exists()){
                        throw new ExcepcionArchivo("ERROR: No se puede abrir el achivo. Verifique que la ruta sea correcta y que el nombre no lleve espacios");
                    }
                    sintactico AnalizadorSintactico;
                    Semantico semantico;
                    Arbol arbolSemantico;
                    try {
                        System.out.println("Iniciando analisis Lexico y Sintactico...");
                        lexico AnalizadorLexico = new lexico(new FileReader(archivoEntrada));
                        AnalizadorSintactico = new sintactico(AnalizadorLexico);
                        AnalizadorSintactico.parse();
                        arbolSemantico = AnalizadorSintactico.arbolSintactico;
                        System.out.println("Analisis finalizado.");
                    } catch (Exception e) {
                        throw new ExcepcionArchivo("ERROR: Archivo no analizado: " + e.getMessage());
                    }
                    
                    semantico = new Semantico(arbolSemantico);
                    try {
                        System.out.println("Iniciando Analisis Semantico...");
                        semantico.analizar();
                        System.out.println("Analisis finalizado");
                    } catch (ExcepcionDeclaracion ex) {
                        Logger.getLogger(loop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    try {
                        System.out.println("********Ejecuatando archivo*******");
                        semantico.ejecutar();
                        System.out.println("**************FIN*****************");
                    } catch (Exception e) {
                        System.out.println("ERROR DE EJECUCCION: " + e.getMessage());
                    }
                   /* break;
                case 0:
                    System.out.println("No se recibio un archivo");
                    break;
            }*/
            String entrada = bf.readLine();
        } catch (IOException ex) {
            Logger.getLogger(loop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
