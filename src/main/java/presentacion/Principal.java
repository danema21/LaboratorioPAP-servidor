package presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import interfaces.*;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import interfaces.ICAltaUsuario;

import publicadores.ControladorAltaFuncionPublish;
import publicadores.CAltaFuncionDeEspectaculoPublish;
import publicadores.CConsultaDeFuncionDeEspectaculoPublish;
import publicadores.ControladorAltaUsuarioPublish;
import publicadores.ControladorConsultaEspectaculoPublish;
import publicadores.ControladorGestionSeguimientoUsuarioPublish;
import publicadores.ControladorIniciarSesionPublish;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frame;
	private AltaPlataforma altaPlataformaInternalFrame;
	private AltaEspectaculo altaEspectaculoInternalFrame;
	private AltaUsuario altaUsuarioInternalFrame;
	private AltaDeFuncionDeEspectaculo altaDeFuncionDeEspectaculoInternalFrame;
	private ConsultaEspectaculo consultaEspectaculoFrame;
	private ConsultaDeFuncionDeEspectaculo consultaDeFuncionDeEspectaculoFrame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		ControladorIniciarSesionPublish cisp = new ControladorIniciarSesionPublish();
		CConsultaDeFuncionDeEspectaculoPublish cfep = new CConsultaDeFuncionDeEspectaculoPublish();
		ControladorAltaUsuarioPublish caup = new ControladorAltaUsuarioPublish();
		ControladorGestionSeguimientoUsuarioPublish cgsu = new ControladorGestionSeguimientoUsuarioPublish();
		ControladorConsultaEspectaculoPublish ccep = new ControladorConsultaEspectaculoPublish();
		CAltaFuncionDeEspectaculoPublish cafe = new CAltaFuncionDeEspectaculoPublish(); 
		ControladorAltaFuncionPublish cafp = new ControladorAltaFuncionPublish();
		cisp.publicar();
		cfep.publicar();
		caup.publicar();
		cgsu.publicar();
		cafp.publicar();
		ccep.publicar();
		cafe.publicar();

		initialize();

		Fabrica fabrica = Fabrica.getInstance();
		ICAltaUsuario icau = fabrica.getCAltaUsuario();
		ICAltaPlataforma icaltaplataforma = fabrica.getCAltaPlataforma();
		ICAltaEspectaculo icaltaespectaculo = fabrica.getCAltaEspectaculo();
		ICAltaDeFuncionDeEspectaculo iCAltaDeFuncionDeEspectaculo = fabrica.getIAltaDeFuncionDeEspectaculo();
		IConsultaEspectaculo ice = fabrica.getCConsultaEspectaculo();
		IConsultaDeFuncionDeEspectaculo icdfde = fabrica.getIConsultaDeFuncionDeEspectaculo();
		
		Dimension desktopSize = frame.getSize();
		Dimension jInternalFrameSize;
		
		altaUsuarioInternalFrame = new AltaUsuario(icau);
		altaUsuarioInternalFrame.setLocation(230, 150);
		altaUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(altaUsuarioInternalFrame);
		
		altaPlataformaInternalFrame = new AltaPlataforma(icaltaplataforma);
		jInternalFrameSize = altaPlataformaInternalFrame.getSize();
		altaPlataformaInternalFrame.setLocation(230, 150);
		altaPlataformaInternalFrame.setVisible(false);
		frame.getContentPane().add(altaPlataformaInternalFrame);
		
		altaEspectaculoInternalFrame = new AltaEspectaculo(icaltaespectaculo);
		jInternalFrameSize = altaEspectaculoInternalFrame.getSize();
		altaEspectaculoInternalFrame.setLocation(230, 150);
		altaEspectaculoInternalFrame.setVisible(false);
		frame.getContentPane().add(altaEspectaculoInternalFrame);
		
		altaUsuarioInternalFrame = new AltaUsuario(icau);
		altaUsuarioInternalFrame.setLocation(33, 25);
		altaUsuarioInternalFrame.setVisible(false);
		frame.getContentPane().add(altaUsuarioInternalFrame);
		
		altaDeFuncionDeEspectaculoInternalFrame = new AltaDeFuncionDeEspectaculo(iCAltaDeFuncionDeEspectaculo);
		altaDeFuncionDeEspectaculoInternalFrame.setLocation(33, 25);
		altaDeFuncionDeEspectaculoInternalFrame.setVisible(false);
		frame.getContentPane().add(altaDeFuncionDeEspectaculoInternalFrame);

		consultaEspectaculoFrame = new ConsultaEspectaculo(ice);
		consultaEspectaculoFrame.setVisible(false);
		frame.getContentPane().add(consultaEspectaculoFrame);
		
		consultaDeFuncionDeEspectaculoFrame = new ConsultaDeFuncionDeEspectaculo(icdfde);
		consultaDeFuncionDeEspectaculoFrame.setVisible(false);
		consultaDeFuncionDeEspectaculoFrame.setLocation(130, 130);
		frame.getContentPane().add(consultaDeFuncionDeEspectaculoFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenuAltas = new JMenu("Altas");
		menuBar.add(mnMenuAltas);
		
		JMenuItem mntmAltaUsuario = new JMenuItem("Usuario");
		mntmAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuarioInternalFrame.setVisible(true);
			}
		});
		mnMenuAltas.add(mntmAltaUsuario);
		
		JMenuItem mntmAltaEspectaculo = new JMenuItem("Espectaculo");
		mntmAltaEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(altaEspectaculoInternalFrame.iniciarlizarComboBoxes())
					altaEspectaculoInternalFrame.setVisible(true);
			}
		});
		mnMenuAltas.add(mntmAltaEspectaculo);
		
		JMenuItem mntmAltaFunEspectaculo = new JMenuItem("Funcion de Espectaculo");
		mntmAltaFunEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(altaDeFuncionDeEspectaculoInternalFrame.inicializarComboBoxes()) {
					altaDeFuncionDeEspectaculoInternalFrame.setVisible(true);
				}
			}
		});
		mnMenuAltas.add(mntmAltaFunEspectaculo);

		
		
		JMenuItem mntmAltaPlataforma = new JMenuItem("Plataforma");
		mntmAltaPlataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaPlataformaInternalFrame.setVisible(true);
			}
		});
		mnMenuAltas.add(mntmAltaPlataforma);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		JMenuItem mntmConEspectaculo = new JMenuItem("Espectaculo");
		mntmConEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(consultaEspectaculoFrame.loadPlataformasBD()) {
					consultaEspectaculoFrame.setVisible(true);
				}
			}
		});
		mnConsultas.add(mntmConEspectaculo);
		
		JMenuItem mntmConFunEspectaculo = new JMenuItem("Funcion de Espectaculo");
		mntmConFunEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(consultaDeFuncionDeEspectaculoFrame.iniciarlizarCBoxPlataformas())
					consultaDeFuncionDeEspectaculoFrame.setVisible(true);				
			}
		});
		mnConsultas.add(mntmConFunEspectaculo);
	}
}

