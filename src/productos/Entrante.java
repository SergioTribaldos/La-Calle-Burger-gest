/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author Sergio
 */
public class Entrante extends Producto{
    private final double kilos;
    private final int unidades;

    public Entrante(short id,String nombre, float precio,double kilos) {
        super(id,nombre, precio);
        this.kilos = kilos;
        this.unidades = 0;
    }
    
    public Entrante(short id,String nombre, float precio,int unidades) {
        super(id,nombre, precio);
        this.unidades = unidades;
        this.kilos = 0;
    }
    
    
    
    
    
    
}
