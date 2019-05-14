package usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.UsuarioException;
import pedidos.Pedido;
import restaurantes.Restaurante;


/**
 *
 * @author Sergio
 */
public class Usuario {
    private String usuario;
    private String contrasena;
    private Restaurante restaurante;
  

    public Usuario(Restaurante restaurante,String usuario,String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.restaurante = restaurante;
        
      
    }
    
    public Usuario(String restaurante,String usuario,String contrasena,String contrasena2,Connection conexion) throws UsuarioException {
        setUsuario(usuario,conexion);
        setContrasena(contrasena,contrasena2);
        String restauranteString = restaurante;
      
    }
    
    public Usuario(String usuario,Restaurante restaurante) {
    	this.usuario = usuario;
        this.restaurante = restaurante;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
    
    
    

    public void setUsuario(String usuario,Connection conexion) throws UsuarioException {
    	if(usuario.length()<5||usuario.length()>20) {
			throw new UsuarioException("El nombre de usuario debe tener entre 5 y 10 caracteres");
			
		}
    	
    	try {
			Statement smt=conexion.createStatement();
			smt.executeQuery("select nombre from usuario");
			ResultSet resultado=smt.executeQuery("select nombre from usuario");
			while(resultado.next()) {
				if(usuario.equals(resultado.getString("nombre"))) {
					throw new UsuarioException("El nombre de usuario ya existe");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	this.usuario = usuario;
	}

	public void setContrasena(String contrasena,String contrasena2) throws UsuarioException {
		if(!contrasena.equals(contrasena2)) {
			
			throw new UsuarioException("Las dos contraseñas no coinciden");
			

		}else if(contrasena.length()<5||contrasena.length()>20) {
			throw new UsuarioException("La contraseña debe tener entre 5 y 10 caracteres");
			
		}
		this.contrasena = contrasena;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public void insertarUsuarioEnBaseDeDatos(Connection conexion,String usuario,String contrasena,String codigoRestaurante) {
    	
    	try {
			PreparedStatement smt=conexion.prepareStatement("insert into usuario(nombre,contraseña,Restaurante_codigoRestaurante) values(?,?,?)");
			smt.setString(1,usuario);
			smt.setString(2,contrasena );
			smt.setString(3,codigoRestaurante );
			smt.executeUpdate();
			
			smt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
   
    
}



