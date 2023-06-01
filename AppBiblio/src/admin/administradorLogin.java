package administrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.SwingConstants;

public class administradorLogin extends JFrame {

	private static administradorLogin frame;
	private JPanel ventanaLoginAdministrador;
	private JLabel usuarioLogin;
	private JPasswordField passwordLogin;
	private JTextPane usuarioText;
	private JLabel tituloLogin;
	private JLabel ContrasenaLabel;
	private final Action action = new entrarAdmin();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new administradorLogin();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public administradorLogin() {
		// DEclaracióVariables --> locals
		
		
		// Codi
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ventanaLoginAdministrador = new JPanel();
		ventanaLoginAdministrador.setBackground(new Color(157, 238, 240));
		ventanaLoginAdministrador.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ventanaLoginAdministrador);
		ventanaLoginAdministrador.setLayout(null);
		
		passwordLogin = new JPasswordField();
		passwordLogin.setBounds(174, 124, 177, 26);
		ventanaLoginAdministrador.add(passwordLogin);
		
		usuarioLogin = new JLabel("Usuario");
		usuarioLogin.setBounds(92, 75, 48, 16);
		ventanaLoginAdministrador.add(usuarioLogin);
		
		tituloLogin = new JLabel();
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setOpaque(rootPaneCheckingEnabled);
		tituloLogin.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		tituloLogin.setBounds(6, 6, 438, 26);
		tituloLogin.setText("Login Administrador");
		tituloLogin.setBackground(new Color(255, 150, 49));
		ventanaLoginAdministrador.add(tituloLogin);
		
		usuarioText = new JTextPane();
		usuarioText.setText("Usuario");
		usuarioText.setBounds(174, 75, 177, 20);
		ventanaLoginAdministrador.add(usuarioText);
		
		JLabel label = new JLabel("New label");
		label.setBounds(92, 129, 11, 3);
		ventanaLoginAdministrador.add(label);
		
		ContrasenaLabel = new JLabel("New label");
		ContrasenaLabel.setBounds(69, 129, 71, 16);
		ContrasenaLabel.setText("Contraseña");
		ventanaLoginAdministrador.add(ContrasenaLabel);
		
		JButton loginButton = new JButton("Entrar");
		loginButton.setAction(action);
		loginButton.setBounds(234, 178, 117, 29);
		ventanaLoginAdministrador.add(loginButton);
		
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.setAction(action_1);
		btnNewButton.setBounds(69, 178, 117, 29);
		ventanaLoginAdministrador.add(btnNewButton);
	}//adinistradorLogin
	
	public class entrarAdmin extends AbstractAction {
		public entrarAdmin() {
			putValue(NAME, "Entrar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String usuario;
			String password;
			
			usuario=usuarioText.getText();
			password= new String (passwordLogin.getPassword());
			
			if(usuario.equals("Olopa") && password.equals("12345")) {
				System.out.println("Entrando...");
				administradorMenu panelAdministrador = new administradorMenu();
				panelAdministrador.setVisible(true);
				frame.dispose();
				
			} else {
				System.out.println("Intrduzca un usuario o contraseña valido ");
			}
			
		}//actionPerformed
	}//entrarAdmin
	
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Atras");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Volviendo...");
		}
	}
}
