package pedidos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import producto.Producto;

public class Pedido_info {
	private String[]productos;
	private int[] cantidad;
	private String nombre_restaurante;
	private Timestamp fecha;
	private Producto producto;
	
	public Pedido_info(String[]productos,int[]cantidad,Timestamp fecha,String nombre_restaurante) {
        
        this.productos=productos;
        this.cantidad = cantidad;
        this.fecha =fecha;
        this.nombre_restaurante=nombre_restaurante;
        this.producto=producto;
    }
	
	public Pedido_info() {
		
	}
	

	public String[] getProductos() {
		return productos;
	}

	public void setProductos(String[] productos) {
		this.productos = productos;
	}

	public int[] getCantidad() {
		return cantidad;
	}

	public void setCantidad(int[] cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre_restaurante() {
		return nombre_restaurante;
	}

	public void setNombre_restaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
	public void escribeArchivo(){
        FileWriter log=null;
        try {
        	 DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH_mm");
        	  String fecha = dateFormat.format(this.fecha);
           
            File logFile=new File("./"+this.nombre_restaurante+" "+fecha +".html");
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
            
           
            for(int i=0;i<this.productos.length;i++){
                log.append("<tr>");
                log.append("<td>"+this.productos[i]+"</td><td>"+this.cantidad[i]+"</td>");
 
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
