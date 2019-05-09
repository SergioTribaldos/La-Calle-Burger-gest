package panelAdministrador;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import componentes.CampoTexto;
import excepciones.RestauranteException;
import excepciones.UsuarioException;
import lacalleburger.Ventana;
import restaurantes.Restaurante;
import usuarios.Usuario;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;

public class PanelAltaUsuario extends JPanel {
	private JPasswordField campoConfirmaContrasena;
	private JPasswordField campoContrasena;
	private CampoTexto campoUsuario;
	private JButton botonAltaUsuario;
	private JLabel labelError;
	private JComboBox comboBox;
	private String[]listaRestaurantes;
	private Ventana ventana;
	
	
	public PanelAltaUsuario(Ventana ventana)  {
		
		this.ventana = ventana;
		listaRestaurantes=Restaurante.consultaRestaurantesDisponibles(ventana.getConexion());
		
		initComponents();
		
		boolean[] pulsado = {false};
		botonAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						if(pulsado[0]==false) {
							botonAltaUsuario.setText("Pulsa de nuevo para dar de alta al restaurante");
							pulsado[0]=true;				
						}else {
							
									try {
										Usuario nuevo=new Usuario(String.valueOf(comboBox.getSelectedItem()),
																	campoUsuario.getText(),
																	pasaPasswordAString(campoContrasena),
																	pasaPasswordAString(campoConfirmaContrasena));
										nuevo.insertarUsuarioEnBaseDeDatos(ventana.getConexion(), nuevo.getUsuario(), nuevo.getContrasena(), String.valueOf(comboBox.getSelectedItem()));
										botonAltaUsuario.setText("El usuario ha sido dado de alta");
										botonAltaUsuario.setEnabled(false);
									} catch (UsuarioException e) {
										labelError.setText(e.getMessage());
										e.printStackTrace();
										botonAltaUsuario.setText("Enviar datos");
										pulsado[0]=false;
										
									}

								

								
							
																			
					}
					}
				});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private  String pasaPasswordAString(JPasswordField campoContrasena) {
		String password="";
		
		for(int i=0;i<campoContrasena.getPassword().length;i++) {
			password+=campoContrasena.getPassword()[i];
		}
		
		
		return password;
		
	}
	
	private void initComponents() {
		setLayout(null);
		
		JLabel labelNombreUsuario = new JLabel("Usuario");
		labelNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombreUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		labelNombreUsuario.setBounds(6, 38, 212, 29);
		add(labelNombreUsuario);
		
		campoUsuario = new CampoTexto();
		campoUsuario.setColumns(10);
		campoUsuario.setBounds(223, 34, 396, 37);
		add(campoUsuario);
		
		JLabel labelContrasena = new JLabel("Contrase\u00F1a");
		labelContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		labelContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		labelContrasena.setBounds(6, 110, 212, 29);
		add(labelContrasena);
		
		campoContrasena = new JPasswordField();
		campoContrasena.setColumns(10);
		campoContrasena.setBounds(223, 106, 396, 37);
		add(campoContrasena);
		
		JLabel lblConfirmaContrasea = new JLabel("Confirma contrase\u00F1a");
		lblConfirmaContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		lblConfirmaContrasea.setBounds(6, 174, 212, 29);
		add(lblConfirmaContrasea);
		
		campoConfirmaContrasena = new JPasswordField();
		campoConfirmaContrasena.setColumns(10);
		campoConfirmaContrasena.setBounds(223, 170, 396, 37);
		add(campoConfirmaContrasena);
		
		JLabel labelRestaurante = new JLabel("Restaurante");
		labelRestaurante.setHorizontalAlignment(SwingConstants.RIGHT);
		labelRestaurante.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		labelRestaurante.setBounds(6, 234, 212, 29);
		add(labelRestaurante);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(listaRestaurantes));
		comboBox.setBounds(223, 234, 305, 37);
		add(comboBox);
		
		botonAltaUsuario = new JButton("Enviar datos");
		botonAltaUsuario.setBounds(223, 362, 231, 45);
		add(botonAltaUsuario);
		
		labelError = new JLabel("");
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setForeground(Color.RED);
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelError.setBounds(17, 301, 709, 45);
		add(labelError);
	}
}
