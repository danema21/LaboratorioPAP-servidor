package presentacion;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;

import exepciones.*;
import interfaces.ICAltaDeFuncionDeEspectaculo;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;


public class AltaDeFuncionDeEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private ICAltaDeFuncionDeEspectaculo iControlador;
	private JTextField textFieldNomFuncion;
	private JTextField textFieldFechaDia;
	private JTextField textFieldFechaMes;
	private JTextField textFieldFechaAnio;
	private JTextField textFieldHoraInicio;
	private JComboBox<String> comboBoxPlataforma;
	private JLabel lblNewLabelSelectEspectaculo;
	private JLabel lblNewLabelSelectPlataforma;
	private JComboBox<String> comboBoxEspectaculo;
	private JLabel lblNombreFuncion;
	private JLabel lblNewLabelFecha;
	private JLabel lblNewLabelFechaDia;
	private JLabel lblNewLabelFechaMes;
	private JLabel lblNewLabelFechaAnio;
	private JLabel lblNewLabelHoraInicio;
	private JScrollPane scrollPaneArtistas;
	private JList<String> listArtistasSeleccionados;
	private DefaultListModel<String> modelListArtistas = new DefaultListModel<String>();
	private JLabel lblSeleccionarArtistas;
	private JComboBox<String> comboBoxListArtistas;
	private JButton btnAgregarArtista;
	private JButton btnQuitarArtista;
	private JLabel lblArtistasSeleccionados;
	private JButton btnCancelar;
	private JButton btnAceptar;
	
	public AltaDeFuncionDeEspectaculo(ICAltaDeFuncionDeEspectaculo iControlador) {
		this.iControlador = iControlador;
		getContentPane().setForeground(Color.BLACK);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta De Funcion De Espectaculo");
		setBounds(100, 100, 661, 430);
		getContentPane().setLayout(null);
		
		comboBoxEspectaculo = new JComboBox();
		comboBoxEspectaculo.setBounds(227, 182, 188, 22);
		getContentPane().add(comboBoxEspectaculo);
		
		comboBoxPlataforma = new JComboBox();
		comboBoxPlataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String elementoSeleccionado =(String) comboBoxPlataforma.getSelectedItem();
					DefaultComboBoxModel<String> modelEspectaculo = new DefaultComboBoxModel<String>((String[]) iControlador.selectPlataforma(elementoSeleccionado).toArray(new String[iControlador.selectPlataforma(elementoSeleccionado).size()]));
					comboBoxEspectaculo.setModel(modelEspectaculo);
				}catch(SinEspectaculosException error) {
					JOptionPane.showMessageDialog( comboBoxPlataforma, error.getMessage(), "ERROR AL SELECCIONAR PLATAFORMA", JOptionPane.ERROR_MESSAGE);
					comboBoxPlataforma.setSelectedIndex(-1);
				}
			}
		});		
		comboBoxPlataforma.setBounds(227, 139, 188, 22);
		getContentPane().add(comboBoxPlataforma);
		
		lblNewLabelSelectEspectaculo = new JLabel("Seleccionar Espectaculo");
		lblNewLabelSelectEspectaculo.setBounds(28, 174, 163, 37);
		lblNewLabelSelectEspectaculo.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelSelectEspectaculo);
		
		lblNewLabelSelectPlataforma = new JLabel("Seleccionar Plataforma");
		lblNewLabelSelectPlataforma.setBounds(28, 135, 163, 28);
		lblNewLabelSelectPlataforma.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelSelectPlataforma);
		
		
		textFieldNomFuncion = new JTextField();
		textFieldNomFuncion.setBounds(172, 227, 174, 20);
		getContentPane().add(textFieldNomFuncion);
		textFieldNomFuncion.setColumns(10);
		
		lblNombreFuncion = new JLabel("Nombre de la Funcion");
		lblNombreFuncion.setBounds(28, 222, 148, 28);
		lblNombreFuncion.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNombreFuncion);
		
		lblNewLabelFecha = new JLabel("Fecha");
		lblNewLabelFecha.setBounds(30, 278, 49, 14);
		lblNewLabelFecha.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelFecha);
		
		textFieldFechaDia = new JTextField();
		textFieldFechaDia.setBounds(74, 276, 49, 20);
		getContentPane().add(textFieldFechaDia);
		textFieldFechaDia.setColumns(10);
		
		textFieldFechaMes = new JTextField();
		textFieldFechaMes.setBounds(137, 276, 49, 20);
		getContentPane().add(textFieldFechaMes);
		textFieldFechaMes.setColumns(10);
		
		textFieldFechaAnio = new JTextField();
		textFieldFechaAnio.setBounds(194, 276, 49, 20);
		textFieldFechaAnio.setColumns(10);
		getContentPane().add(textFieldFechaAnio);
		
		lblNewLabelFechaDia = new JLabel("Dia");
		lblNewLabelFechaDia.setBounds(92, 261, 49, 14);
		lblNewLabelFechaDia.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelFechaDia);
		
		lblNewLabelFechaMes = new JLabel("Mes");
		lblNewLabelFechaMes.setBounds(151, 261, 25, 14);
		lblNewLabelFechaMes.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelFechaMes);
		
		lblNewLabelFechaAnio = new JLabel("A\u00F1o");
		lblNewLabelFechaAnio.setBounds(212, 261, 31, 14);
		lblNewLabelFechaAnio.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelFechaAnio);
		
		textFieldHoraInicio = new JTextField();
		textFieldHoraInicio.setBounds(137, 313, 49, 20);
		textFieldHoraInicio.setColumns(10);
		getContentPane().add(textFieldHoraInicio);
		
		lblNewLabelHoraInicio = new JLabel("Hora de Inicio");
		lblNewLabelHoraInicio.setBounds(30, 315, 93, 14);
		lblNewLabelHoraInicio.setFont(new Font("Arial Black", Font.PLAIN, 11));
		getContentPane().add(lblNewLabelHoraInicio);
		
		
		listArtistasSeleccionados = new JList<String>(modelListArtistas);
		scrollPaneArtistas = new JScrollPane(listArtistasSeleccionados);
		scrollPaneArtistas.setBounds(478, 40, 163, 207);
		getContentPane().add(scrollPaneArtistas);
		
		lblSeleccionarArtistas = new JLabel("Seleccionar Artistas");
		lblSeleccionarArtistas.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblSeleccionarArtistas.setBounds(28, 50, 163, 37);
		getContentPane().add(lblSeleccionarArtistas);
		
		comboBoxListArtistas = new JComboBox();
		comboBoxListArtistas.setBounds(227, 58, 188, 22);
		getContentPane().add(comboBoxListArtistas);
		
		btnAgregarArtista = new JButton("Agregar");
		btnAgregarArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String artista = (String) comboBoxListArtistas.getSelectedItem();
				agregarArtistaALista(artista);
			}
		});
		btnAgregarArtista.setBounds(10, 97, 89, 23);
		getContentPane().add(btnAgregarArtista);
		
		btnQuitarArtista = new JButton("Quitar");
		btnQuitarArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int posElementoAQuitar = listArtistasSeleccionados.getSelectedIndex();
				if(posElementoAQuitar != -1)
					modelListArtistas.remove(posElementoAQuitar);
				else
					JOptionPane.showMessageDialog(getContentPane(),"No ha seleccionado ningun elemento para quitar", "Error quitar elemento", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnQuitarArtista.setBounds(109, 97, 89, 23);
		getContentPane().add(btnQuitarArtista);
		
		lblArtistasSeleccionados = new JLabel("Artistas Seleccionados :");
		lblArtistasSeleccionados.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblArtistasSeleccionados.setBounds(478, 11, 163, 22);
		getContentPane().add(lblArtistasSeleccionados);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaFuncionDeEspectaculoCancelarActionPerformed(e);
			}
		});
		btnCancelar.setBounds(271, 354, 89, 22);
		getContentPane().add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaFuncionDeEspectaculoAceptarActionPerformed(e);
			}
		});
		btnAceptar.setBounds(397, 354, 89, 22);
		getContentPane().add(btnAceptar);
}

	
	public boolean inicializarComboBoxes() {
		try {
			DefaultComboBoxModel<String> modeloPlataformas = new DefaultComboBoxModel<String>((String[]) this.iControlador.listarPlataformas().toArray(new String[this.iControlador.listarPlataformas().size()]));
			DefaultComboBoxModel<String> modeloArtistas =    new DefaultComboBoxModel<String>((String[]) this.iControlador.listarArtistasEnSistema().toArray(new String[this.iControlador.listarArtistasEnSistema().size()]));
			this.comboBoxPlataforma.setModel(modeloPlataformas);
			this.comboBoxListArtistas.setModel(modeloArtistas);
			return true;
		}catch(SinPlataformasException error) {
			JOptionPane.showMessageDialog( this, "No se puede crear Funcion. \n" + error.getMessage() ,"Alta De Funcion De Espectaculo Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void agregarArtistaALista(String nombreArtista) {
		if(!artistaAgregado(nombreArtista))
			modelListArtistas.addElement(nombreArtista);
		else
			JOptionPane.showMessageDialog(this,"artista ya agregado!", "Error al agregar Artista", JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean artistaAgregado(String nombreArtista) {
		for(int i = 0; i < this.modelListArtistas.getSize(); i++) {
			if(this.modelListArtistas.getElementAt(i).equals(nombreArtista)) {
				return true; 
			}
		}
		
		return false;
	}
	public void limpiarCampos() {
		this.textFieldNomFuncion.setText("");
		this.textFieldFechaDia.setText("");
		this.textFieldFechaMes.setText("");
		this.textFieldFechaAnio.setText("");
		this.textFieldHoraInicio.setText("");
		
		//limpia la lista de artistas seleccionados.
		this.modelListArtistas.clear();
	}
	
	protected void altaFuncionDeEspectaculoCancelarActionPerformed(ActionEvent arg0) {
		limpiarCampos();
        setVisible(false);
    }
	
	protected void altaFuncionDeEspectaculoAceptarActionPerformed(ActionEvent arg0) {
		if(checkFormulario()) {
			String nombreFuncion = this.textFieldNomFuncion.getText();
			int dia = Integer.parseInt(this.textFieldFechaDia.getText());
			int mes = Integer.parseInt(this.textFieldFechaMes.getText());
			int anio = Integer.parseInt(this.textFieldFechaAnio.getText());
			int horaInicio = Integer.parseInt(this.textFieldHoraInicio.getText());
			String nombreEspectaculo =(String) this.comboBoxEspectaculo.getSelectedItem();
			Date fecha = new Date(anio-1900, mes-1, dia);
			Date fechaAlta = new Date();
			
			for(int i = 0 ; i < this.modelListArtistas.getSize(); i++) {
				this.iControlador.seleccionarArtista(this.modelListArtistas.getElementAt(i));
			}
			
			try 
			{
				this.iControlador.ingresarDatos(nombreEspectaculo, nombreFuncion, fecha,horaInicio,fechaAlta, null);
				this.iControlador.altaFuncion();
				JOptionPane.showMessageDialog(this, "Se dio de alta la funcion con exito.", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				limpiarCampos();
			}
			catch(NombreFuncionRepetido error)
			{
				JOptionPane.showMessageDialog(this,"Ya existe en el sistema una funcion con el nombre ingresado", "Nombre Funcion Repetido", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean checkFormulario() {
		if(this.textFieldNomFuncion.getText().isEmpty() || this.textFieldFechaDia.getText().isEmpty() || this.textFieldFechaMes.getText().isEmpty() ||
			this.textFieldFechaAnio.getText().isEmpty() || this.textFieldHoraInicio.getText().isEmpty() || this.comboBoxPlataforma.getSelectedIndex() == -1 || 
			this.comboBoxEspectaculo.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios excepto agregar artistas", "Campos Obligatorios Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		int dia = Integer.parseInt(this.textFieldFechaDia.getText());
        int mes = Integer.parseInt(this.textFieldFechaMes.getText());
        int anio = Integer.parseInt(this.textFieldFechaAnio.getText());
        
        if(dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1900){
        	JOptionPane.showMessageDialog(this, "Fecha incorrecta, vuelva a introducir una", "Alta Funcion De Espectaculo ERROR", JOptionPane.ERROR_MESSAGE);
        	return false;
        }else {
        	return true;
        }
	}
}