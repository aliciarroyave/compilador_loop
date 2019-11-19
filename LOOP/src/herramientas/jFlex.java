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
 * 
 */
public class jFlex {
    public static void main(String args[]){
        jflex.Main.generate(
                new File("src" + File.separator + 
                "principal" + File.separator + "config_ocup.flex"));//metodo principal jflex archivo flex en àquete testfles
    }
}
