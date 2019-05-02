package producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
   
    /*
     * Consulta la tabla producto de la base de datos, los mete en un arrayList y los devuelve
     */
    public static ArrayList<Producto> recogeInformacionProductos(Connection conexion){  	
    	try {
    		Statement stm=conexion.createStatement();				
    		ResultSet resultadoProductos=stm.executeQuery("select * from producto order by id asc");
    		int precio;
    		short id;
    		String nombreProducto;
    		
    		ArrayList<Producto> productos=new ArrayList<Producto>();
    		while(resultadoProductos.next()) {
    			nombreProducto=resultadoProductos.getString("nombre");
    			precio=resultadoProductos.getInt("precio");
    			id=(short)resultadoProductos.getInt("id");
    			productos.add(new Producto(id,nombreProducto,precio));
    			
    		}

    		stm.close();
    		resultadoProductos.close();
    		
    		return productos;
    	}catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return null;
		}
    	
    	
    }
}

