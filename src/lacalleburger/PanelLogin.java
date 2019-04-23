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

public class PanelLogin extends JPanel {
	JButton siguiente;
	private JTextField usuarioEntradaTexto;
	private JPasswordField contrasenaEntradaTexto;
	
	public PanelLogin(JFrame ventana) {
		PanelLogin estePanel=this;
		setBackground(Color.BLACK);
		this.setSize(new Dimension(807, 566));
		setLayout(null);
		
		JLabel datosIncorrectos = new JLabel("El usuario o la contrase\u00F1a son incorrectos");
		datosIncorrectos.setBounds(221, 62, 394, 41);
		datosIncorrectos.setForeground(Color.BLACK);
		datosIncorrectos.setBackground(Color.BLACK);
		datosIncorrectos.setFont(new Font("SansSerif", Font.BOLD, 19));
		add(datosIncorrectos);
		
		
		
		JLabel usuario_label = new JLabel("Usuario");
		usuario_label.setBounds(261, 223, 66, 32);
		usuario_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		usuario_label.setForeground(Color.WHITE);
		add(usuario_label);
		
		
		usuarioEntradaTexto = new JTextField();
		usuarioEntradaTexto.setBounds(373, 227, 242, 28);
		add(usuarioEntradaTexto);
		usuarioEntradaTexto.setColumns(10);
		
		JLabel contrasena_label = new JLabel("Contrase\u00F1a");
		contrasena_label.setBounds(244, 260, 101, 26);
		contrasena_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		contrasena_label.setForeground(Color.WHITE);
		add(contrasena_label);
		
		contrasenaEntradaTexto = new JPasswordField();
		contrasenaEntradaTexto.setBounds(373, 260, 242, 28);
		add(contrasenaEntradaTexto);
		siguiente=new JButton("Login");
		siguiente.setBounds(297, 433, 242, 37);
		add(siguiente);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		
		lblNewLabel.setBounds(23, 34, 132, 122);
		add(lblNewLabel);
		
		
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexion=compruebaConexion(datosIncorrectos);

				try {
					String usuarioIntroducido=usuarioEntradaTexto.getText();
					String contrasenaIntroducida=String.copyValueOf(contrasenaEntradaTexto.getPassword());
					Statement smt=conexion.createStatement();
		            ResultSet resultado=smt.executeQuery("select * from usuario where nombre='"+usuarioIntroducido+"' and contraseña='"+contrasenaIntroducida+"';");
		            resultado.next();
		            String nombreUsuario=resultado.getString("nombre");
		            String pass=resultado.getString("contraseña");
		            
		            String restaurante=resultado.getString("Restaurante_codigoRestaurante");
	                Statement smt2=conexion.createStatement();
	                ResultSet resultado2=smt2.executeQuery("select * from Restaurante where codigoRestaurante='"+restaurante+"';");
	                resultado2.next();
	                String cif=resultado2.getString("cif");
	                String nombreRestaurante=resultado2.getString("nombre");
	                String direccion=resultado2.getString("direccion");
	                String telefono=resultado2.getString("telefono");
	                Restaurante restauranteElegido=new Restaurante(cif,nombreRestaurante,direccion,telefono,restaurante);
	                Usuario usuarioElegido=new Usuario(restauranteElegido,nombreUsuario,pass);
	                estePanel.setVisible(false);
	                ventana.setContentPane(new PanelPedido(usuarioElegido,conexion,ventana));
               
				}catch(SQLException ex) {
					datosIncorrectos.setForeground(Color.red);
					
				}
	
			}
		});
	
		
	}
	
	public static Connection compruebaConexion(JLabel datosIncorrectos){
        try {
        	
            Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lacalle", "root", "kaotiko666");
            
            return conexion;
        } catch (SQLException ex) {          
            datosIncorrectos.setForeground(Color.red);
            datosIncorrectos.setText("Error en la conexion a la base de datos");
            
        return null;       
    }
}
}

