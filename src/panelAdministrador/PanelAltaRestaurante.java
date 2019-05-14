package panelAdministrador;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import componentes.CampoTexto;
import excepciones.RestauranteException;
import lacalleburger.Ventana;
import restaurantes.Restaurante;

public class PanelAltaRestaurante extends JPanel{
	private CampoTexto campoCodigoRestaurante;
	private CampoTexto campoDireccion;
	private CampoTexto campoCif;
	private CampoTexto campoTelefono;
	private JButton botonAltaRestaurante;	
	private Ventana ventana;
	private JLabel labelError;
	
	public PanelAltaRestaurante(Ventana ventana) {
		this.setLayout(null);
		this.ventana=ventana;
		
		initComponents();
		
		boolean[] pulsado = {false};
		botonAltaRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						if(pulsado[0]==false) {
							botonAltaRestaurante.setText("Pulsa de nuevo para dar de alta al restaurante");
							pulsado[0]=true;				
						}else {
							try {
								Restaurante nuevo= new Restaurante(
										campoCif.getText(),			
										campoDireccion.getText(),			
										campoTelefono.getText(),
										campoCodigoRestaurante.getText()	
										);
								nuevo.insertarRestauranteEnBaseDeDatos(ventana);
								botonAltaRestaurante.setText("El restaurante ha sido dado de alta");
								botonAltaRestaurante.setEnabled(false);
							} catch (RestauranteException e) {	
								labelError.setText(e.getMessage());
								botonAltaRestaurante.setText("Enviar datos");
								pulsado[0]=false;
								e.printStackTrace();
								
								
							}													
					}
					}
				});

		

		
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				try {
					Thread.sleep(800);
					labelError.setText("");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	

		
		
	}
	
	private void initComponents() {
		
		this.setBounds(22, 25, 711, 441);
		setLayout(null);
		
		JLabel labelCodigoRestaurante = new JLabel("Codigo de Restaurante");
		labelCodigoRestaurante.setBounds(6, 34, 212, 29);
		labelCodigoRestaurante.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		this.add(labelCodigoRestaurante);
		
		campoCodigoRestaurante = new CampoTexto();
		campoCodigoRestaurante.setBounds(223, 30, 396, 37);
		this.add(campoCodigoRestaurante);
		campoCodigoRestaurante.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(131, 106, 87, 29);
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		this.add(lblDireccion);
		
		campoDireccion = new CampoTexto();
		campoDireccion.setBounds(223, 102, 396, 37);
		this.add(campoDireccion);
		campoDireccion.setColumns(10);
		
		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setBounds(137, 178, 81, 29);
		labelTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		this.add(labelTelefono);
		
		campoTelefono = new CampoTexto();
		campoTelefono.setBounds(223, 174, 396, 37);
		this.add(campoTelefono);
		campoTelefono.setColumns(10);
		
		JLabel labelCif = new JLabel("CIF");
		labelCif.setBounds(189, 250, 29, 29);
		labelCif.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		this.add(labelCif);
		
		campoCif = new CampoTexto();
		campoCif.setBounds(223, 246, 396, 37);
		campoCif.setText("");
		this.add(campoCif);
		campoCif.setColumns(10);
		
		botonAltaRestaurante = new JButton("Enviar datos");
		
		botonAltaRestaurante.setBounds(247, 357, 231, 45);
		this.add(botonAltaRestaurante);
		
		labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelError.setBounds(86, 301, 533, 45);
		add(labelError);
		
		
	}
}
