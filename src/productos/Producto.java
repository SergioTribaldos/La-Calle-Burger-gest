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
    private tipoProducto tipoProducto;
    private double cantidadPorUnidad;
    private unidadDeMedida unidadDeMedida;

    public Producto(short id,String nombre, float precio, double cantidadPorUnidad, unidadDeMedida unidadDeMedida, tipoProducto tipoProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
        this.tipoProducto = tipoProducto;
        this.cantidadPorUnidad = cantidadPorUnidad;
        this.unidadDeMedida = unidadDeMedida;
    }

   
    
    
    public enum unidadDeMedida{
        KG,
        UD
    }
    public enum tipoProducto{
        HAMBURGUESA,
        ENTRANTE,
        SALSA,
        BOCADILLO,
        POSTRE
        
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

    public tipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public double getCantidadPorUnidad() {
        return cantidadPorUnidad;
    }

    public unidadDeMedida getUnidadDeMedida() {
        return unidadDeMedida;
    }

    
    
    
  
}

