package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaModificarDocumento1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarDocumento1 frame = new VentanaModificarDocumento1();
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
	public VentanaModificarDocumento1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 329);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("MODIFICAR DOCUMENTO");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Verificaci\u00F3n de la existencia", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
		
		JLabel lblIsbn = new JLabel("isbn");
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn.setBounds(93, 99, 60, 17);
		panel_1.add(lblIsbn);

		JLabel lblIntroduceElIsbn = new JLabel("Introduce el ISBN");
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 20));
		lblIntroduceElIsbn.setBounds(93, 41, 212, 28);
		panel_1.add(lblIntroduceElIsbn);
		
		textField = new JTextField();
		textField.setBounds(137, 98, 133, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Modificar\n");
		btnNewButton.setBounds(257, 168, 91, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaModificarDocumento2 frame = new VentanaModificarDocumento2();
				frame.setVisible(true);
				dispose();
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
				MenuGestor frame = new MenuGestor();
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
