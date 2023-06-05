package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.VentanaConsultarDocumento;
import gui.VentanaInicioSesion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MenuGestor() {
		setTitle("Gestor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		//Creación de panel Titulo
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(null);
		panel.setBounds(0, 0, 584, 70);
		contentPane.add(panel);
		panel.setLayout(null);

		//Titulo en el panel
		JLabel lblGestores = new JLabel("MENÚ GESTOR");
		lblGestores.setForeground(new Color(255, 255, 255));
		lblGestores.setFont(new Font("Dialog", Font.BOLD, 40));
		lblGestores.setBounds(143, 11, 319, 43);
		panel.add(lblGestores);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(435, 0, 119, 65);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(MenuGestor.class.getResource("/imagenes/ImagenGestor.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Panel de contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(null);
		panel_1.setBounds(0, 70, 584, 291);
		contentPane.add(panel_1);
		
		//Ttitulo del panel de contenido
		JLabel lblSelecciona = new JLabel("SELECCIONA UNA FUNCIÓ");
		lblSelecciona.setBackground(new Color(255, 255, 255));
		lblSelecciona.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSelecciona.setForeground(new Color(0, 0, 0));
		lblSelecciona.setBounds(130, 70, 333, 36);
		panel_1.add(lblSelecciona);
		
		//Selección de la función a realizar
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setForeground(new Color(238, 238, 236));
		comboBox.setBackground(new Color(0, 128, 192));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Alta Document", "Modificar Document", "Consultar Document", "Còpia de seguretat", "Restauració"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(128, 118, 333, 26);
		panel_1.add(comboBox);
		
		//Boton para avanzar
		JButton btnCerrarSesion = new JButton("Tancar Sesió");
		btnCerrarSesion.setBounds(10, 255, 286, 26);
		panel_1.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaInicioSesion app = new VentanaInicioSesion();
				app.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCerrarSesion.setBackground(new Color(0, 128, 192));
		
		//Boton para avanzar
		JButton btnNewButton = new JButton("Següent");
		btnNewButton.setBounds(306, 255, 268, 26);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				//extracción de la funcion seleccionada
				String seleccion = comboBox.getSelectedItem().toString();
				if (seleccion == "Alta Document") {
					VentanaAltaDocumento frame = new VentanaAltaDocumento();
					frame.setVisible(true);
				}else if (seleccion == "Modificar Document") {
					VentanaModificarDocumento frame = new VentanaModificarDocumento();
					frame.setVisible(true);
				}else if(seleccion == "Consultar Document"){
					VentanaConsultarDocumento frame = new VentanaConsultarDocumento();
					frame.setVisible(true);
				} else if(seleccion == "Còpia de seguretat"){
					VentanaCopiaSeguridad frame = new VentanaCopiaSeguridad();
					frame.setVisible(true);
				} else {
					VentanaRestauracion frame = new VentanaRestauracion();
					frame.setVisible(true);
				}//if
				dispose();
			}//actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBackground(new Color(0, 128, 192));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGestor frame = new MenuGestor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//try catch
			}//run
		});
	}// main
}
