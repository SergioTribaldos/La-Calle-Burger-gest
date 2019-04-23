package lacalleburger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Ventana extends JFrame{
	PanelLogin panelLogin;	
	
	public Ventana() {
		panelLogin=new PanelLogin(this);
		this.setSize(877,625);
		setContentPane(panelLogin);
		
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
