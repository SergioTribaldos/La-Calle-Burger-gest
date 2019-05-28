package pedidos;

import java.io.BufferedWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import producto.Producto;
import usuarios.Usuario;

/**
 *
 * @author Sergio
 */
public class Pedido {
	private short id;
	private Usuario usuario;
	private ArrayList<Producto> listaProductos;
	private int[] cantidad;
	private Timestamp fechaPedido;
	private Connection conexion;
	private String[] lote;
	private byte facturado;
	double sumaTotalSinIva;

	public Pedido(Usuario usuario, Connection conexion, int[] cantidad) {

		this.fechaPedido = new Timestamp(System.currentTimeMillis());
		this.usuario = usuario;
		this.conexion = conexion;
		this.cantidad = cantidad;

	}

	public Pedido(short id, Timestamp fechaPedido, ArrayList<Producto> listaProductos, int[] cantidad, String[] lote,
			Usuario usuario, byte facturado) {
		this.id = id;
		this.fechaPedido = fechaPedido;
		this.usuario = usuario;
		this.listaProductos = listaProductos;
		this.cantidad = cantidad;
		this.usuario = usuario;
		this.facturado = facturado;
		this.lote = lote;
	}

	public Pedido(ArrayList<Producto> listaProductos, int[] cantidad) {
		this.listaProductos = listaProductos;
		this.cantidad = cantidad;
	}

	public byte getFacturado() {
		return facturado;
	}

	public void setFacturado(byte facturado) {
		this.facturado = facturado;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public int[] getCantidad() {
		return cantidad;
	}

	public void setCantidad(int[] cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public String[] getLote() {
		return lote;
	}

	public void setLote(String[] lote) {
		this.lote = lote;
	}
	/**
	 * Introduce los datos de pedido en las tablas "pedido" y "productospedidos" en la base de datos.
	 */
	public void nuevoPedido() {
		try {

			// Creamos el pedido
			Statement smt3 = conexion.createStatement();
			smt3.executeUpdate(
					"insert into pedido(fecha,usuario_Usuario) values(NOW(),'" + this.usuario.getUsuario() + "');");

			// Creamos un statement nuevo
			Statement smt2 = conexion.createStatement();

			// Cogemos el ultimo id del pedido (el que acabamos de insertar) para usarlo en
			// la tabla intermedia
			ResultSet resultado2 = smt2.executeQuery("select max(id) from pedido;");
			resultado2.next();
			int id_pedido = resultado2.getInt(1);

			//
			PreparedStatement smt = conexion
					.prepareStatement("insert into productospedidos(producto_id,pedido_id,cantidad) values (?,?,?)");
			ResultSet resultado3 = smt.executeQuery("select * from producto;");
			int iterador = 0;

			// Introducimos los datos en la tabla intermedia de producto y pedido, es decir
			// , el pedido en si
			while (resultado3.next()) {

				smt.setInt(1, resultado3.getInt("id"));
				smt.setInt(2, id_pedido);
				smt.setInt(3, cantidad[iterador]);
				smt.addBatch();
				iterador++;
			}
			smt.executeBatch();
			smt.close();
			smt2.close();
			smt3.close();

		} catch (SQLException ex) {
			Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();

		}

	}
	/**
	 * Escribe en un archivo de extension .html el pedido. Si detecta que no tiene fecha de pedido lo hace con el nombre de PEDIDO TOTAL.
	 */
	public void escribeArchivo() {
		String fechaArchivo = "";
		String nombreRestaurante = "";
		
		//Escribe como pedido total o como pedido individual.
		if (this.fechaPedido == null) {
			this.fechaPedido = new Timestamp(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm");
			fechaArchivo = dateFormat.format(this.fechaPedido);
			nombreRestaurante = "PEDIDO TOTAL";
		} else {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm");
			fechaArchivo = dateFormat.format(this.fechaPedido);
			nombreRestaurante = this.getUsuario().getRestaurante().getCodigoRestaurante();
		}

		BufferedWriter log = null;
		try {
			
			File logFile = new File(".\\#PEDIDOS\\" + nombreRestaurante + " " + fechaArchivo + ".html");
			//Cambia la codificacion para que escriba correctamente las tildes.
			log = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(
									".\\#PEDIDOS\\" + "PEDIDO " + nombreRestaurante + " " + fechaArchivo + ".html"),
							"UTF-8"));

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String fecha = dateFormat.format(this.fechaPedido);

			log.append("<!DOCTYPE html>\n" + "<html lang=\"en\" dir=\"ltr\">\n" + "  <head>\n"
					+ "    <meta charset=\"utf-8\">\n" + "    <title></title>\n" + "    <style>\n" + "    table{\n"
					+ "      height: 900px !important;\n" + "    }\n" + "    table, th, td {\n"
					+ "      border: 2px solid black;\n" + "      border-collapse: collapse;\n" + "      height: 5%;\n"
					+ "      }\n" + "      th:first-child{\n" + "        width: 500px;\n" + "      }\n"
					+ "      td:nth-child(2){\n" + "        font-weight: bolder;\n" + "        text-align: center;\n"
					+ "        font-size: 25px;\n" + "      }\n" + "      td:first-child{\n"
					+ "        padding-left: 15px;\n" + "      }\n" + "\n" + "    </style>\n" + "  </head>\n"
					+ "  <h1>Pedido de " + nombreRestaurante + " </h1>\n" + "  <h3>Fecha: " + fecha + " </h3>\n" + "\n"
					+ "  <body>\n" + "    <table>\n" + "      <tr>\n" + "        <th>HAMBURGUESAS</th>\n"
					+ "        <th>CANTIDAD</th>\n" + "      </tr>");

			for (int i = 0; i < this.listaProductos.size(); i++) {
				log.append("<tr>");
				log.append("<td>" + this.listaProductos.get(i).getNombre() + "</td><td>" + this.cantidad[i] + "</td>");

			}
			log.flush();
			log.close();

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
	/**
	 * Escribe en un archivo de extension .html la factura.
	 */
	public void generaFactura() {

		String fechaArchivo = "";
		String nombreRestaurante = "";
		BufferedWriter log = null;
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm");
			fechaArchivo = dateFormat.format(this.fechaPedido);
			nombreRestaurante = this.getUsuario().getRestaurante().getCodigoRestaurante();
			File logFile = new File(".\\#ALBARANES\\" + "ALBARAN " + nombreRestaurante + " " + fechaArchivo + ".html");
			log = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(
							".\\#ALBARANES\\" + "ALBARAN " + nombreRestaurante + " " + fechaArchivo + ".html"),
					"UTF-8"));
			// log = new FileWriter(logFile.getAbsoluteFile(),false);

			log.append("<!DOCTYPE html>\r\n" + "<html lang=\"en\" dir=\"ltr\">\r\n" + "  <head>\r\n"
					+ "    <meta charset=\"utf-8\">\r\n" + "    <title></title>\r\n"
					+ "    <style type=\"text/css\">\r\n" + "    .tg  {border-collapse:collapse;border-spacing:0;}\r\n"
					+ "    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\r\n"
					+ "    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}\r\n"
					+ "    .tg .tg-hfk9{border-color:#000000;text-align:left;vertical-align:top}\r\n"
					+ "    .tg .tg-p1nr{font-size:11px;border-color:inherit;text-align:left;vertical-align:top}\r\n"
					+ "    .tg .tg-uys7{border-color:inherit;text-align:center}\r\n"
					+ "    .tg .tg-3pun{font-size:12px;border-color:inherit;text-align:left}\r\n"
					+ "    .tg .tg-gzo9{font-size:11px;border-color:inherit;text-align:center;vertical-align:top}\r\n"
					+ "    .tg .tg-1plf{font-size:12px;border-color:inherit;text-align:center}\r\n"
					+ "    .tg .tg-xldj{border-color:inherit;text-align:left}\r\n"
					+ "    .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}\r\n"
					+ "    .tg .tg-73a0{font-size:12px;border-color:inherit;text-align:left;vertical-align:top}\r\n"
					+ "    .tg .tg-73oq{border-color:#000000;text-align:left;vertical-align:top}\r\n" + "\r\n"
					+ "    .tg .celda{font-size:11px;border-color:inherit;text-align:left;vertical-align:top;border-top:none;border-bottom: none;padding-top: 4px;padding-bottom: 4px;}\r\n"
					+ "\r\n" + "    </style>\r\n" + "  </head>\r\n" + "  <body>\r\n"
					+ "    <table class=\"tg\" style=\"undefined;table-layout: fixed; width: 770px\">\r\n"
					+ "<colgroup>\r\n" + "<col style=\"width: 79px\">\r\n" + "<col style=\"width: 427px\">\r\n"
					+ "<col style=\"width: 81px\">\r\n" + "<col style=\"width: 101px\">\r\n"
					+ "<col style=\"width: 82px\">\r\n" + "</colgroup>\r\n" + "  <tr>\r\n"
					+ "    <th class=\"tg-1plf\" colspan=\"5\">LA CALLE BURGER, S.L. B93612109<br>C/ Escritora Carmen Martín Gaite Nº2 Local 6 C.P.29196 Málaga</th>\r\n"
					+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td class=\"tg-uys7\" colspan=\"5\">ALBARÁN</td>\r\n"
					+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td class=\"tg-3pun\">PARA:</td>\r\n"
					+ "    <td class=\"tg-xldj\">" + this.getUsuario().getRestaurante().getCodigoRestaurante() + "     "
					+ this.getUsuario().getRestaurante().getCif() + "</td>\r\n"
					+ "    <td class=\"tg-3pun\">FECHA:</td>\r\n" + "    <td class=\"tg-0pky\" colspan=\"2\">"
					+ fechaArchivo + " </td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
					+ "    <td class=\"tg-73a0\">ENTREGA:</td>\r\n" + "    <td class=\"tg-0pky\">"
					+ this.getUsuario().getRestaurante().getDireccion() + "</td>\r\n"
					+ "    <td class=\"tg-73a0\">ALBARAN:</td>\r\n"
					+ "    <td class=\"tg-0pky\" colspan=\"2\"></td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
					+ "    <td class=\"tg-0pky\" colspan=\"5\"></td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
					+ "    <td class=\"tg-gzo9\">Nº LOTE</td>\r\n"
					+ "    <td class=\"tg-gzo9\">DESCRIPCION DE PRODUCTO</td>\r\n"
					+ "    <td class=\"tg-gzo9\">CANTIDAD</td>\r\n"
					+ "    <td class=\"tg-gzo9\">PRECIO UNITARIO</td>\r\n"
					+ "    <td class=\"tg-gzo9\">IMPORTE</td>\r\n" + "  </tr>\r\n" + "");
			
			double[] sumaCantidadYProducto = new double[listaProductos.size()];
			int filasIntroducidas = 0;
			
			for (int i = 0; i < this.listaProductos.size(); i++) {
				if (this.cantidad[i] != 0) {
					//Suma el precio por la cantidad correspondiente
					sumaCantidadYProducto[i] = (this.listaProductos.get(i).getPrecio() * this.cantidad[i]);
					log.append("<tr>");
					log.append("<td class=\"celda\">" + this.lote[i] + "</td>");
					log.append("<td class=\"celda\">" + this.listaProductos.get(i).getNombre() + "</td>");
					log.append("<td class=\"celda\">" + this.cantidad[i] + "</td>");
					log.append("<td class=\"celda\">" + this.listaProductos.get(i).getPrecio() + "</td>");
					log.append("<td class=\"celda\">" + sumaCantidadYProducto[i] + "</td>");
					filasIntroducidas++;
				}

			}
			// Genera tantos espacios vacios como filas no se han introducido, para que
			// todos los albaranes tengan el mismo formato
			for (int e = 0; e < this.cantidad.length - filasIntroducidas; e++) {
				log.append("<tr>");
				log.append("<td class=\"celda\"></td>");
				log.append("<td class=\"celda\"></td>");
				log.append("<td class=\"celda\"></td>");
				log.append("<td class=\"celda\"></td>");
				log.append("<td class=\"celda\"></td>");
			}
			
			//Suma el total del pedido en una variable
			sumaTotalSinIva = DoubleStream.of(sumaCantidadYProducto).sum();
			DecimalFormat sumaSinIva = new DecimalFormat("###.###");

			log.append("  <tr>\r\n" + "    <td class=\"tg-0pky\" colspan=\"4\">EXTRAS/ABONOS/DESCUENTOS</td>\r\n"
					+ "    <td class=\"tg-0pky\"></td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
					+ "    <td class=\"tg-73oq\"></td>\r\n" + "    <td class=\"tg-73oq\"></td>\r\n"
					+ "    <td class=\"tg-0pky\" colspan=\"2\">IMPORTE</td>\r\n" + "    <td class=\"tg-0pky\">"
					+ sumaTotalSinIva + "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
					+ "    <td class=\"tg-73oq\"></td>\r\n" + "    <td class=\"tg-hfk9\"></td>\r\n"
					+ "    <td class=\"tg-0pky\" colspan=\"2\">IVA                                        10%</td>\r\n"
					+ "    <td class=\"tg-0pky\">" + sumaSinIva.format(((sumaTotalSinIva * 1.10) - sumaTotalSinIva))
					+ "</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n" + "    <td class=\"tg-73oq\"></td>\r\n"
					+ "    <td class=\"tg-73oq\"></td>\r\n" + "    <td class=\"tg-0pky\" colspan=\"2\">TOTAL</td>\r\n"
					+ "    <td class=\"tg-0pky\">" + sumaSinIva.format((sumaTotalSinIva * 1.10)) + "</td>\r\n"
					+ "  </tr>\r\n" + "</table>\r\n" + "\r\n" + "  </body>\r\n" + "</html>");
			log.flush();
			log.close();

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

	public void actualizaPedidoConLotesYPrecioTotal(Connection conexion) {
		this.conexion = conexion;
		try {
			PreparedStatement smt = this.getConexion().prepareStatement(
					"update productospedidos set lote=(?) where pedido_id=" + this.id + " and producto_id=?;");

			for (int i = 0; i < this.getListaProductos().size(); i++) {

				smt.setString(1, this.getLote()[i]);
				smt.setInt(2, this.listaProductos.get(i).getId());
				smt.addBatch();
			}
			smt.executeBatch();

			smt = this.getConexion().prepareStatement("update pedido set facturado=true where id=" + this.id + ";");
			smt.executeUpdate();

			smt = this.getConexion().prepareStatement(
					"update pedido set totalSinIva=" + sumaTotalSinIva + " where id=" + this.id + ";");
			smt.executeUpdate();

			smt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
