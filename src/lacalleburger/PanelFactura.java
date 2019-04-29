package lacalleburger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import pedidos.Pedido;
import pedidos.Pedido_info;
import restaurantes.Restaurante;
import usuarios.Administrador;
import usuarios.Usuario;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PanelFactura extends JPanel{
	private JTextField textField;
	public PanelFactura(Pedido_info pedido) {
		setBackground(Color.BLACK);
		this.setSize(950,861);
		setLayout(new BorderLayout(0, 0));
		

		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 343, 72, 366, 72};
		gbl_panel.rowHeights = new int[]{45, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		panel.setLayout(gbl_panel);
		fdfsdgdfsgdf
		JLabel lblNewLabel = new JLabel(pedido.getRestaurante().getNombre()+" "+pedido.getRestaurante().getDireccion()+" "+pedido.getRestaurante().getTelefono());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 23));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel hamburguesasTitulo = new JLabel("HAMBURGUESAS");
		hamburguesasTitulo.setForeground(new Color(248, 248, 255));
		hamburguesasTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_hamburguesasTitulo = new GridBagConstraints();
		gbc_hamburguesasTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_hamburguesasTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_hamburguesasTitulo.gridx = 1;
		gbc_hamburguesasTitulo.gridy = 1;
		panel.add(hamburguesasTitulo, gbc_hamburguesasTitulo);
		 
		
		JLabel salsasTitulo = new JLabel("SALSAS (bolsas)");
		salsasTitulo.setForeground(new Color(248, 248, 255));
		salsasTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_salsasTitulo = new GridBagConstraints();
		gbc_salsasTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_salsasTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_salsasTitulo.gridx = 3;
		gbc_salsasTitulo.gridy = 1;
		panel.add(salsasTitulo, gbc_salsasTitulo);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel aguja = new JLabel("HAMBURGUESA DE AGUJA 20 ud");
		aguja.setForeground(Color.WHITE);
		aguja.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_aguja = new GridBagConstraints();
		gbc_aguja.fill = GridBagConstraints.BOTH;
		gbc_aguja.insets = new Insets(0, 0, 5, 5);
		gbc_aguja.gridx = 1;
		gbc_aguja.gridy = 2;
		panel.add(aguja, gbc_aguja);
		
		JLabel aguja_num = new JLabel("New label");
		aguja_num.setForeground(Color.WHITE);
		aguja_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_aguja_num = new GridBagConstraints();
		gbc_aguja_num.insets = new Insets(0, 0, 5, 5);
		gbc_aguja_num.gridx = 2;
		gbc_aguja_num.gridy = 2;
		panel.add(aguja_num, gbc_aguja_num);
		
		
		JLabel salteado = new JLabel("CALDO PARA SALTEADO bolsa 1 kg");
		salteado.setForeground(Color.WHITE);
		salteado.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_salteado = new GridBagConstraints();
		gbc_salteado.fill = GridBagConstraints.BOTH;
		gbc_salteado.insets = new Insets(0, 0, 5, 5);
		gbc_salteado.gridx = 3;
		gbc_salteado.gridy = 2;
		panel.add(salteado, gbc_salteado);
		
		JLabel salteado_num = new JLabel();
		salteado_num.setForeground(Color.WHITE);
		salteado_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_salteado_num = new GridBagConstraints();
		gbc_salteado_num.fill = GridBagConstraints.VERTICAL;
		gbc_salteado_num.insets = new Insets(0, 0, 5, 0);
		gbc_salteado_num.gridx = 4;
		gbc_salteado_num.gridy = 2;
		panel.add(salteado_num, gbc_salteado_num);
		
		JLabel angus = new JLabel("HAMBURGUESA DE ANGUS 20 ud");
		angus.setForeground(Color.WHITE);
		angus.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_angus = new GridBagConstraints();
		gbc_angus.fill = GridBagConstraints.BOTH;
		gbc_angus.insets = new Insets(0, 0, 5, 5);
		gbc_angus.gridx = 1;
		gbc_angus.gridy = 3;
		panel.add(angus, gbc_angus);
		
		JLabel angus_num = new JLabel("New label");
		angus_num.setForeground(Color.WHITE);
		angus_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_angus_num = new GridBagConstraints();
		gbc_angus_num.insets = new Insets(0, 0, 5, 5);
		gbc_angus_num.gridx = 2;
		gbc_angus_num.gridy = 3;
		panel.add(angus_num, gbc_angus_num);
		
		JLabel ketchup = new JLabel("KETCHUP LA CALLE bolsa 1 kg");
		ketchup.setForeground(Color.WHITE);
		ketchup.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_ketchup = new GridBagConstraints();
		gbc_ketchup.fill = GridBagConstraints.BOTH;
		gbc_ketchup.insets = new Insets(0, 0, 5, 5);
		gbc_ketchup.gridx = 3;
		gbc_ketchup.gridy = 3;
		panel.add(ketchup, gbc_ketchup);
		
		JLabel ketchup_num = new JLabel();
		ketchup_num.setForeground(Color.WHITE);
		ketchup_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_ketchup_num = new GridBagConstraints();
		gbc_ketchup_num.fill = GridBagConstraints.VERTICAL;
		gbc_ketchup_num.insets = new Insets(0, 0, 5, 0);
		gbc_ketchup_num.gridx = 4;
		gbc_ketchup_num.gridy = 3;
		panel.add(ketchup_num, gbc_ketchup_num);
		
		JLabel entrana = new JLabel("HAMBURGUESA DE ENTRA\u00D1A 20 ud");
		entrana.setForeground(Color.WHITE);
		entrana.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_entrana = new GridBagConstraints();
		gbc_entrana.fill = GridBagConstraints.BOTH;
		gbc_entrana.insets = new Insets(0, 0, 5, 5);
		gbc_entrana.gridx = 1;
		gbc_entrana.gridy = 4;
		panel.add(entrana, gbc_entrana);
		
		JLabel entrana_num = new JLabel("New label");
		entrana_num.setForeground(Color.WHITE);
		entrana_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_entrana_num = new GridBagConstraints();
		gbc_entrana_num.insets = new Insets(0, 0, 5, 5);
		gbc_entrana_num.gridx = 2;
		gbc_entrana_num.gridy = 4;
		panel.add(entrana_num, gbc_entrana_num);
		
		JLabel lacalle = new JLabel(" MAYONESA LA CALLE bolsa 2 kg");
		lacalle.setForeground(Color.WHITE);
		lacalle.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lacalle = new GridBagConstraints();
		gbc_lacalle.fill = GridBagConstraints.BOTH;
		gbc_lacalle.insets = new Insets(0, 0, 5, 5);
		gbc_lacalle.gridx = 3;
		gbc_lacalle.gridy = 4;
		panel.add(lacalle, gbc_lacalle);
		
		JLabel lacalle_num = new JLabel();
		lacalle_num.setForeground(Color.WHITE);
		lacalle_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_lacalle_num = new GridBagConstraints();
		gbc_lacalle_num.fill = GridBagConstraints.VERTICAL;
		gbc_lacalle_num.insets = new Insets(0, 0, 5, 0);
		gbc_lacalle_num.gridx = 4;
		gbc_lacalle_num.gridy = 4;
		panel.add(lacalle_num, gbc_lacalle_num);
		
		JLabel garbanzos = new JLabel("HAMBURGUESA DE GARBANZOS 30 ud");
		garbanzos.setForeground(Color.WHITE);
		garbanzos.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_garbanzos = new GridBagConstraints();
		gbc_garbanzos.fill = GridBagConstraints.BOTH;
		gbc_garbanzos.insets = new Insets(0, 0, 5, 5);
		gbc_garbanzos.gridx = 1;
		gbc_garbanzos.gridy = 5;
		panel.add(garbanzos, gbc_garbanzos);
		
		JLabel garbanzos_num = new JLabel("New label");
		garbanzos_num.setForeground(Color.WHITE);
		garbanzos_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_garbanzos_num = new GridBagConstraints();
		gbc_garbanzos_num.insets = new Insets(0, 0, 5, 5);
		gbc_garbanzos_num.gridx = 2;
		gbc_garbanzos_num.gridy = 5;
		panel.add(garbanzos_num, gbc_garbanzos_num);
		
		JLabel chimichurri = new JLabel("MAYONESA DE CHIMICHURRI bolsa 2 kg");
		chimichurri.setForeground(Color.WHITE);
		chimichurri.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_chimichurri = new GridBagConstraints();
		gbc_chimichurri.fill = GridBagConstraints.BOTH;
		gbc_chimichurri.insets = new Insets(0, 0, 5, 5);
		gbc_chimichurri.gridx = 3;
		gbc_chimichurri.gridy = 5;
		panel.add(chimichurri, gbc_chimichurri);
		
		JLabel chimichurri_num = new JLabel();
		chimichurri_num.setForeground(Color.WHITE);
		chimichurri_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_chimichurri_num = new GridBagConstraints();
		gbc_chimichurri_num.fill = GridBagConstraints.VERTICAL;
		gbc_chimichurri_num.insets = new Insets(0, 0, 5, 0);
		gbc_chimichurri_num.gridx = 4;
		gbc_chimichurri_num.gridy = 5;
		panel.add(chimichurri_num, gbc_chimichurri_num);
		
		JLabel lentejas = new JLabel("HAMBURGUESA DE LENTEJAS 30 ud");
		lentejas.setForeground(Color.WHITE);
		lentejas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lentejas = new GridBagConstraints();
		gbc_lentejas.fill = GridBagConstraints.BOTH;
		gbc_lentejas.insets = new Insets(0, 0, 5, 5);
		gbc_lentejas.gridx = 1;
		gbc_lentejas.gridy = 6;
		panel.add(lentejas, gbc_lentejas);
		
		JLabel lentejas_num = new JLabel("New label");
		lentejas_num.setForeground(Color.WHITE);
		lentejas_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_lentejas_num = new GridBagConstraints();
		gbc_lentejas_num.insets = new Insets(0, 0, 5, 5);
		gbc_lentejas_num.gridx = 2;
		gbc_lentejas_num.gridy = 6;
		panel.add(lentejas_num, gbc_lentejas_num);
		
		JLabel trufa = new JLabel("MAYONESA DE PIMIENTA Y TRUFA bolsa 2 kg");
		trufa.setForeground(Color.WHITE);
		trufa.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_trufa = new GridBagConstraints();
		gbc_trufa.fill = GridBagConstraints.BOTH;
		gbc_trufa.insets = new Insets(0, 0, 5, 5);
		gbc_trufa.gridx = 3;
		gbc_trufa.gridy = 6;
		panel.add(trufa, gbc_trufa);
		
		JLabel trufa_num = new JLabel();
		trufa_num.setForeground(Color.WHITE);
		trufa_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_trufa_num = new GridBagConstraints();
		gbc_trufa_num.fill = GridBagConstraints.VERTICAL;
		gbc_trufa_num.insets = new Insets(0, 0, 5, 0);
		gbc_trufa_num.gridx = 4;
		gbc_trufa_num.gridy = 6;
		panel.add(trufa_num, gbc_trufa_num);
		
		JLabel vacio = new JLabel("HAMBURGUESA DE VAC\u00CDO 20 ud");
		vacio.setForeground(Color.WHITE);
		vacio.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_vacio = new GridBagConstraints();
		gbc_vacio.fill = GridBagConstraints.BOTH;
		gbc_vacio.insets = new Insets(0, 0, 5, 5);
		gbc_vacio.gridx = 1;
		gbc_vacio.gridy = 7;
		panel.add(vacio, gbc_vacio);
		
		JLabel vacio_num = new JLabel("New label");
		vacio_num.setForeground(Color.WHITE);
		vacio_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_vacio_num = new GridBagConstraints();
		gbc_vacio_num.insets = new Insets(0, 0, 5, 5);
		gbc_vacio_num.gridx = 2;
		gbc_vacio_num.gridy = 7;
		panel.add(vacio_num, gbc_vacio_num);
		
		JLabel sweetChili = new JLabel("MAYONESA SWEET CHILI bolsa 1 kg");
		sweetChili.setForeground(Color.WHITE);
		sweetChili.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_sweetChili = new GridBagConstraints();
		gbc_sweetChili.fill = GridBagConstraints.BOTH;
		gbc_sweetChili.insets = new Insets(0, 0, 5, 5);
		gbc_sweetChili.gridx = 3;
		gbc_sweetChili.gridy = 7;
		panel.add(sweetChili, gbc_sweetChili);
		
		JLabel sweetChili_num = new JLabel();
		sweetChili_num.setForeground(Color.WHITE);
		sweetChili_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_sweetChili_num = new GridBagConstraints();
		gbc_sweetChili_num.fill = GridBagConstraints.VERTICAL;
		gbc_sweetChili_num.insets = new Insets(0, 0, 5, 0);
		gbc_sweetChili_num.gridx = 4;
		gbc_sweetChili_num.gridy = 7;
		panel.add(sweetChili_num, gbc_sweetChili_num);
		
		JLabel doble = new JLabel("HAMBURGUESA DOBLE 30 ud");
		doble.setForeground(Color.WHITE);
		doble.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_doble = new GridBagConstraints();
		gbc_doble.fill = GridBagConstraints.BOTH;
		gbc_doble.insets = new Insets(0, 0, 5, 5);
		gbc_doble.gridx = 1;
		gbc_doble.gridy = 8;
		panel.add(doble, gbc_doble);
		
		JLabel doble_num = new JLabel("New label");
		doble_num.setForeground(Color.WHITE);
		doble_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_doble_num = new GridBagConstraints();
		gbc_doble_num.insets = new Insets(0, 0, 5, 5);
		gbc_doble_num.gridx = 2;
		gbc_doble_num.gridy = 8;
		panel.add(doble_num, gbc_doble_num);
		
		JLabel vegana = new JLabel("MAYONESA VEGANA bolsa 500 g");
		vegana.setForeground(Color.WHITE);
		vegana.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_vegana = new GridBagConstraints();
		gbc_vegana.fill = GridBagConstraints.BOTH;
		gbc_vegana.insets = new Insets(0, 0, 5, 5);
		gbc_vegana.gridx = 3;
		gbc_vegana.gridy = 8;
		panel.add(vegana, gbc_vegana);
		
		JLabel vegana_num = new JLabel();
		vegana_num.setForeground(Color.WHITE);
		vegana_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_vegana_num = new GridBagConstraints();
		gbc_vegana_num.fill = GridBagConstraints.VERTICAL;
		gbc_vegana_num.insets = new Insets(0, 0, 5, 0);
		gbc_vegana_num.gridx = 4;
		gbc_vegana_num.gridy = 8;
		panel.add(vegana_num, gbc_vegana_num);
		
		JLabel entrantesTitulo = new JLabel("ENTRANTES Y GUARNICIONES");
		entrantesTitulo.setForeground(new Color(248, 248, 255));
		entrantesTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_entrantesTitulo = new GridBagConstraints();
		gbc_entrantesTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_entrantesTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_entrantesTitulo.gridx = 1;
		gbc_entrantesTitulo.gridy = 9;
		panel.add(entrantesTitulo, gbc_entrantesTitulo);
		
		JLabel bbq = new JLabel("SALSA BBQ bolsa 2 kg");
		bbq.setForeground(Color.WHITE);
		bbq.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_bbq = new GridBagConstraints();
		gbc_bbq.fill = GridBagConstraints.BOTH;
		gbc_bbq.insets = new Insets(0, 0, 5, 5);
		gbc_bbq.gridx = 3;
		gbc_bbq.gridy = 9;
		panel.add(bbq, gbc_bbq);
		
		JLabel bbq_num = new JLabel();
		bbq_num.setForeground(Color.WHITE);
		bbq_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_bbq_num = new GridBagConstraints();
		gbc_bbq_num.fill = GridBagConstraints.VERTICAL;
		gbc_bbq_num.insets = new Insets(0, 0, 5, 0);
		gbc_bbq_num.gridx = 4;
		gbc_bbq_num.gridy = 9;
		panel.add(bbq_num, gbc_bbq_num);
		
		JLabel alitas = new JLabel("ALITAS DE POLLO bolsa 2 kg");
		alitas.setForeground(Color.WHITE);
		alitas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_alitas = new GridBagConstraints();
		gbc_alitas.fill = GridBagConstraints.BOTH;
		gbc_alitas.insets = new Insets(0, 0, 5, 5);
		gbc_alitas.gridx = 1;
		gbc_alitas.gridy = 10;
		panel.add(alitas, gbc_alitas);
		
		JLabel alitas_num = new JLabel();
		alitas_num.setForeground(Color.WHITE);
		alitas_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_alitas_num = new GridBagConstraints();
		gbc_alitas_num.fill = GridBagConstraints.VERTICAL;
		gbc_alitas_num.insets = new Insets(0, 0, 5, 5);
		gbc_alitas_num.gridx = 2;
		gbc_alitas_num.gridy = 10;
		panel.add(alitas_num, gbc_alitas_num);
		
		JLabel toffee = new JLabel("SALSA BBQ TOFFEE bolsa 1 kg");
		toffee.setForeground(Color.WHITE);
		toffee.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_toffee = new GridBagConstraints();
		gbc_toffee.fill = GridBagConstraints.BOTH;
		gbc_toffee.insets = new Insets(0, 0, 5, 5);
		gbc_toffee.gridx = 3;
		gbc_toffee.gridy = 10;
		panel.add(toffee, gbc_toffee);
		
		JLabel toffee_num = new JLabel();
		toffee_num.setForeground(Color.WHITE);
		toffee_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_toffee_num = new GridBagConstraints();
		gbc_toffee_num.fill = GridBagConstraints.VERTICAL;
		gbc_toffee_num.insets = new Insets(0, 0, 5, 0);
		gbc_toffee_num.gridx = 4;
		gbc_toffee_num.gridy = 10;
		panel.add(toffee_num, gbc_toffee_num);
		
		JLabel bacon = new JLabel("BACON CRUJIENTE bolsa 400 gr");
		bacon.setForeground(Color.WHITE);
		bacon.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_bacon = new GridBagConstraints();
		gbc_bacon.fill = GridBagConstraints.BOTH;
		gbc_bacon.insets = new Insets(0, 0, 5, 5);
		gbc_bacon.gridx = 1;
		gbc_bacon.gridy = 11;
		panel.add(bacon, gbc_bacon);
		
		JLabel bacon_num = new JLabel();
		bacon_num.setForeground(Color.WHITE);
		bacon_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_bacon_num = new GridBagConstraints();
		gbc_bacon_num.fill = GridBagConstraints.VERTICAL;
		gbc_bacon_num.insets = new Insets(0, 0, 5, 5);
		gbc_bacon_num.gridx = 2;
		gbc_bacon_num.gridy = 11;
		panel.add(bacon_num, gbc_bacon_num);
		
		JLabel callejera = new JLabel("SALSA CALLEJERA bolsa 2 kg");
		callejera.setForeground(Color.WHITE);
		callejera.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_callejera = new GridBagConstraints();
		gbc_callejera.fill = GridBagConstraints.BOTH;
		gbc_callejera.insets = new Insets(0, 0, 5, 5);
		gbc_callejera.gridx = 3;
		gbc_callejera.gridy = 11;
		panel.add(callejera, gbc_callejera);
		
		JLabel callejera_num = new JLabel();
		callejera_num.setForeground(Color.WHITE);
		callejera_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_callejera_num = new GridBagConstraints();
		gbc_callejera_num.fill = GridBagConstraints.VERTICAL;
		gbc_callejera_num.insets = new Insets(0, 0, 5, 0);
		gbc_callejera_num.gridx = 4;
		gbc_callejera_num.gridy = 11;
		panel.add(callejera_num, gbc_callejera_num);
		
		JLabel cebollaVino = new JLabel("CEBOLLA AL VINO bolsa 1,5 kg");
		cebollaVino.setForeground(Color.WHITE);
		cebollaVino.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_cebollaVino = new GridBagConstraints();
		gbc_cebollaVino.fill = GridBagConstraints.BOTH;
		gbc_cebollaVino.insets = new Insets(0, 0, 5, 5);
		gbc_cebollaVino.gridx = 1;
		gbc_cebollaVino.gridy = 12;
		panel.add(cebollaVino, gbc_cebollaVino);
		
		JLabel cebolla_num = new JLabel();
		cebolla_num.setForeground(Color.WHITE);
		cebolla_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_cebolla_num = new GridBagConstraints();
		gbc_cebolla_num.fill = GridBagConstraints.VERTICAL;
		gbc_cebolla_num.insets = new Insets(0, 0, 5, 5);
		gbc_cebolla_num.gridx = 2;
		gbc_cebolla_num.gridy = 12;
		panel.add(cebolla_num, gbc_cebolla_num);
		
		JLabel cesar = new JLabel("SALSA C\u00C9SAR bolsa 1 kg");
		cesar.setForeground(Color.WHITE);
		cesar.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_cesar = new GridBagConstraints();
		gbc_cesar.fill = GridBagConstraints.BOTH;
		gbc_cesar.insets = new Insets(0, 0, 5, 5);
		gbc_cesar.gridx = 3;
		gbc_cesar.gridy = 12;
		panel.add(cesar, gbc_cesar);
		
		JLabel cesar_num = new JLabel();
		cesar_num.setForeground(Color.WHITE);
		cesar_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_cesar_num = new GridBagConstraints();
		gbc_cesar_num.fill = GridBagConstraints.VERTICAL;
		gbc_cesar_num.insets = new Insets(0, 0, 5, 0);
		gbc_cesar_num.gridx = 4;
		gbc_cesar_num.gridy = 12;
		panel.add(cesar_num, gbc_cesar_num);
		
		JLabel portobello = new JLabel("CHAMPI\u00D1ON PORTOBELLO bolsa 1 kg");
		portobello.setForeground(Color.WHITE);
		portobello.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_portobello = new GridBagConstraints();
		gbc_portobello.fill = GridBagConstraints.BOTH;
		gbc_portobello.insets = new Insets(0, 0, 5, 5);
		gbc_portobello.gridx = 1;
		gbc_portobello.gridy = 13;
		panel.add(portobello, gbc_portobello);
		
		JLabel portobello_num = new JLabel();
		portobello_num.setForeground(Color.WHITE);
		portobello_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_portobello_num = new GridBagConstraints();
		gbc_portobello_num.fill = GridBagConstraints.VERTICAL;
		gbc_portobello_num.insets = new Insets(0, 0, 5, 5);
		gbc_portobello_num.gridx = 2;
		gbc_portobello_num.gridy = 13;
		panel.add(portobello_num, gbc_portobello_num);
		
		JLabel salsaAlitas = new JLabel("SALSA DE ALITAS bolsa 2 kg");
		salsaAlitas.setForeground(Color.WHITE);
		salsaAlitas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_salsaAlitas = new GridBagConstraints();
		gbc_salsaAlitas.fill = GridBagConstraints.BOTH;
		gbc_salsaAlitas.insets = new Insets(0, 0, 5, 5);
		gbc_salsaAlitas.gridx = 3;
		gbc_salsaAlitas.gridy = 13;
		panel.add(salsaAlitas, gbc_salsaAlitas);
		
		JLabel salsaAlitas_num = new JLabel();
		salsaAlitas_num.setForeground(Color.WHITE);
		salsaAlitas_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_salsaAlitas_num = new GridBagConstraints();
		gbc_salsaAlitas_num.fill = GridBagConstraints.VERTICAL;
		gbc_salsaAlitas_num.insets = new Insets(0, 0, 5, 0);
		gbc_salsaAlitas_num.gridx = 4;
		gbc_salsaAlitas_num.gridy = 13;
		panel.add(salsaAlitas_num, gbc_salsaAlitas_num);
		
		JLabel costillas = new JLabel("COSTILLAS DE CERDO bolsa 2 kg");
		costillas.setForeground(Color.WHITE);
		costillas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_costillas = new GridBagConstraints();
		gbc_costillas.fill = GridBagConstraints.BOTH;
		gbc_costillas.insets = new Insets(0, 0, 5, 5);
		gbc_costillas.gridx = 1;
		gbc_costillas.gridy = 14;
		panel.add(costillas, gbc_costillas);
		
		JLabel costillas_num = new JLabel();
		costillas_num.setForeground(Color.WHITE);
		costillas_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_costillas_num = new GridBagConstraints();
		gbc_costillas_num.fill = GridBagConstraints.VERTICAL;
		gbc_costillas_num.insets = new Insets(0, 0, 5, 5);
		gbc_costillas_num.gridx = 2;
		gbc_costillas_num.gridy = 14;
		panel.add(costillas_num, gbc_costillas_num);
		
		JLabel ensCol = new JLabel("VINAGRETA PARA ENSALADA DE COL bolsa 1 kg");
		ensCol.setForeground(Color.WHITE);
		ensCol.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_ensCol = new GridBagConstraints();
		gbc_ensCol.fill = GridBagConstraints.BOTH;
		gbc_ensCol.insets = new Insets(0, 0, 5, 5);
		gbc_ensCol.gridx = 3;
		gbc_ensCol.gridy = 14;
		panel.add(ensCol, gbc_ensCol);
		
		JLabel ensCol_num = new JLabel();
		ensCol_num.setForeground(Color.WHITE);
		ensCol_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_ensCol_num = new GridBagConstraints();
		gbc_ensCol_num.fill = GridBagConstraints.VERTICAL;
		gbc_ensCol_num.insets = new Insets(0, 0, 5, 0);
		gbc_ensCol_num.gridx = 4;
		gbc_ensCol_num.gridy = 14;
		panel.add(ensCol_num, gbc_ensCol_num);
		
		JLabel brocheta = new JLabel("ENTRA\u00D1A PARA BROCHETA bolsa 500 g");
		brocheta.setForeground(Color.WHITE);
		brocheta.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_brocheta = new GridBagConstraints();
		gbc_brocheta.fill = GridBagConstraints.BOTH;
		gbc_brocheta.insets = new Insets(0, 0, 5, 5);
		gbc_brocheta.gridx = 1;
		gbc_brocheta.gridy = 15;
		panel.add(brocheta, gbc_brocheta);
		
		JLabel brocheta_num = new JLabel();
		brocheta_num.setForeground(Color.WHITE);
		brocheta_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_brocheta_num = new GridBagConstraints();
		gbc_brocheta_num.fill = GridBagConstraints.VERTICAL;
		gbc_brocheta_num.insets = new Insets(0, 0, 5, 5);
		gbc_brocheta_num.gridx = 2;
		gbc_brocheta_num.gridy = 15;
		panel.add(brocheta_num, gbc_brocheta_num);
		
		JLabel bocadillosTitulo = new JLabel("BOCADILLOS");
		bocadillosTitulo.setForeground(new Color(248, 248, 255));
		bocadillosTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_bocadillosTitulo = new GridBagConstraints();
		gbc_bocadillosTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_bocadillosTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_bocadillosTitulo.gridx = 3;
		gbc_bocadillosTitulo.gridy = 15;
		panel.add(bocadillosTitulo, gbc_bocadillosTitulo);
		
		JLabel fingers = new JLabel("FINGERS DE POLLO bolsa 2 kg");
		fingers.setForeground(Color.WHITE);
		fingers.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_fingers = new GridBagConstraints();
		gbc_fingers.fill = GridBagConstraints.BOTH;
		gbc_fingers.insets = new Insets(0, 0, 5, 5);
		gbc_fingers.gridx = 1;
		gbc_fingers.gridy = 16;
		panel.add(fingers, gbc_fingers);
		
		JLabel fingers_num = new JLabel();
		fingers_num.setForeground(Color.WHITE);
		fingers_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_fingers_num = new GridBagConstraints();
		gbc_fingers_num.fill = GridBagConstraints.VERTICAL;
		gbc_fingers_num.insets = new Insets(0, 0, 5, 5);
		gbc_fingers_num.gridx = 2;
		gbc_fingers_num.gridy = 16;
		panel.add(fingers_num, gbc_fingers_num);
		
		JLabel pibil = new JLabel("COCHINITA PIBIL bolsa 1 kg");
		pibil.setForeground(Color.WHITE);
		pibil.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_pibil = new GridBagConstraints();
		gbc_pibil.fill = GridBagConstraints.BOTH;
		gbc_pibil.insets = new Insets(0, 0, 5, 5);
		gbc_pibil.gridx = 3;
		gbc_pibil.gridy = 16;
		panel.add(pibil, gbc_pibil);
		
		JLabel pibil_num = new JLabel();
		pibil_num.setForeground(Color.WHITE);
		pibil_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_pibil_num = new GridBagConstraints();
		gbc_pibil_num.fill = GridBagConstraints.VERTICAL;
		gbc_pibil_num.insets = new Insets(0, 0, 5, 0);
		gbc_pibil_num.gridx = 4;
		gbc_pibil_num.gridy = 16;
		panel.add(pibil_num, gbc_pibil_num);
		
		JLabel pimiento = new JLabel("PIMIENTO CONFITADO 12 ud");
		pimiento.setForeground(Color.WHITE);
		pimiento.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_pimiento = new GridBagConstraints();
		gbc_pimiento.fill = GridBagConstraints.BOTH;
		gbc_pimiento.insets = new Insets(0, 0, 5, 5);
		gbc_pimiento.gridx = 1;
		gbc_pimiento.gridy = 17;
		panel.add(pimiento, gbc_pimiento);
		
		JLabel pimiento_num = new JLabel();
		pimiento_num.setForeground(Color.WHITE);
		pimiento_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_pimiento_num = new GridBagConstraints();
		gbc_pimiento_num.fill = GridBagConstraints.VERTICAL;
		gbc_pimiento_num.insets = new Insets(0, 0, 5, 5);
		gbc_pimiento_num.gridx = 2;
		gbc_pimiento_num.gridy = 17;
		panel.add(pimiento_num, gbc_pimiento_num);
		
		JLabel entrecot = new JLabel("ENTRECOT MARINADO bolsa 500 g");
		entrecot.setForeground(Color.WHITE);
		entrecot.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_entrecot = new GridBagConstraints();
		gbc_entrecot.fill = GridBagConstraints.BOTH;
		gbc_entrecot.insets = new Insets(0, 0, 5, 5);
		gbc_entrecot.gridx = 3;
		gbc_entrecot.gridy = 17;
		panel.add(entrecot, gbc_entrecot);
		
		JLabel entrecot_num = new JLabel();
		entrecot_num.setForeground(Color.WHITE);
		entrecot_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_entrecot_num = new GridBagConstraints();
		gbc_entrecot_num.insets = new Insets(0, 0, 5, 0);
		gbc_entrecot_num.fill = GridBagConstraints.VERTICAL;
		gbc_entrecot_num.gridx = 4;
		gbc_entrecot_num.gridy = 17;
		panel.add(entrecot_num, gbc_entrecot_num);
		
		JLabel pollo = new JLabel("POLLO MARINADO bandeja 2,5 kg");
		pollo.setForeground(Color.WHITE);
		pollo.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_pollo = new GridBagConstraints();
		gbc_pollo.anchor = GridBagConstraints.WEST;
		gbc_pollo.insets = new Insets(0, 0, 5, 5);
		gbc_pollo.gridx = 1;
		gbc_pollo.gridy = 18;
		panel.add(pollo, gbc_pollo);
		
		JLabel pollo_num = new JLabel();
		pollo_num.setForeground(Color.WHITE);
		pollo_num.setFont(new Font("Dialog", Font.BOLD, 22));
		pollo_num.setEnabled(true);
		GridBagConstraints gbc_pollo_num = new GridBagConstraints();
		gbc_pollo_num.fill = GridBagConstraints.VERTICAL;
		gbc_pollo_num.insets = new Insets(0, 0, 5, 5);
		gbc_pollo_num.gridx = 2;
		gbc_pollo_num.gridy = 18;
		panel.add(pollo_num, gbc_pollo_num);
		
		JLabel salchichas = new JLabel("SALCHICHAS bolsa 8 ud");
		salchichas.setForeground(Color.WHITE);
		salchichas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_salchichas = new GridBagConstraints();
		gbc_salchichas.anchor = GridBagConstraints.WEST;
		gbc_salchichas.insets = new Insets(0, 0, 5, 5);
		gbc_salchichas.gridx = 3;
		gbc_salchichas.gridy = 18;
		panel.add(salchichas, gbc_salchichas);
		
		JLabel salchichas_num = new JLabel();
		salchichas_num.setForeground(Color.WHITE);
		salchichas_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_salchichas_num = new GridBagConstraints();
		gbc_salchichas_num.fill = GridBagConstraints.VERTICAL;
		gbc_salchichas_num.insets = new Insets(0, 0, 5, 0);
		gbc_salchichas_num.gridx = 4;
		gbc_salchichas_num.gridy = 18;
		panel.add(salchichas_num, gbc_salchichas_num);
		
		JLabel calambrito = new JLabel("SALSA CALAMBRITO bolsa 2 kg");
		calambrito.setForeground(Color.WHITE);
		calambrito.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_calambrito = new GridBagConstraints();
		gbc_calambrito.anchor = GridBagConstraints.WEST;
		gbc_calambrito.insets = new Insets(0, 0, 5, 5);
		gbc_calambrito.gridx = 1;
		gbc_calambrito.gridy = 19;
		panel.add(calambrito, gbc_calambrito);
		
		JLabel calambrito_num = new JLabel();
		calambrito_num.setForeground(Color.WHITE);
		calambrito_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_calambrito_num = new GridBagConstraints();
		gbc_calambrito_num.fill = GridBagConstraints.VERTICAL;
		gbc_calambrito_num.insets = new Insets(0, 0, 5, 5);
		gbc_calambrito_num.gridx = 2;
		gbc_calambrito_num.gridy = 19;
		panel.add(calambrito_num, gbc_calambrito_num);
		
		JLabel lblPostres = new JLabel("POSTRES");
		lblPostres.setForeground(new Color(248, 248, 255));
		lblPostres.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblPostres = new GridBagConstraints();
		gbc_lblPostres.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostres.gridx = 3;
		gbc_lblPostres.gridy = 19;
		panel.add(lblPostres, gbc_lblPostres);
		
		JLabel oreo = new JLabel("TARTA OREO caja 16 ud");
		oreo.setForeground(Color.WHITE);
		oreo.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_oreo = new GridBagConstraints();
		gbc_oreo.anchor = GridBagConstraints.WEST;
		gbc_oreo.insets = new Insets(0, 0, 5, 5);
		gbc_oreo.gridx = 3;
		gbc_oreo.gridy = 20;
		panel.add(oreo, gbc_oreo);
		
		JLabel oreo_num = new JLabel();
		oreo_num.setForeground(Color.WHITE);
		oreo_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_oreo_num = new GridBagConstraints();
		gbc_oreo_num.fill = GridBagConstraints.VERTICAL;
		gbc_oreo_num.insets = new Insets(0, 0, 5, 0);
		gbc_oreo_num.gridx = 4;
		gbc_oreo_num.gridy = 20;
		panel.add(oreo_num, gbc_oreo_num);
		
		
		
		
		JLabel tartaQueso = new JLabel("TARTA QUESO caja 16 ud");
		tartaQueso.setForeground(Color.WHITE);
		tartaQueso.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_tartaQueso = new GridBagConstraints();
		gbc_tartaQueso.anchor = GridBagConstraints.WEST;
		gbc_tartaQueso.insets = new Insets(0, 0, 5, 5);
		gbc_tartaQueso.gridx = 3;
		gbc_tartaQueso.gridy = 21;
		panel.add(tartaQueso, gbc_tartaQueso);
		
		JLabel tartaQueso_num = new JLabel();
		tartaQueso_num.setForeground(Color.WHITE);
		tartaQueso_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_tartaQueso_num = new GridBagConstraints();
		gbc_tartaQueso_num.fill = GridBagConstraints.VERTICAL;
		gbc_tartaQueso_num.insets = new Insets(0, 0, 5, 0);
		gbc_tartaQueso_num.gridx = 4;
		gbc_tartaQueso_num.gridy = 21;
		panel.add(tartaQueso_num, gbc_tartaQueso_num);
		
		JLabel sacher = new JLabel("TARTA SACHER caja 20 ud");
		sacher.setForeground(Color.WHITE);
		sacher.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_sacher = new GridBagConstraints();
		gbc_sacher.anchor = GridBagConstraints.WEST;
		gbc_sacher.insets = new Insets(0, 0, 5, 5);
		gbc_sacher.gridx = 3;
		gbc_sacher.gridy = 22;
		panel.add(sacher, gbc_sacher);
		
		JLabel sacher_num = new JLabel();
		sacher_num.setForeground(Color.WHITE);
		sacher_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_sacher_num = new GridBagConstraints();
		gbc_sacher_num.fill = GridBagConstraints.VERTICAL;
		gbc_sacher_num.insets = new Insets(0, 0, 5, 0);
		gbc_sacher_num.gridx = 4;
		gbc_sacher_num.gridy = 22;
		panel.add(sacher_num, gbc_sacher_num);
		
		JLabel zanahoria = new JLabel("TARTA ZANAHORIA caja 20 ud");
		zanahoria.setForeground(Color.WHITE);
		zanahoria.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_zanahoria = new GridBagConstraints();
		gbc_zanahoria.anchor = GridBagConstraints.WEST;
		gbc_zanahoria.insets = new Insets(0, 0, 5, 5);
		gbc_zanahoria.gridx = 3;
		gbc_zanahoria.gridy = 23;
		panel.add(zanahoria, gbc_zanahoria);
		
		JLabel zanahoria_num = new JLabel();
		zanahoria_num.setForeground(Color.WHITE);
		zanahoria_num.setFont(new Font("Dialog", Font.BOLD, 22));
		GridBagConstraints gbc_zanahoria_num = new GridBagConstraints();
		gbc_zanahoria_num.insets = new Insets(0, 0, 5, 0);
		gbc_zanahoria_num.fill = GridBagConstraints.VERTICAL;
		gbc_zanahoria_num.gridx = 4;
		gbc_zanahoria_num.gridy = 23;
		panel.add(zanahoria_num, gbc_zanahoria_num);
		
		JButton btnEnviar = new JButton("Enviar pedido");
		btnEnviar.setFont(new Font("SansSerif", Font.BOLD, 18));
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.gridheight = 1;
		gbc_btnEnviar.gridwidth = 4;
		gbc_btnEnviar.fill = GridBagConstraints.BOTH;
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 25;
		panel.add(btnEnviar, gbc_btnEnviar);
		
		int cantidad[]=pedido.getCantidad();
		
					aguja_num.setText(String.valueOf(cantidad[0]));
					angus_num.setText(String.valueOf(cantidad[1]));
					entrana_num.setText(String.valueOf(cantidad[2]));
					garbanzos_num.setText(String.valueOf(cantidad[3]));
					lentejas_num.setText(String.valueOf(cantidad[4]));
					vacio_num.setText(String.valueOf(cantidad[5]));
					doble_num.setText(String.valueOf(cantidad[6]));
					alitas_num.setText(String.valueOf(cantidad[7]));
					bacon_num.setText(String.valueOf(cantidad[8]));
					cebolla_num.setText(String.valueOf(cantidad[9]));
					portobello_num.setText(String.valueOf(cantidad[10]));
					costillas_num.setText(String.valueOf(cantidad[11]));
					brocheta_num.setText(String.valueOf(cantidad[12]));
					fingers_num.setText(String.valueOf(cantidad[13]));
					pimiento_num.setText(String.valueOf(cantidad[14]));
					pollo_num.setText(String.valueOf(cantidad[15]));
					calambrito_num.setText(String.valueOf(cantidad[16]));
					salteado_num.setText(String.valueOf(cantidad[17]));
					ketchup_num.setText(String.valueOf(cantidad[18]));
					lacalle_num.setText(String.valueOf(cantidad[19]));
					chimichurri_num.setText(String.valueOf(cantidad[20]));
					trufa_num.setText(String.valueOf(cantidad[21]));
					sweetChili_num.setText(String.valueOf(cantidad[22]));
					vegana_num.setText(String.valueOf(cantidad[23]));
					bbq_num.setText(String.valueOf(cantidad[24]));
					toffee_num.setText(String.valueOf(cantidad[25]));
					callejera_num.setText(String.valueOf(cantidad[26]));
					cesar_num.setText(String.valueOf(cantidad[27]));
					salsaAlitas_num.setText(String.valueOf(cantidad[28]));
					ensCol_num.setText(String.valueOf(cantidad[29]));
					pibil_num.setText(String.valueOf(cantidad[30]));
					entrecot_num.setText(String.valueOf(cantidad[31]));
					salchichas_num.setText(String.valueOf(cantidad[32]));
					oreo_num.setText(String.valueOf(cantidad[33]));
					tartaQueso_num.setText(String.valueOf(cantidad[34]));
					sacher_num.setText(String.valueOf(cantidad[35]));
					zanahoria_num.setText(String.valueOf(cantidad[36]));
					
					//Pedido nuevo=new Pedido(usuario,ventana.getConexion(),cantidad);
					//nuevo.nuevoPedido();
					btnEnviar.setText("Tu pedido ha sido enviado!");
					btnEnviar.setEnabled(false);
		
		
		
		/*try {
			
            
            
            Statement smt=conexion.createStatement();
            ResultSet resultado=smt.executeQuery("select id from pedido where Usuario_Restaurante_codigoRestaurante='CENTRO';");
            Statement smt2=conexion.createStatement();
            ResultSet resultado2;
            String id="";
            while(resultado.next()){
                id=resultado.getString("id");
               resultado2=smt2.executeQuery("select producto.*,productospedidos.*,pedido.*\n" +
                "from productospedidos\n" +
                " join producto on producto.id=productospedidos.producto_id\n" +
                " join pedido on productospedidos.Pedido_id=pedido.id\n" +
                "where pedido.id='"+id+"';"); 
               
               while(resultado2.next()){
                   String nombre=resultado2.getString("nombre");
                   String precio=resultado2.getString("precio");
                   String cantidadUnidad=resultado2.getString("cantidadPorUnidad");
                   String unidad=resultado2.getString("unidadDeMedida");
                   String cantidad=resultado2.getString("cantidad");
                   String fecha=resultado2.getString("fecha");
                   
                 
                   
               }
            }
            
            
            
         
    
} catch (SQLException ex) {
    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
}
*/
		
	}

}

