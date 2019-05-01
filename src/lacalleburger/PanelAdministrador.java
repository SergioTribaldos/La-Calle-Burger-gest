package lacalleburger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import pedidos.Pedido;
import pedidos.Pedido_info;
import producto.Producto;
import restaurantes.Restaurante;
import restaurantes.Restaurante.codRestaurante;
import usuarios.Usuario;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

public class PanelAdministrador extends JPanel{
	DefaultListModel<String> listModelString;
	DefaultListModel<Pedido_info> listModelObjeto;
	ArrayList<Producto> productos;
	Ventana ventana;
	
	public PanelAdministrador(Ventana ventan) {
		this.ventana = ventan;

		listModelString=new DefaultListModel();
		listModelObjeto=new DefaultListModel();
		setForeground(Color.ORANGE);
		setLayout(null);
		ventana.setSize(1200,800);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 672, 570);
		add(tabbedPane);
		ventana.setSize(tabbedPane.getSize());
		
		JPanel panelRecibirPedidos = new JPanel();
		panelRecibirPedidos.setBorder(null);
		tabbedPane.addTab("Recibir pedidos", null, panelRecibirPedidos, null);
		tabbedPane.setEnabledAt(0, true);
		panelRecibirPedidos.setLayout(null);
		
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 17));
		list.setBounds(257, 70, 350, 390);
		panelRecibirPedidos.add(list);
		
		JButton btnGuardarPedidos = new JButton("Guardar pedidos");
		btnGuardarPedidos.setBounds(35, 125, 202, 46);
		panelRecibirPedidos.add(btnGuardarPedidos);

		JButton btnRecibirPedidos = new JButton("Recibir pedidos");
		btnRecibirPedidos.setBounds(35, 61, 202, 46);
		panelRecibirPedidos.add(btnRecibirPedidos);
		
		JLabel lblNewLabel = new JLabel("       FECHA                   RESTAURANTE");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(257, 32, 350, 38);
		panelRecibirPedidos.add(lblNewLabel);
		
		JButton btnGenerarFactura = new JButton("Generar factura");		
		btnGenerarFactura.setBounds(35, 194, 202, 46);
		panelRecibirPedidos.add(btnGenerarFactura);
		
		btnRecibirPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stm=ventana.getConexion().createStatement();				
					ResultSet resultadoProductos=stm.executeQuery("select * from producto order by id asc");
					int precio;
					short id;
					String nombreProducto;
					productos=new ArrayList<Producto>();
					while(resultadoProductos.next()) {
						nombreProducto=resultadoProductos.getString("nombre");
						precio=resultadoProductos.getInt("precio");
						id=(short)resultadoProductos.getInt("id");
						productos.add(new Producto(id,nombreProducto,precio));
						
					}
					
					stm.close();
					resultadoProductos.close();
					stm=ventana.getConexion().createStatement();
					
					
					
					
					ResultSet resultadoPedidosCompleto=stm.executeQuery("select pedido.*,usuario.nombre,productospedidos.*,restaurante.*\r\n" + 
							"from productospedidos \r\n" + 
							"join pedido  on pedido.id=productospedidos.pedido_id\r\n" + 
							"join usuario on pedido.Usuario_usuario=usuario.nombre\r\n" + 
							"join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante\r\n" + 
							"order by pedido_id desc, producto_id asc;");
					//Crea un arraylist de tipo Pedido_info con todos los pedidos de la base de datos//						
					ArrayList<Integer> cantidad= new ArrayList<Integer>();
					
					Timestamp fecha=null;

				
					int iterador=0;
					while(resultadoPedidosCompleto.next()) {
						//int id=resultadoPedidosCompleto.getInt("id");							
						cantidad.add(iterador,resultadoPedidosCompleto.getInt("cantidad"));						
						iterador++;

						if(iterador==37) {
							String nombreUsuario=(resultadoPedidosCompleto.getString("usuario.nombre"));
							Restaurante restaurante=new Restaurante(resultadoPedidosCompleto.getString("cif"),
									                          resultadoPedidosCompleto.getString("restaurante.nombre"),
									                          resultadoPedidosCompleto.getString("direccion"),
									                          resultadoPedidosCompleto.getString("telefono"),
									                          resultadoPedidosCompleto.getString("codigoRestaurante"));
							
									
							fecha=resultadoPedidosCompleto.getTimestamp("Fecha");
							
							Pedido_info pedido= new Pedido_info((short)resultadoPedidosCompleto.getInt("pedido.id"),productos,cantidad,fecha,restaurante,nombreUsuario);
							listModelObjeto.addElement(pedido);
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy  HH:mm");
							listModelString.addElement(dateFormat.format(listModelObjeto.get(listModelObjeto.getSize()-1).getFecha())+"              "+listModelObjeto.get(listModelObjeto.getSize()-1).getRestaurante().getNombre());
							iterador=0;
							cantidad=new ArrayList<Integer>();
							
						}
				
					}

					list.setModel(listModelString);
					btnRecibirPedidos.setEnabled(false);

				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		
		
		ArrayList<Pedido_info> listaObjetosSeleccionados = new ArrayList<Pedido_info>();
		
		btnGuardarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//pasa los objetos seleccionados en un ArrayList
				guardarObjetosSeleccionados(list,listaObjetosSeleccionados);
				
				
				///calcula la cantidad total del pedido seleccionado///
				ArrayList<Integer> cantidadTotal= new ArrayList<Integer>();	
				Integer[]cantTot=new Integer[listaObjetosSeleccionados.get(0).getCantidad().size()];
				int suma=0;
				for(int i=0;i<listaObjetosSeleccionados.size();i++) {
					for(int r=0;r<listaObjetosSeleccionados.get(i).getCantidad().size();r++) {
						cantTot[r]+=listaObjetosSeleccionados.get(i).getCantidad().get(r);
				
					}
	
				}	
				
			
				
				for(int f=0;f<cantTot.length;f++) {
					cantidadTotal.add(cantTot[f]);
				}
				
				///escribe la cantidad total en un archivo///
				Pedido_info pedidoTotal=new Pedido_info(productos,cantidadTotal);
				pedidoTotal.escribeArchivo();
				
				///escribe cada pedido seleccionado en un archivo
				for(int i=0;i<listaObjetosSeleccionados.size();i++) {
					listaObjetosSeleccionados.get(i).escribeArchivo();
				}

			}	
		});
		
		
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarObjetosSeleccionados(list,listaObjetosSeleccionados);
				
				for(int f=0;f<listaObjetosSeleccionados.size();f++) {
					Ventana ventanaFactura=new Ventana(new PanelFactura(listaObjetosSeleccionados.get(f)),ventana.getConexion());
					
				}
				
				
				
			}
				
		});
		

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
	}
	
	public void guardarObjetosSeleccionados(JList list,ArrayList listaObjetosSeleccionados ) {
		
		int[] indicesSeleccionados=list.getSelectedIndices();
		List seleccionado =list.getSelectedValuesList();
		listaObjetosSeleccionados.clear();
									
		//listaObjetosSeleccionados.addAll(seleccionado);
		
		for(int i=0;i<indicesSeleccionados.length;i++) {
			listaObjetosSeleccionados.add(listModelObjeto.get(indicesSeleccionados[i]));
		}
		
	}
	
	private String convertirFecha(Timestamp r) {
		LocalDateTime y=r.toLocalDateTime();
		y.format(DateTimeFormatter.ofPattern("dd/MM/u-hh:mm"));
		return y.format(DateTimeFormatter.ofPattern("dd/MM/u-hh:mm"))+"    ";
		
	}
}
