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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class VentanaAsigna extends JFrame {

	private JPanel panel;
	private JTextField usuariText;
	private JComboBox<String> select;
	private JButton eixirButton;
	private JButton asignarButton;
	private final Action action = new Eixir();
	private final Action action_1 = new Asignar();
	private JLabel contraLabel;
	private JPasswordField contraText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAsigna frame = new VentanaAsigna();
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
	public VentanaAsigna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel titulo = new JLabel("Asignar Usuari");
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(6, 6, 438, 26);
		panel.add(titulo);
		
		JLabel usuariLabel = new JLabel("Usuari");
		usuariLabel.setBounds(87, 78, 61, 16);
		panel.add(usuariLabel);
		
		usuariText = new JTextField();
		usuariText.setBounds(197, 73, 130, 26);
		panel.add(usuariText);
		usuariText.setColumns(10);
		
		select = new JComboBox<String>();
		select.setBounds(197, 132, 130, 27);
		select.addItem("socio");
		select.addItem("gestor");
		select.addItem("admin");
		panel.add(select);
		
		JLabel asignarLabel = new JLabel("Asignar");
		asignarLabel.setBounds(87, 136, 61, 16);
		panel.add(asignarLabel);
		
		eixirButton = new JButton("Eixir");
		eixirButton.setAction(action);
		eixirButton.setBounds(6, 215, 117, 29);
		panel.add(eixirButton);
		
		asignarButton = new JButton("Asignar");
		asignarButton.setAction(action_1);
		asignarButton.setBounds(327, 215, 117, 29);
		panel.add(asignarButton);
		
		contraLabel = new JLabel("Contraseña");
		contraLabel.setBounds(62, 106, 74, 16);
		panel.add(contraLabel);
		
		contraText = new JPasswordField();
		contraText.setBounds(197, 101, 130, 26);
		panel.add(contraText);
	}//administradorAsigna

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Vuelve al menu de admin");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}
	}//Eixir
	private class Asignar extends AbstractAction {
		public Asignar() {
			putValue(NAME, "Asignar");
			putValue(SHORT_DESCRIPTION, "Modifica la asignación");
		}
		public void actionPerformed(ActionEvent e) {
			String usuari, contra, asigna;
			usuari= usuariText.getText();
			contra= new String(contraText.getPassword());
			asigna= select.getSelectedItem().toString();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			
			if (!usuDB.validarCuenta(usuari, contra)) {
				JOptionPane.showMessageDialog(panel, "El usuario actual y la contraseña no coinciden.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {// Verificar si el nuevo usuario ya está en uso
				usuDB.actualizarRol(usuari, asigna);
				JOptionPane.showMessageDialog(panel, "El nombre de usuario se ha actualizado correctamente.",
						"Éxito", JOptionPane.INFORMATION_MESSAGE);
			}

			usuariText.setText("");
			contraText.setText("");
		}
	}//Asignar
}//end
