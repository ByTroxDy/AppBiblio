package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Libro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAltaLibro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField, textField_1;
	private String editorial, tematica;
	private int numpaginas;
	
	static Documento documento;

	public VentanaAltaLibro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 128, 192));
		panelTitle.setForeground(new Color(0, 0, 0));
		panelTitle.setBorder(null);
		panelTitle.setBounds(0, 0, 592, 72);
		panelTitle.setLayout(null);
		contentPane.add(panelTitle);
		
		JLabel lblAlta = new JLabel("ALTA LLIBRE");
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		lblAlta.setBounds(135, 11, 272, 50);
		panelTitle.add(lblAlta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/icono64.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(415, 0, 62, 72);
		panelTitle.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(238, 238, 236));
		panel.setBackground(SystemColor.window);
		panel.setBorder(null);
		panel.setBounds(0, 70, 592, 297);
		contentPane.add(panel);

		JLabel lblIntroduceElIsbn = new JLabel("Introdueix les dades");
		lblIntroduceElIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceElIsbn.setBounds(0, 28, 584, 28);
		panel.add(lblIntroduceElIsbn);

		JLabel lblIsbn = new JLabel("Editorial");
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn.setBounds(119, 82, 79, 17);
		panel.add(lblIsbn);

		textField = new JTextField();
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(new Color(0, 128, 192));
		textField.setBounds(194, 81, 262, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblPginas = new JLabel("Pàgines");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(119, 122, 79, 17);
		panel.add(lblPginas);

		textField_1 = new JTextField();
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setBackground(new Color(0, 128, 192));
		textField_1.setColumns(10);
		textField_1.setBounds(194, 121, 262, 21);
		panel.add(textField_1);

		JLabel lblTemtica = new JLabel("Temàtica");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(119, 163, 79, 17);
		panel.add(lblTemtica);

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(0, 128, 192));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ciències", "Història", "Literatura", "Filosofia", "Tècnics", "Altres..."}));
		comboBox.setBounds(194, 159, 262, 26);
		panel.add(comboBox);

		JButton btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 262, 286, 23);
		panel.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		JButton btnNewButton = new JButton("Acceptar");
		btnNewButton.setBounds(316, 261, 268, 24);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorial = textField.getText().toString();
				numpaginas = Integer.parseInt(textField_1.getText());
				tematica = comboBox.getSelectedItem().toString();

				Libro libro = new Libro(documento.getISBN(), editorial, numpaginas, tematica);
				DocumentoMaxDB docDB = new DocumentoMaxDB();

				if (docDB.insertDocLib(documento, libro)) {
					JOptionPane.showMessageDialog(panel, "Registro exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panel, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}// if else
			}//actionPerformed
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
					VentanaAltaLibro frame = new VentanaAltaLibro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}