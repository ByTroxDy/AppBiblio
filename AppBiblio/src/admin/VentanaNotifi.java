package admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import db.UsuarioMaxDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.swing.Action;

@SuppressWarnings("serial")
public class VentanaNotifi extends JFrame {

	private JPanel panel;
	private JTextField usuariText;
	private JTextArea notificarText;
	private final Action action = new Eixir();
	private final Action action_1 = new Notificar();
	private JTextField asuntoText;

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
		setBounds(100, 100, 450, 350);
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
		titulo.setBounds(0, 0, 450, 32);
		panel.add(titulo);
		
		JLabel usuarioLabel = new JLabel("Usuari");
		usuarioLabel.setBounds(24, 73, 61, 16);
		panel.add(usuarioLabel);
		
		usuariText = new JTextField();
		usuariText.setBounds(97, 68, 237, 26);
		panel.add(usuariText);
		usuariText.setColumns(10);
		
		JLabel notiLabel = new JLabel("Cos");
		notiLabel.setVerticalAlignment(SwingConstants.CENTER);
		notiLabel.setBounds(46, 144, 39, 16);
		panel.add(notiLabel);
		
		notificarText = new JTextArea();
		notificarText.setBounds(97, 144, 316, 101);
		panel.add(notificarText);
		notificarText.setColumns(10);
		
		JButton eixirButton = new JButton("Eixir");
		eixirButton.setAction(action);
		eixirButton.setBounds(6, 274, 117, 29);
		panel.add(eixirButton);
		
		JButton Notificar = new JButton("Notificar");
		Notificar.setAction(action_1);
		Notificar.setBounds(333, 274, 117, 29);
		panel.add(Notificar);
		
		asuntoText = new JTextField();
		asuntoText.setBounds(97, 106, 237, 26);
		panel.add(asuntoText);
		asuntoText.setColumns(10);
		
		JLabel asuntoLabel = new JLabel("Assumpte");
		asuntoLabel.setBounds(6, 111, 79, 16);
		panel.add(asuntoLabel);
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
			@SuppressWarnings("unused")
			String usuari, noti, mail, asunto;
			usuari= usuariText.getText();
			noti= notificarText.getText();
			asunto= asuntoText.getText();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			mail = usuDB.obtenerMail(usuari);
			sendEmail(mail, asunto, noti);
			usuariText.setText("");
			notificarText.setText("");
		}
	}//Notificar
	
	public void sendEmail(String recipient, String subject, String body) {
        String senderEmail = "appbiblioteca@outlook.es";
        String senderPassword = "PasswordOutlook";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            JOptionPane.showMessageDialog(panel, "Correu enviat exitosament.",
					"Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}//end
