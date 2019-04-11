/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.util.ArrayList;
import pedidos.Pedido;
import restaurantes.Restaurante;
import restaurantes.Restaurante.codRestaurante;
import static restaurantes.Restaurante.codRestaurante.AMERICAS;
import static restaurantes.Restaurante.codRestaurante.CENTRO;
import static restaurantes.Restaurante.codRestaurante.FUENGIROLA;
import static restaurantes.Restaurante.codRestaurante.GAMARRA;
import static restaurantes.Restaurante.codRestaurante.MARBELLA;
import static restaurantes.Restaurante.codRestaurante.PARQUE_OESTE;
import static restaurantes.Restaurante.codRestaurante.PEDREGALEJO;
import static restaurantes.Restaurante.codRestaurante.PLAZA_MAYOR;
import static restaurantes.Restaurante.codRestaurante.SAN_PEDRO;
import static restaurantes.Restaurante.codRestaurante.TEATINOS;

/**
 *
 * @author Sergio
 */
public class Usuario {
    private String usuario;
    private String contraseña;
    private codRestaurante codigoRestaurante;
    private Pedido pedido;

    public Usuario(String restaurante,String usuario,String contrasena) {
        this.usuario = usuario;
        this.contraseña = contrasena;
        switch(restaurante){
            case "CENTRO":
                this.codigoRestaurante = CENTRO;
                break;
            case "TEATINOS":
                this.codigoRestaurante = TEATINOS;
                break;
            case "FUENGIROLA":
                this.codigoRestaurante = FUENGIROLA;
                break;
            case "PEDREGALEJO":
                this.codigoRestaurante = PEDREGALEJO;
                break;
            case "AMERICAS":
                this.codigoRestaurante = AMERICAS;
                break;
            case "PLAZA_MAYOR":
                this.codigoRestaurante = PLAZA_MAYOR;
                break;
            case "PARQUE_OESTE":
                this.codigoRestaurante = PARQUE_OESTE;
                break; 
            case "GAMARRA":
                this.codigoRestaurante = GAMARRA;
                break;
            case "SAN_PEDRO":
                this.codigoRestaurante = SAN_PEDRO;
                break;
            case "MARBELLA":
                this.codigoRestaurante = MARBELLA;
                break;        
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public codRestaurante getRestaurante() {
        return codigoRestaurante;
    }

    public Pedido getPedido() {
        return pedido;
    }
    
    
    
  
    
    public void logearse(){
        
    }
    
    
    
    
    
    
    
    
}
