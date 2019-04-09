/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import productos.Bocadillo;
import productos.Entrante;
import productos.Hamburguesa;
import productos.Postre;
import productos.Producto;
import productos.Salsa;
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

    public Pedido(Usuario usuario) {
        
        this.listaProductos = listaProductos();
        this.cantidad = new int[listaProductos.length];
        this.lote = new String[listaProductos.length];
        this.fechaPedido = LocalDateTime.now();
        this.usuario = usuario;

    }
    
    public Producto[] listaProductos(){
        final Producto [] listaProductos=new Producto[37];
        listaProductos[0]=new Hamburguesa((short)0,"HAMBURGUESA DE AGUJA 20ud",1,20);
        listaProductos[1]=new Hamburguesa((short)1,"HAMBURGUESA DE ANGUS 20ud",1,20);
        listaProductos[2]=new Hamburguesa((short)2,"HAMBURGUESA DE ENTRAÑA 20ud",1,20);
        listaProductos[3]=new Hamburguesa((short)3,"HAMBURGUESA DE GARBANZOS 30ud",1,30);
        listaProductos[4]=new Hamburguesa((short)4,"HAMBURGUESA DE LENTEJAS 30ud",1,30);
        listaProductos[5]=new Hamburguesa((short)5,"HAMBURGUESA DE VACIO 20ud",1,20);
        listaProductos[6]=new Hamburguesa((short)6,"HAMBURGUESA DOBLE 30ud",1,30);
        listaProductos[7]=new Entrante((short)7,"ALITAS DE POLLO 2kg",1,2.0);
        listaProductos[8]=new Entrante((short)8,"BACON CRUJIENTE bolsa 400gr",1,0.4);
        listaProductos[9]=new Entrante((short)9,"CEBOLLA AL VINO bolsa 1,5kg",1,1.5);
        listaProductos[10]=new Entrante((short)10,"CHAMPIÑON PORTOBELLO bolsa 1kg",1,1.0);
        listaProductos[11]=new Entrante((short)11,"COSTILLAS DE CERDO bolsa 2kg",1,2.0);
        listaProductos[12]=new Entrante((short)12,"ENTRAÑA PARA BROCHETA bolsa 500gr",1,0.5);
        listaProductos[13]=new Entrante((short)13,"FINGERS DE POLLO bolsa 2kg",1,2.0);
        listaProductos[14]=new Entrante((short)14,"PIMIENTO CONFITADO",1,12);
        listaProductos[15]=new Entrante((short)15,"POLLO MARINADO bandeja 2,5kg",1,2.5);
        listaProductos[16]=new Entrante((short)16,"SALSA CALAMBRITO bolsa 2 k",1,2.0);
        listaProductos[17]=new Salsa((short)17,"CALDO PARA SALTEADO bolsa 1 kg",1,1.0);
        listaProductos[18]=new Salsa((short)18,"KETCHUP LA CALLE bolsa 1 kg",1,1.0);
        listaProductos[19]=new Salsa((short)19,"MAYONESA LA CALLE bolsa 2 kg",1,2.0);
        listaProductos[20]=new Salsa((short)20,"MAYONESA DE CHIMICHURRI bolsa 2 kg",1,2.0);
        listaProductos[21]=new Salsa((short)21,"MAYONESA DE PIMIENTA Y TRUFA bolsa 2 kg",1,2.0);
        listaProductos[22]=new Salsa((short)22,"MAYONESA SWEET CHILI bolsa 1 kg",1,1.0);
        listaProductos[23]=new Salsa((short)23,"MAYONESA VEGANA bolsa 500 g",1,0.5);
        listaProductos[24]=new Salsa((short)24,"SALSA BBQ bolsa 2 kg",1,2.0);
        listaProductos[25]=new Salsa((short)25,"SALSA BBQ TOFFEE bolsa 1 kg",1,1.0);
        listaProductos[26]=new Salsa((short)26,"SALSA CALLEJERA bolsa 2 kg",1,2.0);
        listaProductos[27]=new Salsa((short)27,"SALSA CÉSAR bolsa 1 kg",1,1.0);
        listaProductos[28]=new Salsa((short)28,"SALSA DE ALITAS bolsa 2 kg",1,2.0);
        listaProductos[29]=new Salsa((short)29,"VINAGRETA PARA ENSALADA DE COL bolsa 1 kg",1,1.0);
        listaProductos[30]=new Bocadillo((short)30,"COCHINITA PIBIL bolsa 1 kg",1,1.0);
        listaProductos[31]=new Bocadillo((short)31,"ENTRECOT MARINADO bolsa 500 g",1,0.5);
        listaProductos[32]=new Bocadillo((short)32,"SALCHICHAS bolsa 8 ud",1,8);
        listaProductos[33]=new Postre((short)33,"TARTA OREO caja 16 ud",1,16);
        listaProductos[34]=new Postre((short)34,"TARTA QUESO caja 16 ud",1,16);
        listaProductos[35]=new Postre((short)35,"TARTA SACHER caja 20 ud",1,20);
        listaProductos[36]=new Postre((short)36,"TARTA ZANAHORIA caja 20 ud",1,20);
        
        return listaProductos;
    }
    
    public void meteDatos(){
        Scanner sc=new Scanner(System.in);
        String lote="";
        int cantidad;
        
        for(int i=0;i<6;i++){
            System.out.println("Introduce cantidad para "+this.listaProductos[i].getNombre());
            cantidad=Integer.parseInt(sc.nextLine());
            this.cantidad[i]=cantidad;
           
        }
        
        escribeArchivo();
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
