package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaAltaUsuario extends JFrame {
	private JPanel panel;
	private JTextField txtUsuario, txtCorreo;
	private JPasswordField txtContra;
	private JComboBox<String> cmbSelectRol;
	private JButton btnEixir, btnRegistrar;
	private final Action actionSalir = new Salir();
	private final Action actionRegistrar = new Registrar();
	
	private String usuario, contra, select, correo;

	public VentanaAltaUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAltaUsuario.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Alta Usuari");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 444, 26);
		
		JLabel lblUsuario = new JLabel("Usuari");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(90, 78, 80, 18);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(180, 73, 130, 26);
		txtUsuario.setColumns(10);
		
		JLabel lblContra = new JLabel("Contrasenya");
		lblContra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContra.setBounds(90, 116, 80, 18);
		
		txtContra = new JPasswordField();
		txtContra.setBounds(180, 111, 130, 26);
		txtContra.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correu");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(90, 152, 80, 18);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(180, 148, 130, 26);
		txtCorreo.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setBounds(90, 190, 80, 18);

		cmbSelectRol = new JComboBox<String>();
		cmbSelectRol.setBounds(180, 185, 130, 27);
		cmbSelectRol.addItem("socio");
		cmbSelectRol.addItem("gestor");
		cmbSelectRol.addItem("admin");
		
		btnRegistrar = new JButton();
		btnRegistrar.setAction(actionRegistrar);
		btnRegistrar.setBounds(302, 269, 117, 29);

		btnEixir = new JButton();
		btnEixir.setAction(actionSalir);
		btnEixir.setBounds(19, 269, 117, 29);
		
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblContra);
		panel.add(txtContra);
		panel.add(lblCorreo);
		panel.add(txtCorreo);
		panel.add(lblRol);
		panel.add(cmbSelectRol);
		panel.add(btnRegistrar);
		panel.add(btnEixir);
		
		setContentPane(panel);
		
	}//administradorAltaUsuario
	
	private class Salir extends AbstractAction {
		public Salir() {
			putValue(NAME, "Eixir");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}//actionPerformed
	}//Salir
	
	private class Registrar extends AbstractAction {
		public Registrar() {
			putValue(NAME, "Registrar");
		}
		public void actionPerformed(ActionEvent e) {		
			usuario = txtUsuario.getText().toLowerCase();
			contra = new String(txtContra.getPassword());
			correo = txtCorreo.getText();
			select = cmbSelectRol.getSelectedItem().toString();

            UsuarioMaxDB usuDB = new UsuarioMaxDB();
            if (usuDB.guardarRegistro2(usuario, contra, select, correo)) {
            	JOptionPane.showMessageDialog(panel, "Registre exitos.", "Registre", JOptionPane.INFORMATION_MESSAGE);
            	MenuAdmin app = new MenuAdmin();
	            app.setVisible(true);
	            dispose();
            } else {
            	JOptionPane.showMessageDialog(panel, "El nom d'usuari ja està registrat.", "Registre", JOptionPane.ERROR_MESSAGE);
            }
            
			// Limpiar los campos de texto después de intentar iniciar sesión
            txtUsuario.setText("");
            txtContra.setText("");
            txtCorreo.setText("");

		}//actionPerformed
	}//Registrar
}//end
