package lacalleburger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import pedidos.Pedido;
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
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.toedter.calendar.JCalendar;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;

public class PanelAdministrador extends JPanel{
	DefaultListModel<Pedido> listModelObjeto;
	ArrayList<Producto> productos;
	ArrayList<Pedido> listaObjetosSeleccionados;
	JCalendar calendario;
	Ventana ventana;
	private JButton btnGenerarFactura;
	private JList JListDePedidos;
	private JButton btnGuardarPedidos;
	private JTabbedPane tabbedPane;
	JLabel etiquetaPedidoRealizado;
	ListCellRenderer renderJList;
	

	public PanelAdministrador(Ventana ventan) {
		this.ventana = ventan;
		
		initComponents();

		listModelObjeto=new DefaultListModel();
		productos=Producto.recogeInformacionProductos(ventana.getConexion());
		renderJList = new JLista( listModelObjeto);
		
		
		
		btnGuardarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardaPedidosSeleccionadosEnArchivos();
				
			}	
		});
		
		
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pasaObjetosSeleccionadosAUnArrayList(JListDePedidos);
				
				for(int f=0;f<listaObjetosSeleccionados.size();f++) {
					PanelFactura nuevo=new PanelFactura(listaObjetosSeleccionados.get(f),ventana);
					Ventana ventanaFactura=new Ventana(nuevo,ventana.getConexion());				
				}

			}
				
		});
		
		JListDePedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int r=JListDePedidos.getSelectedIndex();
				listModelObjeto.get(JListDePedidos.getSelectedIndex()) ;
				if(listModelObjeto.get(JListDePedidos.getSelectedIndex()).getFacturado()==1 ) {
					etiquetaPedidoRealizado.setText("El pedido ya ha sido facturado");
					etiquetaPedidoRealizado.setForeground(Color.RED);
					btnGenerarFactura.setEnabled(false);
				}else {
					etiquetaPedidoRealizado.setText("El pedido NO ha sido facturado");
					etiquetaPedidoRealizado.setForeground(new Color(35, 142, 67));
					btnGenerarFactura.setEnabled(true);
				}
			}
		});
		
		calendario.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				asignaPedidosAJList();
			}
		});
		
		
		
		
	}
	
	public void pasaObjetosSeleccionadosAUnArrayList(JList list ) {
		
		int[] indicesSeleccionados=list.getSelectedIndices();
		listaObjetosSeleccionados = new ArrayList<Pedido>();								
		//listaObjetosSeleccionados.clear();
		for(int i=0;i<indicesSeleccionados.length;i++) {
			listaObjetosSeleccionados.add(listModelObjeto.get(indicesSeleccionados[i]));
		}
		
	}
	
	private void asignaPedidosAJList() {
		
		try {
			etiquetaPedidoRealizado.setText("");
			listModelObjeto.clear();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");			
			String fechaElegida=format.format(calendario.getDate());

			
			Statement smt=ventana.getConexion().createStatement();
			ResultSet res=smt.executeQuery("select DATE(fecha),Usuario_usuario,id,facturado from pedido where DATE(fecha)='"+fechaElegida+"' order by id desc;");
			res.last();
			int[] idPedidoSeleccionado=new int [res.getRow()];
			res.beforeFirst();
			int iterador2=0;
			while(res.next()) {
				idPedidoSeleccionado[iterador2]=res.getInt("id");
				iterador2++;
				
			}
	
			if(idPedidoSeleccionado.length>0) {

			Statement stm=ventana.getConexion().createStatement();
			ResultSet resultadoPedidosCompleto=stm.executeQuery("select pedido.*,usuario.nombre,productospedidos.*,restaurante.*\r\n" + 
					"from productospedidos \r\n" + 
					"join pedido  on pedido.id=productospedidos.pedido_id\r\n" + 
					"join usuario on pedido.Usuario_usuario=usuario.nombre\r\n" + 
					"join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante\r\n"
					+ "  where pedido.id<="+idPedidoSeleccionado[0]+" and pedido.id>="+idPedidoSeleccionado[idPedidoSeleccionado.length-1]+" " + 
					"order by pedido_id desc, producto_id asc;");
								
			int[] cantidad= new int[productos.size()];
			

		
			int iterador=0;
			while(resultadoPedidosCompleto.next()) {					
				cantidad[iterador]=resultadoPedidosCompleto.getInt("cantidad");						
				iterador++;

				if(iterador==37) {

					Restaurante restaurante=new Restaurante(resultadoPedidosCompleto.getString("cif"),
							                          resultadoPedidosCompleto.getString("restaurante.nombre"),
							                          resultadoPedidosCompleto.getString("direccion"),
							                          resultadoPedidosCompleto.getString("telefono"),
							                          resultadoPedidosCompleto.getString("codigoRestaurante"));
					Usuario usuario=new Usuario(resultadoPedidosCompleto.getString("usuario.nombre"),restaurante);

					
					Pedido pedido=new Pedido((short)resultadoPedidosCompleto.getInt("pedido.id"),
													resultadoPedidosCompleto.getTimestamp("Fecha"),
													productos,
													cantidad,
													usuario,
													resultadoPedidosCompleto.getByte("facturado"));
					
					listModelObjeto.addElement(pedido);

					iterador=0;
					cantidad=new int[productos.size()];
					btnGuardarPedidos.setEnabled(true);
					btnGenerarFactura.setEnabled(true);
					
				}
		
			}
			smt.close();
			stm.close();
			}else {
				listModelObjeto.addElement(null);
				btnGuardarPedidos.setEnabled(false);
				btnGenerarFactura.setEnabled(false);
			}

			JListDePedidos.setModel(listModelObjeto);
			
			JListDePedidos.setCellRenderer(renderJList);
			

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}
		
	private void guardaPedidosSeleccionadosEnArchivos() {
		
		
		
		//pasa los objetos seleccionados en un ArrayList
		pasaObjetosSeleccionadosAUnArrayList(JListDePedidos);
		
		
		///calcula la cantidad total del pedido seleccionado///
			
		int[]cantidadTotal=new int[productos.size()];
		int suma=0;
		
		for(int i=0;i<listaObjetosSeleccionados.size();i++) {
			for(int r=0;r<listaObjetosSeleccionados.get(i).getCantidad().length;r++) {						
				cantidadTotal[r]+=listaObjetosSeleccionados.get(i).getCantidad()[r];				
			}
		}	
		
		///escribe la cantidad total en un archivo///
		Pedido pedidoTotal=new Pedido(productos,cantidadTotal);
		pedidoTotal.escribeArchivo();
		
		///escribe cada pedido seleccionado en un archivo
		for(int i=0;i<listaObjetosSeleccionados.size();i++) {
			listaObjetosSeleccionados.get(i).escribeArchivo();
		}

		
	}
	
	private String convertirFecha(Timestamp r) {
		LocalDateTime y=r.toLocalDateTime();
		y.format(DateTimeFormatter.ofPattern("dd/MM/u-hh:mm"));
		return y.format(DateTimeFormatter.ofPattern("dd/MM/u-hh:mm"))+"    ";
		
	}
	
	public void initComponents() {
		
		setForeground(Color.ORANGE);
		setLayout(null);
		ventana.setSize(1200,800);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 672, 570);
		add(tabbedPane);
		ventana.setSize(tabbedPane.getSize());
		
		JPanel panelRecibirPedidos = new JPanel();
		tabbedPane.addTab("Recibir pedidos", null, panelRecibirPedidos, null);
		tabbedPane.setEnabledAt(0, true);
		panelRecibirPedidos.setLayout(null);
		
		
		JListDePedidos = new JList();		
		JListDePedidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JListDePedidos.setBounds(257, 70, 350, 390);
		panelRecibirPedidos.add(JListDePedidos);
		
		btnGuardarPedidos = new JButton("Guardar pedidos");
		btnGuardarPedidos.setBounds(33, 298, 202, 46);
		panelRecibirPedidos.add(btnGuardarPedidos);
		
		JLabel lblNewLabel = new JLabel("       FECHA                   RESTAURANTE");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(257, 32, 350, 38);
		panelRecibirPedidos.add(lblNewLabel);
		
		btnGenerarFactura = new JButton("Generar factura");		
		btnGenerarFactura.setBounds(33, 367, 202, 46);
		panelRecibirPedidos.add(btnGenerarFactura);
		
		calendario = new JCalendar();
		calendario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendario.setBorder(null);
		calendario.setBounds(33, 70, 202, 153);
		calendario.setDate(Date.valueOf(LocalDate.now()));
		panelRecibirPedidos.add(calendario);
		
		etiquetaPedidoRealizado = new JLabel();
		etiquetaPedidoRealizado.setBounds(253, 471, 344, 29);
		panelRecibirPedidos.add(etiquetaPedidoRealizado);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		
	}
	
}
