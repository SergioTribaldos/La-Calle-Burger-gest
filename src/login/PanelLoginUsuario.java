package login;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.JOptionPane;

import java.awt.Font;

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
import excepciones.RestauranteException;
import lacalleburger.Ventana;
import panelUsuario.PanelPedido;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelLoginUsuario extends JPanel {
	JButton siguiente;
	private CampoTexto usuarioEntradaTexto;
	private JPasswordField contrasenaEntradaTexto;
	private Ventana ventana;
	JLabel datosIncorrectos;

	public PanelLoginUsuario(Ventana ventana, JPanel panelAnterior) {

		initComponents();

		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Comprueba en la base de datos si el usuario y la contraseña son correctos, en
					// caso contrario lanza una excepcion

					String usuarioIntroducido = usuarioEntradaTexto.getText();
					String contrasenaIntroducida = String.copyValueOf(contrasenaEntradaTexto.getPassword());
					Statement smt = ventana.getConexion().createStatement();
					ResultSet resultado = smt.executeQuery("select * from usuario where nombre='" + usuarioIntroducido
							+ "' and contraseña='" + contrasenaIntroducida + "';");
					resultado.next();
					String nombreUsuario = resultado.getString("nombre");
					String pass = resultado.getString("contraseña");

					String codigoRestaurante = resultado.getString("Restaurante_codigoRestaurante");
					Statement smt2 = ventana.getConexion().createStatement();
					ResultSet resultado2 = smt2.executeQuery(
							"select * from restaurante where codigoRestaurante='" + codigoRestaurante + "';");
					resultado2.next();
					String cif = resultado2.getString("cif");
					String direccion = resultado2.getString("direccion");
					String telefono = resultado2.getString("telefono");
					Restaurante restauranteElegido = new Restaurante(cif, direccion, telefono, codigoRestaurante);
					Usuario usuarioElegido = new Usuario(restauranteElegido, nombreUsuario, pass);

					// Comprobamos si el usuario ya ha realizado un pedido hoy, en caso afirmativo,
					// te impide hacer uno nuevo
					smt = ventana.getConexion().createStatement();
					resultado = smt.executeQuery(
							"select pedido.Usuario_usuario,pedido.fecha,usuario.Restaurante_codigoRestaurante\r\n"
									+ "from pedido\r\n" + "join usuario on pedido.Usuario_usuario=usuario.nombre\r\n"
									+ "where DATE(pedido.fecha)=curdate() and Restaurante_codigoRestaurante='"
									+ codigoRestaurante + "' ;");

					if (resultado.next()) {
						JOptionPane.showConfirmDialog(ventana, "El restaurante ya ha realizado un pedido hoy", "Error",
								JOptionPane.CLOSED_OPTION);
						ventana.cambiaPanel(panelAnterior, PanelLoginUsuario.this);
					} else {
						ventana.cambiaPanel(new PanelPedido(usuarioElegido, ventana), PanelLoginUsuario.this);
					}

					smt.close();
					resultado.close();

				} catch (SQLException ex) {
					datosIncorrectos.setForeground(Color.red);
					ex.printStackTrace();

				} catch (RestauranteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	public void initComponents() {
		setBackground(Color.BLACK);
		this.setSize(new Dimension(807, 566));
		setLayout(null);

		datosIncorrectos = new JLabel("El usuario o la contrase\u00F1a son incorrectos");
		datosIncorrectos.setBounds(125, 287, 394, 41);
		datosIncorrectos.setForeground(Color.BLACK);
		datosIncorrectos.setBackground(Color.BLACK);
		datosIncorrectos.setFont(new Font("SansSerif", Font.BOLD, 19));
		add(datosIncorrectos);

		contrasenaEntradaTexto = new JPasswordField();
		contrasenaEntradaTexto.setBounds(277, 247, 242, 28);
		add(contrasenaEntradaTexto);
		siguiente = new JButton("Login");
		siguiente.setBounds(201, 378, 242, 37);
		add(siguiente);

		JLabel usuario_label = new JLabel("Usuario");
		usuario_label.setBounds(165, 210, 66, 32);
		usuario_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		usuario_label.setForeground(Color.WHITE);
		add(usuario_label);

		usuarioEntradaTexto = new CampoTexto();
		usuarioEntradaTexto.setBounds(277, 214, 242, 28);
		add(usuarioEntradaTexto);
		// usuarioEntradaTexto.setColumns(10);

		JLabel contrasena_label = new JLabel("Contrase\u00F1a");
		contrasena_label.setBounds(148, 247, 101, 26);
		contrasena_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		contrasena_label.setForeground(Color.WHITE);
		add(contrasena_label);

		JLabel logoEmpresa = new JLabel("New label");
		logoEmpresa.setIcon(new ImageIcon(PanelLoginUsuario.class.getResource("/descarga.png")));

		logoEmpresa.setBounds(0, 0, 226, 192);
		add(logoEmpresa);

		JLabel lblPanelDeUsuario = new JLabel("Panel de usuario");
		lblPanelDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelDeUsuario.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		lblPanelDeUsuario.setForeground(Color.WHITE);
		lblPanelDeUsuario.setBounds(148, 473, 371, 28);
		add(lblPanelDeUsuario);

	}

}
