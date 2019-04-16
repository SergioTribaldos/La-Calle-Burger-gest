/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;


import pedidos.Pedido;
import restaurantes.Restaurante;


/**
 *
 * @author Sergio
 */
public class Usuario {
    private String usuario;
    private String contraseña;
    private Restaurante restaurante;
  

    public Usuario(Restaurante restaurante,String usuario,String contrasena) {
        this.usuario = usuario;
        this.contraseña = contrasena;
        this.restaurante = restaurante;
      
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
    
    

    public void hacerPedido(){
        System.out.println("Panel de control de pedidos, usuario "+this.usuario+" Restaurante: "+this.restaurante.getNombre());
        Pedido actual=new Pedido(this);
        actual.nuevoPedido();
    }
  
    
    
  
    
    public void logearse(){
        
    }
    
    
    
    
    
    
    
    
}
