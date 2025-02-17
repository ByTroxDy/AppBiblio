package app;

import java.util.Date;

public class Musica extends Documento {
	private String lugar;
	private Date fecha;
	private int duracion;
	private String formato;

	public Musica(int isbn, String titulo, String autor, int replicas, String lugar, Date fecha, int duracion, String formato) {
		super(isbn);
		this.lugar = lugar;
		this.fecha = fecha;
		this.duracion = duracion;
		this.formato = formato;
	}
	
	public Musica(int isbn, String lugar, Date fecha2, int duracion, String formato) {
		super(isbn);
		this.lugar = lugar;
		this.fecha = fecha2;
		this.duracion = duracion;
		this.formato = formato;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Titulo: " + titulo + "\n" + "Autor: " + autor + "\n" + "Replicas: " + replicas
				+ "\n" + "Lugar: " + lugar + "\n" + "Fecha: " + fecha + "\n" + "Duracion: " + duracion + "\n"
				+ "Formato: " + formato + "\n";
	}
}
