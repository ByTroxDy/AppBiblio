package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestor.VentanaAltaDocumento;
import gestor.VentanaCopiaSeguridad;
import gestor.VentanaRestauracion;
import socio.VentanaConsultarDocumento;
import socio.VentanaInicioSesion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class MenuAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel menuAdministrador;
	private final Action action = new alta();
	private final Action action_1 = new Baixa();
	private final Action action_2 = new Modificacio();
	private final Action action_3 = new Notificar();
	private final Action action_5 = new AltaDocu();
	private final Action action_8 = new consultaDocu();
	private final Action action_9 = new copiaDocu();
	private final Action action_10 = new restauraDocu();
	private final Action action_11 = new Eixir();

	public MenuAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		menuAdministrador = new JPanel();
		menuAdministrador.setBackground(new Color(186, 255, 248));
		menuAdministrador.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(menuAdministrador);
		menuAdministrador.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setOpaque(true);
		menuLabel.setBackground(new Color(255, 150, 49));
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuLabel.setBounds(6, 6, 438, 16);
		menuAdministrador.add(menuLabel);
		
		JButton buttonAltaUsuario = new JButton("Alta");
		buttonAltaUsuario.setAction(action);
		buttonAltaUsuario.setBackground(new Color(253, 180, 105));
		buttonAltaUsuario.setBounds(0, 50, 117, 55);
		menuAdministrador.add(buttonAltaUsuario);
		
		JButton buttonBajaUsuario = new JButton("Baixa");
		buttonBajaUsuario.setAction(action_1);
		buttonBajaUsuario.setBounds(0, 105, 117, 55);
		menuAdministrador.add(buttonBajaUsuario);
		
		JButton buttonModificacionUsuario = new JButton("Modificació");
		buttonModificacionUsuario.setAction(action_2);
		buttonModificacionUsuario.setBounds(0, 158, 117, 55);
		menuAdministrador.add(buttonModificacionUsuario);
		
		JButton buttonNotificacion = new JButton("Notificar");
		buttonNotificacion.setAction(action_3);
		buttonNotificacion.setBounds(0, 211, 117, 55);
		menuAdministrador.add(buttonNotificacion);
		
		JLabel labelAdminUsuari = new JLabel("Administar Usuaris");
		labelAdminUsuari.setBounds(6, 22, 128, 16);
		menuAdministrador.add(labelAdminUsuari);
		
		JButton buttonAltaDocumento = new JButton("Alta");
		buttonAltaDocumento.setAction(action_5);
		buttonAltaDocumento.setBounds(333, 50, 117, 55);
		menuAdministrador.add(buttonAltaDocumento);
		
		JButton buttonConsultaDocumento = new JButton("Consultar");
		buttonConsultaDocumento.setAction(action_8);
		buttonConsultaDocumento.setBounds(333, 105, 117, 54);
		menuAdministrador.add(buttonConsultaDocumento);
		
		JLabel labelAdministradorDocumento = new JLabel("Administrar Document");
		labelAdministradorDocumento.setBounds(293, 22, 151, 26);
		menuAdministrador.add(labelAdministradorDocumento);
		
		JButton buttonCopiaDocumentos = new JButton("Copia Seguretat");
		buttonCopiaDocumentos.setAction(action_9);
		buttonCopiaDocumentos.setBounds(333, 158, 117, 55);
		menuAdministrador.add(buttonCopiaDocumentos);
		
		JButton buttonRestauracionDocu = new JButton("Restauració ");
		buttonRestauracionDocu.setAction(action_10);
		buttonRestauracionDocu.setBounds(333, 211, 117, 55);
		menuAdministrador.add(buttonRestauracionDocu);
		
		JButton btnNewButton = new JButton("Eixir");
		btnNewButton.setAction(action_11);
		btnNewButton.setBounds(0, 330, 117, 29);
		menuAdministrador.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Administrar");
		lblNewLabel.setBounds(200, 144, 74, 16);
		menuAdministrador.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Biblioteca");
		lblNewLabel_1.setBounds(210, 176, 61, 16);
		menuAdministrador.add(lblNewLabel_1);
	}//administradorMenu
	
	private class alta extends AbstractAction {
		public alta() {
			putValue(NAME, "Alta");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de alta de Admin");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaAltaUsuario admin = new VentanaAltaUsuario();
			admin.setVisible(true);
			dispose();
		}
	}//alta
	
	private class Baixa extends AbstractAction {
		public Baixa() {
			putValue(NAME, "Baixa");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Baixa de Admin");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaBaja baixa = new VentanaBaja();
			baixa.setVisible(true);
			dispose();
		}
	}//Baixa
	
	private class Modificacio extends AbstractAction {
		public Modificacio() {
			putValue(NAME, "Modificació");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Modificar de Admin");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaSelectModificacion modi = new VentanaSelectModificacion();
			modi.setVisible(true);
			dispose();
		}
	}//Modificacio
	
	private class Notificar extends AbstractAction {
		public Notificar() {
			putValue(NAME, "Notificar");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Notificar de Admin");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaNotifi noti = new VentanaNotifi();
			noti.setVisible(true);
			dispose();
		}
	}//Notificar
	
	private class AltaDocu extends AbstractAction {
		public AltaDocu() {
			putValue(NAME, "Alta");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Alta de documentos");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaAltaDocumento altaDocu = new VentanaAltaDocumento();
			altaDocu.setVisible(true);
			dispose();
		}
	}//AltaDocu
	
	private class consultaDocu extends AbstractAction {
		public consultaDocu() {
			putValue(NAME, "Consulta");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Consulta de documentos");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaConsultarDocumento consulDocu = new VentanaConsultarDocumento();
			consulDocu.setVisible(true);
			dispose();
		}
	}//consultaDocu
	
	private class copiaDocu extends AbstractAction {
		public copiaDocu() {
			putValue(NAME, "Copia seguretat");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Copia de seguredad de documentos");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaCopiaSeguridad copiar = new VentanaCopiaSeguridad();
			copiar.setVisible(true);
			dispose();
		}
	}//copiaDocu
	
	private class restauraDocu extends AbstractAction {
		public restauraDocu() {
			putValue(NAME, "Restauració");
			putValue(SHORT_DESCRIPTION, "Abre la ventana de Restauració de documentos");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaRestauracion restaura = new VentanaRestauracion();
			restaura.setVisible(true);
			dispose();
		}
	}//restauraDocu
	
	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Sale del menu de Administrador");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaInicioSesion ventana = new VentanaInicioSesion();
			ventana.setVisible(true);
			dispose();
		}
	}//Eixir
}//end
