package gestor;

import a.Inicio;
import socio.VentanaConsultarDocumento;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuGestor extends JFrame {
	private JPanel contentPane;
	private JLabel lblGestores, lblImagen, lblSelecciona;
	private JButton btnCerrarSesion, btnAceptar;
	private JComboBox<Object> cmbFunciones;
	
	private String seleccion;
	
	public MenuGestor() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuGestor.class.getResource("/img/icono32.png")));
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
		panelPrincipal.setLayout(null);

		//Titulo en el panel
		lblGestores = new JLabel("MENÚ GESTOR");
		lblGestores.setForeground(new Color(255, 255, 255));
		lblGestores.setFont(new Font("Dialog", Font.BOLD, 40));
		lblGestores.setBounds(143, 11, 319, 43);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(452, 0, 89, 70);
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

		panelPrincipal.add(lblGestores);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);
		
		//Panel de contenido
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(0, 0, 0));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
		
		//Ttitulo del panel de contenido
		lblSelecciona = new JLabel("SELECCIONA UNA FUNCIÓ");
		lblSelecciona.setBackground(new Color(255, 255, 255));
		lblSelecciona.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSelecciona.setForeground(new Color(0, 0, 0));
		lblSelecciona.setBounds(130, 70, 333, 36);
		
		//Selección de la función a realizar
		String[] funcion = {"Alta Document", "Consultar Document", "Suport"};
		cmbFunciones = new JComboBox<>(funcion);
		cmbFunciones.setForeground(new Color(238, 238, 236));
		cmbFunciones.setBackground(new Color(0, 128, 192));
		cmbFunciones.setToolTipText("");
		cmbFunciones.setBounds(128, 118, 333, 26);
		
		//Boton para avanzar
		btnCerrarSesion = new JButton("Tancar Sesió");
		btnCerrarSesion.setBounds(8, 259, 286, 26);
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCerrarSesion.setBackground(new Color(0, 128, 192));
		
		//Boton para avanzar
		btnAceptar = new JButton("Següent");
		btnAceptar.setBounds(312, 259, 268, 26);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAceptar.setBackground(new Color(0, 128, 192));
		
		panelSecundario.add(lblSelecciona);
		panelSecundario.add(cmbFunciones);
		panelSecundario.add(btnCerrarSesion);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		//Funciones
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				Inicio app = new Inicio();
				app.setVisible(true);
				dispose();
			}//actionPerformed
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				//extracción de la funcion seleccionada
				seleccion = cmbFunciones.getSelectedItem().toString();
				if (seleccion == "Alta Document") {
					VentanaActivarDoc frame = new VentanaActivarDoc();
					frame.setVisible(true);
				} else if (seleccion == "Consultar Document") {
					VentanaConsultarDocumento frame = new VentanaConsultarDocumento();
					frame.setVisible(true);
				} else {
					VentanaRespaldo frame = new VentanaRespaldo();
					frame.setVisible(true);
				}//if
				dispose();
			}//actionPerformed
		});
	}
}// MenuGestor
