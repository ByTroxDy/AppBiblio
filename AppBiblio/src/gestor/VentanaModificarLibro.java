package gestor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import app.Documento;
import app.Libro;
import db.DocumentoMaxDB;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaModificarLibro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEditorial, textFieldPaginas;
	private String editorial, tematica;
	private int numpaginas;
	
	static Documento documento;

	public VentanaModificarLibro() {
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
		panelTitle.setLayout(null);
		panelTitle.setBackground(new Color(0, 128, 192));
		panelTitle.setForeground(new Color(0, 0, 0));
		panelTitle.setBounds(0, 0, 592, 75);
		contentPane.add(panelTitle);
		
				JLabel lblAlta = new JLabel("MODIFICAR LIBRO");
				lblAlta.setBounds(94, 0, 385, 75);
				panelTitle.add(lblAlta);
				lblAlta.setForeground(new Color(238, 238, 236));
				lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(238, 238, 236));
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 70, 592, 297);
		contentPane.add(panel);

		JLabel lblIntroduceElIsbn = new JLabel("Introduce los nuevos datos");
		lblIntroduceElIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceElIsbn.setBounds(0, 29, 592, 28);
		panel.add(lblIntroduceElIsbn);

		JLabel lblIsbn = new JLabel("Editorial");
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn.setBounds(172, 85, 79, 17);
		panel.add(lblIsbn);

		textFieldEditorial = new JTextField();
		textFieldEditorial.setBackground(new Color(0, 128, 192));
		textFieldEditorial.setForeground(new Color(238, 238, 236));
		textFieldEditorial.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldEditorial.setBounds(247, 84, 183, 21);
		panel.add(textFieldEditorial);
		textFieldEditorial.setColumns(10);

		JLabel lblPginas = new JLabel("Páginas");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(172, 132, 79, 17);
		panel.add(lblPginas);

		textFieldPaginas = new JTextField();
		textFieldPaginas.setBackground(new Color(0, 128, 192));
		textFieldPaginas.setForeground(new Color(238, 238, 236));
		textFieldPaginas.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPaginas.setColumns(10);
		textFieldPaginas.setBounds(247, 131, 183, 21);
		panel.add(textFieldPaginas);

		JLabel lblTemtica = new JLabel("Temática");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(172, 186, 79, 17);
		panel.add(lblTemtica);

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBackground(new Color(0, 128, 192));
		comboBox.setForeground(new Color(238, 238, 236));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Ciencias", "Historia", "Literatura", "Filosofía", "Técnicos", "Otros..." }));
		comboBox.setBounds(247, 182, 183, 26);
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
		
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 262, 291, 23);
		panel.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(315, 261, 265, 24);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorial = textFieldEditorial.getText().toString();
				numpaginas = Integer.parseInt(textFieldPaginas.getText());
				tematica = comboBox.getSelectedItem().toString();

				Libro libro = new Libro(documento.getISBN(), editorial, numpaginas, tematica);
				DocumentoMaxDB docDB = new DocumentoMaxDB();

				if (docDB.updateDocLib(documento, libro)) {
					JOptionPane.showMessageDialog(panel, "Actualización exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panel, "Error al intentar actualizar datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}// if else
			}//actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(238, 238, 236));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarLibro frame = new VentanaModificarLibro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}