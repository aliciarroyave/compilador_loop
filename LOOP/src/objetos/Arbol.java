/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
  

Guarda bloques de codigo
*/
package objetos;

/**
 * Objeto de tipo arbol encargado de relizar los recorridos
 * @author arroyave
 */
public class Arbol {
    private Nodo raiz;
    
    /**
     * 
     */
    public Arbol() {
    }

    /**
     * 
     * @param raiz 
     */
    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    /**
     * 
     * @return 
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * 
     * @param raiz 
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    /**
     * 
     * @param nodo
     * @return 
     */
    public int  agregarHijo(Nodo nodo){
        return this.raiz.agregarHijo(nodo);
    }
    
    public Nodo getHijo(int id){
        return this.raiz.getHijo(id);
    }
    /**
     * recorrido in-orden
     */
    public void recorrido(){
        
    }
    
    
}
