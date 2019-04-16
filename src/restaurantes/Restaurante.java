/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantes;

import java.util.ArrayList;
import pedidos.Pedido;
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
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class Restaurante {
    private final String cif;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private codRestaurante codigoRestaurante;

    

    public Restaurante(String cif, String nombre, String direccion, String telefono,String codigo) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;

        setCodigoRestaurante(codigo);
    }
    
    public enum codRestaurante{
        CENTRO,
        TEATINOS,
        FUENGIROLA,
        PEDREGALEJO,
        AMERICAS,
        PLAZA_MAYOR,
        PARQUE_OESTE,
        GAMARRA,
        SAN_PEDRO,
        MARBELLA       
    }

    public String getCif() {
        return cif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    
    public codRestaurante getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(String codigoRestaurante) {
        switch(codigoRestaurante){
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
    
    
    
    
    
    
    
      
    
    
    
}
