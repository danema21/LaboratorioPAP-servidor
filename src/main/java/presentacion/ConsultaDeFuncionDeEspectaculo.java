package presentacion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import datatypes.DtFuncion;
import exepciones.SinPlataformasException;
import interfaces.IConsultaDeFuncionDeEspectaculo;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConsultaDeFuncionDeEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JTextField txtHoraIni;
	private JTextField txtFechaC;
	private JComboBox<String> cBoxPlataformas;
	private JComboBox<String> cBoxEspectaculos;
	private JComboBox<String> cBoxFunciones;
	private JComboBox<String> cBoxArtistas;
	
	private IConsultaDeFuncionDeEspectaculo icdfde;
	
	// Create the frame.

	public ConsultaDeFuncionDeEspectaculo(IConsultaDeFuncionDeEspectaculo icdfde) {
		this.icdfde = icdfde;
		
		setResizable(false);
        setIconifiable(false);
        setMaximizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
		setTitle("Consulta de Funcion de Espectaculo.");
		setBounds(50, 100, 640, 246);
		getContentPane().setLayout(null);
		
		JLabel lblPlataforma = new JLabel("Selecione una plataforma:");
		lblPlataforma.setBounds(30, 30, 200, 15);
		getContentPane().add(lblPlataforma);
		
		JLabel lblEspectaculo = new JLabel("Selecione un espectaculo:");
		lblEspectaculo.setBounds(30, 60, 200, 15);
		getContentPane().add(lblEspectaculo);
		
		JLabel lblFuncion = new JLabel("Selecione una funcion:");
		lblFuncion.setBounds(30, 90, 200, 15);
		getContentPane().add(lblFuncion);
		
		cBoxPlataformas = new JComboBox();
		cBoxPlataformas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBoxPlataformasItemStateChanged(e);
			}
		});
		
		cBoxPlataformas.setBounds(270, 25, 200, 24);
		getContentPane().add(cBoxPlataformas);
		
		cBoxEspectaculos = new JComboBox();
		cBoxEspectaculos.setBounds(270, 55, 200, 24);
		// Se cargan datos manuales de prueba.
		//cBoxEspectaculos.setModel(new DefaultComboBoxModel<String>(new String[] {"Sin Espectaculos"}));
		//cBoxEspectaculosItemStateChanged(ActionEvent e)
		cBoxEspectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBoxEspectaculos.getSelectedItem()!=null && !(cBoxEspectaculos.getSelectedItem().toString().equals("Sin Espectaculos"))) {
						cBoxEspectaculosItemStateChanged(e);
					}
			}
		});
		getContentPane().add(cBoxEspectaculos);
		
		cBoxFunciones = new JComboBox();
		cBoxFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBoxFunciones.getSelectedItem()!=null && !(cBoxFunciones.getSelectedItem().toString().equals("Sin Funciones"))) {
					llenarCamposActionPerformed();
				}
			}
		});
		cBoxFunciones.setBounds(270, 85, 200, 24);
		getContentPane().add(cBoxFunciones);
		
		JButton btnNombre = new JButton("Nombre");
		btnNombre.setBounds(12, 135, 117, 25);
		getContentPane().add(btnNombre);
		
		JButton btnFecha = new JButton("Fecha");
		btnFecha.setBounds(128, 135, 117, 25);
		getContentPane().add(btnFecha);
		
		JButton btnHoraDeInicio = new JButton("Hora de inicio");
		btnHoraDeInicio.setBounds(242, 135, 131, 25);
		getContentPane().add(btnHoraDeInicio);
		
		JButton btnArtistas = new JButton("Artistas");
		btnArtistas.setBounds(373, 135, 117, 25);
		getContentPane().add(btnArtistas);
		
		JButton btnFechaC = new JButton("Fecha creacion");
		btnFechaC.setBounds(488, 135, 140, 25);
		getContentPane().add(btnFechaC);
		
		cBoxArtistas = new JComboBox();
		cBoxArtistas.setBounds(372, 159, 118, 25);
		getContentPane().add(cBoxArtistas);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(12, 160, 118, 25);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(128, 160, 118, 25);
		getContentPane().add(txtFecha);
		
		txtHoraIni = new JTextField();
		txtHoraIni.setColumns(10);
		txtHoraIni.setBounds(242, 160, 131, 25);
		getContentPane().add(txtHoraIni);
		
		txtFechaC = new JTextField();
		txtFechaC.setColumns(10);
		txtFechaC.setBounds(489, 160, 140, 25);
		getContentPane().add(txtFechaC);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);				
			}
		});
		btnVolver.setBounds(488, 55, 117, 25);
		getContentPane().add(btnVolver);
		

	}
	
	private void cBoxPlataformasItemStateChanged(ActionEvent e) {
			String espectaculos[] = icdfde.selectPlataforma(cBoxPlataformas.getSelectedItem().toString());
			if(espectaculos.length == 0) {
				this.cBoxEspectaculos.setModel(new DefaultComboBoxModel<String>(new String[] {"Sin Espectaculos"}));
			}
			else {
				this.cBoxEspectaculos.setModel(new DefaultComboBoxModel<String>(espectaculos));
			}
				
	}
	
	private void cBoxEspectaculosItemStateChanged(ActionEvent e) {
		String funciones[] = icdfde.selectEspectaculo(cBoxEspectaculos.getSelectedItem().toString());
		if(funciones.length == 0) {
			this.cBoxFunciones.setModel(new DefaultComboBoxModel<String>(new String[] {"Sin Funciones"}));
		}
		else {
			this.cBoxFunciones.setModel(new DefaultComboBoxModel<String>(funciones));
		}
	}
	
	private void llenarCamposActionPerformed() {
		DtFuncion func = this.icdfde.selectFuncion(this.cBoxFunciones.getSelectedItem().toString());
		String artistas[] = func.getArtistas();
		this.txtNombre.setText(func.getNombre().toString());
		this.txtFecha.setText(func.getFecha().toString());
		this.txtHoraIni.setText(""+func.getHoraInicio()+"");
		// ==> cargar comboBOx con artistas
		//this.cBoxArtistas.setModel(new DefaultComboBoxModel<String>(new String[] {"Sin Artistas"}));
		this.cBoxArtistas.setModel(new DefaultComboBoxModel<String>(artistas));
		this.txtFechaC.setText(func.getFechaRegistro().toString());
		
	}
	
	
	//Dejar todos los campos en blanco.
	private void limpiarCampos() {
		this.txtNombre.setText("");
		this.txtFecha.setText("");
		this.txtFechaC.setText("");
		this.txtHoraIni.setText("");
		this.cBoxArtistas.removeAllItems();
		this.cBoxFunciones.removeAllItems();
		this.cBoxEspectaculos.removeAllItems();
		
	}
	
	public boolean iniciarlizarCBoxPlataformas() {
		boolean res;
		try {
			DefaultComboBoxModel<String> modelplataformas = new DefaultComboBoxModel<String>(icdfde.listarPlataformas());
			cBoxPlataformas.setModel(modelplataformas);
			res = true;
		}
        catch (SinPlataformasException e) {
        	res = false;
        	JOptionPane.showMessageDialog(this, "No puede consultar funciones. \n" + e.getMessage()+ "",
        								  "Consulta de Funcion de Espectaculo", JOptionPane.ERROR_MESSAGE);
        	
        }
		return res;
	}
}
