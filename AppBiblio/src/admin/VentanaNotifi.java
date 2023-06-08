package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class VentanaNotifi extends JFrame {

	private JPanel panel;
	private JTextField usuariText;
	private JTextField notificarText;
	private final Action action = new Eixir();
	private final Action action_1 = new Notificar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNotifi frame = new VentanaNotifi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//run
		});
	}//main

	/**
	 * Create the frame.
	 */
	public VentanaNotifi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel titulo = new JLabel("Notificar");
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(6, 6, 438, 26);
		panel.add(titulo);
		
		JLabel usuarioLabel = new JLabel("Usuari");
		usuarioLabel.setBounds(24, 73, 61, 16);
		panel.add(usuarioLabel);
		
		usuariText = new JTextField();
		usuariText.setBounds(97, 68, 237, 26);
		panel.add(usuariText);
		usuariText.setColumns(10);
		
		JLabel notiLabel = new JLabel("Notificar");
		notiLabel.setBounds(24, 129, 61, 16);
		panel.add(notiLabel);
		
		notificarText = new JTextField();
		notificarText.setBounds(97, 124, 316, 101);
		panel.add(notificarText);
		notificarText.setColumns(10);
		
		JButton eixirButton = new JButton("Eixir");
		eixirButton.setAction(action);
		eixirButton.setBounds(6, 237, 117, 29);
		panel.add(eixirButton);
		
		JButton Notificar = new JButton("Notificar");
		Notificar.setAction(action_1);
		Notificar.setBounds(327, 237, 117, 29);
		panel.add(Notificar);
	}//administradorNoti

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			MenuAdmin admin = new MenuAdmin();
			admin.setVisible(true);
			dispose();
		}//actionPerformed
	}//Eixir
	private class Notificar extends AbstractAction {
		public Notificar() {
			putValue(NAME, "Notificar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String usuari, noti;
			usuari= usuariText.getText();
			noti= notificarText.getText();
		}
	}//Notificar
}//end
