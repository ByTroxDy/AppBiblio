package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import socio.VentanaConsultarDocumento;
import socio.VentanaInicioSesion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblGestores, lblNewLabel, lblSelecciona;
	private JButton btnCerrarSesion, btnAceptar;
	
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
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 70);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		//Titulo en el panel
		lblGestores = new JLabel("MENÚ GESTOR");
		lblGestores.setForeground(new Color(255, 255, 255));
		lblGestores.setFont(new Font("Dialog", Font.BOLD, 40));
		lblGestores.setBounds(143, 11, 319, 43);
		panelPrincipal.add(lblGestores);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(435, 0, 119, 65);
		lblNewLabel.setIcon(new ImageIcon("img/icono64.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelPrincipal.add(lblNewLabel);

		//Panel de contenido
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(0, 0, 0));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
		contentPane.add(panelSecundario);
		
		//Ttitulo del panel de contenido
		lblSelecciona = new JLabel("SELECCIONA UNA FUNCIÓ");
		lblSelecciona.setBackground(new Color(255, 255, 255));
		lblSelecciona.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSelecciona.setForeground(new Color(0, 0, 0));
		lblSelecciona.setBounds(130, 70, 333, 36);
		panelSecundario.add(lblSelecciona);
		
		//Selección de la función a realizar
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setForeground(new Color(238, 238, 236));
		comboBox.setBackground(new Color(0, 128, 192));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Alta Document", "Modificar Document", "Consultar Document", "Còpia de seguretat", "Restauració"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(128, 118, 333, 26);
		panelSecundario.add(comboBox);
		
		//Boton para avanzar
		btnCerrarSesion = new JButton("Tancar Sesió");
		btnCerrarSesion.setBounds(8, 259, 286, 26);
		panelSecundario.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaInicioSesion app = new VentanaInicioSesion();
				app.setVisible(true);
				dispose();
			}//actionPerformed
		});
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCerrarSesion.setBackground(new Color(0, 128, 192));
		
		//Boton para avanzar
		btnAceptar = new JButton("Següent");
		btnAceptar.setBounds(312, 259, 268, 26);
		panelSecundario.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {

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
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAceptar.setBackground(new Color(0, 128, 192));
	}// MenuGestor
	
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
}// MenuGestor
