package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class dd extends JFrame {

	private JPanel panel;
	private JTextField usuariText;
	private JPasswordField contraText;
	private JButton btnNewButton;
	private JButton BorrarButton;
	private final Action action = new Eixir();
	private final Action action_1 = new Borrar();
	private JTextField adminUser;
	private JLabel adminLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dd frame = new dd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//exception
			}//run
		});
	}//main

	/**
	 * Create the frame.
	 */
	public dd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel Titulo = new JLabel("Baixa Usuari");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setOpaque(true);
		Titulo.setBackground(new Color(255, 150, 49));
		Titulo.setBounds(6, 6, 438, 26);
		panel.add(Titulo);
		
		JLabel usuarioLabel = new JLabel("Usuari");
		usuarioLabel.setBounds(31, 63, 61, 16);
		panel.add(usuarioLabel);
		
		usuariText = new JTextField();
		usuariText.setBounds(135, 58, 187, 26);
		panel.add(usuariText);
		usuariText.setColumns(10);
		
		contraText = new JPasswordField();
		contraText.setBounds(135, 126, 187, 26);
		panel.add(contraText);
		
		JLabel contraLabel = new JLabel("Contrasenya");
		contraLabel.setBounds(21, 131, 78, 16);
		panel.add(contraLabel);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setAction(action_1);
		btnNewButton.setBounds(299, 198, 117, 29);
		panel.add(btnNewButton);
		
		BorrarButton = new JButton("New button");
		BorrarButton.setAction(action);
		BorrarButton.setBounds(31, 198, 117, 29);
		panel.add(BorrarButton);
		
		adminUser = new JTextField();
		adminUser.setBounds(135, 96, 187, 26);
		panel.add(adminUser);
		adminUser.setColumns(10);
		
		adminLabel = new JLabel("Admin");
		adminLabel.setBounds(31, 101, 61, 16);
		panel.add(adminLabel);
	}//admniistradorBaja
	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}//ActionPerformed
	}//Eixir
	private class Borrar extends AbstractAction {
		public Borrar() {
			putValue(NAME, "Borrar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String usuario;
			String contra;
			
			usuario= usuariText.getText();
			contra= new String (contraText.getPassword());
			
		}//actionPerformed
	}//Borrar
}//end
