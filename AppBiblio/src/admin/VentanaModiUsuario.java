package admin;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.UsuarioMaxDB;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

@SuppressWarnings("serial")
public class VentanaModiUsuario extends JFrame {

	private JPanel panel;
	private JTextField anticText;
	private JTextField nouText;
	private final Action action = new eixir();
	private final Action action_1 = new Modificar();

	public VentanaModiUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel titulo = new JLabel("Modificació Usuari");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setOpaque(true);
		titulo.setBackground(new Color(255, 150, 49));
		titulo.setBounds(6, 6, 438, 16);
		panel.add(titulo);
		
		anticText = new JTextField();
		anticText.setBounds(158, 66, 130, 26);
		panel.add(anticText);
		anticText.setColumns(10);
		
		nouText = new JTextField();
		nouText.setBounds(158, 104, 130, 26);
		panel.add(nouText);
		nouText.setColumns(10);
		
		JLabel aUsuariLabel = new JLabel("Antic Usuari");
		aUsuariLabel.setBounds(52, 71, 77, 16);
		panel.add(aUsuariLabel);
		
		JLabel nUsuariLabel = new JLabel("Nou Usuari");
		nUsuariLabel.setBounds(52, 109, 77, 16);
		panel.add(nUsuariLabel);
		
		JButton eixirButton = new JButton("Eixir");
		eixirButton.setAction(action);
		eixirButton.setBounds(12, 225, 117, 29);
		panel.add(eixirButton);
		
		JButton modiButton = new JButton("Modificar");
		modiButton.setAction(action_1);
		modiButton.setBounds(313, 225, 117, 29);
		panel.add(modiButton);
	}//VentanaModiUsuario

	private class eixir extends AbstractAction {
		public eixir() {
			putValue(NAME, "Eixir");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			VentanaSelectModificacion frame = new VentanaSelectModificacion();
			frame.setVisible(true);
			dispose();
		}
	}//Eixir
	
	private class Modificar extends AbstractAction {
		public Modificar() {
			putValue(NAME, "Modificar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String nou, antic;
			nou = nouText.getText();
			antic = anticText.getText();
			
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			if (usuDB.nombreUsuarioEnUso(nou)) {
            	JOptionPane.showMessageDialog(panel, "El nou nom d'usuari ya esta escogit agafa un altre", "Modificació", JOptionPane.INFORMATION_MESSAGE);
			} else if(usuDB.nombreUsuarioEnUso(antic)) {
				usuDB.actualizarNombreUsuario(antic, nou);
            	JOptionPane.showMessageDialog(panel, "Ha cambiat el nom del Usuari correctament", "Modificació", JOptionPane.INFORMATION_MESSAGE);
            	MenuAdmin admin1 = new MenuAdmin();
				admin1.setVisible(true);
            	dispose();
			} else {
            	JOptionPane.showMessageDialog(panel, "Usuari no encontrat", "Modificació", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}//Modificar
}//end
