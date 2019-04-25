package lacalleburger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToolBar;

import pedidos.Pedido;
import pedidos.Pedido_info;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

public class PanelAdministrador extends JPanel{
	
	public PanelAdministrador(JFrame ventana,Connection conexion) {
		setForeground(Color.ORANGE);
		setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 977, 570);
		add(tabbedPane);
		ventana.setSize(tabbedPane.getSize());
		
		JPanel panelRecibirPedidos = new JPanel();
		tabbedPane.addTab("Recibir pedidos", null, panelRecibirPedidos, null);
		tabbedPane.setEnabledAt(0, true);
		panelRecibirPedidos.setLayout(null);
		
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 17));
		list.setBounds(259, 35, 489, 390);
		panelRecibirPedidos.add(list);
		
		JButton guardarPedidos = new JButton("Guardar pedidos");
		guardarPedidos.setBounds(35, 157, 202, 46);
		panelRecibirPedidos.add(guardarPedidos);

		JButton btnRecibirPedidos = new JButton("Recibir pedidos");
		btnRecibirPedidos.setBounds(35, 61, 202, 46);
		panelRecibirPedidos.add(btnRecibirPedidos);
		btnRecibirPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stm=conexion.createStatement();
					ResultSet resultadoPedidosCompleto=stm.executeQuery("select pedido.*,usuario.Restaurante_codigoRestaurante,productospedidos.*,producto.*\r\n" + 
							"from producto\r\n" + 
							"join productospedidos on producto.id=productospedidos.producto_id\r\n" + 
							"join pedido on pedido.id=productospedidos.pedido_id\r\n" + 
							"join usuario on pedido.Usuario_usuario=usuario.nombre;");
					ArrayList<Pedido_info>pedidos=new ArrayList<Pedido_info>();					
					int[]cantidad=new int[37];
					String[]productos=new String[37];
					Timestamp fecha=null;
					String nombre_restaurante;
					int iterador=0;
					while(resultadoPedidosCompleto.next()) {
						int id=resultadoPedidosCompleto.getInt("id");
						fecha=resultadoPedidosCompleto.getTimestamp("Fecha");	
						nombre_restaurante=resultadoPedidosCompleto.getString("Restaurante_codigoRestaurante");
						cantidad[iterador]=resultadoPedidosCompleto.getInt("cantidad");
						productos[iterador]=resultadoPedidosCompleto.getString("nombre");
						iterador++;
						
						if(productos[36]!=null) {
							pedidos.add(new Pedido_info(productos,cantidad,fecha,nombre_restaurante));
							iterador=0;
							cantidad=new int[37];
							productos=new String[37];
							
						}
						
						
						
					}
					
					
					ResultSet resultadoPedidos=stm.executeQuery("select pedido.*,usuario.*,restaurante.*\r\n" + 
							"from pedido\r\n" + 
							"join usuario on pedido.Usuario_usuario=usuario.nombre\r\n" + 
							"join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante;");
					ArrayList<String>listaSeleccionable=new ArrayList<String>();
					DefaultListModel listModel = new DefaultListModel();
					while(resultadoPedidos.next()) {
						String resultado="";
						fecha=Timestamp.valueOf(resultadoPedidos.getString("Fecha"));
						resultado+=convertirFecha(fecha);

						//resultado+=resultadoPedidos.getString("Fecha")+"      ";
						resultado+=resultadoPedidos.getString("codigoRestaurante");
						listaSeleccionable.add(resultado);
					}
					
					
					for(int i=0;i<pedidos.size();i++) {
						listModel.addElement(pedidos.get(i)); 
						
					}
					
					
					list.setModel(listModel);

					list.addListSelectionListener(new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {						
							
							if (!e.getValueIsAdjusting()) {
								
			                }
							
							
						}
						
					});
					
					

					guardarPedidos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							List seleccionado =list.getSelectedValuesList();
							ArrayList<Pedido_info> lista = new ArrayList<Pedido_info>();
							Pedido_info listaTotal = new Pedido_info();							
							lista.addAll(seleccionado);
							
							///calcula la cantidad total del pedido seleccionado///
							int []cantidadTotal=new int[37];
							
							String []listaProductos=lista.get(0).getProductos();
							
							for(int i=0;i<lista.size();i++) {
								for(int r=0;r<lista.get(i).getCantidad().length;r++) {
									
									cantidadTotal[r]+=lista.get(i).getCantidad()[r];
								}

							}
							
							for(int i=0;i<lista.size();i++) {
								lista.get(i).escribeArchivo();
							}

	
						}
	
					
					});
					
					
					
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
	}
	
	private String convertirFecha(Timestamp r) {
		LocalDateTime y=r.toLocalDateTime();
		y.format(DateTimeFormatter.ofPattern("dd/MM/u-hh:mm"));
		return y.format(DateTimeFormatter.ofPattern("dd/MM/u-hh:mm"))+"    ";
		
	}
}
