package presentacion;

import java.awt.EventQueue;
import interfaces.ICAltaEspectaculo;
import logic.manejadores.ManejadorPlataforma;
import exepciones.NombrePlataformaRepetidoException;
import exepciones.SinArtistasException;
import exepciones.SinPlataformasException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtDuracion;
	private JTextField txtMinEspec;
	private JTextField txtMaxEspec;
	private JTextField txtURL;
	private JTextField txtCosto;
	private JComboBox<String> cBoxPlataformas;
	private JComboBox<String> cBoxArtistas;
	
	private ICAltaEspectaculo icae;
	

	public AltaEspectaculo(ICAltaEspectaculo icae) {
		this.icae = icae;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		setTitle("Alta de Espectaculo");
		setBounds(50, 100, 550, 456);
		getContentPane().setLayout(null);
		
		JLabel lblSelectPlataforma = new JLabel("Seleccione Plataforma:");
		lblSelectPlataforma.setBounds(50, 30, 170, 15);
		getContentPane().add(lblSelectPlataforma);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(50, 60, 170, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(50, 90, 170, 15);
		getContentPane().add(lblDescripcion);
		
		JLabel lblDuracionDelEspectaculo = new JLabel("Duracion:");
		lblDuracionDelEspectaculo.setAlignmentY(Component.TOP_ALIGNMENT);
		lblDuracionDelEspectaculo.setBounds(50, 120, 170, 15);
		getContentPane().add(lblDuracionDelEspectaculo);
		
		JLabel lblMinimoEspectadores = new JLabel("Minimo espectadores:");
		lblMinimoEspectadores.setBounds(50, 150, 170, 15);
		getContentPane().add(lblMinimoEspectadores);
		
		JLabel lblMaximoEspectadores = new JLabel("Maximo espectadores:");
		lblMaximoEspectadores.setBounds(50, 180, 170, 15);
		getContentPane().add(lblMaximoEspectadores);
		
		JLabel lblUrl = new JLabel("Direccion URL:");
		lblUrl.setBounds(50, 210, 170, 15);
		getContentPane().add(lblUrl);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarForm();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(68, 344, 117, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaEspectaculoAceptarActionPerformed(e);
			}
		});
		
		btnAceptar.setBounds(351, 344, 117, 25);
		getContentPane().add(btnAceptar);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(238, 60, 240, 19);
		getContentPane().add(txtNombre);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(238, 90, 240, 19);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtDuracion = new JTextField();
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(238, 120, 240, 19);
		getContentPane().add(txtDuracion);
		
		txtMinEspec = new JTextField();
		txtMinEspec.setColumns(10);
		txtMinEspec.setBounds(238, 150, 240, 19);
		getContentPane().add(txtMinEspec);
		
		txtMaxEspec = new JTextField();
		txtMaxEspec.setColumns(10);
		txtMaxEspec.setBounds(238, 180, 240, 19);
		getContentPane().add(txtMaxEspec);
		
		txtURL = new JTextField();
		txtURL.setColumns(10);
		txtURL.setBounds(238, 210, 240, 19);
		getContentPane().add(txtURL);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(238, 240, 240, 19);
		getContentPane().add(txtCosto);
		
		cBoxPlataformas = new JComboBox<String>();
		cBoxPlataformas.setBounds(238, 30, 240, 24);
		getContentPane().add(cBoxPlataformas);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(50, 240, 170, 15);
		getContentPane().add(lblCosto);
		
		JLabel lblSeleccioneArtista = new JLabel("Seleccione Artista:");
		lblSeleccioneArtista.setBounds(50, 276, 146, 15);
		getContentPane().add(lblSeleccioneArtista);
		
		cBoxArtistas = new JComboBox<String>();
		cBoxArtistas.setBounds(238, 271, 238, 24);
		getContentPane().add(cBoxArtistas);
		
		
	}
	
	public boolean iniciarlizarComboBoxes() {
		boolean res;
		try {
			DefaultComboBoxModel<String> modelplataformas = new DefaultComboBoxModel<String>(icae.listarPlataformas());
			cBoxPlataformas.setModel(modelplataformas);
			
			DefaultComboBoxModel<String> modelArtistas = new DefaultComboBoxModel<String>(icae.listarArtistas());
			cBoxArtistas.setModel(modelArtistas);
			
			res = true;
		}
        catch (SinPlataformasException e) {
        	res = false;
        	JOptionPane.showMessageDialog(this, "No puede crear espectaculos. \n" + e.getMessage()+ "", "Alta Espectaculo", JOptionPane.ERROR_MESSAGE);
        } catch (SinArtistasException e) {
        	res = false;
        	JOptionPane.showMessageDialog(this, "No puede crear espectaculos. \n" + e.getMessage()+ "", "Alta Espectaculo", JOptionPane.ERROR_MESSAGE);
		}
		return res;
	}
	
	protected void altaEspectaculoAceptarActionPerformed(ActionEvent arg0) {
		if(verificarCampos()) {
			String nombreP = this.cBoxPlataformas.getSelectedItem().toString();
			String nomArtista = this.cBoxArtistas.getSelectedItem().toString();
			String nombre = this.txtNombre.getText();
			String descripcion = this.txtDescripcion.getText();
			int duracion =  Integer.parseInt(this.txtDuracion.getText());
			int capMax = Integer.parseInt(this.txtMaxEspec.getText());
			int capMin = Integer.parseInt(this.txtMinEspec.getText());
			String url = this.txtURL.getText();
			float costo = Float.parseFloat(this.txtCosto.getText());
		
			try {
				this.icae.selectPlataforma(nombreP);
				this.icae.ingresarDatos(nombre, descripcion, duracion, capMin, capMax, url, costo, null);
				JOptionPane.showMessageDialog(this, "El Espectaculo se ha creado con exito","Alta Espectaculo.", JOptionPane.INFORMATION_MESSAGE);
				this.icae.altaEspectaculo(nomArtista);
				limpiarForm();				
			}
            catch (NombrePlataformaRepetidoException e) {
            	JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Espectaculo", JOptionPane.ERROR_MESSAGE);
            }
			limpiarForm();
			setVisible(false);
        }
	}
	
	/*private int validarNum(JTextField txtField) {
		int retorno =0;
		try{
			retorno = Integer.parseInt(txtField.getText());
		}
		catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Valor invalido.", "Duracion",
                JOptionPane.ERROR_MESSAGE);
		}
		return retorno;
	}*/
	
	private void limpiarForm() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtDuracion.setText("");
		txtMaxEspec.setText("");
		txtMinEspec.setText("");
		txtURL.setText("");
		txtCosto.setText("");
	}
	
	private boolean verificarCampos() {
		String nombre = this.txtNombre.getText();
		String descripcion = this.txtDescripcion.getText();
		String duracion = this.txtDuracion.getText();
		String capMax = this.txtMaxEspec.getText();
		String capMin = this.txtMinEspec.getText();
		String url = this.txtURL.getText();
		String costo = this.txtCosto.getText();
		
		
		if(nombre.isEmpty() || descripcion.isEmpty() || duracion.isEmpty() || capMax.isEmpty() 
		   || capMin.isEmpty() || url.isEmpty()	|| costo.isEmpty()) {
		   
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacios.", "Alta Espectaculo", JOptionPane.ERROR_MESSAGE);
       		return false;
         /*   try {
                Integer.parseInt(ci);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La CI debe ser un numero", "Agregar Socio",
                        JOptionPane.ERROR_MESSAGE);
                return false;	*/
       		
    	}else {
    		return true;
    	}
    }
}
