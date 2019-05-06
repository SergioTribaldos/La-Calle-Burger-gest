package lacalleburger;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import pedidos.Pedido;

class JLista extends DefaultListCellRenderer {
	DefaultListModel<Pedido> listModelObjeto;
	DateFormat dateFormat;

	  public JLista(DefaultListModel<Pedido> listModelObjeto) {
		this.listModelObjeto=listModelObjeto;
		this.dateFormat=new SimpleDateFormat("dd/MM/yy  HH:mm");
	}

	@Override
	  public Component getListCellRendererComponent(JList list, Object value,
	        int index, boolean isSelected, boolean cellHasFocus) {
	     if (value != null) {
	    	 
	        value =dateFormat.format(listModelObjeto.get(index).getFechaPedido())+"               "+listModelObjeto.get(index).getUsuario().getRestaurante().getNombre() ;
	     }else {
	    	 value = "NO HAY PEDIDOS";
	     }
	     return super.getListCellRendererComponent(list, value, index,
	           isSelected, cellHasFocus);
	  }

	}