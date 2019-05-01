package producto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sergio
 */
public class Producto {
    private short id;
    private String nombre;
    private float precio;
    private String tipoProducto;
    private double cantidadPorUnidad;
    private unidadDeMedida unidadDeMedida;

    public Producto(short id,String nombre, float precio, double cantidadPorUnidad, unidadDeMedida unidadDeMedida, String tipoProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.cantidadPorUnidad = cantidadPorUnidad;
        this.unidadDeMedida = unidadDeMedida;
    }
    
    public Producto(short id,String nombre,float precio) {
    	 this.id = id;
         this.nombre = nombre;
         this.precio = precio;
    	
    }
 
    
    public enum unidadDeMedida{
        KG,
        UD
    }
  

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }


    public String getTipoProducto() {
        return tipoProducto.toUpperCase();
    }

    public double getCantidadPorUnidad() {
        return cantidadPorUnidad;
    }

    public unidadDeMedida getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public short getId() {
        return id;
    }
   
  
}

