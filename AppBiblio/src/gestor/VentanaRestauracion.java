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

public class VentanaRestauracion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private String nom;
	
	public VentanaRestauracion() {
		setTitle("Restauració");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 584, 71);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("RESTAURACIÓ");
		lblAlta.setBounds(112, 0, 370, 71);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panel.add(lblAlta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaRestauracion.class.getResource("/imagenes/ImagenGestor.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(476, 0, 46, 71);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(0, 70, 584, 291);
		contentPane.add(panel_1);

		JLabel lblIntroduceElIsbn = new JLabel("Nom del backup");
		lblIntroduceElIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceElIsbn.setBounds(0, 52, 584, 28);
		panel_1.add(lblIntroduceElIsbn);
		
		JLabel lblIsbn = new JLabel("Nom");
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn.setBounds(190, 109, 60, 17);
		panel_1.add(lblIsbn);
		
		//Nom
		textFieldNom = new JTextField();
		textFieldNom.setForeground(new Color(255, 255, 255));
		textFieldNom.setBackground(new Color(0, 128, 192));
		textFieldNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNom.setBounds(236, 108, 165, 21);
		panel_1.add(textFieldNom);
		textFieldNom.setColumns(10);

		//Volver
		JButton btnVolver = new JButton("Tornar");
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
		btnVolver.setBounds(10, 250, 286, 30);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		//Aceptar
		JButton btnNewButton = new JButton("Acceptar");
		btnNewButton.setBounds(306, 250, 268, 30);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nom = textFieldNom.getText();
				
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				docDB.restaurarBackup(nom);
				
				JOptionPane.showMessageDialog(panel_1, nom +" Restaurado correctamene", "Restauración", JOptionPane.INFORMATION_MESSAGE);
			}//actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
	}// VentanaRestauracion
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRestauracion frame = new VentanaRestauracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try carch
			}// run
		});
	}// main
}// VentanaRestauracion
