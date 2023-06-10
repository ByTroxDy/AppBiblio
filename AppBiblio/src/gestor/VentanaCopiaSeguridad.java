package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VentanaCopiaSeguridad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTitulo;
	private JButton btnVolver, btnAceptar;
	
	public VentanaCopiaSeguridad() {
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
		lblAlta = new JLabel("CÒPIA DE SEGURETAT");
		lblAlta.setBounds(10, 0, 470, 71);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(501, 0, 46, 71);
		
		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		//Panel contenido
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
		
		//Titulo contenido
		lblTitulo = new JLabel("Crea la teúa còpia de seguretat");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setBounds(0, 81, 584, 28);

		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(151, 190, 284, 30);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Aceptar
		btnAceptar = new JButton("Backup\r\n");
		btnAceptar.setBounds(151, 138, 284, 30);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 0, 0));
		
		panelSecundario.add(lblTitulo);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(panelSecundario, "Backup realizat correctament", "Backup", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    	JOptionPane.showMessageDialog(panelSecundario, "Algo ha salido mal", "Error", JOptionPane.ERROR_MESSAGE);
                    }// if else
		        }// if
			}//actionPerformed
		});
	}// VentanaCopiaSeguridad

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
				try {
					VentanaCopiaSeguridad frame = new VentanaCopiaSeguridad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}//run
		});
	}// main
}// VentanaCopiaSeguridad
