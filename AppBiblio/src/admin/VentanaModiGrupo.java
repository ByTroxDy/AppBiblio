package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaModiGrupo extends JFrame {

	private JPanel panel;
	private JTextField txtUsuario;
	private JComboBox<String> cmbSelectGrup;
	private JButton btnEixir, btnAasignar;
	private final Action actionEixir = new Eixir();
	private final Action actionAsignar = new Asignar();

	private String usuari, asigna;

	public VentanaModiGrupo() {
		setResizable(false);
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiGrupo.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblTitulo = new JLabel("Asignar Usuari");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 444, 26);

		JLabel lblUsuario = new JLabel("Usuari");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(98, 96, 74, 18);

		txtUsuario = new JTextField(10);
		txtUsuario.setBounds(182, 92, 130, 26);

		JLabel lblRol = new JLabel("Grup");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setBounds(98, 129, 74, 18);

		cmbSelectGrup = new JComboBox<String>();
		cmbSelectGrup.setBounds(182, 125, 130, 27);
		cmbSelectGrup.addItem("socio");
		cmbSelectGrup.addItem("gestor");
		cmbSelectGrup.addItem("admin");

		btnAasignar = new JButton("Asignar");
		btnAasignar.setAction(actionAsignar);
		btnAasignar.setBounds(311, 215, 117, 29);

		btnEixir = new JButton("Eixir");
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 215, 117, 29);

		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblRol);
		panel.add(cmbSelectGrup);
		panel.add(btnAasignar);
		panel.add(btnEixir);

		setContentPane(panel);

	}// administradorAsigna

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Torna al menú d'administrador.");
		}

		public void actionPerformed(ActionEvent e) {
			VentanaModificacion frame = new VentanaModificacion();
			frame.setVisible(true);
			dispose();
		}
	}// Eixir

	private class Asignar extends AbstractAction {
		public Asignar() {
			putValue(NAME, "Asignar");
			putValue(SHORT_DESCRIPTION, "Modifica l'assignació.");
		}

		public void actionPerformed(ActionEvent e) {
			usuari = txtUsuario.getText();
			asigna = cmbSelectGrup.getSelectedItem().toString();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();

			usuDB.actualizarRol(usuari, asigna);
			JOptionPane.showMessageDialog(panel, "El rol de l'usuari s'ha actualitzat correctament.", "Éxit",
					JOptionPane.INFORMATION_MESSAGE);

			txtUsuario.setText("");
		}
	}// Asignar
}// end
