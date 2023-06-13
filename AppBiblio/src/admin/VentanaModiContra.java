package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaModiContra extends JFrame {

	private JPanel panel;
	private JTextField txtUsuario;
	private JPasswordField txtNewContra;
	private JButton btnEixir, btnModificar;
	private final Action actionEixir = new Eixir();
	private final Action actionModificar = new Modificar();
	
	private String usuario, nou;

	public VentanaModiContra() {
		setResizable(false);
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiContra.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Modificació Contrasenya");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 6, 444, 26);
		
		JLabel lblUsuario = new JLabel("Usuari");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(45, 95, 130, 18);
		
		txtUsuario = new JTextField(10);
		txtUsuario.setBounds(185, 91, 130, 26);
		
		JLabel lblNewContra = new JLabel("Nova contrasenya");
		lblNewContra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewContra.setBounds(45, 128, 130, 18);

		txtNewContra = new JPasswordField();
		txtNewContra.setBounds(185, 124, 130, 27);
		
		btnModificar = new JButton();
		btnModificar.setAction(actionModificar);
		btnModificar.setBounds(311, 215, 117, 29);
		
		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 215, 117, 29);
		
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblNewContra);
		panel.add(txtNewContra);
		panel.add(btnModificar);
		panel.add(btnEixir);
		
		setContentPane(panel);
	}//VentanaModiContra

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaModificacion frame = new VentanaModificacion();
			frame.setVisible(true);
			dispose();
		}
	}//Eixir
	
	private class Modificar extends AbstractAction {
		public Modificar() {
			putValue(NAME, "Modificar");
		}
		public void actionPerformed(ActionEvent e) {
			usuario = txtUsuario.getText();
			nou = new String(txtNewContra.getPassword());
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			if (usuDB.nombreUsuarioEnUso(usuario)) {
				usuDB.cambiarContrasena(usuario, nou);
            	JOptionPane.showMessageDialog(panel, "S'ha canviat la contrasenya de l'usuari correctament.", "Modificació", JOptionPane.INFORMATION_MESSAGE);
            	MenuAdmin admin = new MenuAdmin();
				admin.setVisible(true);
            	dispose();
			} else {
            	JOptionPane.showMessageDialog(panel, "Usuari no trobat.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			txtUsuario.setText("");
			txtNewContra.setText("");
		}
	}//Modificar
}//end
