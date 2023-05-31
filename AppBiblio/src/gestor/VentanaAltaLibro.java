package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Libro;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
		setBounds(100, 100, 478, 329);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(SystemColor.window);
		panelTitle.setForeground(new Color(0, 0, 0));
		panelTitle.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panelTitle.setBounds(53, 14, 359, 44);
		contentPane.add(panelTitle);

		JLabel lblAlta = new JLabel("ALTA LIBRO");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		panelTitle.add(lblAlta);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(238, 238, 236));
		panel.setBackground(SystemColor.window);
		panel.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)),
				"Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(53, 70, 360, 208);
		contentPane.add(panel);

		JLabel lblIntroduceElIsbn = new JLabel("Introduce los datos");
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblIntroduceElIsbn.setBounds(66, 28, 212, 28);
		panel.add(lblIntroduceElIsbn);

		JLabel lblIsbn = new JLabel("Editorial");
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn.setBounds(48, 68, 79, 17);
		panel.add(lblIsbn);

		textField = new JTextField();
		textField.setBounds(123, 67, 155, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblPginas = new JLabel("Páginas");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(48, 94, 79, 17);
		panel.add(lblPginas);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 93, 155, 21);
		panel.add(textField_1);

		JLabel lblTemtica = new JLabel("Temática");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(48, 123, 79, 17);
		panel.add(lblTemtica);

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Ciencias", "Historia", "Literatura", "Filosofía", "Técnicos", "Otros..." }));
		comboBox.setBounds(123, 119, 155, 26);
		panel.add(comboBox);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 174, 171, 23);
		panel.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		JButton btnNewButton = new JButton("Alta");
		btnNewButton.setBounds(187, 173, 161, 24);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorial = textField.getText().toString();
				numpaginas = Integer.parseInt(textField_1.getText());
				tematica = comboBox.getSelectedItem().toString();

				Libro libro = new Libro(documento.getISBN(), editorial, numpaginas, tematica);
				
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				if (!docDB.checkDocumento(libro)) {
					if (docDB.insertDocLib(documento, libro)) {
						JOptionPane.showMessageDialog(panel, "Registro exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(panel, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(panel, "Actulizado exitosamente", "Libro", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
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
