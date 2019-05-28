package componentes;

import java.awt.Color;
import java.awt.Component;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import pedidos.Pedido;

public class JListRenderCustom extends DefaultListCellRenderer {
	DefaultListModel<Pedido> listModelObjeto;
	DateFormat dateFormat;

	public JListRenderCustom(DefaultListModel listModelObjeto) {
		this.listModelObjeto = listModelObjeto;
		this.dateFormat = new SimpleDateFormat("dd/MM/yy  HH:mm");
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// Recupera la informacion de pedido si hay pedido ese dia, en caso contrario
		// deshabilita la lista

		// Parseamos la fecha para que la muestre correctamente
		Timestamp ts = listModelObjeto.get(index).getFechaPedido();
		Date date = new Date(ts.getTime() - 3600 * 2000);
		String fechaFormateada = dateFormat.format(date);

		if (value != null) {
			list.setEnabled(true);
			value = fechaFormateada + "               "
					+ listModelObjeto.get(index).getUsuario().getRestaurante().getCodigoRestaurante();
		} else {
			value = "No hay pedidos";
			list.setEnabled(false);
		}
		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}