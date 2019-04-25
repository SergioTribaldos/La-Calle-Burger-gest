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
    private tipoProducto tipoProducto;
    private double cantidadPorUnidad;
    private unidadDeMedida unidadDeMedida;

    public Producto(short id,String nombre, float precio, double cantidadPorUnidad, unidadDeMedida unidadDeMedida, tipoProducto tipoProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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


    public tipoProducto getTipoProducto() {
        return tipoProducto;
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
    
    public tipoProducto getTipoProducto(String tipo) {
    	switch(tipo) {
    	case "HAMBURGUESA":
    		return tipoProducto.HAMBURGUESA;

		case "ENTRANTE":
		    return tipoProducto.ENTRANTE;	
		    
		case "SALSA":
			return tipoProducto.SALSA;
			
		case "BOCADILLO":
			return tipoProducto.BOCADILLO;
		case "POSTRE":
			return tipoProducto.POSTRE;
		default:
			return null;

    	}
    }

    
    
    
  
}

