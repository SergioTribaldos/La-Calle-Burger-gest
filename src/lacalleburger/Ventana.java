package lacalleburger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ventana extends JFrame{
	PanelPrincipal panelPrincipal;	
	Connection conexion;
	
	
	public Ventana() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					conexion.close();
					
				} catch (SQLException e) {
					System.exit(ABORT);
					e.printStackTrace();
				}
				
			}
		});
		
		setResizable(false);
		panelPrincipal=new PanelPrincipal(this);
		conexion=compruebaConexion(this);
		this.setSize(720,561);
		setContentPane(panelPrincipal);

	}
	
	public Ventana(PanelFactura panelFactura,Connection conexion) {		
		setContentPane(panelFactura);
		this.conexion = conexion;
		this.setVisible(true);
		
		setResizable(false);
		//panelPrincipal=new PanelPrincipal(this);
		this.setSize(950,861);
		
	}
	
	
	public Connection getConexion() {
		return conexion;
	}



	public Ventana getThis() {
		return this;
	}
	
	public static Connection compruebaConexion(JFrame ventana){
        try {
        	
            Connection conexion = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7289249", "sql7289249", "qE87qDQdJB");
            
            return conexion;
        } catch (SQLException ex) {          
        	JOptionPane.showMessageDialog(ventana, "Error en la base de datos");
        	System.exit(ABORT);
          //  datosIncorrectos.setForeground(Color.red);
            //datosIncorrectos.setText("Error en la conexion a la base de datos");
            ex.printStackTrace();
            
        return null;       
    }
}

}
