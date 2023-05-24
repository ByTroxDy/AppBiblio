package gestor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class pruebas extends JFrame {

	private JPanel contentPane;
	private JTextField txtDasdasd;
	private JTextField txtHola;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebas frame = new pruebas();
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
	public pruebas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 434, 261);
		panel_1.setLayout(null);
		contentPane.add(panel_1);
		
		txtHola = new JTextField();
		txtHola.setText("hola 1");
		txtHola.setBounds(146, 92, 86, 20);
		panel_1.add(txtHola);
		txtHola.setColumns(10);
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 434, 261);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		contentPane.add(panel_2);
		
		txtHola = new JTextField();
		txtHola.setText("hola 2");
		txtHola.setBounds(146, 92, 86, 20);
		panel_2.add(txtHola);
		txtHola.setColumns(10);
		
		JButton btnNewButton2 = new JButton("Siguiente");
		btnNewButton2.setBounds(266, 139, 89, 23);
		panel_2.add(btnNewButton2);
		
		
		JButton btnNewButton = new JButton("Siguiente");
		btnNewButton.setBounds(266, 139, 89, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
	}
}
