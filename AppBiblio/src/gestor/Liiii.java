package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Liiii extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Liiii() {
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
		
		JLabel lblAlta = new JLabel("COPIA DE SEGURIDAD");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Backup de base de dades", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
		
		JLabel lblIntroduceElNombre = new JLabel("Backup DataBase");
		lblIntroduceElNombre.setForeground(new Color(0, 0, 0));
		lblIntroduceElNombre.setFont(new Font("Dialog", Font.BOLD, 17));
		lblIntroduceElNombre.setBounds(104, 55, 159, 28);
		panel_1.add(lblIntroduceElNombre);
		
		JButton btnVolver = new JButton("Salir");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 168, 159, 30);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		JButton btnNewButton = new JButton("Backup");
		btnNewButton.setBounds(183, 168, 165, 30);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de sql", "sql");
		        fileChooser.setFileFilter(filter);
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        int returnValue = fileChooser.showSaveDialog(null);
		        
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            // Seleccionó una ubicación para guardar el archivo
		            java.io.File selectedFile = fileChooser.getSelectedFile();
		            String file = selectedFile.getAbsolutePath();
					if (copiaSeguridad(file)) {
						JOptionPane.showMessageDialog(panel_1, "Backup realizat correctament", "Backup", JOptionPane.INFORMATION_MESSAGE);
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
	
	public boolean copiaSeguridad(String backupName) {
		Process process;
		InputStream is;
		FileOutputStream fos;
		byte[] buffer = new byte[1000];
		int leido;
		
		try {
			process = Runtime.getRuntime().exec("mysqldump -h 10.2.18.222 -u phpmyadmin -pphpmyadmin app_biblioteca");
			is = process.getInputStream();
			fos = new FileOutputStream(backupName);
			leido = is.read(buffer);
			
			while(leido > 0) {
				fos.write(buffer, 0, leido);
				leido = is.read(buffer);
			}
			
			fos.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Liiii frame = new Liiii();
				frame.setVisible(true);
			}
		});
	}
	
}
