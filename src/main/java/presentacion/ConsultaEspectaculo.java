package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datatypes.DtDataEspectaculo;
import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import exepciones.SinPlataformasException;
import interfaces.Fabrica;
import interfaces.IConsultaEspectaculo;
import logic.clases.Plataforma;
import logic.manejadores.ManejadorPlataforma;
import logic.manejadores.ManejadorUsuario;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaEspectaculo extends JInternalFrame {
	
	private IConsultaEspectaculo ice = Fabrica.getInstance().getCConsultaEspectaculo();
	DtDataEspectaculo data;
	
	
	JLabel lblVerDesc, lblVerFunciones, lblVerPaquetes, lblEspectaculos;
	private JLabel[] verLabels = {lblVerFunciones, lblVerDesc, lblVerPaquetes};
	private JTable table;
	JPanel panel;
	JList list;
	JComboBox comboBox;
	JLabel labelNombre,labelDescripcion,lblCantidadMinimaDe, lblDuracionEnMinutos,labelFechaRegistro, labelCosto, lblWeb, lblCantidadMaximaDe, lblPaquetes, lblFunciones;
	private JLabel lblFechaTabla;
	private JLabel lblHoraDeInicioTabla;
	private JLabel lblFechaDeRegistro;

	/**
	 * Create the frame.
	 */
	public ConsultaEspectaculo(IConsultaEspectaculo ice) {
		
	
		setBounds(100, 100, 579, 390);
		getContentPane().setLayout(null);
		setClosable(true);
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				setBounds(100, 100, 579, 390);
				panel.setVisible(true);
				cargarDatosDeEspectaculo();
			}
		});
		list.setBackground(SystemColor.controlHighlight);
		list.setBounds(12, 63, 123, 166);
		getContentPane().add(list);
		
		
		
		JLabel labelSeleccionarPlataforma = new JLabel("Seleccionar Plataforma:");
		labelSeleccionarPlataforma.setBounds(12, 12, 169, 15);
		getContentPane().add(labelSeleccionarPlataforma);
		
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBounds(100, 100, 579, 390);
				if(!cargarEspectaculosDePlataforma()) {
					list.setVisible(false);
					lblEspectaculos.setVisible(false);
					panel.setVisible(false);
					mostrarError("Plataforma sin espectaculos", "Error");
				} else {
					list.setVisible(true);
					lblEspectaculos.setVisible(true);
					panel.setVisible(false);
				}
			}
		});
		comboBox.setBounds(184, 7, 159, 24);
		getContentPane().add(comboBox);
		
		//loadPlataformasBD();
		
		lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setBounds(12, 39, 98, 15);
		getContentPane().add(lblEspectaculos);
		
		
		panel = new JPanel();
		panel.setBounds(205, 43, 336, 292);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(12, 12, 312, 15);
		panel.add(labelNombre);
		
		labelDescripcion = new JLabel("Descripcion:");
		labelDescripcion.setBounds(12, 39, 87, 15);
		panel.add(labelDescripcion);
		
		lblCantidadMinimaDe = new JLabel("Cantidad minima de espectadores:");
		lblCantidadMinimaDe.setBounds(12, 93, 312, 15);
		panel.add(lblCantidadMinimaDe);
		
		lblDuracionEnMinutos = new JLabel("Duracion en minutos:");
		lblDuracionEnMinutos.setBounds(12, 66, 312, 15);
		panel.add(lblDuracionEnMinutos);
		
		labelFechaRegistro = new JLabel("Fecha de registro:");
		labelFechaRegistro.setBounds(12, 201, 312, 15);
		panel.add(labelFechaRegistro);
		
		labelCosto = new JLabel("Costo:");
		labelCosto.setBounds(12, 174, 312, 15);
		panel.add(labelCosto);
		
		lblWeb = new JLabel("WEB:");
		lblWeb.setBounds(12, 147, 312, 15);
		panel.add(lblWeb);
		
		lblCantidadMaximaDe = new JLabel("Cantidad maxima de espectadores:");
		lblCantidadMaximaDe.setBounds(12, 120, 312, 15);
		panel.add(lblCantidadMaximaDe);
		
		lblPaquetes = new JLabel("Paquetes que lo contienen:");
		lblPaquetes.setBounds(12, 255, 196, 15);
		panel.add(lblPaquetes);
		
		lblFunciones = new JLabel("Funciones:");
		lblFunciones.setBounds(12, 228, 87, 15);
		panel.add(lblFunciones);
		
		lblVerPaquetes = new JLabel("Ver");
		lblVerPaquetes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarError("No hay paquetes disponibles", "Error");
			}
		});
		lblVerPaquetes.setForeground(Color.BLUE);
		lblVerPaquetes.setBounds(220, 255, 24, 15);
		lblVerPaquetes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(lblVerPaquetes);
		
		
		lblVerFunciones = new JLabel("Ver");
		lblVerFunciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!ice.espectaculoNoTieneFunciones() && data != null) {
					setBounds(100, 100, 579, 564);
					table.setVisible(true);
					cargarFuncionesEspectaculos();
				} else {
					mostrarError("Sin funciones", "Error");
				}
			}
		});
		lblVerFunciones.setForeground(Color.BLUE);
		lblVerFunciones.setBounds(111, 228, 24, 15);
		lblVerFunciones.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(lblVerFunciones);
		
		lblVerDesc = new JLabel("Ver");
		lblVerDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(list.getSelectedIndex() != -1) {
					mostrarMensajeInfo(data.getEspectaculo().getDescripcion(),"Descripcion");
				} else {
					mostrarError("No se ha seleccionado un Espectaculo.", "Error");
				}
			}
		});
		lblVerDesc.setForeground(Color.BLUE);
		lblVerDesc.setBounds(111, 39, 24, 15);
		lblVerDesc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(lblVerDesc);
		
		table = new JTable();
		table.setBounds(39, 378, 502, 118);
		getContentPane().add(table);
		table.setEnabled(false);
		
		JLabel lblNombreTabla = new JLabel("Nombre");
		lblNombreTabla.setBounds(51, 361, 70, 15);
		getContentPane().add(lblNombreTabla);
		
		lblFechaTabla = new JLabel("Fecha");
		lblFechaTabla.setBounds(168, 361, 123, 15);
		getContentPane().add(lblFechaTabla);
		
		lblHoraDeInicioTabla = new JLabel("Hora de Inicio");
		lblHoraDeInicioTabla.setBounds(290, 361, 98, 15);
		getContentPane().add(lblHoraDeInicioTabla);
		
		lblFechaDeRegistro = new JLabel("Fecha de Registro");
		lblFechaDeRegistro.setBounds(408, 361, 133, 15);
		getContentPane().add(lblFechaDeRegistro);
		//table.setVisible(false);
	}
	
	public boolean loadPlataformasBD() {
		try {
			DefaultComboBoxModel<String> modelplataformas = new DefaultComboBoxModel<String>((String[]) ice.listarPlataformas().toArray(new String[ice.listarPlataformas().size()]));
			this.comboBox.setModel(modelplataformas);
			return true;
		} catch(SinPlataformasException error) {
			JOptionPane.showMessageDialog( this, "No hay plataformas para consultar" + error.getMessage() ,"Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}		
	
	private boolean cargarEspectaculosDePlataforma() {
		List<String> lista = ice.listarEspectaculos(String.valueOf(this.comboBox.getSelectedItem()));
		
		if (lista.isEmpty()) return false;
		
		DefaultListModel<String> mod = new DefaultListModel<String>();
		
		list.setModel(mod);
		
		for (String t: lista) {
			mod.addElement(t);
		}
		
		return true;
	}
	
	private void cargarDatosDeEspectaculo() {
		String selected = String.valueOf(list.getSelectedValue());
		vaciarLabels();
		if(ice.plataformaTieneEspectaculo(selected)) {
			data = ice.mostrarDatosEspectaculos(selected);
			cargarLabels(data.getEspectaculo());
		}
	}
	
	private void cargarLabels(DtEspectaculo data) {
		this.labelNombre.setText(this.labelNombre.getText() + " " + data.getNombre());
		this.lblCantidadMinimaDe.setText(this.lblCantidadMinimaDe.getText() + " " + data.getCantMinEspectadores());
		this.lblDuracionEnMinutos.setText(this.lblDuracionEnMinutos.getText() + " " + data.getDuracionMin());
		this.labelFechaRegistro.setText(this.labelFechaRegistro.getText() + " " + data.getFechaRegistro());
		this.labelCosto.setText(this.labelCosto.getText() + " " + data.getCosto());
		this.lblWeb.setText(this.lblWeb.getText() + " " + data.getUrl());
		this.lblCantidadMaximaDe.setText(this.lblCantidadMaximaDe.getText() + " " + data.getCantMaxEspectadores());
	}
	
	private void vaciarLabels() {
		this.labelNombre.setText("Nombre:");
		this.lblCantidadMinimaDe.setText("Cantidad minima de espectadores:");
		this.lblDuracionEnMinutos.setText("Duracion en minutos:");
		this.labelFechaRegistro.setText("Fecha de registro");
		this.labelCosto.setText("Costo:");
		this.lblWeb.setText("WEB:");
		this.lblCantidadMaximaDe.setText("Cantidad maxima de espectadores:");
		this.data = null;
	}
	
	private void mostrarMensajeInfo(String msg, String title) {
		JOptionPane.showMessageDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void mostrarError(String msg, String title) {
		JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	private void cargarFuncionesEspectaculos() {
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Nombre");
		model.addColumn("Fecha");
		model.addColumn("Hora de Inicio");
		model.addColumn("Fecha de Registro");
		
		DtFuncion[] funciones = data.getEspectaculo().getFunciones();
		DtFuncion f;
		
		if(funciones != null) {
			for(int i = 0; i < funciones.length; i++) {
				f = funciones[i];
				model.addRow(new Object[]{
					f.getNombre(),
					f.getFecha(),
					f.getHoraInicio(),
					f.getFechaRegistro()
				});
			}
			table.setModel(model);
		}
	}	
}
