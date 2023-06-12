package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaModiUsuario extends JFrame {

	private JPanel panel;
	private JTextField txtOldUsuario, txtNewUsuario;
	private JButton btnEixir, btnModificar;
	private final Action actionEixir = new Eixir();
	private final Action actionModificar = new Modificar();
	
	private String nou, antic;

	public VentanaModiUsuario() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiUsuario.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Modificació Usuari");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 388, 26);
		
		JLabel lblOldUsuario = new JLabel("Antic Usuari");
		lblOldUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOldUsuario.setBounds(65, 78, 85, 18);
		
		txtOldUsuario = new JTextField(10);
		txtOldUsuario.setBounds(160, 74, 130, 26);
		
		JLabel lblNewUsuario = new JLabel("Nou Usuari");
		lblNewUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewUsuario.setBounds(65, 115, 85, 18);

		txtNewUsuario = new JPasswordField();
		txtNewUsuario.setBounds(160, 111, 130, 26);
		
		btnModificar = new JButton();
		btnModificar.setAction(actionModificar);
		btnModificar.setBounds(261, 175, 117, 29);
		
		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 175, 117, 29);
		
		panel.add(lblTitulo);
		panel.add(lblOldUsuario);
		panel.add(txtOldUsuario);
		panel.add(lblNewUsuario);
		panel.add(txtNewUsuario);
		panel.add(btnModificar);
		panel.add(btnEixir);
		
		setContentPane(panel);
	}//VentanaModiUsuario

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
			antic = txtOldUsuario.getText();
			nou = txtNewUsuario.getText();
			
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			if (usuDB.nombreUsuarioEnUso(nou)) {
            	JOptionPane.showMessageDialog(panel, "El nou nom d'usuari ja està en ús. Si us plau, tria un altre.", "Modificació", JOptionPane.INFORMATION_MESSAGE);
			} else if(usuDB.nombreUsuarioEnUso(antic)) {
				usuDB.actualizarNombreUsuario(antic, nou);
            	JOptionPane.showMessageDialog(panel, "S'ha canviat el nom de l'usuari correctament.", "Modificació", JOptionPane.INFORMATION_MESSAGE);
            	MenuAdmin admin1 = new MenuAdmin();
				admin1.setVisible(true);
            	dispose();
			} else {
            	JOptionPane.showMessageDialog(panel, "Usuari no trobat.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			txtOldUsuario.setText("");
			txtNewUsuario.setText("");
		}
	}//Modificar
}//end
