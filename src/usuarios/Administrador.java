package usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class Administrador {
    private final String nombre;
    private final String contrasena;

    public Administrador(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    
    public void panelAdministrador(Connection conexion){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Hola "+this.nombre+", que quieres hacer?");
            String accion=sc.nextLine();
            switch(accion){
                case "1":
                    System.out.println("Elige el local donde quieras ver los pedidos");
                    String local=sc.nextLine();
                    Statement smt=conexion.createStatement();
                    ResultSet resultado=smt.executeQuery("select id from pedido where Usuario_Restaurante_codigoRestaurante='"+local+"';");
                    Statement smt2=conexion.createStatement();
                    ResultSet resultado2;
                    String id="";
                    while(resultado.next()){
                        id=resultado.getString("id");
                       resultado2=smt2.executeQuery("select producto.*,productospedidos.*,pedido.*\n" +
                        "from productospedidos\n" +
                        " join producto on producto.id=productospedidos.producto_id\n" +
                        " join pedido on productospedidos.Pedido_id=pedido.id\n" +
                        "where pedido.id='"+id+"';"); 
                       
                       while(resultado2.next()){
                           String nombre=resultado2.getString("nombre");
                           String precio=resultado2.getString("precio");
                           String cantidadUnidad=resultado2.getString("cantidadPorUnidad");
                           String unidad=resultado2.getString("unidadDeMedida");
                           String cantidad=resultado2.getString("cantidad");
                           String fecha=resultado2.getString("fecha");
                           
                           System.out.println(id+"___"+nombre+" "+precio+" "+cantidadUnidad+" "+unidad+" "+cantidad+" "+fecha);
                           
                       }
                    }
                    
                    
                    
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

