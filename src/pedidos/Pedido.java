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
import producto.Producto;
import restaurantes.Restaurante;
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class Pedido {
    private Usuario usuario;
    private Producto[] listaProductos;
    private int [] cantidad;
    private String [] lote;
    private LocalDateTime fechaPedido;
    private Connection conexion;
 

    public Pedido(Usuario usuario,Connection conexion,int[]cantidad) {
 
        this.fechaPedido = LocalDateTime.now();
        this.usuario = usuario;
        this.conexion = conexion;
        this.cantidad = cantidad;

    }
    
    public Producto[] listaProductos(){
        return listaProductos;
    }
    
    public void nuevoPedido(){
        Scanner sc=new Scanner(System.in);
        try {
            Statement smt=conexion.createStatement();
            ResultSet resultado=smt.executeQuery("select * from producto;");
            
            
            
            //Recogemos la hora exacta del pedido, que sera la clave primaria
                LocalDateTime horaPedido=LocalDateTime.now();
          ///////////////////MIRAR ESTO//      String horaPedido=horaPedidoCrear.format(DateTimeFormatter.ofPattern("d/M/u k:m"));
             
            //Creamos el pedido
                smt.executeUpdate("insert into pedido(fecha,usuario_Usuario,Usuario_Restaurante_codigoRestaurante) values("
                        + "'"+horaPedido+"','"+this.usuario.getUsuario()+"','"+this.usuario.getRestaurante().getCodigoRestaurante()+"');");
            
            //Creamos un statement nuevo
            Statement smt2=conexion.createStatement();
            ResultSet resultado2=smt2.executeQuery("select max(id) from pedido;");
            resultado2.next();
            String id_pedido=resultado2.getString(1);
            
            Statement smt3=conexion.createStatement();
            ResultSet resultado3=smt3.executeQuery("select * from producto;");
            int iterador=0;
            
            String id_producto="";
            //Introducimos los datos en la tabla intermedia de producto y pedido, es decir , el pedido en si
            while(resultado3.next()){
                id_producto=resultado3.getString("id");
                
                
               /* String r="insert into producto_has_pedido("
                        + "'"+producto_id+"','"+producto_nombre+"','"+horaPedido+"','"+cantidad.get(iterador)+"','null');";
                System.out.println(r);*/
                smt.executeUpdate("insert into productospedidos values("
                        + "'"+id_producto+"','"+id_pedido+"','"+cantidad[iterador]+"');");
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
            
           
           /* for(int i=0;i<this.listaProductos.length;i++){
                log.append("<tr>");
                log.append("<td>"+this.listaProductos[i].getNombre()+"</td><td>"+this.cantidad[i]+"</td>");
 
        } */
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

