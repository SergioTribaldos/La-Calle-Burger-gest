package lacalleburger;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {
	private Ventana ventana;
	public PanelPrincipal(Ventana ventan) {
		this.ventana = ventan;
		JPanel estePanel=this;
		setBackground(Color.BLACK);
		setLayout(null);
		this.setSize(500,500);
		
		JLabel lblEligeComoTe = new JLabel("Elige como te quieres logear");
		lblEligeComoTe.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligeComoTe.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblEligeComoTe.setForeground(Color.WHITE);
		lblEligeComoTe.setBounds(130, 77, 442, 70);
		add(lblEligeComoTe);
		
		JButton usuario = new JButton("Usuario");
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelLogin panelLogin=new PanelLogin(ventana);
				ventana.setContentPane(panelLogin);
				estePanel.setVisible(false);
				
				
			}
		});
		usuario.setBounds(177, 211, 350, 77);
		add(usuario);
		
		JButton administrador = new JButton("Administrador");
		administrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelLoginAdministrador panelLoginAdministrador=new PanelLoginAdministrador(ventana);
				ventana.setContentPane(panelLoginAdministrador);
				estePanel.setVisible(false);
				
			}
		});
		administrador.setBounds(177, 324, 349, 77);
		add(administrador);
		
	}
}
