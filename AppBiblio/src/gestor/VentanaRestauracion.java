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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 592, 71);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("RESTAURACIÓ");
		lblAlta.setBounds(112, 0, 370, 71);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panel.add(lblAlta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/icono64.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(457, 0, 46, 71);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(0, 70, 592, 291);
		contentPane.add(panel_1);

		JLabel lblIntroduceElIsbn = new JLabel("Data Base");
		lblIntroduceElIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceElIsbn.setBounds(0, 52, 584, 28);
		panel_1.add(lblIntroduceElIsbn);

		//Volver
		JButton btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 261, 268, 30);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		//Aceptar
		JButton btnNewButton = new JButton("Restaura");
		btnNewButton.setBounds(316, 261, 268, 30);
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
			}//actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
		
		JLabel lblSeleccionaElArchivo = new JLabel("Selecciona el archivo sql a restaurar");
		lblSeleccionaElArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionaElArchivo.setForeground(Color.BLACK);
		lblSeleccionaElArchivo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSeleccionaElArchivo.setBounds(0, 110, 584, 28);
		panel_1.add(lblSeleccionaElArchivo);
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
