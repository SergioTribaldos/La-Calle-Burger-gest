package lacalleburger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Ventana extends JFrame{
	PanelPrincipal panelPrincipal;	
	
	
	public Ventana() {
		setResizable(false);

		panelPrincipal=new PanelPrincipal(this);
		this.setSize(720,561);
		setContentPane(panelPrincipal);
		
		/*panelLogin.getSiguiente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getThis().setContentPane(new PanelPedido());
			}
		});*/
	}
	
	public Ventana getThis() {
		return this;
	}

}
