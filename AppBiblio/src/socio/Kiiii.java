package socio;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Kiiii extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Kiiii() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 329);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("RESTAURACIÓN");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido/a", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);

		JLabel lblIntroduceElIsbn = new JLabel("Restaurar DataBase");
		lblIntroduceElIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 17));
		lblIntroduceElIsbn.setBounds(89, 76, 185, 28);
		panel_1.add(lblIntroduceElIsbn);

		JButton btnVolver = new JButton("Salir");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 168, 102, 30);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		JButton btnNewButton = new JButton("Restaurar");
		btnNewButton.setBounds(246, 166, 102, 30);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de sql", "sql");
		        fileChooser.setFileFilter(filter);
		        fileChooser.setAcceptAllFileFilterUsed(false);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Seleccionó un archivo
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String file = selectedFile.getAbsolutePath();
                    if (restaurarBackup(file)) {
                    	JOptionPane.showMessageDialog(panel_1, "Restaurado correctamene", "Restauración", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    	JOptionPane.showMessageDialog(panel_1, "Algo ha salido mal", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
	}
	
	public boolean restaurarBackup(String backupName) {
		Process process;
		OutputStream os;
		FileInputStream fis;
		byte[] buffer = new byte[1000];
		int leido;
		
		try {
			process = Runtime.getRuntime().exec("mysql -h 10.2.18.222 -u phpmyadmin -pphpmyadmin test");
			os = process.getOutputStream();
			fis = new FileInputStream(backupName);
			leido = fis.read(buffer);
			
			while(leido > 0) {
				os.write(buffer, 0, leido);
				leido = fis.read(buffer);
			}
			os.flush();
			os.close();
			fis.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Kiiii frame = new Kiiii();
				frame.setVisible(true);
			}
		});
	}
	
}
