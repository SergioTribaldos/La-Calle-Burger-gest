/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantes;

import java.util.ArrayList;

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
    

    public Restaurante(String cif, String nombre, String direccion, String telefono,codRestaurante codigo) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoRestaurante = codigo;
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
    
    
    
      
    
    
    
}
