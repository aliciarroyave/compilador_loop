/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.io.File;

/**
 *
 * @author arroyave
 */
public class jCup {
    public static void main(String[] args) {
        String opciones[] = new String[5];
        opciones[0]="-destdir";
        opciones[1]="src"
                +File.separator+
                "principal"; //cambiar destino
        opciones[2]="-parser";
        opciones[3]="sintactico"; //cambiar el nombre de la clase
        opciones[4]="src"
                +File.separator+
                "principal"
                +File.separator+
                "config.cup"; //cambiar el nombre de la fuente
        try {     
            java_cup.Main.main(opciones);
            } 
        catch (Exception e) {
            System.out.print(e);
            }
    }
}
