package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.UsuarioMaxDB;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class VentanaModiContra extends JFrame {

	private JPanel panel;
	private JTextField usuari;
	private JPasswordField contra;
	private JPasswordField newContra;
	private final Action action = new Eixir();
	private final Action action_1 = new Modificar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModiContra frame = new VentanaModiContra();
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
	public VentanaModiContra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel titulo = new JLabel("Modificació Contrasenya");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setBounds(6, 6, 438, 16);
		panel.add(titulo);
		
		usuari = new JTextField();
		usuari.setBounds(168, 58, 130, 26);
		panel.add(usuari);
		usuari.setColumns(10);
		
		contra = new JPasswordField();
		contra.setBounds(168, 96, 130, 26);
		panel.add(contra);
		contra.setColumns(10);
		
		newContra = new JPasswordField();
		newContra.setBounds(168, 140, 130, 26);
		panel.add(newContra);
		newContra.setColumns(10);
		
		JLabel usuariLabel = new JLabel("Usuari");
		usuariLabel.setBounds(102, 63, 47, 16);
		panel.add(usuariLabel);
		
		JLabel contraLabel = new JLabel("Antiga Contrasenya");
		contraLabel.setBounds(19, 101, 130, 16);
		panel.add(contraLabel);
		
		JLabel newContraLabel = new JLabel("Nova Contrasenya");
		newContraLabel.setBounds(29, 145, 120, 16);
		panel.add(newContraLabel);
		
		JButton eixirButton = new JButton("Eixir");
		eixirButton.setAction(action);
		eixirButton.setBounds(6, 222, 117, 29);
		panel.add(eixirButton);
		
		JButton modiButton = new JButton("Modificar");
		modiButton.setAction(action_1);
		modiButton.setBounds(312, 222, 117, 29);
		panel.add(modiButton);
	}//VentanaModiContra

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaSelectModificacion frame = new VentanaSelectModificacion();
			frame.setVisible(true);
			dispose();
		}
	}//Eixir
	private class Modificar extends AbstractAction {
		public Modificar() {
			putValue(NAME, "Modificar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String usuario, nou, antic;
			usuario= usuari.getText();
			nou= new String(newContra.getPassword());
			antic= new String(contra.getPassword());
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			if (usuDB.nombreUsuarioEnUso(usuario)&&usuDB.iniciarSesion(usuario, antic)&&antic.length()<8) {
				usuDB.cambiarContrasena(usuario, nou);
            	JOptionPane.showMessageDialog(panel, "Ha cambiat la contrasenya del Usuari correctament", "Modificació", JOptionPane.INFORMATION_MESSAGE);
            	MenuAdmin admin1 = new MenuAdmin();
				admin1.setVisible(true);
            	dispose();
			}else if (usuDB.nombreUsuarioEnUso(usuario)&&usuDB.iniciarSesion(usuario, antic)==false) {
            	JOptionPane.showMessageDialog(panel, "Contrasenya Incorrecta", "Modificació", JOptionPane.INFORMATION_MESSAGE);
			}else if (antic.length()>8) {
            	JOptionPane.showMessageDialog(panel, "Contrasenya Incorrecta", "Modificació", JOptionPane.INFORMATION_MESSAGE);
			} else {
            	JOptionPane.showMessageDialog(panel, "Usuari no encontrat", "Modificació", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}//Modificar
}//end
