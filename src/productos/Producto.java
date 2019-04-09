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
public class Producto {
    private final String nombre;
    private final float precio;
    private final short id; 

    public Producto(short id,String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public short getId() {
        return id;
    }
    
    
    
  
}

