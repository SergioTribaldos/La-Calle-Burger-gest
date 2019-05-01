package pedidos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import producto.Producto;
import restaurantes.Restaurante;
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class Pedido {
	private short id;
    private Usuario usuario;
    private Producto[] listaProductos;
    private int [] cantidad;
    private LocalDateTime fechaPedido;
    private Connection conexion;
    
    private Restaurante restaurante;
 

    public Pedido(Usuario usuario,Connection conexion,int[]cantidad) {
 
        this.fechaPedido = LocalDateTime.now();
        this.usuario = usuario;
        this.conexion = conexion;
        this.cantidad = cantidad;

    }
    
    public Pedido(short id,LocalDateTime fechaPedido,Usuario usuario,Restaurante restaurante) {
    	this.id = id;
    	this.fechaPedido = fechaPedido;
    	this.usuario = usuario;
    	this.restaurante = restaurante;
    }
    
   
    
    
    public Producto[] listaProductos(){
        return listaProductos;
    }
    

    public void nuevoPedido(){
        Scanner sc=new Scanner(System.in);
        try {
            PreparedStatement smt=conexion.prepareStatement("insert into pedido(fecha,usuario_Usuario) values(?,?)");
            ResultSet resultado=smt.executeQuery("select * from producto;");

            //Creamos el pedido
                smt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                smt.setString(2, this.usuario.getUsuario());
                smt.executeUpdate();
                smt.close();

            //Creamos un statement nuevo
            Statement smt2=conexion.createStatement();
            ResultSet resultado2=smt2.executeQuery("select max(id) from pedido;");
            resultado2.next();
            int id_pedido=resultado2.getInt(1);
            
            PreparedStatement smt3=conexion.prepareStatement("insert into productospedidos(producto_id,pedido_id,cantidad) values (?,?,?)");
            ResultSet resultado3=smt3.executeQuery("select * from producto;");
            int iterador=0;

            //Introducimos los datos en la tabla intermedia de producto y pedido, es decir , el pedido en si
            while(resultado3.next()){
            	
            	smt3.setInt(1,resultado3.getInt("id"));
            	smt3.setInt(2, id_pedido);
            	smt3.setInt(3,cantidad[iterador] );
            	smt3.addBatch();
            	iterador++;               
            }
            smt3.executeBatch();
            smt3.close();
                
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }   
     
    }
    
    
    
    
    
    
}

