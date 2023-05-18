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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class VentanaModificarDocumento2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarDocumento2 frame = new VentanaModificarDocumento2();
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
	public VentanaModificarDocumento2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//Creación de panel Titulo
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		//Titulo del panel creado
		JLabel lblAlta = new JLabel("MODIFICAR DOCUMENTO");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		//Creación de panel de contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Introduce los nuevos datos", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
				
		//Labels y textField para la introducción de datos
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(12, 27, 60, 17);
		panel_1.add(lblTitulo);
		
		JLabel lblIsbn = new JLabel("isbn");
		lblIsbn.setBounds(12, 56, 60, 17);
		panel_1.add(lblIsbn);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_2.setColumns(10);
		textField_2.setBounds(53, 86, 69, 21);
		panel_1.add(textField_2);
		
		JLabel lblAutor = new JLabel("autor");
		lblAutor.setBounds(12, 86, 60, 17);
		panel_1.add(lblAutor);
		
		JLabel lblEditorialproductora = new JLabel("editorial/productora");
		lblEditorialproductora.setBounds(138, 62, 138, 17);
		panel_1.add(lblEditorialproductora);
		
		JLabel lblIsbn_3 = new JLabel("Paginas/Duración");
		lblIsbn_3.setBounds(152, 29, 111, 17);
		panel_1.add(lblIsbn_3);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(191, 90, 85, 17);
		panel_1.add(lblDescripcin);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_1.setColumns(10);
		textField_1.setBounds(53, 54, 69, 21);
		panel_1.add(textField_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField.setColumns(10);
		textField.setBounds(53, 25, 69, 21);
		panel_1.add(textField);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_4.setColumns(10);
		textField_4.setBounds(263, 27, 69, 21);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_5.setColumns(10);
		textField_5.setBounds(263, 58, 69, 21);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_6.setColumns(10);
		textField_6.setBounds(263, 88, 69, 21);
		panel_1.add(textField_6);
		
		JLabel lblFormato_1 = new JLabel("Tipo");
		lblFormato_1.setBounds(12, 125, 85, 17);
		panel_1.add(lblFormato_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Película", "Música", "Libro", "Documental"}));
		comboBox.setBounds(41, 125, 93, 17);
		panel_1.add(comboBox);
		
		JLabel lblFormato_1_1 = new JLabel("Formato");
		lblFormato_1_1.setBounds(191, 125, 85, 17);
		panel_1.add(lblFormato_1_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"MP3", "MP4", "Digital", "Físico"}));
		comboBox_1.setBounds(245, 125, 87, 17);
		panel_1.add(comboBox_1);
		
		//Boton para dar de alta
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setBounds(245, 168, 103, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaGestor frame = new VentanaGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 168, 79, 28);
		panel_1.add(btnVolver);
		


	}

}
