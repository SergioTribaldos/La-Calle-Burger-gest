package pedidos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import producto.Producto;
import restaurantes.Restaurante;
import usuarios.Usuario;

public class Pedido_info {
	private short id_pedido;
	private ArrayList<Producto> productos;
	private ArrayList<Integer> cantidad;
	private Restaurante restaurante;
	private Timestamp fecha;
	private Producto [] producto;
	private String [] lote;
	private int[]precio;
	private Pedido pedido;
	private String nombreUsuario;
	
	/*public Pedido_info(String[]productos,int[]cantidad,Timestamp fecha,Restaurante restaurante,int id_pedido,int[]precio) {
        this.id_pedido = id_pedido;
        this.productos=productos;
        this.cantidad = cantidad;
        this.fecha =fecha;
        this.restaurante = restaurante;
        this.producto=producto;
        this.lote = lote;
        this.precio = precio;
    }
	*/
	 public Pedido_info(short id_pedido,ArrayList<Producto> productos,ArrayList<Integer> cantidad,Timestamp fecha,Restaurante restaurante,String nombreUsuario) {
	    	this.id_pedido = id_pedido;
	        this.productos=productos;
	        this.cantidad = cantidad;
	        this.restaurante = restaurante;
	        this.nombreUsuario = nombreUsuario;
	        this.fecha = fecha;
	        
	    }


	
	public Pedido_info(ArrayList<Producto> productos,ArrayList<Integer> cantidad) {
		this.productos=productos;
		this.cantidad = cantidad;
	}
	
	public Pedido_info() {
		
	}
	

	
	

	public ArrayList<Producto> getProductos() {
		return productos;
	}



	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}



	public ArrayList<Integer> getCantidad() {
		return cantidad;
	}



	public void setCantidad(ArrayList<Integer> cantidad) {
		this.cantidad = cantidad;
	}



	public Producto[] getProducto() {
		return producto;
	}



	public void setProducto(Producto[] producto) {
		this.producto = producto;
	}



	public int[] getPrecio() {
		return precio;
	}



	public void setPrecio(int[] precio) {
		this.precio = precio;
	}



	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}



	public String getNombreUsuario() {
		return nombreUsuario;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public void setId_pedido(short id_pedido) {
		this.id_pedido = id_pedido;
	}



	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}


	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	public int getId_pedido() {
		return id_pedido;
	}


	public String[] getLote() {
		return lote;
	}


	public void setLote(String[] lote) {
		this.lote = lote;
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
            
           
            for(int i=0;i<this.productos.size();i++){
                log.append("<tr>");
                log.append("<td>"+this.productos.get(i).getNombre()+"</td><td>"+this.cantidad.get(i)+"</td>");
 
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
	
	public void generaFactura() {
	
		
		String fechaArchivo="";
		String nombreRestaurante="";
			
        FileWriter log=null;
        try {
        	
        	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm");
           	fechaArchivo = dateFormat.format(this.fecha);
           	nombreRestaurante=this.restaurante.getNombre();
           
            File logFile=new File("ALBARAN "+nombreRestaurante+" "+fechaArchivo+".html");
            log = new FileWriter(logFile.getAbsoluteFile(),false);
            
            
            
            
            log.append("<!DOCTYPE html>\r\n" + 
            		"<html lang=\"en\" dir=\"ltr\">\r\n" + 
            		"  <head>\r\n" + 
            		"    <meta charset=\"utf-8\">\r\n" + 
            		"    <title></title>\r\n" + 
            		"    <style type=\"text/css\">\r\n" + 
            		"    .tg  {border-collapse:collapse;border-spacing:0;}\r\n" + 
            		"    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\r\n" + 
            		"    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\r\n" + 
            		"    .tg .tg-hfk9{border-color:#000000;text-align:left;vertical-align:top}\r\n" + 
            		"    .tg .tg-p1nr{font-size:11px;border-color:inherit;text-align:left;vertical-align:top}\r\n" + 
            		"    .tg .tg-uys7{border-color:inherit;text-align:center}\r\n" + 
            		"    .tg .tg-3pun{font-size:12px;border-color:inherit;text-align:left}\r\n" + 
            		"    .tg .tg-gzo9{font-size:11px;border-color:inherit;text-align:center;vertical-align:top}\r\n" + 
            		"    .tg .tg-1plf{font-size:12px;border-color:inherit;text-align:center}\r\n" + 
            		"    .tg .tg-xldj{border-color:inherit;text-align:left}\r\n" + 
            		"    .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\r\n" + 
            		"    .tg .tg-73a0{font-size:12px;border-color:inherit;text-align:left;vertical-align:top}\r\n" + 
            		"    .tg .tg-73oq{border-color:#000000;text-align:left;vertical-align:top}\r\n" + 
            		"\r\n" + 
            		"    .tg .celda{font-size:11px;border-color:inherit;text-align:left;vertical-align:top;border-top:none;border-bottom: none;padding-top: 4px;padding-bottom: 4px;}\r\n" + 
            		"\r\n" + 
            		"    </style>\r\n" + 
            		"  </head>\r\n" + 
            		"  <body>\r\n" + 
            		"    <table class=\"tg\" style=\"undefined;table-layout: fixed; width: 770px\">\r\n" + 
            		"<colgroup>\r\n" + 
            		"<col style=\"width: 79px\">\r\n" + 
            		"<col style=\"width: 427px\">\r\n" + 
            		"<col style=\"width: 81px\">\r\n" + 
            		"<col style=\"width: 101px\">\r\n" + 
            		"<col style=\"width: 82px\">\r\n" + 
            		"</colgroup>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <th class=\"tg-1plf\" colspan=\"5\">LA CALLE BURGER, S.L. B93612109<br>C/ Escritora Carmen Martín Gaite Nº2 Local 6 C.P.29196 Málaga</th>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-uys7\" colspan=\"5\">ALBARÁN</td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-3pun\">PARA:</td>\r\n" + 
            		"    <td class=\"tg-xldj\">"+this.restaurante.getNombre()+"     "+this.restaurante.getCif()+"</td>\r\n" + 
            		"    <td class=\"tg-3pun\">FECHA:</td>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"2\">"+fechaArchivo+" </td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-73a0\">ENTREGA:</td>\r\n" + 
            		"    <td class=\"tg-0pky\">direccion</td>\r\n" + 
            		"    <td class=\"tg-73a0\">ALBARAN:</td>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"2\"></td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"5\"></td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-gzo9\">Nº LOTE</td>\r\n" + 
            		"    <td class=\"tg-gzo9\">DESCRIPCION DE PRODUCTO</td>\r\n" + 
            		"    <td class=\"tg-gzo9\">CANTIDAD</td>\r\n" + 
            		"    <td class=\"tg-gzo9\">PRECIO UNITARIO</td>\r\n" + 
            		"    <td class=\"tg-gzo9\">IMPORTE</td>\r\n" + 
            		"  </tr>\r\n" + 
            		"");
            
           int[]sumaCantidadYProducto=new int[37];
            for(int i=0;i<this.productos.size();i++){
            	if(this.cantidad.get(i)!=0) {
            		sumaCantidadYProducto[i]=(this.precio[i]*this.cantidad.get(i));
            		log.append("<tr>");
                    log.append("<td class=\"celda\">"+this.lote[i]+"</td>");
                    log.append("<td class=\"celda\">"+this.productos.get(i)+"</td>");
                    log.append("<td class=\"celda\">"+this.cantidad.get(i)+"</td>");
                    log.append("<td class=\"celda\">"+this.precio[i]+"</td>");
                    log.append("<td class=\"celda\">"+sumaCantidadYProducto[i]+"</td>");
            	}
        } 
            int sumaTotalSinIva=IntStream.of(sumaCantidadYProducto).sum();
            
            log.append("  <tr>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"4\">EXTRAS/ABONOS/DESCUENTOS</td>\r\n" + 
            		"    <td class=\"tg-0pky\"></td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-73oq\"></td>\r\n" + 
            		"    <td class=\"tg-73oq\"></td>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"2\">IMPORTE</td>\r\n" + 
            		"    <td class=\"tg-0pky\">"+sumaTotalSinIva+"</td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-73oq\"></td>\r\n" + 
            		"    <td class=\"tg-hfk9\"></td>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"2\">IVA                                        10%</td>\r\n" + 
            		"    <td class=\"tg-0pky\">"+((sumaTotalSinIva*1.10)-sumaTotalSinIva)+"</td>\r\n" + 
            		"  </tr>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <td class=\"tg-73oq\"></td>\r\n" + 
            		"    <td class=\"tg-73oq\"></td>\r\n" + 
            		"    <td class=\"tg-0pky\" colspan=\"2\">TOTAL</td>\r\n" + 
            		"    <td class=\"tg-0pky\">"+(sumaTotalSinIva*1.10)+"</td>\r\n" + 
            		"  </tr>\r\n" + 
            		"</table>\r\n" + 
            		"\r\n" + 
            		"  </body>\r\n" + 
            		"</html>");
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
