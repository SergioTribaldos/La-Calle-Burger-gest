package restaurantes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

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
        setCodigoRestaurante(codigo);

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



	public void setCodigoRestaurante(String codigoRestaurante) throws RestauranteException {
		if(codigoRestaurante.length()==0) {
			throw new RestauranteException("El campo Código de restaurante no puede estar vacio");
		}
		
		for(int i=0;i<codigoRestaurante.length();i++) {
			if(Character.isDigit(codigoRestaurante.charAt(i))) {
				throw new RestauranteException("El campo Código de restaurante no puede contener numeros");
			}
		}

		this.codigoRestaurante = codigoRestaurante.toUpperCase();
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
