package admin;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import db.UsuarioMaxDB;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class VentanaAltaUsuario extends JFrame {

	private JPanel panel;
	private JTextField usuarioText;
	private JPasswordField contraText;
	private JPasswordField ConfirmarContra;
	private JComboBox<String> select;
	private final Action action = new Salir();
	private final Action action_1 = new Registrar();
	private JTextField correoText;

	public VentanaAltaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel contra = new JLabel("Confirmar");
		contra.setHorizontalAlignment(SwingConstants.RIGHT);
		contra.setBounds(43, 149, 93, 16);
		panel.add(contra);
		
		JLabel titulo = new JLabel("Alta Usuari");
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(6, 0, 438, 26);
		panel.add(titulo);
		
		usuarioText = new JTextField();
		usuarioText.setBounds(176, 66, 130, 26);
		panel.add(usuarioText);
		usuarioText.setColumns(10);
		
		JLabel labelUsuario = new JLabel("Usuari");
		labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuario.setBounds(75, 71, 61, 16);
		panel.add(labelUsuario);
		
		contraText = new JPasswordField();
		contraText.setBounds(176, 104, 130, 26);
		panel.add(contraText);
		contraText.setColumns(10);
		
		JLabel labelContra = new JLabel("Contrasenya");
		labelContra.setBounds(56, 109, 80, 16);
		panel.add(labelContra);
		
		ConfirmarContra = new JPasswordField();
		ConfirmarContra.setBounds(176, 144, 130, 26);
		panel.add(ConfirmarContra);
		ConfirmarContra.setColumns(10);
		
		JButton buttonRegistrar = new JButton("Registrar");
		buttonRegistrar.setAction(action_1);
		buttonRegistrar.setBounds(302, 269, 117, 29);
		panel.add(buttonRegistrar);
		
		JButton buttonSalir = new JButton("Eixir");
		buttonSalir.setAction(action);
		buttonSalir.setBounds(19, 269, 117, 29);
		panel.add(buttonSalir);
		
		JLabel lblNewLabel = new JLabel("Contrasenya");
		lblNewLabel.setBounds(53, 165, 83, 16);
		panel.add(lblNewLabel);
		
		select = new JComboBox<String>();
		select.setBounds(185, 230, 100, 27);
		select.addItem("socio");
		select.addItem("gestor");
		select.addItem("admin");
		panel.add(select);
		
		correoText = new JTextField();
		correoText.setBounds(176, 192, 130, 26);
		panel.add(correoText);
		correoText.setColumns(10);
		
		JLabel correoLabel = new JLabel("Correu");
		correoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		correoLabel.setBounds(75, 197, 61, 16);
		panel.add(correoLabel);
		
		JLabel rolLabel = new JLabel("Rol");
		rolLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		rolLabel.setBounds(75, 234, 61, 16);
		panel.add(rolLabel);
	}//administradorAltaUsuario
	
	private class Salir extends AbstractAction {
		public Salir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Some short description");
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
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String usuario;
			String contra;
			String confirmaContra;
			String Select;
			String correo;
			
			usuario = usuarioText.getText().toLowerCase();
			contra = new String (contraText.getPassword());
			confirmaContra = new String(ConfirmarContra.getPassword());
			correo = correoText.getText();
			Select = select.getSelectedItem().toString();

			if (contra.equals(confirmaContra) && contra.length() > 8) {//Enviar usuario y contraseña
                UsuarioMaxDB usuDB = new UsuarioMaxDB();
                
                if (usuDB.guardarRegistro2(usuario, contra, Select, correo)) {
                	JOptionPane.showMessageDialog(panel, "Registre exitos", "Registre", JOptionPane.INFORMATION_MESSAGE);
                	MenuAdmin app = new MenuAdmin();
    	            app.setVisible(true);
    	            dispose();
                } else {
                	JOptionPane.showMessageDialog(panel, "El nombre de usuario ya está registrado", "Registro", JOptionPane.ERROR_MESSAGE);
                }
                
				// Limpiar los campos de texto después de intentar iniciar sesión
                usuarioText.setText("");
                contraText.setText("");
                ConfirmarContra.setText("");
			} else if (contra.length() < 8) {
            	JOptionPane.showMessageDialog(panel, "Contrasenya masa curt", "Registre", JOptionPane.INFORMATION_MESSAGE);
			} else {
            	JOptionPane.showMessageDialog(panel, "Contrasenya y Confirmar contrasenya no coincidixen", "Registre", JOptionPane.INFORMATION_MESSAGE);
			}
		}//actionPerformed
	}//Registrar
}//end
