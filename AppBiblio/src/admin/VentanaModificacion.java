package admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaModificacion extends JFrame {

	private JPanel panel;
	private JComboBox<String> cmbModi;
	private JButton btnEixir, btnModifica;
	private final Action actionEixir = new Eixir();
	private final Action actionModifica = new Modifica();
	
	private String modi;

	public VentanaModificacion() {
		setResizable(false);
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModificacion.class.getResource("/img/icono32.png")));
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

		JLabel lblModi = new JLabel("Modificar");
		lblModi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModi.setBounds(79, 113, 90, 16);
		
		cmbModi = new JComboBox<String>();
		cmbModi.setBounds(181, 109, 141, 27);
		cmbModi.addItem("Usuari");
		cmbModi.addItem("Contrasenya");
		cmbModi.addItem("Correu");
		cmbModi.addItem("Grup");
		
		btnModifica = new JButton();
		btnModifica.setAction(actionModifica);
		btnModifica.setBounds(311, 225, 117, 29);

		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 225, 117, 29);	
		
		panel.add(lblTitulo);
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
			modi = cmbModi.getSelectedItem().toString();
			if (modi.equals("Usuari")) {
				VentanaModiUsuario modiUser = new VentanaModiUsuario();
				modiUser.setVisible(true);
			} else if (modi.equals("Contrasenya")) {
				VentanaModiContra modiContra = new VentanaModiContra();
				modiContra.setVisible(true);
			} else if (modi.equals("Correu")) {
				VentanaModiCorreo modiCorreo = new VentanaModiCorreo();
				modiCorreo.setVisible(true);
			} else if (modi.equals("Grup")) {
				VentanaModiGrupo modiGrup = new VentanaModiGrupo();
				modiGrup.setVisible(true);
			}
			dispose();
		}
	}// modifica
}// exit
