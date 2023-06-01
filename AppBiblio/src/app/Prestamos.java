package app;

import java.util.Date;

public class Prestamos {

	private int isbn;
	private String usuario;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private int diasRetardo;

	public Prestamos(int isbn, String usuario, Date fechaPrestamo, Date fechaDevolucion, int diasRetardo) {
		this.isbn = isbn;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.diasRetardo = diasRetardo;
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

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getDiasRetardo() {
		return diasRetardo;
	}

	public void setDiasRetardo(int diasRetardo) {
		this.diasRetardo = diasRetardo;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Usuario: " + usuario + "\n" + "Fecha Prestamo: " + fechaPrestamo + "\n"
				+ "Fecha Devolucion: " + fechaDevolucion + "\n" + "Dias Retardo: " + diasRetardo + "\n";
	}

}
