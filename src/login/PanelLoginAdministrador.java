package login;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import restaurantes.Restaurante;
import usuarios.Usuario;

import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import componentes.CampoTexto;
import lacalleburger.Ventana;
import panelAdministrador.PanelAdministrador;

import javax.swing.JList;

public class PanelLoginAdministrador extends JPanel {
	private JButton siguiente;
	private CampoTexto usuarioEntradaTexto;
	private JPasswordField contrasenaEntradaTexto;
	private Ventana ventana;
	private JLabel datosIncorrectos;

	public PanelLoginAdministrador(Ventana ventan) {
		this.ventana = ventan;
		
		initComponents();
		
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Comprueba en la base de datos si el usuario y la contraseņa son correctos, en
					// caso contrario lanza una excepcion
					String usuarioIntroducido = usuarioEntradaTexto.getText();
					String contrasenaIntroducida = String.copyValueOf(contrasenaEntradaTexto.getPassword());
					Statement smt = ventana.getConexion().createStatement();
					ResultSet resultado = smt.executeQuery("select * from administrador where nombre='"
							+ usuarioIntroducido + "' and contraseņa='" + contrasenaIntroducida + "';");
					resultado.next();
					String nombreUsuario = resultado.getString("nombre");
					String pass = resultado.getString("contraseņa");

					ventana.cambiaPanel(new PanelAdministrador(ventana), PanelLoginAdministrador.this);

				} catch (SQLException ex) {
					datosIncorrectos.setForeground(Color.red);
					ex.printStackTrace();

				}

			}
		});

	}

	public void initComponents() {

		PanelLoginAdministrador estePanel = this;
		setBackground(Color.BLACK);
		this.setSize(new Dimension(807, 566));
		setLayout(null);

		datosIncorrectos = new JLabel("El usuario o la contrase\u00F1a son incorrectos");
		datosIncorrectos.setBounds(125, 287, 394, 41);
		datosIncorrectos.setForeground(Color.BLACK);
		datosIncorrectos.setBackground(Color.BLACK);
		datosIncorrectos.setFont(new Font("SansSerif", Font.BOLD, 19));
		add(datosIncorrectos);

		JLabel usuario_label = new JLabel("Usuario");
		usuario_label.setBounds(165, 210, 66, 32);
		usuario_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		usuario_label.setForeground(Color.WHITE);
		add(usuario_label);

		usuarioEntradaTexto = new CampoTexto();
		usuarioEntradaTexto.setText("johan");
		usuarioEntradaTexto.setBounds(277, 214, 242, 28);
		add(usuarioEntradaTexto);
		usuarioEntradaTexto.setColumns(10);

		JLabel contrasena_label = new JLabel("Contrase\u00F1a");
		contrasena_label.setBounds(148, 247, 101, 26);
		contrasena_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		contrasena_label.setForeground(Color.WHITE);
		add(contrasena_label);

		contrasenaEntradaTexto = new JPasswordField();
		contrasenaEntradaTexto.setBounds(277, 247, 242, 28);
		add(contrasenaEntradaTexto);
		
		siguiente = new JButton("Login");
		siguiente.setBounds(201, 378, 242, 37);
		add(siguiente);

		JLabel logoEmpresa = new JLabel("New label");
		logoEmpresa.setIcon(new ImageIcon(PanelLoginUsuario.class.getResource("/descarga.png")));

		logoEmpresa.setBounds(0, 0, 226, 192);
		add(logoEmpresa);

		JLabel lblPanelDeAdministrador = new JLabel("Panel de administrador");
		lblPanelDeAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelDeAdministrador.setForeground(Color.WHITE);
		lblPanelDeAdministrador.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		lblPanelDeAdministrador.setBounds(125, 449, 371, 28);
		add(lblPanelDeAdministrador);

	}
}