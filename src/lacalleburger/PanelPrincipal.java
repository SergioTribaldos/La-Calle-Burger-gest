package lacalleburger;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {
	private Ventana ventana;
	
	public PanelPrincipal(Ventana ventana) {
		this.ventana = ventana;
		setBackground(Color.BLACK);
		setLayout(null);
		this.setSize(673,511);
		
		JLabel lblEligeComoTe = new JLabel("Elige como te quieres logear");
		lblEligeComoTe.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligeComoTe.setFont(new Font("Segoe Print", Font.PLAIN, 24));
		lblEligeComoTe.setForeground(Color.WHITE);
		lblEligeComoTe.setBounds(131, 180, 442, 70);
		add(lblEligeComoTe);
		
		JButton usuario = new JButton("Usuario");
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ventana.cambiaPanel(new PanelLoginUsuario(ventana,PanelPrincipal.this),PanelPrincipal.this);
							
			}
		});
		usuario.setBounds(178, 285, 350, 77);
		add(usuario);
		
		JButton administrador = new JButton("Administrador");
		administrador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		administrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ventana.cambiaPanel(new PanelLoginAdministrador(ventana),PanelPrincipal.this);
				/*
				PanelLoginAdministrador panelLoginAdministrador=new PanelLoginAdministrador(ventana);
				ventana.setContentPane(panelLoginAdministrador);
				estePanel.setVisible(false);*/
				
			}
		});
		administrador.setBounds(178, 373, 349, 77);
		add(administrador);
		
		JLabel logoEmpresa = new JLabel("New label");
		logoEmpresa.setIcon(new ImageIcon(PanelLoginUsuario.class.getResource("/descarga.png")));
		logoEmpresa.setBounds(0, 0, 226, 192);
		add(logoEmpresa);
		
	}
}
