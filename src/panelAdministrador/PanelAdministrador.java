package panelAdministrador;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import pedidos.Pedido;
import producto.Producto;
import restaurantes.Restaurante;
import usuarios.Usuario;
import componentes.CampoTexto;
import componentes.JListRenderCustom;
import componentes.JLista;
import excepciones.RestauranteException;
import lacalleburger.Ventana;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class PanelAdministrador extends JPanel{

	
	Ventana ventana;

	private JTabbedPane tabbedPane;
	
	
	private PanelAltaRestaurante panelAltaRestaurante;
	private PanelRecibirPedidos panelRecibirPedidos;
	private PanelAltaUsuario panelAltaUsuario;
	

	public PanelAdministrador(Ventana ventan) {
		this.ventana = ventan;
		
		initComponents();
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				panelRecibirPedidos.revalidate();
				panelRecibirPedidos.repaint();
				
			}
		});

		
	}

	
		
	


	
	
	public void initComponents() {
		
		setForeground(Color.ORANGE);
		setLayout(null);
		ventana.setSize(1200,800);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(0, 0, 672, 570);
		add(tabbedPane);
		ventana.setSize(tabbedPane.getSize());
		
		panelRecibirPedidos = new PanelRecibirPedidos(this.ventana);
		tabbedPane.addTab("Recibir pedidos", null, panelRecibirPedidos, null);
		tabbedPane.setEnabledAt(0, true);

		panelAltaRestaurante = new PanelAltaRestaurante(this.ventana);	
		tabbedPane.addTab("Nuevo Restaurante", null, panelAltaRestaurante, null);
		
		panelAltaUsuario = new PanelAltaUsuario(this.ventana);
		tabbedPane.addTab("Nuevo Usuario", null, panelAltaUsuario, null);

		
	}
}
