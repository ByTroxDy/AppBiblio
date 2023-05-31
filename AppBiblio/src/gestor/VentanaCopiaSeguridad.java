package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import db.DocumentoMaxDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCopiaSeguridad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private String backupName;
	
	/**
	 * Launch the application.
	 */
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
	}
	/**
	 * Create the frame.
	 */
	public VentanaCopiaSeguridad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 329);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//Panel Principal
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		//Titulo
		JLabel lblAlta = new JLabel("COPIA DE SEGURIDAD");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		//Panel contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Backup de base de dades", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
		
		//Titulo contenido
		JLabel lblIntroduceElNombre = new JLabel("Introdueix el nom");
		lblIntroduceElNombre.setForeground(new Color(0, 0, 0));
		lblIntroduceElNombre.setFont(new Font("Dialog", Font.BOLD, 17));
		lblIntroduceElNombre.setBounds(104, 55, 159, 28);
		panel_1.add(lblIntroduceElNombre);
		
		JLabel lblBackup = new JLabel("Nom backup");
		lblBackup.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBackup.setBounds(75, 96, 122, 17);
		panel_1.add(lblBackup);
				
		textFieldName = new JTextField();
		textFieldName.setBounds(176, 95, 114, 21);
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
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 168, 159, 30);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Aceptar
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(183, 166, 165, 30);
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
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
	}
}
