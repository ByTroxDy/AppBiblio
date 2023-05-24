package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGestor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestor frame = new VentanaGestor();
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
	public VentanaGestor() {
		setTitle("Gestor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		// ------------------------------ PRIMERO PANEL ------------------------------ //
		//Creación de panel Titulo
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		//Titulo en el panel
		JLabel lblGestores = new JLabel("GESTOR");
		lblGestores.setForeground(new Color(0, 0, 0));
		lblGestores.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblGestores);
		
		//Panel de contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
		
		//Ttitulo del panel de contenido
		JLabel lblSelecciona = new JLabel("Selecciona una funcion");
		lblSelecciona.setBackground(SystemColor.activeCaption);
		lblSelecciona.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSelecciona.setForeground(new Color(0, 0, 0));
		lblSelecciona.setBounds(65, 33, 272, 36);
		panel_1.add(lblSelecciona);
		
		
		//Selección de la función a realizar
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(238, 238, 236));
		comboBox.setBackground(new Color(46, 52, 54));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Alta Documento", "Baja Documento", "Modificar Documento", "Copia de seguridad", "Restauracion"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(101, 92, 165, 26);
		panel_1.add(comboBox);
		
		//Boton para avanzar
		JButton btnNewButton = new JButton("Siguiente");
		btnNewButton.setBounds(110, 161, 151, 35);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				//extracción de la funcion seleccionada
				String seleccion = comboBox.getSelectedItem().toString();
				if (seleccion == "Alta Documento") {
					VentanaAltaDocumento frame = new VentanaAltaDocumento();
					frame.setVisible(true);
					dispose();
				} else if(seleccion == "Baja Documento"){
					VentanaBajaDocumento frame = new VentanaBajaDocumento();
					frame.setVisible(true);
					dispose();
				} else if(seleccion == "Modificar Documento") {
					VentanaModificarDocumento1 frame = new VentanaModificarDocumento1();
					frame.setVisible(true);
					dispose();
				} else if(seleccion == "Copia de seguridad"){
					VentanaCopiaSeguridad frame = new VentanaCopiaSeguridad();
					frame.setVisible(true);
					dispose();
				} else {
					VentanaRestauracion frame = new VentanaRestauracion();
					frame.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(UIManager.getColor("CheckBoxMenuItem.foreground"));
	}
}
