package gui;

import javax.swing.*;

import app.Reserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class VentanaReservarDocumento extends JFrame {
	private JTextField txtUsuario;
	private JTextField txtTituloDocumento;
	private JComboBox<String> cmbTipoDocumento;
	private JButton btnConsultarReservas;
	private JButton btnReservar;
	private JButton btnCancelar;
	private ArrayList<Reserva> reservas;

	public VentanaReservarDocumento(String nombreUsuario) {
		
		reservas = new ArrayList<>();
		
		setTitle("Reservar Documento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 10, 10));

		JLabel lblUsuario = new JLabel("Usuario:");
		txtUsuario = new JTextField(nombreUsuario);
		txtUsuario.setEditable(false);

		JLabel lblTituloDocumento = new JLabel("Título del Documento:");
		txtTituloDocumento = new JTextField();

		JLabel lblTipoDocumento = new JLabel("Tipo de Documento:");
		String[] tiposDocumento = { "Pelicula", "Musica", "Libro", "Documentales" };
		cmbTipoDocumento = new JComboBox<>(tiposDocumento);

		btnConsultarReservas = new JButton("Consultar Mis Reservas");
		btnReservar = new JButton("Reservar");
		btnCancelar = new JButton("Cancelar");

		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblTituloDocumento);
		panel.add(txtTituloDocumento);
		panel.add(lblTipoDocumento);
		panel.add(cmbTipoDocumento);
		panel.add(btnConsultarReservas);
		panel.add(btnReservar);
		panel.add(new JLabel());
		panel.add(btnCancelar);

		add(panel);

		btnConsultarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lógica para consultar las reservas del usuario
				consultarMisReservas(nombreUsuario);
			}
		});
		
		btnReservar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ex) {
		        // Obtener la información de la reserva
		        String usuario = txtUsuario.getText();
		        String tituloDocumento = txtTituloDocumento.getText();
		        String tipoDocumento = (String) cmbTipoDocumento.getSelectedItem();
		        LocalDate fechaReserva = LocalDate.now();

		        // Mostrar el mensaje de reserva exitosa
		        JOptionPane.showMessageDialog(VentanaReservarDocumento.this,
		                "Reserva realizada\nUsuario: " + usuario + "\nTítulo del Documento: " + tituloDocumento
		                        + "\nTipo de Documento: " + tipoDocumento + "\nFecha de Reserva: " + fechaReserva,
		                "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);

		        // Crear el objeto Reserva y añadirlo al ArrayList
		        Reserva reserva = new Reserva(usuario, tituloDocumento, tipoDocumento, fechaReserva);
		        reservas.add(reserva);

		    }
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
				dispose();
			}
		});
	}
	
	private void consultarMisReservas(String nombreUsuario) {

	    // Ejemplo de visualización en un diálogo de mensaje
	    StringBuilder mensaje = new StringBuilder();
	    mensaje.append("Consultando las reservas de: ").append(nombreUsuario).append("\n\n");
	    for (Reserva reserva : reservas) {
	        mensaje.append("Información de reserva:\n");
	        mensaje.append("Usuario: ").append(reserva.getUsuario()).append("\n");
	        mensaje.append("Título del Documento: ").append(reserva.getTituloDocumento()).append("\n");
	        mensaje.append("Tipo de Documento: ").append(reserva.getTipoDocumento()).append("\n");
	        mensaje.append("Fecha de Reserva: ").append(reserva.getFechaReserva()).append("\n\n");
	    }

	    JOptionPane.showMessageDialog(VentanaReservarDocumento.this,
	            mensaje.toString(), "Mis Reservas", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String nombreUsuario = "admin";
				VentanaReservarDocumento ventana = new VentanaReservarDocumento(nombreUsuario);
				ventana.setVisible(true);
			}
		});
	}

}
