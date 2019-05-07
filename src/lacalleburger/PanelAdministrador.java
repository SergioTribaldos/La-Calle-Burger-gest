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
import usuarios.Usuario;
import componentes.CampoTexto;
import componentes.JLista;
import excepciones.RestauranteException;

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
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;


public class PanelAdministrador extends JPanel{
	DefaultListModel<Pedido> listModelObjeto;
	ArrayList<Producto> productos;
	ArrayList<Pedido> listaObjetosSeleccionados;
	JCalendar calendario;
	Ventana ventana;
	private JButton btnGenerarFactura;
	private JList<Pedido> JListDePedidos;
	private JButton btnGuardarPedidos;
	private JTabbedPane tabbedPane;
	JLabel etiquetaPedidoRealizado;
	ListCellRenderer renderJList;
	private CampoTexto campoCodigoRestaurante;
	private CampoTexto campoDireccion;
	private CampoTexto campoCif;
	private CampoTexto campoTelefono;
	private JButton botonAltaRestaurante;
	private JLabel labelError;
	JPanel panelDatosRestaurante;
	

	public PanelAdministrador(Ventana ventan) {
		this.ventana = ventan;
		
		initComponents();

		listModelObjeto=new DefaultListModel<Pedido>();
		productos=Producto.recogeInformacionProductos(ventana.getConexion());
		renderJList = new JLista( listModelObjeto);
		
		
		
		btnGuardarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				guardaPedidosSeleccionadosEnArchivos();
				
			}	
		});
		
		
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] indicesSeleccionados=JListDePedidos.getSelectedIndices();
				for(int i=0;i<indicesSeleccionados.length;i++) {
					PanelFactura2 nuevo=new PanelFactura2(listModelObjeto.get(indicesSeleccionados[i]),ventana);
					Ventana ventanaFactura=new Ventana(nuevo,ventana.getConexion());
					ventanaFactura.setExtendedState(ventana.getExtendedState() | ventana.MAXIMIZED_BOTH);
					
				}

			}
				
		});
		
		JListDePedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			//Permite clickar la lista solo si hay pedidos
			if(JListDePedidos.isEnabled()) {
				listModelObjeto.get(JListDePedidos.getSelectedIndex());
				
				//Muestra si el pedido ha sido facturado o no, y habilita el boton de generar factura en caso de que si
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
			}
		});
		
		calendario.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Recoge los pedidos del dia seleccionado y los añade a la JList
				try {
					asignaPedidosAJList();
				} catch (RestauranteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		botonAltaRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Restaurante nuevo= new Restaurante(
							campoCif.getText(),			
							campoDireccion.getText(),			
							campoTelefono.getText(),
							campoCodigoRestaurante.getText()	
							);
					nuevo.insertarRestauranteEnBaseDeDatos(ventana);
				} catch (RestauranteException e) {	
					labelError.setText(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		
		panelDatosRestaurante.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				try {
					Thread.sleep(300);
					labelError.setText("");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		

	}

	private void asignaPedidosAJList() throws RestauranteException {
		
		try {
			etiquetaPedidoRealizado.setText("");
			listModelObjeto.clear();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");			
			String fechaElegida=format.format(calendario.getDate());

			Statement stm=ventana.getConexion().createStatement();
			ResultSet resultadoPedidosCompleto=stm.executeQuery("select pedido.*,usuario.nombre,productospedidos.*,restaurante.*\r\n" + 
					"					from productospedidos \r\n" + 
					"					join pedido  on pedido.id=productospedidos.pedido_id\r\n" + 
					"					join usuario on pedido.Usuario_usuario=usuario.nombre \r\n" + 
					"					join restaurante on usuario.Restaurante_codigoRestaurante=restaurante.codigoRestaurante\r\n" + 
					"					where DATE(pedido.fecha)='"+fechaElegida+"'\r\n" + 
					"					order by pedido_id desc, producto_id asc;");
								
			
			
			//En caso de que encuentra algun pedido en la base de datos, lo muestra. En caso contrario no.
			if(resultadoPedidosCompleto.next()==true) {
				resultadoPedidosCompleto.beforeFirst();
				int[] cantidad= new int[productos.size()];
				int iterador=0;
				while(resultadoPedidosCompleto.next()) {					
					cantidad[iterador]=resultadoPedidosCompleto.getInt("cantidad");						
					iterador++;

					if(iterador==37) {
						Restaurante restaurante=new Restaurante(resultadoPedidosCompleto.getString("cif"),
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
		//Coge los indices de los elementos seleccionados del JList y los mete en un array
		int[] indicesSeleccionados=JListDePedidos.getSelectedIndices();		
		
		///calcula la cantidad total del pedido seleccionado///			
		int[]cantidadTotal=new int[productos.size()];
		
		for(int i=0;i<indicesSeleccionados.length;i++) {
			for(int r=0;r<listModelObjeto.get(indicesSeleccionados[i]).getCantidad().length;r++) {						
				cantidadTotal[r]+=listModelObjeto.get(indicesSeleccionados[i]).getCantidad()[r];				
			}
		}	
		
		///escribe la cantidad total en un archivo y lo guarda en un archivo///
		Pedido pedidoTotal=new Pedido(productos,cantidadTotal);
		pedidoTotal.escribeArchivo();
		
		///escribe cada pedido seleccionado en un archivo
		for(int i=0;i<indicesSeleccionados.length;i++) {
			listModelObjeto.get(indicesSeleccionados[i]).escribeArchivo();
		}

		
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
		
		JPanel panelAltaRestaurante = new JPanel();
		
		tabbedPane.addTab("Nuevo Restaurante", null, panelAltaRestaurante, null);
		panelAltaRestaurante.setLayout(null);
		
		panelDatosRestaurante = new JPanel();
		
		panelDatosRestaurante.setBounds(22, 25, 465, 280);
		panelAltaRestaurante.add(panelDatosRestaurante);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{118, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatosRestaurante.setLayout(gridBagLayout);
		
		JLabel labelCodigoRestaurante = new JLabel("Codigo de Restaurante");
		labelCodigoRestaurante.setFont(new Font("SansSerif", Font.PLAIN, 21));
		GridBagConstraints gbc_labelCodigoRestaurante = new GridBagConstraints();
		gbc_labelCodigoRestaurante.anchor = GridBagConstraints.EAST;
		gbc_labelCodigoRestaurante.insets = new Insets(0, 0, 5, 5);
		gbc_labelCodigoRestaurante.gridx = 0;
		gbc_labelCodigoRestaurante.gridy = 0;
		panelDatosRestaurante.add(labelCodigoRestaurante, gbc_labelCodigoRestaurante);
		
		campoCodigoRestaurante = new CampoTexto();
		GridBagConstraints gbc_campoCodigoRestaurante = new GridBagConstraints();
		gbc_campoCodigoRestaurante.insets = new Insets(0, 0, 5, 0);
		gbc_campoCodigoRestaurante.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoCodigoRestaurante.gridx = 1;
		gbc_campoCodigoRestaurante.gridy = 0;
		panelDatosRestaurante.add(campoCodigoRestaurante, gbc_campoCodigoRestaurante);
		campoCodigoRestaurante.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("SansSerif", Font.PLAIN, 21));
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.EAST;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 1;
		panelDatosRestaurante.add(lblDireccion, gbc_lblDireccion);
		
		campoDireccion = new CampoTexto();
		GridBagConstraints gbc_campoDireccion = new GridBagConstraints();
		gbc_campoDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_campoDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoDireccion.gridx = 1;
		gbc_campoDireccion.gridy = 1;
		panelDatosRestaurante.add(campoDireccion, gbc_campoDireccion);
		campoDireccion.setColumns(10);
		
		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setFont(new Font("SansSerif", Font.PLAIN, 21));
		GridBagConstraints gbc_labelTelefono = new GridBagConstraints();
		gbc_labelTelefono.anchor = GridBagConstraints.EAST;
		gbc_labelTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_labelTelefono.gridx = 0;
		gbc_labelTelefono.gridy = 2;
		panelDatosRestaurante.add(labelTelefono, gbc_labelTelefono);
		
		campoTelefono = new CampoTexto();
		GridBagConstraints gbc_campoTelefono = new GridBagConstraints();
		gbc_campoTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_campoTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoTelefono.gridx = 1;
		gbc_campoTelefono.gridy = 2;
		panelDatosRestaurante.add(campoTelefono, gbc_campoTelefono);
		campoTelefono.setColumns(10);
		
		JLabel labelCif = new JLabel("CIF");
		labelCif.setFont(new Font("SansSerif", Font.PLAIN, 21));
		GridBagConstraints gbc_labelCif = new GridBagConstraints();
		gbc_labelCif.anchor = GridBagConstraints.EAST;
		gbc_labelCif.insets = new Insets(0, 0, 5, 5);
		gbc_labelCif.gridx = 0;
		gbc_labelCif.gridy = 3;
		panelDatosRestaurante.add(labelCif, gbc_labelCif);
		
		campoCif = new CampoTexto();
		campoCif.setText("");
		GridBagConstraints gbc_campoCif = new GridBagConstraints();
		gbc_campoCif.insets = new Insets(0, 0, 5, 0);
		gbc_campoCif.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoCif.gridx = 1;
		gbc_campoCif.gridy = 3;
		panelDatosRestaurante.add(campoCif, gbc_campoCif);
		campoCif.setColumns(10);
		
		botonAltaRestaurante = new JButton("Enviar datos");
		
		botonAltaRestaurante.setBounds(22, 423, 214, 33);
		panelAltaRestaurante.add(botonAltaRestaurante);
		
		labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelError.setBounds(32, 353, 606, 57);
		panelAltaRestaurante.add(labelError);
		
		
	}
}
