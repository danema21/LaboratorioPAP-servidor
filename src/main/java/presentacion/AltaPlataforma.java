package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import exepciones.NombrePlataformaRepetidoException;
import interfaces.ICAltaPlataforma;
import logic.clases.Plataforma;
import logic.manejadores.ManejadorPlataforma;

public class AltaPlataforma extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ICAltaPlataforma icon;
	
	private JTextField textFieldURL;
	private JTextField textFieldNombre;
	private JTextArea textAreaDescripcion;


	 // Create the frame.

	public AltaPlataforma(ICAltaPlataforma icon) {
		this.icon = icon;
		setTitle("AltaPlataforma");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(22, 14, 77, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DESCRIPCION");
		lblNewLabel_1.setBounds(22, 57, 107, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("URL");
		lblNewLabel_2.setBounds(72, 196, 38, 14);
		getContentPane().add(lblNewLabel_2);
		
		textFieldURL = new JTextField();
		textFieldURL.setBounds(109, 193, 315, 20);
		getContentPane().add(textFieldURL);
		textFieldURL.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(109, 11, 149, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textAreaDescripcion.setBounds(109, 52, 315, 119);
		getContentPane().add(textAreaDescripcion);
		
		JButton btnNewButtonAceptar = new JButton("Aceptar");
		btnNewButtonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaPlataformaAceptarActionPerformed(e);
			}
		});
		btnNewButtonAceptar.setBounds(335, 236, 89, 23);
		getContentPane().add(btnNewButtonAceptar);
		
		JButton btnNewButtonCancelar = new JButton("Cancelar");
		btnNewButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaPlataformaCancelarActionPerformed(e);
			}
		});
		btnNewButtonCancelar.setBounds(236, 236, 89, 23);
		getContentPane().add(btnNewButtonCancelar);

	}
	
	protected void altaPlataformaCancelarActionPerformed(ActionEvent arg0) {
		limpiarCampos();
        setVisible(false);
    }
	
	protected void altaPlataformaAceptarActionPerformed(ActionEvent arg0) {
        String nombre = this.textFieldNombre.getText();
        String url = this.textFieldURL.getText();
        String descripcion = this.textAreaDescripcion.getText();
        
        if(checkFormulario()) {
        	this.icon.ingresarDatos(nombre, descripcion, url);
        	ManejadorPlataforma mP = ManejadorPlataforma.getInstance();
        	Plataforma varPlataforma = mP.buscarPlataforma(nombre);
        	if(varPlataforma != null) {
        		try {
        			this.icon.reingresarNombre(nombre);
        			
        		}catch(NombrePlataformaRepetidoException e){
        			JOptionPane.showMessageDialog(this, e.getMessage(), "AltaPlataforma", JOptionPane.ERROR_MESSAGE);
        		}
        	}else if(varPlataforma == null) {
	    		this.icon.altaPlataforma();
				JOptionPane.showMessageDialog(this, "La plataforma se ha creado con exito", "AltaPlataforma", JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				setVisible(false);
        	}
        }
    }
	
	private boolean checkFormulario() {
		String nombre = this.textFieldNombre.getText();
        String url = this.textFieldURL.getText();
        String descripcion = this.textAreaDescripcion.getText();
        
        if(nombre.isEmpty() || url.isEmpty() || descripcion.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "No pueden haber campos vacios", "AltaPlataforma", JOptionPane.ERROR_MESSAGE);
        	return false;
        }else {
        	return true;
        }
	}
	
	private void limpiarCampos() {
		this.textFieldNombre.setText("");
		this.textAreaDescripcion.setText("");
		this.textFieldURL.setText("");
	}
}
