package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaModificacion extends JFrame {

	private JPanel panel;
	private JTextField txtAdmin;
	private JPasswordField txtContra;
	private JComboBox<String> cmbModi;
	private JButton btnEixir, btnModifica;
	private final Action actionEixir = new Eixir();
	private final Action actionModifica = new Modifica();
	
	private String admin, contra, modi, grupo;

	public VentanaModificacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Modificació");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 444, 26);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmin.setBounds(88, 81, 92, 16);

		txtAdmin = new JTextField();
		txtAdmin.setBounds(190, 76, 141, 26);

		JLabel lblContra = new JLabel("Contrasenya");
		lblContra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContra.setBounds(88, 119, 90, 16);
		
		txtContra = new JPasswordField();
		txtContra.setBounds(190, 114, 141, 26);

		JLabel lblModi = new JLabel("Modificar");
		lblModi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModi.setBounds(88, 156, 90, 16);
		
		cmbModi = new JComboBox<String>();
		cmbModi.setBounds(190, 152, 141, 27);
		cmbModi.addItem("Usuari");
		cmbModi.addItem("Contrasenya");
		cmbModi.addItem("Correu");
		cmbModi.addItem("Rol");
		
		btnModifica = new JButton();
		btnModifica.setAction(actionModifica);
		btnModifica.setBounds(311, 225, 117, 29);

		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 225, 117, 29);	
		
		panel.add(lblTitulo);
		panel.add(lblAdmin);
		panel.add(txtAdmin);
		panel.add(lblContra);
		panel.add(txtContra);
		panel.add(lblModi);
		panel.add(cmbModi);
		panel.add(btnModifica);
		panel.add(btnEixir);
		
		setContentPane(panel);

	}// ventanaSelectModificacion

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Torna al menú d'administrador.");
		}

		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}
	}// Eixir

	private class Modifica extends AbstractAction {
		public Modifica() {
			putValue(NAME, "Modifica");
			putValue(SHORT_DESCRIPTION, "Selecciona la modificació.");
		}

		public void actionPerformed(ActionEvent e) {
			admin = txtAdmin.getText();
			contra = new String(txtContra.getPassword());
			modi = cmbModi.getSelectedItem().toString();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			grupo = usuDB.obtenerGrupo(admin);
			if (usuDB.iniciarSesion(admin, contra) && grupo.equals("admin")) {
				if (modi.equals("Usuari")) {
					VentanaModiUsuario modiUser = new VentanaModiUsuario();
					modiUser.setVisible(true);
				} else if (modi.equals("Contrasenya")) {
					VentanaModiContra modiContra = new VentanaModiContra();
					modiContra.setVisible(true);
				} else if (modi.equals("Correu")) {
					VentanaModiCorreo modiCorreo = new VentanaModiCorreo();
					modiCorreo.setVisible(true);
				} else if (modi.equals("Rol")) {
					VentanaModiGrupo modiRol = new VentanaModiGrupo();
					modiRol.setVisible(true);
				}
				dispose();
			} else {
				JOptionPane.showMessageDialog(panel, "No s'ha trobat l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			txtAdmin.setText("");
			txtContra.setText("");
		}
	}// modifica
}// exit
