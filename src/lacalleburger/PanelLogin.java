package lacalleburger;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelLogin extends JPanel {
	JButton siguiente;
	private JTextField usuarioEntradaTexto;
	private JPasswordField contrasenaEntradaTexto;
	
	public PanelLogin(Ventana ventana) {
		PanelLogin estePanel=this;
		setBackground(Color.BLACK);
		this.setSize(new Dimension(807, 566));
		setLayout(null);
		
		JLabel datosIncorrectos = new JLabel("El usuario o la contrase\u00F1a son incorrectos");
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
		
		
		usuarioEntradaTexto = new JTextField();
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
		siguiente=new JButton("Login");
		siguiente.addKeyListener(new KeyAdapter() {
			@Override                                 ////////////////////////////AÑADIR PULSAR ENTER
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
		            System.out.println("Hello");
		        }
				
			}
		});
		siguiente.setBounds(201, 378, 242, 37);
		add(siguiente);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(PanelLogin.class.getResource("/descarga.png")));
		
		lblNewLabel.setBounds(23, 6, 226, 192);
		add(lblNewLabel);
		
		JLabel lblPanelDeUsuario = new JLabel("Panel de usuario");
		lblPanelDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelDeUsuario.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		lblPanelDeUsuario.setForeground(Color.WHITE);
		lblPanelDeUsuario.setBounds(148, 473, 371, 28);
		add(lblPanelDeUsuario);
		
		
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
					String usuarioIntroducido=usuarioEntradaTexto.getText();
					String contrasenaIntroducida=String.copyValueOf(contrasenaEntradaTexto.getPassword());
					Statement smt=ventana.getConexion().createStatement();
		            ResultSet resultado=smt.executeQuery("select * from usuario where nombre='"+usuarioIntroducido+"' and contraseña='"+contrasenaIntroducida+"';");
		            resultado.next();
		            String nombreUsuario=resultado.getString("nombre");
		            String pass=resultado.getString("contraseña");
		            
		            String restaurante=resultado.getString("Restaurante_codigoRestaurante");
	                Statement smt2=ventana.getConexion().createStatement();
	                ResultSet resultado2=smt2.executeQuery("select * from restaurante where codigoRestaurante='"+restaurante+"';");
	                resultado2.next();
	                String cif=resultado2.getString("cif");
	                String nombreRestaurante=resultado2.getString("nombre");
	                String direccion=resultado2.getString("direccion");
	                String telefono=resultado2.getString("telefono");
	                Restaurante restauranteElegido=new Restaurante(cif,nombreRestaurante,direccion,telefono,restaurante);
	                Usuario usuarioElegido=new Usuario(restauranteElegido,nombreUsuario,pass);
	                estePanel.setVisible(false);
	                ventana.setContentPane(new PanelPedido(usuarioElegido,ventana));
               
				}catch(SQLException ex) {
					datosIncorrectos.setForeground(Color.red);
					ex.printStackTrace();
					
				}
	
			}
		});
	
		
	}
	
	
}

