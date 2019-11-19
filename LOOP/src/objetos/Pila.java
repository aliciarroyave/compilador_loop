/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 

se usa para las funciones*/
package objetos;

import java.util.ArrayList;

/**
 *
 * @author arroyave
 */
public class Pila {
    private final ArrayList<Nodo> pila;

    public Pila(ArrayList<Nodo> pila) {
        this.pila = pila;
    }

    public Pila() {
        this.pila = new ArrayList<>();
    }

    public ArrayList<Nodo> getPila() {
        return pila;
    }
    
    /**
     * verfiica los valores que se igresan a la pila regresa un arreglo de nodos con laslineas de codigo de las funciones
     * @param nodo
     * @return 
     */
    public int push(Nodo nodo){
        if (pila.isEmpty() && nodo.getTab() == 1) {
            this.pila.add(nodo);
            return 1;
        }
        
        else if(!pila.isEmpty()){
            if (nodo.getTab() == this.pila.get(this.pila.size()-1).getTab()) {
                if (nodo.getTipo() == Nodo.TIPO_SINO) {
                    if(this.pila.get(this.pila.size()-1).getTipo() == Nodo.TIPO_SINO)
                        return 2;
                    if (this.pila.get(this.pila.size()-1).getTipo() == Nodo.TIPO_SI || this.pila.get(this.pila.size()-1).getTipo() == Nodo.TIPO_SINO_COND){
                        this.popUlt();
                        int result = this.insertarSino(nodo);
                        this.pila.add(nodo);
                        return result;
                    }
                    else return 2;
                }
                else if (nodo.getTipo() == Nodo.TIPO_SINO_COND) {
                    if(this.pila.get(this.pila.size()-1).getTipo() == Nodo.TIPO_SINO)
                        return 2;
                    if (this.pila.get(this.pila.size()-1).getTipo() == Nodo.TIPO_SI || this.pila.get(this.pila.size()-1).getTipo() == Nodo.TIPO_SINO_COND){
                        this.popUlt();
                        int result = this.insertarSino(nodo);
                        this.pila.add(nodo);
                        return result;
                    }
                    else return 2;
                }
                if (nodo.getTab() == 3) {
                    this.popUlt();
                    this.pila.add(nodo);
                    return 1;
                }else{
                    int ultimo = this.pila.size()-1;
                    if (this.pila.get(ultimo).getTab() != 3) this.pila.remove(ultimo);
                    if (esPadre(nodo)) {
                        int result = this.insertar(nodo);
                        this.pila.add(nodo);
                        return result;
                    } else{
                        return this.insertar(nodo);
                    }
                }
            }
            else if (nodo.getTab() == this.pila.get(this.pila.size()-1).getTab() + 1){
                if(esPadre(this.pila.get(this.pila.size()-1))){
                    if (esPadre(nodo)){
                        int result = this.insertar(nodo);
                        this.pila.add(nodo);
                        return result;
                    }else{
                        return this.insertar(nodo);
                    }
                }
            }
            else if (nodo.getTab() < this.pila.get(this.pila.size()-1).getTab()){
                pop(nodo.getTab());
                return push(nodo);
            }
        }
        
        return 0;
    }
    
    private int insertar(Nodo nodo){
        int ultimo = this.pila.size()-1;
        if (this.esPadre(this.pila.get(ultimo))) {
            switch (this.pila.get(ultimo).getTipo()) {
                case Nodo.TIPO_DESDE:
                    return this.pila.get(ultimo).getHijo(3).agregarHijo(nodo);
                case Nodo.TIPO_PARACADA:
                    return this.pila.get(ultimo).getHijo(2).agregarHijo(nodo);
                case Nodo.TIPO_SINO:
                    return this.pila.get(ultimo).getHijo(0).agregarHijo(nodo);
                default:
                    return this.pila.get(ultimo).getHijo(1).agregarHijo(nodo);
            }
        }else return this.pila.get(ultimo).agregarHijo(nodo);
    }
    private int insertarSino(Nodo nodo){
        int ultimo = this.pila.size()-1;
        if (this.pila.get(ultimo).getTipo() == Nodo.TIPO_SI) {
            this.pila.get(ultimo).agregarHijoNI(nodo);
            return 1;
        }else return 2;
    }
    /**
     * verifica que el nodo a insertar pueda ser padre o no
     * @param nodo
     * @return 
     */
    private boolean esPadre(Nodo nodo){
        switch (nodo.getTipo()) {
            case Nodo.TIPO_SI:
                return true;
            case Nodo.TIPO_SINO:
                return true;
            case Nodo.TIPO_SINO_COND:
                return true;
            case Nodo.TIPO_PARACADA:
                return true;
            case Nodo.TIPO_DESDE:
                return true;
        }
        return false;
    }
    
    private void pop(int tab){
        
        for (int i = this.pila.size()-1; i >= 0; i--) {
            int ultimo = this.pila.size()-1;
            if (this.pila.get(i).getTab() > tab || this.pila.get(ultimo).getTipo() == Nodo.TIPO_SINO || this.pila.get(ultimo).getTipo() == Nodo.TIPO_SINO_COND) {
                this.pila.remove(this.pila.get(i));
            }
        }
    }
    private void popUlt(){
        int ultimo = this.pila.size()-1;
        if (this.pila.get(ultimo).getTipo() == Nodo.TIPO_SINO || this.pila.get(ultimo).getTipo() == Nodo.TIPO_SINO_COND) {
            this.pila.remove(this.pila.get(ultimo));
        }
    }
    
    
}
