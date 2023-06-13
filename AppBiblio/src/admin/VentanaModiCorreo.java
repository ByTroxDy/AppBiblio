package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaModiCorreo extends JFrame {

	private JPanel panel;
	private JTextField txtUsuario, txtCorreo;
	private JButton btnEixir, btnModificar;
	private final Action actionEixir = new Eixir();
	private final Action actionModificar = new Modificar();
	
	private String usuari, correo;

	public VentanaModiCorreo() {
		setResizable(false);
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiCorreo.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Modificació Correu");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 388, 26);
		
		JLabel lblUsuario = new JLabel("Usuari");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(65, 78, 70, 18);
		
		txtUsuario = new JTextField(10);
		txtUsuario.setBounds(145, 74, 130, 26);
		
		JLabel lblCorreo = new JLabel("Correu");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(65, 115, 70, 18);

		txtCorreo = new JPasswordField();
		txtCorreo.setBounds(145, 111, 130, 26);
		
		btnModificar = new JButton();
		btnModificar.setAction(actionModificar);
		btnModificar.setBounds(261, 175, 117, 29);
		
		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 175, 117, 29);
		
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblCorreo);
		panel.add(txtCorreo);
		panel.add(btnModificar);
		panel.add(btnEixir);
		
		setContentPane(panel);
		
	}//VentanaModiCorreo

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Torna a la finestra selecció de modificació.");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaModificacion modiSelect = new VentanaModificacion();
			modiSelect.setVisible(true);
		}
	}//Eixir
	
	private class Modificar extends AbstractAction {
		public Modificar() {
			putValue(NAME, "Modificar");
			putValue(SHORT_DESCRIPTION, "Modifica el correu de l'usuari.");
		}
		public void actionPerformed(ActionEvent e) {
			usuari = txtUsuario.getText();
			correo = txtCorreo.getText();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();

			if (usuDB.cambiarEmail(usuari, correo)) {
            	JOptionPane.showMessageDialog(panel, "Correu electrònic actualitzat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(panel, "Ho sentim, l'usuari introduït no existeix.", "Error", JOptionPane.ERROR_MESSAGE);
            }//else
			
			txtUsuario.setText("");
			txtCorreo.setText("");
		}//actionPerformed
	}//mopdificar
}//end
