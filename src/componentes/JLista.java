package componentes;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import excepciones.RestauranteException;
import lacalleburger.Ventana;
import panelAdministrador.PanelAdministrador;
import panelAdministrador.PanelRecibirPedidos;
import pedidos.Pedido;
import producto.Producto;
import restaurantes.Restaurante;
import usuarios.Usuario;


public class JLista extends JList {
	DefaultListModel<Pedido> listModelObjeto;
	ListCellRenderer renderJListCustom;
	Ventana ventana;
	PanelRecibirPedidos panelPadre;
	ArrayList<Producto> productos;
	Pedido pedido;

	
	public JLista(Ventana ventana,PanelRecibirPedidos panelPadre){
		
		this.ventana = ventana;
		this.panelPadre =panelPadre;
		listModelObjeto=new DefaultListModel<Pedido>();
		this.productos=Producto.recogeInformacionProductos(ventana.getConexion());	
		renderJListCustom = new JListRenderCustom(listModelObjeto);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			//Permite clickar la lista solo si hay pedidos
			if(JLista.this.isEnabled()) {
				listModelObjeto.get(JLista.this.getSelectedIndex());
				
				//Muestra si el pedido ha sido facturado o no, y habilita el boton de generar factura en caso de que si
				if(listModelObjeto.get(JLista.this.getSelectedIndex()).getFacturado()==1 ) {
					panelPadre.getEtiquetaPedidoRealizado().setText("El pedido ya ha sido facturado");
					panelPadre.getEtiquetaPedidoRealizado().setForeground(Color.RED);
					panelPadre.getBtnGenerarFactura().setEnabled(false);
					panelPadre.getBtnGenerarAlbaran().setEnabled(true);
				}else {
					panelPadre.getEtiquetaPedidoRealizado().setText("El pedido NO ha sido facturado");
					panelPadre.getEtiquetaPedidoRealizado().setForeground(new Color(35, 142, 67));
					panelPadre.getBtnGenerarFactura().setEnabled(true);
					panelPadre.getBtnGenerarAlbaran().setEnabled(false);
				}
								
							}				
			}
		});
		
		
		
	}
	
	
	
	
public DefaultListModel<Pedido> getListModelObjeto() {
		return listModelObjeto;
	}




	public ArrayList<Producto> getProductos() {
		return productos;
	}




	public Pedido getPedido() {
		return pedido;
	}




public void asignaPedidosAJList() throws RestauranteException {
		
		try {
			panelPadre.getEtiquetaPedidoRealizado().setText("");
			listModelObjeto.clear();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");			
			String fechaElegida=format.format(panelPadre.getCalendario().getDate());

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
				String[]lote=new String[productos.size()];
				int iterador=0;
				while(resultadoPedidosCompleto.next()) {					
					cantidad[iterador]=resultadoPedidosCompleto.getInt("cantidad");	
					lote[iterador]=resultadoPedidosCompleto.getString("lote");
					iterador++;

					if(iterador==37) {
						Restaurante restaurante=new Restaurante(resultadoPedidosCompleto.getString("cif"),
								                          resultadoPedidosCompleto.getString("direccion"),
								                          resultadoPedidosCompleto.getString("telefono"),
								                          resultadoPedidosCompleto.getString("codigoRestaurante"));
						Usuario usuario=new Usuario(resultadoPedidosCompleto.getString("usuario.nombre"),restaurante);
						
						pedido=new Pedido((short)resultadoPedidosCompleto.getInt("pedido.id"),
														resultadoPedidosCompleto.getTimestamp("Fecha"),
														productos,
														cantidad,
														lote,
														usuario,
														resultadoPedidosCompleto.getByte("facturado"));
						
						listModelObjeto.addElement(pedido);

						iterador=0;
						cantidad=new int[productos.size()];
						lote=new String[productos.size()];
						panelPadre.getBtnGuardarPedidos().setEnabled(true);
						panelPadre.getBtnGenerarFactura().setEnabled(true);
						
					}
			
				}

			stm.close();
			}else {
				listModelObjeto.addElement(null);
				panelPadre.getBtnGuardarPedidos().setEnabled(false);
				panelPadre.getBtnGenerarFactura().setEnabled(false);
			}

			this.setModel(listModelObjeto);
			
			this.setCellRenderer(renderJListCustom);
			

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		
		
	}
}
