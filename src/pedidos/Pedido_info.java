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
import restaurantes.Restaurante;

public class Pedido_info {
	private String[]productos;
	private int[] cantidad;
	private Restaurante restaurante;
	private Timestamp fecha;
	private Producto producto;
	
	public Pedido_info(String[]productos,int[]cantidad,Timestamp fecha,Restaurante restaurante) {
        
        this.productos=productos;
        this.cantidad = cantidad;
        this.fecha =fecha;
        this.restaurante = restaurante;
        this.producto=producto;
    }
	
public Pedido_info(String[]productos,int[]cantidad) {
        
        this.productos=productos;
        this.cantidad = cantidad;
       
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

	

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
	public void escribeArchivo(){
		String fechaArchivo="";
		String nombreRestaurante="";
		
		if(this.fecha==null) {
			this.fecha=new Timestamp(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm");
	        fechaArchivo = dateFormat.format(this.fecha);
			nombreRestaurante="PEDIDO TOTAL";
		}else {
		  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm");
       	  fechaArchivo = dateFormat.format(this.fecha);
       	  nombreRestaurante=this.restaurante.getNombre();
		}
				
        FileWriter log=null;
        try {
        	
           
            File logFile=new File(nombreRestaurante+" "+fechaArchivo+".html");
            log = new FileWriter(logFile.getAbsoluteFile(),false);
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fecha=dateFormat.format(this.fecha);
            
            
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
"  <h1>Pedido de "+nombreRestaurante+" </h1>\n" +
"  <h3>Fecha: "+fecha+" </h3>\n" +
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
