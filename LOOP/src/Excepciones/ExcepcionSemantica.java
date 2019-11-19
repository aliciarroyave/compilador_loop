/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *Excepcion para los errores dentro de la tabla de simbolos
 * @author arroyave
 */
public class ExcepcionSemantica extends Exception{

    public ExcepcionSemantica(String message) {
        super(message);
    }
    
}
