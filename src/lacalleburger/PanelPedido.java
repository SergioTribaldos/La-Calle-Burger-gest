package lacalleburger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import pedidos.Pedido;
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

public class PanelPedido extends JPanel{
	public PanelPedido(Usuario usuario,Connection conexion,JFrame ventana) {
		setBackground(Color.BLACK);
		this.setVisible(true);
		this.setSize(980,900);
		ventana.setSize(this.getSize());
		setLayout(new BorderLayout(0, 0));
		
		JLabel titulo = new JLabel("Usuario: "+usuario.getUsuario()+" Restaurante: "+usuario.getRestaurante().getNombre());
		titulo.setBackground(Color.BLACK);
		titulo.setForeground(new Color(248, 248, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 25));
		add(titulo, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {391, 72, 391, 72};
		gbl_panel.rowHeights = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel hamburguesasTitulo = new JLabel("HAMBURGUESAS");
		hamburguesasTitulo.setForeground(new Color(248, 248, 255));
		hamburguesasTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_hamburguesasTitulo = new GridBagConstraints();
		gbc_hamburguesasTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_hamburguesasTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_hamburguesasTitulo.gridx = 0;
		gbc_hamburguesasTitulo.gridy = 0;
		panel.add(hamburguesasTitulo, gbc_hamburguesasTitulo);
		 
		
		JLabel salsasTitulo = new JLabel("SALSAS (bolsas)");
		salsasTitulo.setForeground(new Color(248, 248, 255));
		salsasTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_salsasTitulo = new GridBagConstraints();
		gbc_salsasTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_salsasTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_salsasTitulo.gridx = 2;
		gbc_salsasTitulo.gridy = 0;
		panel.add(salsasTitulo, gbc_salsasTitulo);
		
		JLabel aguja = new JLabel("HAMBURGUESA DE AGUJA 20 ud");
		aguja.setForeground(new Color(248, 248, 255));
		aguja.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_aguja = new GridBagConstraints();
		gbc_aguja.fill = GridBagConstraints.BOTH;
		gbc_aguja.insets = new Insets(0, 0, 5, 5);
		gbc_aguja.gridx = 0;
		gbc_aguja.gridy = 1;
		panel.add(aguja, gbc_aguja);
		
		JSpinner aguja_num = new JSpinner();
		aguja_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(20)));
		aguja_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_aguja_num = new GridBagConstraints();
		gbc_aguja_num.fill = GridBagConstraints.BOTH;
		gbc_aguja_num.insets = new Insets(0, 0, 5, 5);
		gbc_aguja_num.gridx = 1;
		gbc_aguja_num.gridy = 1;
		panel.add(aguja_num, gbc_aguja_num);
		
		
		JLabel salteado = new JLabel("CALDO PARA SALTEADO bolsa 1 kg");
		salteado.setForeground(new Color(248, 248, 255));
		salteado.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_salteado = new GridBagConstraints();
		gbc_salteado.fill = GridBagConstraints.BOTH;
		gbc_salteado.insets = new Insets(0, 0, 5, 5);
		gbc_salteado.gridx = 2;
		gbc_salteado.gridy = 1;
		panel.add(salteado, gbc_salteado);
		
		JSpinner salteado_num = new JSpinner();
		salteado_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		salteado_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_salteado_num = new GridBagConstraints();
		gbc_salteado_num.fill = GridBagConstraints.BOTH;
		gbc_salteado_num.insets = new Insets(0, 0, 5, 0);
		gbc_salteado_num.gridx = 3;
		gbc_salteado_num.gridy = 1;
		panel.add(salteado_num, gbc_salteado_num);
		
		JLabel angus = new JLabel("HAMBURGUESA DE ANGUS 20 ud");
		angus.setForeground(new Color(248, 248, 255));
		angus.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_angus = new GridBagConstraints();
		gbc_angus.fill = GridBagConstraints.BOTH;
		gbc_angus.insets = new Insets(0, 0, 5, 5);
		gbc_angus.gridx = 0;
		gbc_angus.gridy = 2;
		panel.add(angus, gbc_angus);
		
		JSpinner angus_num = new JSpinner();
		angus_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(20)));
		angus_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_angus_num = new GridBagConstraints();
		gbc_angus_num.fill = GridBagConstraints.BOTH;
		gbc_angus_num.insets = new Insets(0, 0, 5, 5);
		gbc_angus_num.gridx = 1;
		gbc_angus_num.gridy = 2;
		panel.add(angus_num, gbc_angus_num);
		
		JLabel ketchup = new JLabel("KETCHUP LA CALLE bolsa 1 kg");
		ketchup.setForeground(new Color(248, 248, 255));
		ketchup.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_ketchup = new GridBagConstraints();
		gbc_ketchup.fill = GridBagConstraints.BOTH;
		gbc_ketchup.insets = new Insets(0, 0, 5, 5);
		gbc_ketchup.gridx = 2;
		gbc_ketchup.gridy = 2;
		panel.add(ketchup, gbc_ketchup);
		
		JSpinner ketchup_num = new JSpinner();
		ketchup_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		ketchup_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_ketchup_num = new GridBagConstraints();
		gbc_ketchup_num.fill = GridBagConstraints.BOTH;
		gbc_ketchup_num.insets = new Insets(0, 0, 5, 0);
		gbc_ketchup_num.gridx = 3;
		gbc_ketchup_num.gridy = 2;
		panel.add(ketchup_num, gbc_ketchup_num);
		
		JLabel entrana = new JLabel("HAMBURGUESA DE ENTRA\u00D1A 20 ud");
		entrana.setForeground(new Color(248, 248, 255));
		entrana.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_entrana = new GridBagConstraints();
		gbc_entrana.fill = GridBagConstraints.BOTH;
		gbc_entrana.insets = new Insets(0, 0, 5, 5);
		gbc_entrana.gridx = 0;
		gbc_entrana.gridy = 3;
		panel.add(entrana, gbc_entrana);
		
		JSpinner entrana_num = new JSpinner();
		entrana_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(20)));
		entrana_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_entrana_num = new GridBagConstraints();
		gbc_entrana_num.fill = GridBagConstraints.BOTH;
		gbc_entrana_num.insets = new Insets(0, 0, 5, 5);
		gbc_entrana_num.gridx = 1;
		gbc_entrana_num.gridy = 3;
		panel.add(entrana_num, gbc_entrana_num);
		
		JLabel lacalle = new JLabel(" MAYONESA LA CALLE bolsa 2 kg");
		lacalle.setForeground(new Color(248, 248, 255));
		lacalle.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lacalle = new GridBagConstraints();
		gbc_lacalle.fill = GridBagConstraints.BOTH;
		gbc_lacalle.insets = new Insets(0, 0, 5, 5);
		gbc_lacalle.gridx = 2;
		gbc_lacalle.gridy = 3;
		panel.add(lacalle, gbc_lacalle);
		
		JSpinner lacalle_num = new JSpinner();
		lacalle_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		lacalle_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_lacalle_num = new GridBagConstraints();
		gbc_lacalle_num.fill = GridBagConstraints.BOTH;
		gbc_lacalle_num.insets = new Insets(0, 0, 5, 0);
		gbc_lacalle_num.gridx = 3;
		gbc_lacalle_num.gridy = 3;
		panel.add(lacalle_num, gbc_lacalle_num);
		
		JLabel garbanzos = new JLabel("HAMBURGUESA DE GARBANZOS 30 ud");
		garbanzos.setForeground(new Color(248, 248, 255));
		garbanzos.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_garbanzos = new GridBagConstraints();
		gbc_garbanzos.fill = GridBagConstraints.BOTH;
		gbc_garbanzos.insets = new Insets(0, 0, 5, 5);
		gbc_garbanzos.gridx = 0;
		gbc_garbanzos.gridy = 4;
		panel.add(garbanzos, gbc_garbanzos);
		
		JSpinner garbanzos_num = new JSpinner();
		garbanzos_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(30)));
		garbanzos_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_garbanzos_num = new GridBagConstraints();
		gbc_garbanzos_num.fill = GridBagConstraints.BOTH;
		gbc_garbanzos_num.insets = new Insets(0, 0, 5, 5);
		gbc_garbanzos_num.gridx = 1;
		gbc_garbanzos_num.gridy = 4;
		panel.add(garbanzos_num, gbc_garbanzos_num);
		
		JLabel chimichurri = new JLabel("MAYONESA DE CHIMICHURRI bolsa 2 kg");
		chimichurri.setForeground(new Color(248, 248, 255));
		chimichurri.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_chimichurri = new GridBagConstraints();
		gbc_chimichurri.fill = GridBagConstraints.BOTH;
		gbc_chimichurri.insets = new Insets(0, 0, 5, 5);
		gbc_chimichurri.gridx = 2;
		gbc_chimichurri.gridy = 4;
		panel.add(chimichurri, gbc_chimichurri);
		
		JSpinner chimichurri_num = new JSpinner();
		chimichurri_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		chimichurri_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_chimichurri_num = new GridBagConstraints();
		gbc_chimichurri_num.fill = GridBagConstraints.BOTH;
		gbc_chimichurri_num.insets = new Insets(0, 0, 5, 0);
		gbc_chimichurri_num.gridx = 3;
		gbc_chimichurri_num.gridy = 4;
		panel.add(chimichurri_num, gbc_chimichurri_num);
		
		JLabel lentejas = new JLabel("HAMBURGUESA DE LENTEJAS 30 ud");
		lentejas.setForeground(new Color(248, 248, 255));
		lentejas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lentejas = new GridBagConstraints();
		gbc_lentejas.fill = GridBagConstraints.BOTH;
		gbc_lentejas.insets = new Insets(0, 0, 5, 5);
		gbc_lentejas.gridx = 0;
		gbc_lentejas.gridy = 5;
		panel.add(lentejas, gbc_lentejas);
		
		JSpinner lentejas_num = new JSpinner();
		lentejas_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(30)));
		lentejas_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_lentejas_num = new GridBagConstraints();
		gbc_lentejas_num.fill = GridBagConstraints.BOTH;
		gbc_lentejas_num.insets = new Insets(0, 0, 5, 5);
		gbc_lentejas_num.gridx = 1;
		gbc_lentejas_num.gridy = 5;
		panel.add(lentejas_num, gbc_lentejas_num);
		
		JLabel trufa = new JLabel("MAYONESA DE PIMIENTA Y TRUFA bolsa 2 kg");
		trufa.setForeground(new Color(248, 248, 255));
		trufa.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_trufa = new GridBagConstraints();
		gbc_trufa.fill = GridBagConstraints.BOTH;
		gbc_trufa.insets = new Insets(0, 0, 5, 5);
		gbc_trufa.gridx = 2;
		gbc_trufa.gridy = 5;
		panel.add(trufa, gbc_trufa);
		
		JSpinner trufa_num = new JSpinner();
		trufa_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		trufa_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_trufa_num = new GridBagConstraints();
		gbc_trufa_num.fill = GridBagConstraints.BOTH;
		gbc_trufa_num.insets = new Insets(0, 0, 5, 0);
		gbc_trufa_num.gridx = 3;
		gbc_trufa_num.gridy = 5;
		panel.add(trufa_num, gbc_trufa_num);
		
		JLabel vacio = new JLabel("HAMBURGUESA DE VAC\u00CDO 20 ud");
		vacio.setForeground(new Color(248, 248, 255));
		vacio.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_vacio = new GridBagConstraints();
		gbc_vacio.fill = GridBagConstraints.BOTH;
		gbc_vacio.insets = new Insets(0, 0, 5, 5);
		gbc_vacio.gridx = 0;
		gbc_vacio.gridy = 6;
		panel.add(vacio, gbc_vacio);
		
		JSpinner vacio_num = new JSpinner();
		vacio_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(20)));
		vacio_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_vacio_num = new GridBagConstraints();
		gbc_vacio_num.fill = GridBagConstraints.BOTH;
		gbc_vacio_num.insets = new Insets(0, 0, 5, 5);
		gbc_vacio_num.gridx = 1;
		gbc_vacio_num.gridy = 6;
		panel.add(vacio_num, gbc_vacio_num);
		
		JLabel sweetChili = new JLabel("MAYONESA SWEET CHILI bolsa 1 kg");
		sweetChili.setForeground(new Color(248, 248, 255));
		sweetChili.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_sweetChili = new GridBagConstraints();
		gbc_sweetChili.fill = GridBagConstraints.BOTH;
		gbc_sweetChili.insets = new Insets(0, 0, 5, 5);
		gbc_sweetChili.gridx = 2;
		gbc_sweetChili.gridy = 6;
		panel.add(sweetChili, gbc_sweetChili);
		
		JSpinner sweetChili_num = new JSpinner();
		sweetChili_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		sweetChili_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_sweetChili_num = new GridBagConstraints();
		gbc_sweetChili_num.fill = GridBagConstraints.BOTH;
		gbc_sweetChili_num.insets = new Insets(0, 0, 5, 0);
		gbc_sweetChili_num.gridx = 3;
		gbc_sweetChili_num.gridy = 6;
		panel.add(sweetChili_num, gbc_sweetChili_num);
		
		JLabel doble = new JLabel("HAMBURGUESA DOBLE 30 ud");
		doble.setForeground(new Color(248, 248, 255));
		doble.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_doble = new GridBagConstraints();
		gbc_doble.fill = GridBagConstraints.BOTH;
		gbc_doble.insets = new Insets(0, 0, 5, 5);
		gbc_doble.gridx = 0;
		gbc_doble.gridy = 7;
		panel.add(doble, gbc_doble);
		
		JSpinner doble_num = new JSpinner();
		doble_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(30)));
		doble_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_doble_num = new GridBagConstraints();
		gbc_doble_num.fill = GridBagConstraints.BOTH;
		gbc_doble_num.insets = new Insets(0, 0, 5, 5);
		gbc_doble_num.gridx = 1;
		gbc_doble_num.gridy = 7;
		panel.add(doble_num, gbc_doble_num);
		
		JLabel vegana = new JLabel("MAYONESA VEGANA bolsa 500 g");
		vegana.setForeground(new Color(248, 248, 255));
		vegana.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_vegana = new GridBagConstraints();
		gbc_vegana.fill = GridBagConstraints.BOTH;
		gbc_vegana.insets = new Insets(0, 0, 5, 5);
		gbc_vegana.gridx = 2;
		gbc_vegana.gridy = 7;
		panel.add(vegana, gbc_vegana);
		
		JSpinner vegana_num = new JSpinner();
		vegana_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		vegana_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_vegana_num = new GridBagConstraints();
		gbc_vegana_num.fill = GridBagConstraints.BOTH;
		gbc_vegana_num.insets = new Insets(0, 0, 5, 0);
		gbc_vegana_num.gridx = 3;
		gbc_vegana_num.gridy = 7;
		panel.add(vegana_num, gbc_vegana_num);
		
		JLabel entrantesTitulo = new JLabel("ENTRANTES Y GUARNICIONES");
		entrantesTitulo.setForeground(new Color(248, 248, 255));
		entrantesTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_entrantesTitulo = new GridBagConstraints();
		gbc_entrantesTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_entrantesTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_entrantesTitulo.gridx = 0;
		gbc_entrantesTitulo.gridy = 8;
		panel.add(entrantesTitulo, gbc_entrantesTitulo);
		
		JLabel bbq = new JLabel("SALSA BBQ bolsa 2 kg");
		bbq.setForeground(new Color(248, 248, 255));
		bbq.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_bbq = new GridBagConstraints();
		gbc_bbq.fill = GridBagConstraints.BOTH;
		gbc_bbq.insets = new Insets(0, 0, 5, 5);
		gbc_bbq.gridx = 2;
		gbc_bbq.gridy = 8;
		panel.add(bbq, gbc_bbq);
		
		JSpinner bbq_num = new JSpinner();
		bbq_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		bbq_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_bbq_num = new GridBagConstraints();
		gbc_bbq_num.fill = GridBagConstraints.BOTH;
		gbc_bbq_num.insets = new Insets(0, 0, 5, 0);
		gbc_bbq_num.gridx = 3;
		gbc_bbq_num.gridy = 8;
		panel.add(bbq_num, gbc_bbq_num);
		
		JLabel alitas = new JLabel("ALITAS DE POLLO bolsa 2 kg");
		alitas.setForeground(new Color(248, 248, 255));
		alitas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_alitas = new GridBagConstraints();
		gbc_alitas.fill = GridBagConstraints.BOTH;
		gbc_alitas.insets = new Insets(0, 0, 5, 5);
		gbc_alitas.gridx = 0;
		gbc_alitas.gridy = 9;
		panel.add(alitas, gbc_alitas);
		
		JSpinner alitas_num = new JSpinner();
		alitas_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		alitas_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_alitas_num = new GridBagConstraints();
		gbc_alitas_num.fill = GridBagConstraints.BOTH;
		gbc_alitas_num.insets = new Insets(0, 0, 5, 5);
		gbc_alitas_num.gridx = 1;
		gbc_alitas_num.gridy = 9;
		panel.add(alitas_num, gbc_alitas_num);
		
		JLabel toffee = new JLabel("SALSA BBQ TOFFEE bolsa 1 kg");
		toffee.setForeground(new Color(248, 248, 255));
		toffee.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_toffee = new GridBagConstraints();
		gbc_toffee.fill = GridBagConstraints.BOTH;
		gbc_toffee.insets = new Insets(0, 0, 5, 5);
		gbc_toffee.gridx = 2;
		gbc_toffee.gridy = 9;
		panel.add(toffee, gbc_toffee);
		
		JSpinner toffee_num = new JSpinner();
		toffee_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		toffee_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_toffee_num = new GridBagConstraints();
		gbc_toffee_num.fill = GridBagConstraints.BOTH;
		gbc_toffee_num.insets = new Insets(0, 0, 5, 0);
		gbc_toffee_num.gridx = 3;
		gbc_toffee_num.gridy = 9;
		panel.add(toffee_num, gbc_toffee_num);
		
		JLabel bacon = new JLabel("BACON CRUJIENTE bolsa 400 gr");
		bacon.setForeground(new Color(248, 248, 255));
		bacon.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_bacon = new GridBagConstraints();
		gbc_bacon.fill = GridBagConstraints.BOTH;
		gbc_bacon.insets = new Insets(0, 0, 5, 5);
		gbc_bacon.gridx = 0;
		gbc_bacon.gridy = 10;
		panel.add(bacon, gbc_bacon);
		
		JSpinner bacon_num = new JSpinner();
		bacon_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		bacon_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_bacon_num = new GridBagConstraints();
		gbc_bacon_num.fill = GridBagConstraints.BOTH;
		gbc_bacon_num.insets = new Insets(0, 0, 5, 5);
		gbc_bacon_num.gridx = 1;
		gbc_bacon_num.gridy = 10;
		panel.add(bacon_num, gbc_bacon_num);
		
		JLabel callejera = new JLabel("SALSA CALLEJERA bolsa 2 kg");
		callejera.setForeground(new Color(248, 248, 255));
		callejera.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_callejera = new GridBagConstraints();
		gbc_callejera.fill = GridBagConstraints.BOTH;
		gbc_callejera.insets = new Insets(0, 0, 5, 5);
		gbc_callejera.gridx = 2;
		gbc_callejera.gridy = 10;
		panel.add(callejera, gbc_callejera);
		
		JSpinner callejera_num = new JSpinner();
		callejera_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		callejera_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_callejera_num = new GridBagConstraints();
		gbc_callejera_num.fill = GridBagConstraints.BOTH;
		gbc_callejera_num.insets = new Insets(0, 0, 5, 0);
		gbc_callejera_num.gridx = 3;
		gbc_callejera_num.gridy = 10;
		panel.add(callejera_num, gbc_callejera_num);
		
		JLabel cebollaVino = new JLabel("CEBOLLA AL VINO bolsa 1,5 kg");
		cebollaVino.setForeground(new Color(248, 248, 255));
		cebollaVino.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_cebollaVino = new GridBagConstraints();
		gbc_cebollaVino.fill = GridBagConstraints.BOTH;
		gbc_cebollaVino.insets = new Insets(0, 0, 5, 5);
		gbc_cebollaVino.gridx = 0;
		gbc_cebollaVino.gridy = 11;
		panel.add(cebollaVino, gbc_cebollaVino);
		
		JSpinner cebolla_num = new JSpinner();
		cebolla_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		cebolla_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_cebolla_num = new GridBagConstraints();
		gbc_cebolla_num.fill = GridBagConstraints.BOTH;
		gbc_cebolla_num.insets = new Insets(0, 0, 5, 5);
		gbc_cebolla_num.gridx = 1;
		gbc_cebolla_num.gridy = 11;
		panel.add(cebolla_num, gbc_cebolla_num);
		
		JLabel cesar = new JLabel("SALSA C\u00C9SAR bolsa 1 kg");
		cesar.setForeground(new Color(248, 248, 255));
		cesar.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_cesar = new GridBagConstraints();
		gbc_cesar.fill = GridBagConstraints.BOTH;
		gbc_cesar.insets = new Insets(0, 0, 5, 5);
		gbc_cesar.gridx = 2;
		gbc_cesar.gridy = 11;
		panel.add(cesar, gbc_cesar);
		
		JSpinner cesar_num = new JSpinner();
		cesar_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		cesar_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_cesar_num = new GridBagConstraints();
		gbc_cesar_num.fill = GridBagConstraints.BOTH;
		gbc_cesar_num.insets = new Insets(0, 0, 5, 0);
		gbc_cesar_num.gridx = 3;
		gbc_cesar_num.gridy = 11;
		panel.add(cesar_num, gbc_cesar_num);
		
		JLabel portobello = new JLabel("CHAMPI\u00D1ON PORTOBELLO bolsa 1 kg");
		portobello.setForeground(new Color(248, 248, 255));
		portobello.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_portobello = new GridBagConstraints();
		gbc_portobello.fill = GridBagConstraints.BOTH;
		gbc_portobello.insets = new Insets(0, 0, 5, 5);
		gbc_portobello.gridx = 0;
		gbc_portobello.gridy = 12;
		panel.add(portobello, gbc_portobello);
		
		JSpinner portobello_num = new JSpinner();
		portobello_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		portobello_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_portobello_num = new GridBagConstraints();
		gbc_portobello_num.fill = GridBagConstraints.BOTH;
		gbc_portobello_num.insets = new Insets(0, 0, 5, 5);
		gbc_portobello_num.gridx = 1;
		gbc_portobello_num.gridy = 12;
		panel.add(portobello_num, gbc_portobello_num);
		
		JLabel salsaAlitas = new JLabel("SALSA DE ALITAS bolsa 2 kg");
		salsaAlitas.setForeground(new Color(248, 248, 255));
		salsaAlitas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_salsaAlitas = new GridBagConstraints();
		gbc_salsaAlitas.fill = GridBagConstraints.BOTH;
		gbc_salsaAlitas.insets = new Insets(0, 0, 5, 5);
		gbc_salsaAlitas.gridx = 2;
		gbc_salsaAlitas.gridy = 12;
		panel.add(salsaAlitas, gbc_salsaAlitas);
		
		JSpinner salsaAlitas_num = new JSpinner();
		salsaAlitas_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		salsaAlitas_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_salsaAlitas_num = new GridBagConstraints();
		gbc_salsaAlitas_num.fill = GridBagConstraints.BOTH;
		gbc_salsaAlitas_num.insets = new Insets(0, 0, 5, 0);
		gbc_salsaAlitas_num.gridx = 3;
		gbc_salsaAlitas_num.gridy = 12;
		panel.add(salsaAlitas_num, gbc_salsaAlitas_num);
		
		JLabel costillas = new JLabel("COSTILLAS DE CERDO bolsa 2 kg");
		costillas.setForeground(new Color(248, 248, 255));
		costillas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_costillas = new GridBagConstraints();
		gbc_costillas.fill = GridBagConstraints.BOTH;
		gbc_costillas.insets = new Insets(0, 0, 5, 5);
		gbc_costillas.gridx = 0;
		gbc_costillas.gridy = 13;
		panel.add(costillas, gbc_costillas);
		
		JSpinner costillas_num = new JSpinner();
		costillas_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		costillas_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_costillas_num = new GridBagConstraints();
		gbc_costillas_num.fill = GridBagConstraints.BOTH;
		gbc_costillas_num.insets = new Insets(0, 0, 5, 5);
		gbc_costillas_num.gridx = 1;
		gbc_costillas_num.gridy = 13;
		panel.add(costillas_num, gbc_costillas_num);
		
		JLabel ensCol = new JLabel("VINAGRETA PARA ENSALADA DE COL bolsa 1 kg");
		ensCol.setForeground(new Color(248, 248, 255));
		ensCol.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_ensCol = new GridBagConstraints();
		gbc_ensCol.fill = GridBagConstraints.BOTH;
		gbc_ensCol.insets = new Insets(0, 0, 5, 5);
		gbc_ensCol.gridx = 2;
		gbc_ensCol.gridy = 13;
		panel.add(ensCol, gbc_ensCol);
		
		JSpinner ensCol_num = new JSpinner();
		ensCol_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		ensCol_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_ensCol_num = new GridBagConstraints();
		gbc_ensCol_num.fill = GridBagConstraints.BOTH;
		gbc_ensCol_num.insets = new Insets(0, 0, 5, 0);
		gbc_ensCol_num.gridx = 3;
		gbc_ensCol_num.gridy = 13;
		panel.add(ensCol_num, gbc_ensCol_num);
		
		JLabel brocheta = new JLabel("ENTRA\u00D1A PARA BROCHETA bolsa 500 g");
		brocheta.setForeground(new Color(248, 248, 255));
		brocheta.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_brocheta = new GridBagConstraints();
		gbc_brocheta.fill = GridBagConstraints.BOTH;
		gbc_brocheta.insets = new Insets(0, 0, 5, 5);
		gbc_brocheta.gridx = 0;
		gbc_brocheta.gridy = 14;
		panel.add(brocheta, gbc_brocheta);
		
		JSpinner brocheta_num = new JSpinner();
		brocheta_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		brocheta_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_brocheta_num = new GridBagConstraints();
		gbc_brocheta_num.fill = GridBagConstraints.BOTH;
		gbc_brocheta_num.insets = new Insets(0, 0, 5, 5);
		gbc_brocheta_num.gridx = 1;
		gbc_brocheta_num.gridy = 14;
		panel.add(brocheta_num, gbc_brocheta_num);
		
		JLabel bocadillosTitulo = new JLabel("BOCADILLOS");
		bocadillosTitulo.setForeground(new Color(248, 248, 255));
		bocadillosTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_bocadillosTitulo = new GridBagConstraints();
		gbc_bocadillosTitulo.fill = GridBagConstraints.VERTICAL;
		gbc_bocadillosTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_bocadillosTitulo.gridx = 2;
		gbc_bocadillosTitulo.gridy = 14;
		panel.add(bocadillosTitulo, gbc_bocadillosTitulo);
		
		JLabel fingers = new JLabel("FINGERS DE POLLO bolsa 2 kg");
		fingers.setForeground(new Color(248, 248, 255));
		fingers.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_fingers = new GridBagConstraints();
		gbc_fingers.fill = GridBagConstraints.BOTH;
		gbc_fingers.insets = new Insets(0, 0, 5, 5);
		gbc_fingers.gridx = 0;
		gbc_fingers.gridy = 15;
		panel.add(fingers, gbc_fingers);
		
		JSpinner fingers_num = new JSpinner();
		fingers_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		fingers_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_fingers_num = new GridBagConstraints();
		gbc_fingers_num.fill = GridBagConstraints.BOTH;
		gbc_fingers_num.insets = new Insets(0, 0, 5, 5);
		gbc_fingers_num.gridx = 1;
		gbc_fingers_num.gridy = 15;
		panel.add(fingers_num, gbc_fingers_num);
		
		JLabel pibil = new JLabel("COCHINITA PIBIL bolsa 1 kg");
		pibil.setForeground(new Color(248, 248, 255));
		pibil.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_pibil = new GridBagConstraints();
		gbc_pibil.fill = GridBagConstraints.BOTH;
		gbc_pibil.insets = new Insets(0, 0, 5, 5);
		gbc_pibil.gridx = 2;
		gbc_pibil.gridy = 15;
		panel.add(pibil, gbc_pibil);
		
		JSpinner pibil_num = new JSpinner();
		pibil_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		pibil_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_pibil_num = new GridBagConstraints();
		gbc_pibil_num.fill = GridBagConstraints.BOTH;
		gbc_pibil_num.insets = new Insets(0, 0, 5, 0);
		gbc_pibil_num.gridx = 3;
		gbc_pibil_num.gridy = 15;
		panel.add(pibil_num, gbc_pibil_num);
		
		JLabel pimiento = new JLabel("PIMIENTO CONFITADO 12 ud");
		pimiento.setForeground(new Color(248, 248, 255));
		pimiento.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_pimiento = new GridBagConstraints();
		gbc_pimiento.fill = GridBagConstraints.BOTH;
		gbc_pimiento.insets = new Insets(0, 0, 5, 5);
		gbc_pimiento.gridx = 0;
		gbc_pimiento.gridy = 16;
		panel.add(pimiento, gbc_pimiento);
		
		JSpinner pimiento_num = new JSpinner();
		pimiento_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		pimiento_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_pimiento_num = new GridBagConstraints();
		gbc_pimiento_num.fill = GridBagConstraints.BOTH;
		gbc_pimiento_num.insets = new Insets(0, 0, 5, 5);
		gbc_pimiento_num.gridx = 1;
		gbc_pimiento_num.gridy = 16;
		panel.add(pimiento_num, gbc_pimiento_num);
		
		JLabel entrecot = new JLabel("ENTRECOT MARINADO bolsa 500 g");
		entrecot.setForeground(new Color(248, 248, 255));
		entrecot.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_entrecot = new GridBagConstraints();
		gbc_entrecot.fill = GridBagConstraints.BOTH;
		gbc_entrecot.insets = new Insets(0, 0, 5, 5);
		gbc_entrecot.gridx = 2;
		gbc_entrecot.gridy = 16;
		panel.add(entrecot, gbc_entrecot);
		
		JSpinner entrecot_num = new JSpinner();
		entrecot_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		entrecot_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_entrecot_num = new GridBagConstraints();
		gbc_entrecot_num.insets = new Insets(0, 0, 5, 0);
		gbc_entrecot_num.fill = GridBagConstraints.BOTH;
		gbc_entrecot_num.gridx = 3;
		gbc_entrecot_num.gridy = 16;
		panel.add(entrecot_num, gbc_entrecot_num);
		
		JLabel pollo = new JLabel("POLLO MARINADO bandeja 2,5 kg");
		pollo.setForeground(new Color(248, 248, 255));
		pollo.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_pollo = new GridBagConstraints();
		gbc_pollo.anchor = GridBagConstraints.WEST;
		gbc_pollo.insets = new Insets(0, 0, 5, 5);
		gbc_pollo.gridx = 0;
		gbc_pollo.gridy = 17;
		panel.add(pollo, gbc_pollo);
		
		JSpinner pollo_num = new JSpinner();
		pollo_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		pollo_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		pollo_num.setEnabled(true);
		GridBagConstraints gbc_pollo_num = new GridBagConstraints();
		gbc_pollo_num.fill = GridBagConstraints.BOTH;
		gbc_pollo_num.insets = new Insets(0, 0, 5, 5);
		gbc_pollo_num.gridx = 1;
		gbc_pollo_num.gridy = 17;
		panel.add(pollo_num, gbc_pollo_num);
		
		JLabel salchichas = new JLabel("SALCHICHAS bolsa 8 ud");
		salchichas.setForeground(new Color(248, 248, 255));
		salchichas.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_salchichas = new GridBagConstraints();
		gbc_salchichas.anchor = GridBagConstraints.WEST;
		gbc_salchichas.insets = new Insets(0, 0, 5, 5);
		gbc_salchichas.gridx = 2;
		gbc_salchichas.gridy = 17;
		panel.add(salchichas, gbc_salchichas);
		
		JSpinner salchichas_num = new JSpinner();
		salchichas_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		salchichas_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_salchichas_num = new GridBagConstraints();
		gbc_salchichas_num.fill = GridBagConstraints.BOTH;
		gbc_salchichas_num.insets = new Insets(0, 0, 5, 0);
		gbc_salchichas_num.gridx = 3;
		gbc_salchichas_num.gridy = 17;
		panel.add(salchichas_num, gbc_salchichas_num);
		
		JLabel calambrito = new JLabel("SALSA CALAMBRITO bolsa 2 kg");
		calambrito.setForeground(new Color(248, 248, 255));
		calambrito.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_calambrito = new GridBagConstraints();
		gbc_calambrito.anchor = GridBagConstraints.WEST;
		gbc_calambrito.insets = new Insets(0, 0, 5, 5);
		gbc_calambrito.gridx = 0;
		gbc_calambrito.gridy = 18;
		panel.add(calambrito, gbc_calambrito);
		
		JSpinner calambrito_num = new JSpinner();
		calambrito_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		calambrito_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_calambrito_num = new GridBagConstraints();
		gbc_calambrito_num.fill = GridBagConstraints.BOTH;
		gbc_calambrito_num.insets = new Insets(0, 0, 5, 5);
		gbc_calambrito_num.gridx = 1;
		gbc_calambrito_num.gridy = 18;
		panel.add(calambrito_num, gbc_calambrito_num);
		
		JLabel lblPostres = new JLabel("POSTRES");
		lblPostres.setForeground(new Color(248, 248, 255));
		lblPostres.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblPostres = new GridBagConstraints();
		gbc_lblPostres.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostres.gridx = 2;
		gbc_lblPostres.gridy = 18;
		panel.add(lblPostres, gbc_lblPostres);
		
		JLabel oreo = new JLabel("TARTA OREO caja 16 ud");
		oreo.setForeground(new Color(248, 248, 255));
		oreo.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_oreo = new GridBagConstraints();
		gbc_oreo.anchor = GridBagConstraints.WEST;
		gbc_oreo.insets = new Insets(0, 0, 5, 5);
		gbc_oreo.gridx = 2;
		gbc_oreo.gridy = 19;
		panel.add(oreo, gbc_oreo);
		
		JSpinner oreo_num = new JSpinner();
		oreo_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		oreo_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_oreo_num = new GridBagConstraints();
		gbc_oreo_num.fill = GridBagConstraints.BOTH;
		gbc_oreo_num.insets = new Insets(0, 0, 5, 0);
		gbc_oreo_num.gridx = 3;
		gbc_oreo_num.gridy = 19;
		panel.add(oreo_num, gbc_oreo_num);
		
		
		
		
		JLabel tartaQueso = new JLabel("TARTA QUESO caja 16 ud");
		tartaQueso.setForeground(new Color(248, 248, 255));
		tartaQueso.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_tartaQueso = new GridBagConstraints();
		gbc_tartaQueso.anchor = GridBagConstraints.WEST;
		gbc_tartaQueso.insets = new Insets(0, 0, 5, 5);
		gbc_tartaQueso.gridx = 2;
		gbc_tartaQueso.gridy = 20;
		panel.add(tartaQueso, gbc_tartaQueso);
		
		JSpinner tartaQueso_num = new JSpinner();
		tartaQueso_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		tartaQueso_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_tartaQueso_num = new GridBagConstraints();
		gbc_tartaQueso_num.fill = GridBagConstraints.BOTH;
		gbc_tartaQueso_num.insets = new Insets(0, 0, 5, 0);
		gbc_tartaQueso_num.gridx = 3;
		gbc_tartaQueso_num.gridy = 20;
		panel.add(tartaQueso_num, gbc_tartaQueso_num);
		
		JLabel sacher = new JLabel("TARTA SACHER caja 20 ud");
		sacher.setForeground(new Color(248, 248, 255));
		sacher.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_sacher = new GridBagConstraints();
		gbc_sacher.anchor = GridBagConstraints.WEST;
		gbc_sacher.insets = new Insets(0, 0, 5, 5);
		gbc_sacher.gridx = 2;
		gbc_sacher.gridy = 21;
		panel.add(sacher, gbc_sacher);
		
		JSpinner sacher_num = new JSpinner();
		sacher_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		sacher_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_sacher_num = new GridBagConstraints();
		gbc_sacher_num.fill = GridBagConstraints.BOTH;
		gbc_sacher_num.insets = new Insets(0, 0, 5, 0);
		gbc_sacher_num.gridx = 3;
		gbc_sacher_num.gridy = 21;
		panel.add(sacher_num, gbc_sacher_num);
		
		JLabel zanahoria = new JLabel("TARTA ZANAHORIA caja 20 ud");
		zanahoria.setForeground(new Color(248, 248, 255));
		zanahoria.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_zanahoria = new GridBagConstraints();
		gbc_zanahoria.anchor = GridBagConstraints.WEST;
		gbc_zanahoria.insets = new Insets(0, 0, 0, 5);
		gbc_zanahoria.gridx = 2;
		gbc_zanahoria.gridy = 22;
		panel.add(zanahoria, gbc_zanahoria);
		
		JSpinner zanahoria_num = new JSpinner();
		zanahoria_num.setFont(new Font("Tahoma", Font.PLAIN, 27));
		zanahoria_num.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_zanahoria_num = new GridBagConstraints();
		gbc_zanahoria_num.fill = GridBagConstraints.BOTH;
		gbc_zanahoria_num.gridx = 3;
		gbc_zanahoria_num.gridy = 22;
		panel.add(zanahoria_num, gbc_zanahoria_num);
		
		JButton btnEnviar = new JButton("Enviar pedido");
		btnEnviar.setFont(new Font("SansSerif", Font.BOLD, 18));
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.gridheight = 1;
		gbc_btnEnviar.gridwidth = 4;
		gbc_btnEnviar.fill = GridBagConstraints.BOTH;
		gbc_btnEnviar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnviar.gridx = 0;
		gbc_btnEnviar.gridy = 24;
		panel.add(btnEnviar, gbc_btnEnviar);
		
		
		boolean[] pulsado= {false};
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pulsado[0]==false) {
					btnEnviar.setText("Pulsa de nuevo para enviar el pedido");
					pulsado[0]=true;
				}else {
					int []cantidad= {(Integer)aguja_num.getValue(),(Integer)angus_num.getValue(),(Integer)entrana_num.getValue(),(Integer)garbanzos_num.getValue(),(Integer)lentejas_num.getValue(),(Integer)vacio_num.getValue(),(Integer)doble_num.getValue(),(Integer)alitas_num.getValue(),(Integer)bacon_num.getValue(),(Integer)cebolla_num.getValue(),(Integer)portobello_num.getValue(),(Integer)costillas_num.getValue(),(Integer)brocheta_num.getValue(),(Integer)fingers_num.getValue(),(Integer)pimiento_num.getValue(),(Integer)pollo_num.getValue(),(Integer)calambrito_num.getValue(),(Integer)salteado_num.getValue(),(Integer)ketchup_num.getValue(),(Integer)lacalle_num.getValue(),(Integer)chimichurri_num.getValue(),(Integer)trufa_num.getValue(),(Integer)sweetChili_num.getValue(),(Integer)vegana_num.getValue(),(Integer)bbq_num.getValue(),(Integer)toffee_num.getValue(),(Integer)callejera_num.getValue(),(Integer)cesar_num.getValue(),(Integer)salsaAlitas_num.getValue(),(Integer)ensCol_num.getValue(),(Integer)pibil_num.getValue(),(Integer)entrecot_num.getValue(),(Integer)salchichas_num.getValue(),(Integer)oreo_num.getValue(),(Integer)tartaQueso_num.getValue(),(Integer)sacher_num.getValue(),(Integer)zanahoria_num.getValue()};
					Pedido nuevo=new Pedido(usuario,conexion,cantidad);
					nuevo.nuevoPedido();
					btnEnviar.setText("Tu pedido ha sido enviado!");
					btnEnviar.setEnabled(false);
				}
				
				

				
			}
		});
		
		
		try {
			
            
            
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
		
	}

}

