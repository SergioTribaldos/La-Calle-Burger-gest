/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author Sergio
 */
public class Postre extends Producto {
    private final int unidades;

    public Postre(short id, String nombre, float precio,int unidades) {
        super(id, nombre, precio);
        this.unidades = unidades;
    }
    
    
    
}
