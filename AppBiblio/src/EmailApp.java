import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField recipientField;
    private JTextField subjectField;
    private JTextArea bodyArea;
    private JButton sendButton;

    public EmailApp() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
		
        JLabel recipientLabel = new JLabel("Destinatario:");
        recipientField = new JTextField("sim@padye.com");
        recipientField.setEditable(false);
        
        JLabel subjectLabel = new JLabel("Asunto:");
        subjectField = new JTextField(20);
        
        JLabel bodyLabel = new JLabel("Cuerpo:");
        bodyArea = new JTextArea();
        
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        
        sendButton = new JButton("Enviar");
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        mainPanel.add(recipientLabel);
        mainPanel.add(recipientField);
        mainPanel.add(subjectLabel);
        mainPanel.add(subjectField);
        mainPanel.add(bodyLabel);
        mainPanel.add(scrollPane);
        mainPanel.add(new JLabel());
        mainPanel.add(sendButton);

        getContentPane().add(mainPanel);
        
        pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void initListeners() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipient = recipientField.getText();
                String subject = subjectField.getText();
                String body = bodyArea.getText();

                EmailSender.sendEmail(recipient, subject, body);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EmailApp app = new EmailApp();
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		app.setResizable(false);
                app.setVisible(true);
            }
        });
    }
}
