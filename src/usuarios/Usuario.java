package usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
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
    

    
    
    
    
    
    
    
}

