/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import productos.Producto;
import restaurantes.Restaurante;
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class Pedido {
    private Restaurante restaurante;
    private Producto[] listaProductos;
    private int [] cantidad;
    private String [] lote;
    private LocalDateTime fechaPedido;

    public Pedido(Restaurante restaurante) {
 
        this.fechaPedido = LocalDateTime.now();
        this.restaurante = restaurante;

    }
    
    public Producto[] listaProductos(){
        return listaProductos;
    }
    
    public void nuevoPedido(){
        Scanner sc=new Scanner(System.in);
        Connection conexion;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lacalle", "root","kaotiko666");
            Statement smt=conexion.createStatement();
            ResultSet resultado=smt.executeQuery("select * from producto;");
            ArrayList<Integer>cantidad=new ArrayList<Integer>();
            
            //Introducimos los datos del pedido y los guardamos en un ArrayList de Integer
            while(resultado.next()){  
                System.out.println("Introduce cantidad para "+resultado.getString("nombre"));
                cantidad.add(Integer.parseInt(sc.nextLine()));     
                };
            //Recogemos la hora exacta del pedido, que sera la clave primaria
                LocalDateTime horaPedido=LocalDateTime.now();
          ///////////////////MIRAR ESTO//      String horaPedido=horaPedidoCrear.format(DateTimeFormatter.ofPattern("d/M/u k:m"));
             
            //Creamos el pedido
                smt.executeUpdate("insert into pedido values("
                        + "'"+horaPedido+"','"+this.restaurante.getCodigoRestaurante()+"')");
            
            //Creamos un statement nuevo
            Statement smt2=conexion.createStatement();
            ResultSet resultado2=smt2.executeQuery("select * from producto;");
            String producto_id="";
            String producto_nombre="";
            int iterador=0;
            
            //Introducimos los datos en la tabla intermedia de producto y pedido, es decir , el pedido en si
            while(resultado2.next()){
                producto_id=resultado2.getString("id");
                producto_nombre=resultado2.getString("nombre");
               /* String r="insert into producto_has_pedido("
                        + "'"+producto_id+"','"+producto_nombre+"','"+horaPedido+"','"+cantidad.get(iterador)+"','null');";
                System.out.println(r);*/
                smt.executeUpdate("insert into producto_has_pedido values("
                        + "'"+producto_id+"','"+producto_nombre+"','"+horaPedido+"','"+cantidad.get(iterador)+"');");
                iterador++;
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*
        for(int i=0;i<6;i++){
            System.out.println("Introduce cantidad para "+this.listaProductos[i].getNombre());
            cantidad=Integer.parseInt(sc.nextLine());
            this.cantidad[i]=cantidad;
           
        }
        
        escribeArchivo();
                */
    }
    
    public void escribeArchivo(){
        FileWriter log=null;
        try {
           
            File logFile=new File("./"+usuario.getRestaurante().getNombre()+" "+fechaPedido.format(DateTimeFormatter.ofPattern("dd-MM-u(H_m_s)"))+".html");
            log = new FileWriter(logFile.getAbsoluteFile(),false);
            log.append("<!DOCTYPE html>\n" +
"<html lang=\"en\" dir=\"ltr\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <title></title>\n" +
"    <style>\n" +
"    table{\n" +
"      height: 900px !important;\n" +
"    }\n" +
"    table, th, td {\n" +
"      border: 2px solid black;\n" +
"      border-collapse: collapse;\n" +
"      height: 5%;\n" +
"      }\n" +
"      th:first-child{\n" +
"        width: 500px;\n" +
"      }\n" +
"      td:nth-child(2){\n" +
"        font-weight: bolder;\n" +
"        text-align: center;\n" +
"        font-size: 25px;\n" +
"      }\n" +
"      td:first-child{\n" +
"        padding-left: 15px;\n" +
"      }\n" +
"\n" +
"    </style>\n" +
"  </head>\n" +
"  <h1>Pedido de </h1>\n" +
"  <h3>Usuario: </h3>\n" +
"\n" +
"  <body>\n" +
"    <table>\n" +
"      <tr>\n" +
"        <th>HAMBURGUESAS</th>\n" +
"        <th>CANTIDAD</th>\n" +
"      </tr>");
            
           
            for(int i=0;i<this.listaProductos.length;i++){
                log.append("<tr>");
                log.append("<td>"+this.listaProductos[i].getNombre()+"</td><td>"+this.cantidad[i]+"</td>");
 
        }
            log.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                log.close();
            } catch (IOException ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
    
}
