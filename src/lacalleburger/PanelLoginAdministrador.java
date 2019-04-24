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

public class PanelLoginAdministrador extends JPanel {
	JButton siguiente;
	private JTextField usuarioEntradaTexto;
	private JPasswordField contrasenaEntradaTexto;
	
	public PanelLoginAdministrador(JFrame ventana) {
		PanelLoginAdministrador estePanel=this;
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
		siguiente.setBounds(201, 378, 242, 37);
		add(siguiente);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(PanelLogin.class.getResource("/descarga.png")));
		
		lblNewLabel.setBounds(23, 6, 226, 192);
		add(lblNewLabel);
		
		JLabel lblPanelDeAdministrador = new JLabel("Panel de administrador");
		lblPanelDeAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelDeAdministrador.setForeground(Color.WHITE);
		lblPanelDeAdministrador.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		lblPanelDeAdministrador.setBounds(125, 449, 371, 28);
		add(lblPanelDeAdministrador);
		
		
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexion=compruebaConexion(datosIncorrectos);

				try {
					String usuarioIntroducido=usuarioEntradaTexto.getText();
					String contrasenaIntroducida=String.copyValueOf(contrasenaEntradaTexto.getPassword());
					Statement smt=conexion.createStatement();
		            ResultSet resultado=smt.executeQuery("select * from administrador where nombre='"+usuarioIntroducido+"' and contraseña='"+contrasenaIntroducida+"';");
		            resultado.next();
		            String nombreUsuario=resultado.getString("nombre");
		            String pass=resultado.getString("contraseña");
		            
		           
	                estePanel.setVisible(false);
	                ventana.setContentPane(new PanelAdministrador(ventana,conexion));
               
				}catch(SQLException ex) {
					datosIncorrectos.setForeground(Color.red);
					ex.printStackTrace();
					
				}
	
			}
		});
	
		
	}
	
	public static Connection compruebaConexion(JLabel datosIncorrectos){
        try {
        	
            Connection conexion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7289249", "sql7289249", "qE87qDQdJB");
            
            return conexion;
        } catch (SQLException ex) {          
            datosIncorrectos.setForeground(Color.red);
            datosIncorrectos.setText("Error en la conexion a la base de datos");
            ex.printStackTrace();
            
        return null;       
    }
}
}