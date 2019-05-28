package usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sergio
 */
public abstract class Administrador {
    private final String nombre;
    private final String contrasena;
GF
    public Administrador(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
   
}

