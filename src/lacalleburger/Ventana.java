package lacalleburger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import panelAdministrador.PanelFactura;
import usuarios.Usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ventana extends JFrame {
	JPanel panelActual;
	static Connection conexion;

	public Ventana() {

		this.setTitle("La Calle Burger Gest");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Cierra la conexion cuando cerramos el programa.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					conexion.close();
					System.out.println("Conexion cerrada");

				} catch (SQLException e) {
					System.err.println("Conexion NO cerrada");
					System.exit(ABORT);
					e.printStackTrace();

				}

			}
		});

		setResizable(false);
		panelActual = new PanelPrincipal(this);
		conexion = compruebaConexion(this);
		this.setSize(720, 561);
		setContentPane(panelActual);

	}

	public Ventana(PanelFactura panelFactura, Connection conexion) {
		this.conexion = conexion;
		setContentPane(panelFactura);
		this.setVisible(true);

		setResizable(true);
		this.setSize(950, 861);

	}

	public Connection getConexion() {
		return conexion;
	}

	public Ventana getThis() {
		return this;
	}

	public JPanel getPanelActual() {
		return panelActual;
	}

	public void setPanelActual(JPanel panelActual) {
		this.panelActual = panelActual;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	/**
	 * Conecta a la base de datos MySQL y la almacena en una variable interna en el
	 * JFrame.
	 * 
	 * @param ventana Esta ventana
	 * @return Una conexion a base de datos
	 */
	public static Connection compruebaConexion(JFrame ventana) {
		try {

			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://85.214.120.213?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"sergio", "sergio");
			Statement smt = conexion.createStatement();
			smt.executeQuery("use lacalle;");
			smt.close();
			return conexion;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(ventana, "Error en la base de datos");
			ex.printStackTrace();
			System.exit(ABORT);

			return null;
		}
	}

	/**
	 * Cambia el JPanel visible. Usado para navegacion
	 * 
	 * @param panelActual   El panel que esta activo y visible en este JFrame.
	 * @param panelAnterior El panel que queremoscambiar.
	 */
	public void cambiaPanel(JPanel panelActual, JPanel panelAnterior) {
		this.panelActual = panelActual;
		this.setContentPane(panelActual);
		if (panelAnterior != null) {
			panelAnterior.setVisible(false);
		}

		panelActual.setVisible(true);

	}
}
