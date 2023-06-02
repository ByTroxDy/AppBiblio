package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class VentanaModificacion extends JFrame {

	private JPanel panel;
	private JTextField oldContraText;
	private JTextField newContraText;
	private JTextField newUsuariText;
	private JTextField oldUsuariText;
	private final Action action = new Eixir();
	private final Action action_1 = new Modificar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificacion frame = new VentanaModificacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//run
		});
	}//main

	/**
	 * Create the frame.
	 */
	public VentanaModificacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel tituloLabel = new JLabel("Modificació");
		tituloLabel.setOpaque(true);
		tituloLabel.setBackground(new Color(255, 150, 49));
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLabel.setBounds(6, 6, 438, 25);
		panel.add(tituloLabel);
		
		JLabel oldUsuarioLabel = new JLabel("Antic usuari");
		oldUsuarioLabel.setBounds(6, 75, 120, 16);
		panel.add(oldUsuarioLabel);
		
		JLabel newUsuarioLabel = new JLabel("Nou usuari");
		newUsuarioLabel.setBounds(6, 101, 120, 16);
		panel.add(newUsuarioLabel);
		
		JLabel oldContrasenaLabel = new JLabel("Antiga Contrasenya");
		oldContrasenaLabel.setBounds(6, 131, 132, 16);
		panel.add(oldContrasenaLabel);
		
		JLabel newContraLabel = new JLabel("Nova Contrasenya");
		newContraLabel.setBounds(6, 159, 120, 16);
		panel.add(newContraLabel);
		
		oldContraText = new JTextField();
		oldContraText.setBounds(165, 126, 130, 26);
		panel.add(oldContraText);
		oldContraText.setColumns(10);
		
		newContraText = new JTextField();
		newContraText.setBounds(165, 154, 130, 26);
		panel.add(newContraText);
		newContraText.setColumns(10);
		
		newUsuariText = new JTextField();
		newUsuariText.setBounds(165, 96, 130, 26);
		panel.add(newUsuariText);
		newUsuariText.setColumns(10);
		
		oldUsuariText = new JTextField();
		oldUsuariText.setBounds(165, 70, 130, 26);
		panel.add(oldUsuariText);
		oldUsuariText.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAction(action_1);
		btnNewButton.setBounds(298, 222, 117, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAction(action);
		btnNewButton_1.setBounds(21, 222, 117, 29);
		panel.add(btnNewButton_1);
	}//administradorModificacion

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}//actionPerformed
	}//Eixir
	private class Modificar extends AbstractAction {
		public Modificar() {
			putValue(NAME, "Modificar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String oldUsurio, newUsuario, oldContra, newContra;
			oldUsurio= oldUsuariText.getText();
			newUsuario= newUsuariText.getText();
			oldContra= oldContraText.getText();
			newContra= newContraText.getText();
		}//actionPerformed
	}//Modificar
}//end
