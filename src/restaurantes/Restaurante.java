package restaurantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;

import excepciones.RestauranteException;
import lacalleburger.Ventana;
import pedidos.Pedido;
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class Restaurante {
    private String cif;
    private String direccion;
    private String telefono;
    private String codigoRestaurante;
    
    
    
public Restaurante(String cif, String direccion, String telefono,String codigo) throws RestauranteException {
    	
        setCif(cif);
        setDireccion(direccion);
        setTelefono(telefono);
        this.codigoRestaurante = codigo;

    }
    

    public Restaurante(String cif, String direccion, String telefono,String codigo,Connection conexion) throws RestauranteException {
    	
        setCif(cif);
        setDireccion(direccion);
        setTelefono(telefono);
        setCodigoRestaurante(codigo,conexion);

    }



	public String getCif() {
		return cif;
	}



	public void setCif(String cif) throws RestauranteException {
		if(cif.length()==0) {
			throw new RestauranteException("El campo Cif no puede estar vacio");
		}
		
		if(cif.length()>20) {
			throw new RestauranteException("El Cif introducido no puede tener mas de 20 caracteres");
		}
		
		this.cif=cif;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) throws RestauranteException {
		if(direccion.length()==0) {
			throw new RestauranteException("El campo dirección no puede estar vacio");
		}
		
		if(cif.length()>20) {
			throw new RestauranteException("La direccion introducida no puede tener mas de 150 caracteres");
		}
		
		this.direccion=direccion;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) throws RestauranteException {
		if(telefono.length()==0) {
			throw new RestauranteException("El campo teléfono no puede estar vacio");
		}

		this.telefono=telefono;
		
		
	}



	public String getCodigoRestaurante() {
		return codigoRestaurante;
	}



	public void setCodigoRestaurante(String codigoRestaurante,Connection conexion) throws RestauranteException {
		codigoRestaurante=codigoRestaurante.toUpperCase();
		if(codigoRestaurante.length()==0) {
			throw new RestauranteException("El campo Código de restaurante no puede estar vacio");
		}
		
		for(int i=0;i<codigoRestaurante.length();i++) {
			if(Character.isDigit(codigoRestaurante.charAt(i))) {
				throw new RestauranteException("El campo Código de restaurante no puede contener numeros");
			}
		}
		
		String []restaurantes=consultaRestaurantesDisponibles(conexion);
		boolean hayUnRestauranteConEsteNombre = Arrays.stream(restaurantes).anyMatch(codigoRestaurante::equals);
		if(hayUnRestauranteConEsteNombre) {
			throw new RestauranteException("El codigo de restaurante ya existe");
		}else {
			this.codigoRestaurante = codigoRestaurante;
		}
			

		
	}
	
	/**
	 * Consulta la tabla "restaurante" de la base de datos y devuelve un array de String con los codigos de restaurante.
	 * @param conexion La conexion que estamos utilizando
	 * @return un array de String con los codigos de restaurante.
	 */
	public static String[] consultaRestaurantesDisponibles(Connection conexion) {
		String[]restaurantes;
		try {
			
			Statement smt=conexion.createStatement();
			ResultSet resultados=smt.executeQuery("select codigoRestaurante from restaurante;");
			resultados.last();
			restaurantes=new String[resultados.getRow()];
			resultados.beforeFirst();
			int iterador=0;
			while(resultados.next()) {
				restaurantes[iterador]=resultados.getString("codigoRestaurante");
				iterador++;
			}
			return restaurantes;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public void insertarRestauranteEnBaseDeDatos(Ventana ventana) {
		try {
			PreparedStatement smt=ventana.getConexion().prepareStatement("insert into restaurante(cif,direccion,telefono,codigoRestaurante) values (?,?,?,?)");
			smt.setString(1, this.cif);
			smt.setString(2, this.direccion);
			smt.setString(3, this.telefono);
			smt.setString(4, this.codigoRestaurante);
			
			smt.executeUpdate();
			smt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
