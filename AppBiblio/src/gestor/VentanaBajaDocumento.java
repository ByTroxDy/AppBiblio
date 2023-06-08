package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import db.DocumentoMaxDB;

public class VentanaBajaDocumento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn;
	private int isbn; 

	public VentanaBajaDocumento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(null);
		panel.setBounds(0, 0, 592, 71);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("BAIXA DOCUMENT");
		lblAlta.setBounds(71, 0, 410, 70);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panel.add(lblAlta);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setBounds(491, 0, 55, 70);
		panel.add(lblImagen);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(null);
		panel_1.setBounds(0, 70, 592, 297);
		contentPane.add(panel_1);
				
		JLabel lblIsbn = new JLabel("isbn");
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn.setBounds(177, 120, 38, 17);
		panel_1.add(lblIsbn);

		
		JLabel lblIntroduceElIsbn = new JLabel("Introdueix el ISBN");
		lblIntroduceElIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceElIsbn.setBounds(0, 57, 584, 28);
		panel_1.add(lblIntroduceElIsbn);
		
		//isbn
		textFieldIsbn = new JTextField();
		textFieldIsbn.setForeground(new Color(255, 255, 255));
		textFieldIsbn.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldIsbn.setBackground(new Color(0, 128, 192));
		textFieldIsbn.setBounds(214, 119, 197, 21);
		panel_1.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		//Volver
		JButton btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 257, 278, 28);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		//Baja
		JButton btnNewButton = new JButton("Acceptar");
		btnNewButton.setBounds(306, 257, 278, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isbn = Integer.parseInt(textFieldIsbn.getText());
				
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				if (docDB.bajaDocumento(isbn)) {
					JOptionPane.showMessageDialog(panel_1, "Sa donat de baixa correctament", "Donar de Baixa", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(panel_1, "Error al introduir dades en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBajaDocumento frame = new VentanaBajaDocumento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}


