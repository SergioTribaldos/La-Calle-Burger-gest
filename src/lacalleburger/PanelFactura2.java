package lacalleburger;

import javax.swing.JPanel;

import pedidos.Pedido;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import javax.swing.JScrollPane;



import javax.swing.JTextField;

import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class PanelFactura2 extends JPanel{
	
	private Pedido pedido;
	private Ventana ventana;
	ArrayList<JTextField> campos;
	ArrayList<JLabel> labels;
	
	public PanelFactura2(Pedido pedido,Ventana ventana) {
		this.pedido = pedido;
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Factura para: "+pedido.getUsuario().getRestaurante().getCodigoRestaurante());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnEnviar = new JButton("Enviar factura");
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEnviar.setMinimumSize(new Dimension(97, 43));
		add(btnEnviar, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		campos=new ArrayList<JTextField>();
		labels=new ArrayList<JLabel>();
		int e=13;
		int f=13;
		for(int i=0;i<pedido.getCantidad().length;i++) {
		
				
				if(i<=18) {
					campos.add(new JTextField());
					campos.get(campos.size()-1) .setBounds(12,e,169, 28);
					campos.get(campos.size()-1).setFont(new Font("Tahoma", Font.PLAIN, 16));
					
								
					labels.add(new JLabel(pedido.getCantidad()[i]+"      "+pedido.getListaProductos().get(i).getNombre()));
					labels.get(labels.size()-1).setBounds(193,e,300, 36);
					if(pedido.getCantidad()[i]!=0) {
					panel.add(campos.get(campos.size()-1));
					panel.add(labels.get(labels.size()-1));
					e+=38;
					}
					
				}
							
				if(i>18) {
					campos.add(new JTextField());
					campos.get(campos.size()-1).setBounds(491,f,169, 28);
					campos.get(campos.size()-1).setFont(new Font("Tahoma", Font.PLAIN, 16));
					
							
					labels.add(new JLabel(pedido.getCantidad()[i]+"       "+pedido.getListaProductos().get(i).getNombre()));
					labels.get(labels.size()-1).setBounds(672,f,300, 36);
					if(pedido.getCantidad()[i]!=0) {
						panel.add(campos.get(campos.size()-1));
						panel.add(labels.get(labels.size()-1));
						f+=38;
						}
					
				}
				
			}
			
		
		
		
		boolean[] pulsado= {false};
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pulsado[0]==false) {
					btnEnviar.setText("Pulsa de nuevo para generar la factura");
					pulsado[0]=true;
		
				}else {
				String[]lote=recogeLotes();
				pedido.setLote(lote);
				pedido.generaFactura();
				pedido.actualizaPedidoConLotesYPrecioTotal(ventana.getConexion());
				btnEnviar.setEnabled(false);
				btnEnviar.setText("La factura ha sido generada y guardada en la base de datos!");
			}
			}
		});
		
		
		
	
		
		
	}
	
	public String[] recogeLotes() {
		String[]lote=new String[pedido.getCantidad().length];
		
		for(int i=0;i<campos.size();i++) {
			lote[i]=campos.get(i).getText();
		}
		return lote;
	}
}
