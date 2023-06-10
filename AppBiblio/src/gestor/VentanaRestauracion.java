package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class VentanaRestauracion extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblSeleccionaElArchivo;
	private JButton btnVolver, btnRestaurar;
	
	public VentanaRestauracion() {
		setTitle("Restauració");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, 592, 71);
		panelPrincipal.setLayout(null);
		
		lblAlta = new JLabel("RESTAURACIÓ");
		lblAlta.setBounds(112, 0, 370, 71);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon("img/icono64.png"));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(457, 0, 46, 71);
		
		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);
		
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBounds(0, 70, 592, 291);

		lblSeleccionaElArchivo = new JLabel("Selecciona l'arxiu sql a restaurar");
		lblSeleccionaElArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionaElArchivo.setForeground(Color.BLACK);
		lblSeleccionaElArchivo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblSeleccionaElArchivo.setBounds(0, 76, 584, 28);
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(164, 184, 268, 30);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		//Aceptar
		btnRestaurar = new JButton("Restaura");
		btnRestaurar.setBounds(164, 136, 268, 30);
		btnRestaurar.setFocusPainted(false);
		btnRestaurar.setBorderPainted(false);
		btnRestaurar.setForeground(new Color(255, 255, 255));
		btnRestaurar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnRestaurar.setBackground(new Color(0, 0, 0));
		
		contentPane.add(panelSecundario);
		panelSecundario.add(lblSeleccionaElArchivo);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnRestaurar);
		
		//Funciones
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnRestaurar.addActionListener(new ActionListener() {
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
                    	JOptionPane.showMessageDialog(panelSecundario, "Restaurado correctamene", "Restauración", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                    	JOptionPane.showMessageDialog(panelSecundario, "Algo ha salido mal", "Error", JOptionPane.ERROR_MESSAGE);
                    }// if else
                }// if
			}//actionPerformed
		});
	}// VentanaRestauracion
	
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
			}// while
			os.flush();
			os.close();
			fis.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}// try catch
		return false;
	}//restaurarBackup
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRestauracion frame = new VentanaRestauracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try carch
			}// run
		});
	}// main
}// VentanaRestauracion
