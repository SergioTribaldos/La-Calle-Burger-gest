package lacalleburger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToolBar;

import pedidos.Pedido;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import java.awt.Font;

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
		list.setBounds(259, 35, 630, 390);
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
					ArrayList<Pedido>pedidos=new ArrayList<Pedido>();
					int[]cantidad=new int[37];
					String[]productos=new String[37];
					int iterador=0;
					while(resultadoPedidosCompleto.next()) {
						int id=resultadoPedidosCompleto.getInt("id");
												
						cantidad[iterador]=resultadoPedidosCompleto.getInt("cantidad");
						productos[iterador]=resultadoPedidosCompleto.getString("nombre");
						iterador++;
						
						if(productos[36]!=null) {
							pedidos.add(new Pedido(productos,cantidad));
							iterador=0;
							cantidad=new int[37];
							productos=new String[37];
							
						}
						
						
						
					}
					System.out.print("g");
					
					ResultSet resultadoPedidos=stm.executeQuery("select pedido.*,usuario.*,restaurante.*\r\n" + 
							"from pedido\r\n" + 
							"join usuario on pedido.Usuario_usuario=usuario.nombre\r\n" + 
							"join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante;");
					ArrayList<String>listaSeleccionable=new ArrayList<String>();
					DefaultListModel listModel = new DefaultListModel();
					while(resultadoPedidos.next()) {
						String resultado="";
						resultado+=resultadoPedidos.getString("Fecha")+"      ";
						resultado+=resultadoPedidos.getString("codigoRestaurante");
						listaSeleccionable.add(resultado);
						listModel.addElement(resultado);

					}
					
					list.setModel(listModel);
					
					guardarPedidos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.out.println("fd");
							
							
							
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
}
