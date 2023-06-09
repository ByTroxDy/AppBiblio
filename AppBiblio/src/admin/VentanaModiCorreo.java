package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.UsuarioMaxDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class VentanaModiCorreo extends JFrame {

	private JPanel panel;
	private JTextField usuariText;
	private JTextField correoText;
	private final Action action = new Eixir();
	private final Action action_1 = new modificar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModiCorreo frame = new VentanaModiCorreo();
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
	public VentanaModiCorreo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel titulo = new JLabel("Modificació Correu");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setBounds(0, 0, 450, 27);
		panel.add(titulo);
		
		usuariText = new JTextField();
		usuariText.setBounds(152, 74, 130, 26);
		panel.add(usuariText);
		usuariText.setColumns(10);
		
		JLabel usuariLabel = new JLabel("Usuari");
		usuariLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usuariLabel.setBounds(33, 79, 85, 16);
		panel.add(usuariLabel);
		
		correoText = new JTextField();
		correoText.setBounds(152, 132, 130, 26);
		panel.add(correoText);
		correoText.setColumns(10);
		
		JLabel correoLabel = new JLabel("Nou Correu");
		correoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		correoLabel.setBounds(27, 137, 91, 16);
		panel.add(correoLabel);
		
		JButton EixirButton = new JButton("Eixir");
		EixirButton.setAction(action);
		EixirButton.setBounds(16, 221, 117, 29);
		panel.add(EixirButton);
		
		JButton ModificarButton = new JButton("Modificar");
		ModificarButton.setAction(action_1);
		ModificarButton.setBounds(304, 221, 117, 29);
		panel.add(ModificarButton);
	}//VentanaModiCorreo

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Vuelve hacia la ventana VentanaSelectModificacion");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaSelectModificacion modiSelect = new VentanaSelectModificacion();
			modiSelect.setVisible(true);
		}
	}//Eixir
	private class modificar extends AbstractAction {
		public modificar() {
			putValue(NAME, "Modificar");
			putValue(SHORT_DESCRIPTION, "Modifica el correo del usuario");
		}
		public void actionPerformed(ActionEvent e) {
			String usuari, correo;
			usuari= usuariText.getText();
			correo= correoText.getText();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();

			if (usuDB.cambiarEmail(usuari, correo)) {
            	JOptionPane.showMessageDialog(panel, "Email actualitzat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(panel, "Ho sentim el usuari posat no existeix", "Error", JOptionPane.INFORMATION_MESSAGE);
            }//else
		}//actionPerformed
	}//mopdificar
}//end
