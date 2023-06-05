package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import db.DocumentoMaxDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCopiaSeguridad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private String backupName;

	public VentanaCopiaSeguridad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//Panel Principal
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(null);
		panel.setBounds(0, 0, 584, 71);
		panel.setLayout(null);
		contentPane.add(panel);
		
		//Titulo
		JLabel lblAlta = new JLabel("CÒPIA DE SEGURETAT");
		lblAlta.setBounds(10, 0, 470, 71);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panel.add(lblAlta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaCopiaSeguridad.class.getResource("/imagenes/ImagenGestor.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(501, 0, 46, 71);
		panel.add(lblNewLabel);
		
		//Panel contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(null);
		panel_1.setBounds(0, 70, 584, 291);
		contentPane.add(panel_1);
		
		//Titulo contenido
		JLabel lblIntroduceElNombre = new JLabel("Introdueix el nom");
		lblIntroduceElNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElNombre.setForeground(new Color(0, 0, 0));
		lblIntroduceElNombre.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceElNombre.setBounds(0, 66, 584, 28);
		panel_1.add(lblIntroduceElNombre);
		
		JLabel lblBackup = new JLabel("Nom backup");
		lblBackup.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBackup.setBounds(151, 117, 122, 17);
		panel_1.add(lblBackup);
				
		textFieldName = new JTextField();
		textFieldName.setForeground(new Color(255, 255, 255));
		textFieldName.setBackground(new Color(0, 128, 192));
		textFieldName.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldName.setBounds(252, 116, 183, 21);
		panel_1.add(textFieldName);
		textFieldName.setColumns(10);

		//Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 250, 280, 30);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Aceptar
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(300, 250, 274, 30);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backupName = textFieldName.getText().toString();
				
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				docDB.copiaSeguridad(backupName);
				
				JOptionPane.showMessageDialog(panel_1, backupName +" Backup realizat correctament", "Backup", JOptionPane.INFORMATION_MESSAGE);
			}//actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
	}// VentanaCopiaSeguridad
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCopiaSeguridad frame = new VentanaCopiaSeguridad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}//run
		});
	}// main
}// VentanaCopiaSeguridad
