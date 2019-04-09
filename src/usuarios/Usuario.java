/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.util.ArrayList;
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
    private Pedido pedido;

    public Usuario(Restaurante.codRestaurante restaurante,ArrayList<Restaurante> listaRestaurante,String usuario,String contrasena) {
        this.usuario = usuario;
        this.contraseña = contrasena;
        switch(restaurante){
            case CENTRO:
                this.restaurante = listaRestaurante.get(0);
                break;
            case TEATINOS:
                this.restaurante = listaRestaurante.get(1);
                break;
            case FUENGIROLA:
                this.restaurante = listaRestaurante.get(2);
                break;
            case PEDREGALEJO:
                this.restaurante = listaRestaurante.get(3);
                break;
            case AMERICAS:
                this.restaurante = listaRestaurante.get(4);
                break;
            case PLAZA_MAYOR:
                this.restaurante = listaRestaurante.get(5);
                break;
            case PARQUE_OESTE:
                this.restaurante = listaRestaurante.get(6);
                break; 
            case GAMARRA:
                this.restaurante = listaRestaurante.get(7);
                break;
            case SAN_PEDRO:
                this.restaurante = listaRestaurante.get(8);
                break;
            case MARBELLA:
                this.restaurante = listaRestaurante.get(9);
                break;        
        }
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

    public Pedido getPedido() {
        return pedido;
    }
    
    
    
    public void hacerPedido(){
        System.out.println("Usuario "+this.usuario+", vas a hacer un nuevo pedido");
        this.pedido = new Pedido(this);
        this.pedido.meteDatos();
    }
    
    
    
    
    
    
    
    
}
