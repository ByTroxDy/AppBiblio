package app;

import java.util.Date;

public class Reservas {

	private int isbn;
	private String usuario;
	private Date fechaReserva;
	private int diasPendientes;

	public Reservas(int isbn, String usuario, Date fechaReserva, int diasPendientes) {
		this.isbn = isbn;
		this.usuario = usuario;
		this.fechaReserva = fechaReserva;
		this.diasPendientes = diasPendientes;
	}
	
	public Reservas(int isbn, Date fechaReserva, int diasPendientes) {
		this.isbn = isbn;
		this.fechaReserva = fechaReserva;
		this.diasPendientes = diasPendientes;
	}

	public int getISBN() {
		return isbn;
	}

	public void setISBN(int isbn) {
		this.isbn = isbn;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getDiasPendientes() {
		return diasPendientes;
	}

	public void setDiasPendientes(int diasPendientes) {
		this.diasPendientes = diasPendientes;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Usuario: " + usuario + "\n" + "Fecha Reserva: " + fechaReserva + "\n"
				+ "Dias Pendientes: " + diasPendientes + "\n";
	}

}
