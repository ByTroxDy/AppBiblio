package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import db.UsuarioMaxDB;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class VentanaSelectModificacion extends JFrame {

	private JPanel panel;
	private JTextField adminText;
	private JPasswordField contraText;
	private JComboBox<String> modiBox;
	private final Action action = new Eixir();
	private final Action action_1 = new modifica();

	public VentanaSelectModificacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel titulo = new JLabel("Modificació");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setBounds(6, 6, 438, 16);
		panel.add(titulo);
		
		modiBox = new JComboBox<String>();
		modiBox.setBounds(162, 134, 141, 27);
		modiBox.addItem("usuari");
		modiBox.addItem("contrasenya");
		modiBox.addItem("correu");
		modiBox.addItem("rol");
		panel.add(modiBox);
		
		adminText = new JTextField();
		adminText.setBounds(162, 58, 141, 26);
		panel.add(adminText);
		adminText.setColumns(10);
		
		contraText = new JPasswordField();
		contraText.setBounds(162, 96, 141, 26);
		panel.add(contraText);
		contraText.setColumns(10);
		
		JLabel adminLabel = new JLabel("Admin");
		adminLabel.setBounds(60, 63, 61, 16);
		panel.add(adminLabel);
		
		JLabel contraLabel = new JLabel("Contrasenya");
		contraLabel.setBounds(60, 101, 90, 16);
		panel.add(contraLabel);
		
		JLabel modiContra = new JLabel("Modificació");
		modiContra.setBounds(60, 138, 90, 16);
		panel.add(modiContra);
		
		JButton eixirButton = new JButton("Eixir");
		eixirButton.setAction(action);
		eixirButton.setBounds(20, 221, 117, 29);
		panel.add(eixirButton);
		
		JButton modiButton = new JButton("Modificar");
		modiButton.setAction(action_1);
		modiButton.setBounds(297, 221, 117, 29);
		panel.add(modiButton);
		setLocationRelativeTo(null);
		
	}//ventanaSelectModificacion
	
	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Vuelve al Menu de Admin");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}
	}//Eixir
	
	private class modifica extends AbstractAction {
		public modifica() {
			putValue(NAME, "Modifica");
			putValue(SHORT_DESCRIPTION, "Selecciona la modificació");
		}
		public void actionPerformed(ActionEvent e) {
			String admin, contra, modi, grupo; 
			admin = adminText.getText();
			contra = new String(contraText.getPassword());
			modi = modiBox.getSelectedItem().toString();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			grupo = usuDB.obtenerGrupo(admin);
			if (usuDB.iniciarSesion(admin, contra)&&grupo.equals("admin")) {
				if (modi.equals("usuari")) {
					VentanaModiUsuario modiUser = new VentanaModiUsuario();
					modiUser.setVisible(true);
				} else if (modi.equals("contrasenya")) {
					VentanaModiContra modiContra = new VentanaModiContra();
					modiContra.setVisible(true);
				} else if (modi.equals("correu")) {
					VentanaModiCorreo modiCorreo = new VentanaModiCorreo();
					modiCorreo.setVisible(true);
				} else if (modi.equals("rol")) {
					VentanaAsigna modiRol = new VentanaAsigna();
					modiRol.setVisible(true);
				}
				dispose();
			}
			
		}
	}//modifica
}//exit
