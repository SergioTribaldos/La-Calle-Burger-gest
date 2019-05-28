package panelAdministrador;

import javax.swing.JPanel;

import pedidos.Pedido;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.swing.JTextField;

import java.util.ArrayList;
import javax.swing.ScrollPaneConstants;

import lacalleburger.Ventana;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PanelFactura extends JPanel {

	private Pedido pedido;
	private Ventana ventana;
	ArrayList<JTextField> campos;
	ArrayList<JLabel> labels;
	JButton btnEnviar;
	JPanel panel;

	public PanelFactura(Pedido pedido, Ventana ventan) {
		this.pedido = pedido;
		this.ventana = ventan;
		initComponents();

		// Genera dinamicamente los JTextField y los JLabel solo para los productos que
		// tengan al menos 1 unidad pedida.

		campos = new ArrayList<JTextField>();
		labels = new ArrayList<JLabel>();
		int e = 13;
		int f = 13;
		for (int i = 0; i < pedido.getCantidad().length; i++) {

			if (i <= 18) {
				campos.add(new JTextField());
				campos.get(campos.size() - 1).setBounds(12, e, 169, 28);
				campos.get(campos.size() - 1).setFont(new Font("Tahoma", Font.PLAIN, 16));

				labels.add(
						new JLabel(pedido.getCantidad()[i] + "      " + pedido.getListaProductos().get(i).getNombre()));
				labels.get(labels.size() - 1).setBounds(193, e, 300, 36);
				if (pedido.getCantidad()[i] != 0) {
					panel.add(campos.get(campos.size() - 1));
					panel.add(labels.get(labels.size() - 1));
					e += 35;
				}

			}

			if (i > 18) {
				campos.add(new JTextField());
				campos.get(campos.size() - 1).setBounds(491, f, 169, 28);
				campos.get(campos.size() - 1).setFont(new Font("Tahoma", Font.PLAIN, 16));

				labels.add(new JLabel(
						pedido.getCantidad()[i] + "       " + pedido.getListaProductos().get(i).getNombre()));
				labels.get(labels.size() - 1).setBounds(672, f, 300, 36);
				if (pedido.getCantidad()[i] != 0) {
					panel.add(campos.get(campos.size() - 1));
					panel.add(labels.get(labels.size() - 1));
					f += 35;
				}

			}

		}

		boolean[] pulsado = { false };

		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (pulsado[0] == false) {
					// Confirmacion para generar la factura
					btnEnviar.setText("Pulsa de nuevo para generar la factura");
					pulsado[0] = true;

				} else {
					try {
						String[] lote = recogeLotes();
						pedido.setLote(lote);
						pedido.generaFactura();
						pedido.actualizaPedidoConLotesYPrecioTotal(ventana.getConexion());
						btnEnviar.setEnabled(false);
						JOptionPane.showMessageDialog(ventana, "La factura ha sido generada y guardada en la base de datos!");				
						ventana.setContentPane(new PanelAdministrador(ventana));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(ventana, "Alguno de los campos de lote está vacío");
						btnEnviar.setText("Enviar factura");
						pulsado[0] = false;
						e.printStackTrace();
					}

				}
			}
		});

	}

	public Ventana getVentana() {
		return ventana;
	}

	public void setVentana(Ventana ventana) {
		this.ventana = ventana;
	}

	/**
	 * Recoge los lotes introducidos en los JTextField, si no estan todos rellenos,
	 * lanza una excepcion.
	 * 
	 * @return Un array de String con los lotes.
	 * @throws Exception
	 */
	public String[] recogeLotes() throws Exception {
		String[] lote = new String[pedido.getCantidad().length];

		for (int i = 0; i < campos.size(); i++) {
			lote[i] = campos.get(i).getText();
			if (campos.get(i).getText().equals("") && pedido.getCantidad()[i] > 0) {
				throw new Exception();
			}
		}
		return lote;
	}

	public void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Factura para: " + pedido.getUsuario().getRestaurante().getCodigoRestaurante());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblNewLabel, BorderLayout.NORTH);

		btnEnviar = new JButton("Enviar factura");
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnEnviar.setMinimumSize(new Dimension(97, 43));
		add(btnEnviar, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

	}
}
