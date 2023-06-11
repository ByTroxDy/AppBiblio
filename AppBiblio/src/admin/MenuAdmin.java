package admin;

import gestor.VentanaActivarDoc;
import gestor.VentanaRespaldo;
import socio.VentanaConsultarDocumento;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import a.Inicio;

import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenuAdmin extends JFrame {
	
	private JPanel menuAdmin;
	private final Action actionAlta = new Alta();
	private final Action actionBaja = new Baja();
	private final Action actionModi = new Modificacio();
	private final Action actionNoti = new Notificar();
	private final Action actionAltaDocu = new AltaDocu();
	private final Action actionConsultaDocu = new ConsultaDocu();
	private final Action actionRespaldoDocu = new RespaldoDocu();
	private final Action actionEixir = new Eixir();

	public MenuAdmin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 370);
		menuAdmin = new JPanel();
		menuAdmin.setBackground(new Color(186, 255, 248));
		menuAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));
		menuAdmin.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel menuLabel = new JLabel("Menu Admin");
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuLabel.setOpaque(true);
		menuLabel.setBackground(new Color(255, 150, 49));
		menuLabel.setBounds(0, 6, 444, 26);
		
		JLabel labelAdminUsuari = new JLabel("Administar Usuaris");
		labelAdminUsuari.setHorizontalAlignment(SwingConstants.CENTER);
		labelAdminUsuari.setBounds(10, 48, 200, 18);
		
		JButton buttonAltaUsuario = new JButton("Alta");
		buttonAltaUsuario.setAction(actionAlta);
		buttonAltaUsuario.setBackground(new Color(253, 180, 105));
		buttonAltaUsuario.setBounds(50, 77, 117, 55);
		
		JButton buttonBajaUsuario = new JButton("Baixa");
		buttonBajaUsuario.setAction(actionBaja);
		buttonBajaUsuario.setBounds(50, 132, 117, 55);
		
		JButton buttonModificacionUsuario = new JButton("Modificació");
		buttonModificacionUsuario.setAction(actionModi);
		buttonModificacionUsuario.setBounds(50, 186, 117, 55);
		
		JButton buttonNotificacion = new JButton("Notificar");
		buttonNotificacion.setAction(actionNoti);
		buttonNotificacion.setBounds(50, 241, 117, 55);
		
		JLabel labelAdministradorDocumento = new JLabel("Administrar Documents");
		labelAdministradorDocumento.setHorizontalAlignment(SwingConstants.CENTER);
		labelAdministradorDocumento.setBounds(228, 48, 200, 18);
		
		JButton buttonAltaDocumento = new JButton("Alta");
		buttonAltaDocumento.setAction(actionAltaDocu);
		buttonAltaDocumento.setBounds(270, 78, 117, 55);
		
		JButton buttonConsultaDocumento = new JButton("Consultar");
		buttonConsultaDocumento.setAction(actionConsultaDocu);
		buttonConsultaDocumento.setBounds(270, 132, 117, 54);
		
		JButton buttonRespaldo = new JButton("Suport");
		buttonRespaldo.setAction(actionRespaldoDocu);
		buttonRespaldo.setBounds(270, 186, 117, 55);
		
		JButton btnSalir = new JButton("Eixir");
		btnSalir.setAction(actionEixir);
		btnSalir.setBounds(270, 241, 117, 55);
		
		menuAdmin.add(menuLabel);
		menuAdmin.add(labelAdminUsuari);
		menuAdmin.add(buttonAltaUsuario);
		menuAdmin.add(buttonBajaUsuario);
		menuAdmin.add(buttonModificacionUsuario);
		menuAdmin.add(buttonNotificacion);
		menuAdmin.add(labelAdministradorDocumento);
		menuAdmin.add(buttonAltaDocumento);
		menuAdmin.add(buttonConsultaDocumento);
		menuAdmin.add(buttonRespaldo);
		menuAdmin.add(btnSalir);
		
		setContentPane(menuAdmin);
	}//administradorMenu
	
	private class Alta extends AbstractAction {
		public Alta() {
			putValue(NAME, "Alta");
			putValue(SHORT_DESCRIPTION, "Obre la finestra d'alta d'usuari.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaAltaUsuario admin = new VentanaAltaUsuario();
			admin.setVisible(true);
			dispose();
		}
	}//alta
	
	private class Baja extends AbstractAction {
		public Baja() {
			putValue(NAME, "Baixa");
			putValue(SHORT_DESCRIPTION, "Obre la finestra de baixa d'usuari.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaBajaUsuario baja = new VentanaBajaUsuario();
			baja.setVisible(true);
			dispose();
		}
	}//Baixa
	
	private class Modificacio extends AbstractAction {
		public Modificacio() {
			putValue(NAME, "Modificació");
			putValue(SHORT_DESCRIPTION, "Obre la finestra de modificar usuari.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaModificacion modi = new VentanaModificacion();
			modi.setVisible(true);
			dispose();
		}
	}//Modificacio
	
	private class Notificar extends AbstractAction {
		public Notificar() {
			putValue(NAME, "Notificar");
			putValue(SHORT_DESCRIPTION, "Obre la finestra notificar usuari.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaNotificar noti = new VentanaNotificar();
			noti.setVisible(true);
			dispose();
		}
	}//Notificar
	
	private class AltaDocu extends AbstractAction {
		public AltaDocu() {
			putValue(NAME, "Alta");
			putValue(SHORT_DESCRIPTION, "Obre la finestra alta documents.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaActivarDoc altaDocu = new VentanaActivarDoc();
			altaDocu.setVisible(true);
			dispose();
		}
	}//AltaDocu
	
	private class ConsultaDocu extends AbstractAction {
		public ConsultaDocu() {
			putValue(NAME, "Consulta");
			putValue(SHORT_DESCRIPTION, "Obre la finestra consultar documents.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaConsultarDocumento consulDocu = new VentanaConsultarDocumento();
			consulDocu.setVisible(true);
			dispose();
		}
	}//consultaDocu
	
	private class RespaldoDocu extends AbstractAction {
		public RespaldoDocu() {
			putValue(NAME, "Suport");
			putValue(SHORT_DESCRIPTION, "Obre la finestra suport documents.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaRespaldo respaldoDocu = new VentanaRespaldo();
			respaldoDocu.setVisible(true);
			dispose();
		}
	}//respaldoDocu
	
	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Surt del menú d'administrador.");
		}
		public void actionPerformed(ActionEvent e) {
			Inicio app = new Inicio();
			app.setVisible(true);
			dispose();
		}
	}//Eixir
}//end
