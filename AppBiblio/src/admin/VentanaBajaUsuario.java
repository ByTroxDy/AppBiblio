package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaBajaUsuario extends JFrame {

	private JPanel panel;
	private JTextField txtUsuario, txtAdminUser;
	private JPasswordField txtCantra;
	private JButton btnEixir, btnBorrar;
	private final Action actionEixir = new Eixir();
	private final Action actionBorrar = new Borrar();
	
	private String usuario, admin, contra, grupo;

	public VentanaBajaUsuario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 280);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Baixa Usuari");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 444, 26);
		
		JLabel lblUsuario = new JLabel("Usuari");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(83, 71, 78, 18);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(171, 67, 150, 26);
		txtUsuario.setColumns(10);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmin.setBounds(83, 108, 78, 18);
		
		txtAdminUser = new JTextField();
		txtAdminUser.setBounds(171, 104, 150, 26);
		txtAdminUser.setColumns(10);
		
		JLabel lblContra = new JLabel("Contrasenya");
		lblContra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContra.setBounds(83, 145, 78, 18);
		
		txtCantra = new JPasswordField();
		txtCantra.setBounds(171, 141, 150, 26);
		
		btnBorrar = new JButton();
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrar.setAction(actionBorrar);
		btnBorrar.setBounds(311, 205, 117, 29);
		
		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 205, 117, 29);
		
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblAdmin);
		panel.add(txtAdminUser);
		panel.add(lblContra);
		panel.add(txtCantra);
		panel.add(btnBorrar);
		panel.add(btnEixir);
		
		setContentPane(panel);
		
	}//admniistradorBaja
	
	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}//ActionPerformed
	}//Eixir
	
	private class Borrar extends AbstractAction {
		public Borrar() {
			putValue(NAME, "Borrar");
		}
		public void actionPerformed(ActionEvent e) {
			usuario = txtUsuario.getText();
			admin = txtAdminUser.getText();
			contra = new String(txtCantra.getPassword());
			
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			grupo = usuDB.obtenerGrupo(admin);
			if (usuDB.iniciarSesion(admin, contra) && grupo.equals("admin")) {
				if (usuDB.nombreUsuarioEnUso(usuario)) {
					usuDB.bajaUsuario(usuario);
					MenuAdmin admin1 = new MenuAdmin();
					admin1.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(panel, "Usuari esborrat correctament.", "Registre", JOptionPane.INFORMATION_MESSAGE);
				} else {
	            	JOptionPane.showMessageDialog(panel, "Usuari no s'ha trobat.", "Registre", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
            	JOptionPane.showMessageDialog(panel, "Usuari admin o contrasenya incorrectes.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}//actionPerformed
	}//Borrar
}//end
