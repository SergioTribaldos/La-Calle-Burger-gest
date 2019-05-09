package panelAdministrador;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

import componentes.JLista;
import excepciones.RestauranteException;
import lacalleburger.PanelFactura;
import lacalleburger.Ventana;
import pedidos.Pedido;

public class PanelRecibirPedidos extends JPanel{
	private JLista JListDePedidos;
	private JButton btnGenerarFactura;
	private JButton btnGenerarAlbaran;
	private JButton btnGuardarPedidos;
	private JCalendar calendario;
	private JLabel etiquetaPedidoRealizado;
	private Ventana ventana;
	
	public PanelRecibirPedidos(Ventana ventana) {
		
		this.setLayout(null);
		this.ventana = ventana;
		initComponents();
		
		calendario.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Recoge los pedidos del dia seleccionado y los añade a la JList
				try {
					JListDePedidos.asignaPedidosAJList();
					Pedido pedido=new Pedido(null, null);
				} catch (RestauranteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnGenerarAlbaran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				
				guardaAlbaranes();
				
			}
		});
		
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] indicesSeleccionados=JListDePedidos.getSelectedIndices();
				for(int i=0;i<indicesSeleccionados.length;i++) {
					
					PanelFactura nuevo=new PanelFactura(JListDePedidos.getListModelObjeto().get(indicesSeleccionados[i]),ventana);
					Ventana ventanaFactura=new Ventana(nuevo,ventana.getConexion());
					nuevo.setVentana(ventanaFactura);
					ventanaFactura.setExtendedState(ventana.getExtendedState() | ventana.MAXIMIZED_BOTH);
					
				}
				

			}
				
		});
		
		btnGuardarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				guardaPedidosSeleccionadosEnArchivos();
				
			}	
		});
		
	
		
	}
	
	public JLista getJListDePedidos() {
		return JListDePedidos;
	}

	public JButton getBtnGenerarFactura() {
		return btnGenerarFactura;
	}

	public JButton getBtnGenerarAlbaran() {
		return btnGenerarAlbaran;
	}

	public JButton getBtnGuardarPedidos() {
		return btnGuardarPedidos;
	}

	public JCalendar getCalendario() {
		return calendario;
	}

	public JLabel getEtiquetaPedidoRealizado() {
		return etiquetaPedidoRealizado;
	}
	
	
	
	private void guardaPedidosSeleccionadosEnArchivos() {
		//Coge los indices de los elementos seleccionados del JList y los mete en un array
		int[] indicesSeleccionados=JListDePedidos.getSelectedIndices();		
		
		///calcula la cantidad total del pedido seleccionado///			
		int[]cantidadTotal=new int[JListDePedidos.getProductos().size()];
		
		for(int i=0;i<indicesSeleccionados.length;i++) {
			for(int r=0;r<JListDePedidos.getListModelObjeto().get(indicesSeleccionados[i]).getCantidad().length;r++) {						
				cantidadTotal[r]+=JListDePedidos.getListModelObjeto().get(indicesSeleccionados[i]).getCantidad()[r];				
			}
		}	
		
		///escribe la cantidad total en un archivo y lo guarda en un archivo///
		Pedido pedidoTotal=new Pedido(JListDePedidos.getProductos(),cantidadTotal);
		pedidoTotal.escribeArchivo();
		
		///escribe cada pedido seleccionado en un archivo
		for(int i=0;i<indicesSeleccionados.length;i++) {
			JListDePedidos.getListModelObjeto().get(indicesSeleccionados[i]).escribeArchivo();
		}

		
	}
	
	private void guardaAlbaranes() {
			
			//Coge los indices de los elementos seleccionados del JList y los mete en un array
					int[] indicesSeleccionados=JListDePedidos.getSelectedIndices();		
					
					///escribe cada pedido seleccionado en un archivo
					for(int i=0;i<indicesSeleccionados.length;i++) {
						JListDePedidos.getListModelObjeto().get(indicesSeleccionados[i]).generaFactura();
					}
			
		}	

	public void initComponents() {
		btnGenerarFactura = new JButton("Generar factura");		
		btnGenerarFactura.setBounds(33, 367, 202, 46);
		this.add(btnGenerarFactura);
		
		calendario = new JCalendar();
		calendario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendario.setBorder(null);
		calendario.setBounds(33, 70, 202, 153);
		calendario.setDate(Date.valueOf(LocalDate.now()));
		this.add(calendario);
		
		etiquetaPedidoRealizado = new JLabel();
		etiquetaPedidoRealizado.setBounds(253, 471, 344, 29);
		this.add(etiquetaPedidoRealizado);
		
		btnGenerarAlbaran = new JButton("Generar albar\u00E1n");	
		btnGenerarAlbaran.setBounds(33, 435, 202, 46);
		this.add(btnGenerarAlbaran);
		
		JListDePedidos = new JLista(ventana,this);		
		JListDePedidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JListDePedidos.setBounds(257, 70, 350, 390);
		this.add(JListDePedidos);
		
		btnGuardarPedidos = new JButton("Guardar pedidos");
		btnGuardarPedidos.setBounds(33, 298, 202, 46);
		this.add(btnGuardarPedidos);
		
		JLabel lblNewLabel = new JLabel("       FECHA                   RESTAURANTE");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(257, 32, 350, 38);
		this.add(lblNewLabel);
		
		
		
		
		
		
	}

}
