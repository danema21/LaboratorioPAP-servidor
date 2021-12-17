package presentacion;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exepciones.UsuarioRepetidoException;
import interfaces.ICAltaUsuario;
import logic.clases.Usuario;
import logic.manejadores.ManejadorUsuario;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AltaUsuario extends JInternalFrame {
	
	private ICAltaUsuario icau;
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNickname;
	private JTextField textFieldCorreo;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldURL;
	private JRadioButton rdbtnEspectador;
	private JRadioButton rdbtnArtista;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAnio;
	private JTextField textFieldDescripcion;
	private JTextField textFieldBiografia;

	/**
	 * Create the frame.
	 * 
	 * 
	 */

	public AltaUsuario(ICAltaUsuario icau) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setResizable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta Usuario");
		
		this.icau = icau;
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuarioAceptarActionPerformed();
			}
		});
		btnAceptar.setBounds(280, 230, 110, 24);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuarioCancelarActionPerformed();
			}
		});
		btnCancelar.setBounds(40, 230, 110, 24);
		getContentPane().add(btnCancelar);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(10, 11, 75, 14);
		getContentPane().add(lblNickname);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 48, 75, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 88, 75, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 125, 75, 14);
		getContentPane().add(lblApellido);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(80, 9, 101, 20);
		getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setBounds(80, 45, 101, 20);
		getContentPane().add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(80, 85, 101, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(80, 122, 101, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(206, 11, 218, 14);
		getContentPane().add(lblDescripcion);
		
		JLabel lblBiografia = new JLabel("Biografia");
		lblBiografia.setBounds(206, 72, 153, 14);
		getContentPane().add(lblBiografia);
		
		JLabel lblURL = new JLabel("URL");
		lblURL.setBounds(10, 153, 58, 14);
		getContentPane().add(lblURL);
		
		textFieldURL = new JTextField();
		textFieldURL.setBounds(80, 150, 98, 20);
		getContentPane().add(textFieldURL);
		textFieldURL.setColumns(10);
		
		rdbtnEspectador = new JRadioButton("Espectador");
		rdbtnEspectador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEspectador.setSelected(true);
				textFieldURL.setEnabled(false);
				textFieldURL.setText("");
				textFieldDescripcion.setEnabled(false);
				textFieldDescripcion.setText("");
				textFieldBiografia.setEnabled(false);
				textFieldBiografia.setText("");
			}
		});
		rdbtnEspectador.setBounds(282, 157, 109, 23);
		getContentPane().add(rdbtnEspectador);
		rdbtnEspectador.setSelected(true);
		
		rdbtnArtista = new JRadioButton("Artista");
		rdbtnArtista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnArtista.setSelected(true);
				textFieldURL.setEnabled(true);
				textFieldURL.setText("");
				textFieldDescripcion.setEnabled(true);
				textFieldDescripcion.setText("");
				textFieldBiografia.setEnabled(true);
				textFieldBiografia.setText("");
			}
		});
		rdbtnArtista.setBounds(282, 183, 109, 23);
		getContentPane().add(rdbtnArtista);
		rdbtnArtista.setSelected(false);
		
		ButtonGroup bg1 = new ButtonGroup();
		
		bg1.add(rdbtnEspectador);
		bg1.add(rdbtnArtista);
		
		JLabel lblFechaNac = new JLabel("Fecha Nacimiento");
		lblFechaNac.setBounds(10, 181, 130, 23);
		getContentPane().add(lblFechaNac);
		
		textFieldDia = new JTextField();
		textFieldDia.setBounds(148, 181, 19, 20);
		getContentPane().add(textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setColumns(10);
		textFieldMes.setBounds(177, 181, 19, 20);
		getContentPane().add(textFieldMes);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setColumns(10);
		textFieldAnio.setBounds(206, 181, 40, 20);
		getContentPane().add(textFieldAnio);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(206, 27, 215, 40);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldBiografia = new JTextField();
		textFieldBiografia.setBounds(206, 88, 215, 40);
		getContentPane().add(textFieldBiografia);
		textFieldBiografia.setColumns(10);
		
		textFieldDescripcion.setEnabled(false);
		textFieldBiografia.setEnabled(false);
		textFieldURL.setEnabled(false);

	}
	
	public void altaUsuarioCancelarActionPerformed() {
		limpiarCampos();
		setVisible(false);
	}
	
	public void altaUsuarioAceptarActionPerformed() {
		if(checkFormulario()) {
			String nickname = this.textFieldNickname.getText();
			String correo = this.textFieldCorreo.getText();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
	        String descripcion = this.textFieldDescripcion.getText();
	        String URL = this.textFieldURL.getText();
	        String biografia = this.textFieldBiografia.getText();
	        int dia = Integer.parseInt(textFieldDia.getText());
	        int mes = Integer.parseInt(textFieldMes.getText());
	        int anio = Integer.parseInt(textFieldAnio.getText());
	        Date fechanac = new Date(anio-1900, mes-1, dia);
			try {
				this.icau.ingresarDatos(nickname, correo, nombre, apellido, fechanac, "123");
			}catch(UsuarioRepetidoException e){
				JOptionPane.showMessageDialog(this, e.getMessage(), "AltaUsuario", JOptionPane.ERROR_MESSAGE);
			}
			if(!descripcion.isEmpty()) {
				this.icau.ingresarDatosArtista(descripcion);
				if(!URL.isEmpty())
					this.icau.linkPagina(URL);
				if(!biografia.isEmpty())
					this.icau.ingresarBiografia(biografia);
			}
			ManejadorUsuario mP = ManejadorUsuario.getInstancia();
			Usuario varUsuario = mP.buscarUsuario(nickname);
			if(varUsuario == null) {
				this.icau.altaUsuario();
				JOptionPane.showMessageDialog(this, "El Usuario se ha creado con exito", "AltaUsuario", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				limpiarCampos();
			}
		}
	}
	
	private boolean checkFormulario() {
		
        if(textFieldNickname.getText().isEmpty() || textFieldCorreo.getText().isEmpty() || textFieldNombre.getText().isEmpty() || textFieldApellido.getText().isEmpty() || textFieldDia.getText().isEmpty() || textFieldMes.getText().isEmpty() || textFieldAnio.getText().isEmpty() || (textFieldDescripcion.getText().isEmpty() && rdbtnArtista.isSelected())) {
        	JOptionPane.showMessageDialog(this, "No pueden haber campos vacios", "AltaUsuario", JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        int dia = Integer.parseInt(textFieldDia.getText());
        int mes = Integer.parseInt(textFieldMes.getText());
        int anio = Integer.parseInt(textFieldAnio.getText());
        
        if(dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1900){
        	JOptionPane.showMessageDialog(this, "Fecha incorrecta, reintroduzca la fecha", "AltaUsuario", JOptionPane.ERROR_MESSAGE);
        	return false;
        }else {
        	return true;
        }
	}
	
	private void limpiarCampos() {
		textFieldNickname.setText("");
		textFieldCorreo.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldDia.setText("");
		textFieldMes.setText("");
		textFieldAnio.setText("");
		textFieldURL.setText("");
		textFieldDescripcion.setText("");
		textFieldBiografia.setText("");
	}
}

