package gestor;

import admin.MenuAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("serial")
public class VentanaRespaldo extends JFrame {
	private JPanel contentPane;
	private JLabel lblBackup, lblImagen;
	private JButton btnVolver, btnExportar, btnImportar;
	
	public static String grupo;
	
	public VentanaRespaldo() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRespaldo.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//Panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 71);
		panelPrincipal.setLayout(null);
		
		//Titulo
		lblBackup = new JLabel("Suport");
		lblBackup.setBounds(10, 0, 582, 71);
		lblBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackup.setForeground(new Color(255, 255, 255));
		lblBackup.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(501, 0, 46, 71);
		
		panelPrincipal.add(lblBackup);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		//Panel contenido
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 68, 592, 297);
		
		JLabel lblSelectExporta = new JLabel("Selecciona on vas a exportar.");
		lblSelectExporta.setBounds(278, 93, 175, 25);

		//Exportar
		btnExportar = new JButton("Exportar");
		btnExportar.setBounds(138, 91, 130, 25);
		btnExportar.setFocusPainted(false);
		btnExportar.setForeground(new Color(0, 0, 0));
		btnExportar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnExportar.setBackground(new Color(192, 192, 192));
		
		JLabel lblSelectImporta = new JLabel("Selecciona on vas a importar.");
		lblSelectImporta.setBounds(278, 126, 175, 25);
		
		//Importar
		btnImportar = new JButton("Importar");
		btnImportar.setBounds(138, 127, 130, 25);
		btnImportar.setFocusPainted(false);
		btnImportar.setForeground(new Color(0, 0, 0));
		btnImportar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnImportar.setBackground(new Color(192, 192, 192));
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(138, 237, 315, 30);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		panelSecundario.add(lblSelectExporta);
		panelSecundario.add(lblSelectImporta);
		panelSecundario.add(btnExportar);
		panelSecundario.add(btnImportar);
		panelSecundario.add(btnVolver);
		contentPane.add(panelSecundario);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grupo.equals("gestor")) {
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
				} else if (grupo.equals("admin")) {
					MenuAdmin menu = new MenuAdmin();
					menu.setVisible(true);
				}
				dispose();
			}
		});
		
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archius de SQL", "sql");
		        fileChooser.setFileFilter(filter);
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        int returnValue = fileChooser.showSaveDialog(null);
		        
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            // Seleccionó una ubicación para guardar el archivo
		            java.io.File selectedFile = fileChooser.getSelectedFile();
		            String file = selectedFile.getAbsolutePath();
					if (copiaSeguridad(file)) {
						JOptionPane.showMessageDialog(panelSecundario, "Còpia de seguretat realitzada correctament.", "Còpia de seguretat", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    	JOptionPane.showMessageDialog(panelSecundario, "Alguna cosa ha anat malament.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
		        }
			}
		});
		
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archius de SQL", "sql");
		        fileChooser.setFileFilter(filter);
		        fileChooser.setAcceptAllFileFilterUsed(false);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Seleccionó un archivo
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String file = selectedFile.getAbsolutePath();
                    if (restaurarBackup(file)) {
                    	JOptionPane.showMessageDialog(panelSecundario, "Restauració realitzada correctament.", "Restauració", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    	JOptionPane.showMessageDialog(panelSecundario, "Alguna cosa ha anat malament.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
			}
		});
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
			fos = new FileOutputStream(backupName + ".sql");
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
	
	public boolean restaurarBackup(String backupName) {
		Process process;
		OutputStream os;
		FileInputStream fis;
		byte[] buffer = new byte[1000];
		int leido;
		
		try {
			process = Runtime.getRuntime().exec("mysql -h 10.2.18.222 -u phpmyadmin -pphpmyadmin app_biblioteca");
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
}
