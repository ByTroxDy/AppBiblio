package admin;

import db.UsuarioMaxDB;
import a.Inicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaBajaUsuario extends JFrame {

	private JPanel panel;
	private JTextField txtUsuario;
	private JButton btnEixir, btnBorrar;
	private final Action actionEixir = new Eixir();
	private final Action actionBorrar = new Borrar();
	
	private String usuario;
	public static String usuarioActual;

	public VentanaBajaUsuario() {
		setResizable(false);
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaBajaUsuario.class.getResource("/img/icono32.png")));
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
		lblUsuario.setBounds(82, 107, 78, 18);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(170, 103, 150, 26);
		txtUsuario.setColumns(10);
		
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
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			
			if (usuDB.nombreUsuarioEnUso(usuario)) {
				usuDB.bajaUsuario(usuario);
				JOptionPane.showMessageDialog(panel, "Usuari esborrat correctament.", "Baixa", JOptionPane.INFORMATION_MESSAGE);
				if (usuarioActual.equals(usuario)) {
					Inicio inicio = new Inicio();
					inicio.setVisible(true);
					dispose();
				} else {
					MenuAdmin admin = new MenuAdmin();
					admin.setVisible(true);
					dispose();
				}
				
			} else {
            	JOptionPane.showMessageDialog(panel, "Usuari no s'ha trobat.", "Baixa", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}//actionPerformed
	}//Borrar
}//end
