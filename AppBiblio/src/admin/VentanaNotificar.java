package admin;

import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@SuppressWarnings("serial")
public class VentanaNotificar extends JFrame {

	private JPanel panel;
	private JTextField txtUsuario, txtAsunto;
	private JTextArea txtCuerpo;
	private JButton btnEixir, btnNotificar;
	private final Action actionEixir = new Eixir();
	private final Action actionNotificar = new Notificar();
	
	private String usuario, mail, asunto, cuerpo;
	
	public VentanaNotificar() {
		setResizable(false);
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaNotificar.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		panel = new JPanel();
		panel.setBackground(new Color(186, 255, 248));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Notificar Usuari");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(255, 150, 49));
		lblTitulo.setBounds(0, 6, 444, 26);
		
		JLabel lblUsuario = new JLabel("Usuari");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(12, 72, 75, 18);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(97, 68, 130, 26);
		txtUsuario.setColumns(10);
		
		JLabel lblAsunto = new JLabel("Assumpte");
		lblAsunto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAsunto.setBounds(6, 110, 79, 18);

		txtAsunto = new JTextField();
		txtAsunto.setBounds(97, 106, 230, 26);
		txtAsunto.setColumns(10);
		
		JLabel lblCuerpo = new JLabel("Cos");
		lblCuerpo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuerpo.setBounds(10, 147, 75, 18);
		
		txtCuerpo = new JTextArea();
		txtCuerpo.setBounds(97, 144, 316, 101);
		txtCuerpo.setColumns(10);
		txtCuerpo.setLineWrap(true); // Activar el ajuste automático de línea
		txtCuerpo.setWrapStyleWord(true); // Ajustar el texto a palabras completas
		
		btnNotificar = new JButton();
		btnNotificar.setAction(actionNotificar);
		btnNotificar.setBounds(311, 274, 117, 29);	
		
		btnEixir = new JButton();
		btnEixir.setAction(actionEixir);
		btnEixir.setBounds(10, 274, 117, 29);
		
		panel.add(lblTitulo);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblAsunto);
		panel.add(txtAsunto);
		panel.add(lblCuerpo);
		panel.add(txtCuerpo);
		panel.add(btnNotificar);
		panel.add(btnEixir);
		
		setContentPane(panel);
		
	}//administradorNoti

	private class Eixir extends AbstractAction {
		public Eixir() {
			putValue(NAME, "Eixir");
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
		}
		public void actionPerformed(ActionEvent e) {
			usuario = txtUsuario.getText();
			cuerpo = txtCuerpo.getText();
			asunto = txtAsunto.getText();
			UsuarioMaxDB usuDB = new UsuarioMaxDB();
			mail = usuDB.obtenerMail(usuario);
			if (mail == null) {
				JOptionPane.showMessageDialog(panel, "E'usuari no te un correu electronic.", "Notificar", JOptionPane.ERROR_MESSAGE);
			} else {
				sendEmail(mail, asunto, cuerpo);
			}
			txtUsuario.setText("");
			txtAsunto.setText("");
			txtCuerpo.setText("");
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
