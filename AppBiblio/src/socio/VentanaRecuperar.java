package socio;

import db.UsuarioMaxDB;
import a.Inicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("serial")
public class VentanaRecuperar extends JFrame {
	private JTextField txtUsuario;
	private JButton btnVolver, btnEnviar;

	public VentanaRecuperar() {
		super("Biblioteca App - Recuperar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRecuperar.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel passPanel = new JPanel();
		passPanel.setLayout(new BorderLayout(0, 0));
		passPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		JLabel lblTitulo = new JLabel("Restablecer contraseña");
		passPanel.add(lblTitulo);
		getContentPane().add(passPanel, BorderLayout.NORTH);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 2, 10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel lblUsuario = new JLabel("Nom d'usuari:");
		txtUsuario = new JTextField(20);

		btnVolver = new JButton("Tornar");
		btnEnviar = new JButton("Enviar");

		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(btnVolver);
		mainPanel.add(btnEnviar);

		getContentPane().add(mainPanel);
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio app = new Inicio();
				app.setVisible(true);
				dispose();
			}
		});

		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				UsuarioMaxDB usuDB = new UsuarioMaxDB();
				String email = usuDB.obtenerMail(usuario);
				if (email == null) {
					JOptionPane.showMessageDialog(mainPanel, "L'usuari no té un correu electrònic associat.", "Avís", JOptionPane.WARNING_MESSAGE);
				} else {
					// Generar una contraseña aleatoria de 8 caracteres
					String password = generatePassword(8);
					String subject = "Restabliment de contrasenya - Accés a l'acompte";
					String body = "Estimat usuari,"
							+ "\n\nHem rebut una sol·licitud per restablir la teva contrasenya. "
							+ "A continuació, trobaràs la teva nova contrasenya:" + "\n\nContrasenya: " + password
							+ "\n\nEt recomanem iniciar sessió amb aquesta nova contrasenya i canviar-la tan aviat com sigui possible."
							+ "\n\nSi no has sol·licitat restablir la teva contrasenya, pots ignorar aquest missatge."
							+ "\n\nAtentament," + "\nEquip de Suport";

					sendEmail(email, subject, body);
					usuDB.cambiarContrasena(usuario, password);

					JOptionPane.showMessageDialog(mainPanel, "S'ha enviat un correu amb la nova contrasenya.", "Èxit",
							JOptionPane.INFORMATION_MESSAGE);

					VentanaInicioSesion ventanaIS = new VentanaInicioSesion();
					ventanaIS.setVisible(true);
					dispose();
				}
			}
		});

		pack();
		setVisible(true);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	private String generatePassword(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder password = new StringBuilder();

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			password.append(characters.charAt(index));
		}

		return password.toString();
	}
	
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
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
