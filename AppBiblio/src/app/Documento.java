package app;

import java.util.Date;

public class Documento {
	private int isbn;
	private String tipo;
	private String titulo;
	private String descripcion;
	private String editorialProductora;
	private int numPaginasDuracion;
	private String formato;
	private Date fechaPublicacion;
	private int replicas;

	public Documento(int isbn, String tipo, String titulo, String descripcion, String editorialProductora,
			int numPaginasDuracion, String formato, Date fechaPublicacion, int replicas) {
		super();
		this.isbn = isbn;
		this.tipo = tipo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.editorialProductora = editorialProductora;
		this.numPaginasDuracion = numPaginasDuracion;
		this.formato = formato;
		this.fechaPublicacion = fechaPublicacion;
		this.replicas = replicas;
	}
	
	public Documento(int isbn2, String titulo2, String tipo2, int replicas2) {
		this.isbn = isbn2;
		this.titulo = titulo2;
		this.tipo = tipo2;
		this.replicas = replicas2;
	}


	public int getISBN() {
		return isbn;
	}

	public void setISBN(int isbn) {
		this.isbn = isbn;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEditorialProductora() {
		return editorialProductora;
	}

	public void setEditorialProductora(String editorialProductora) {
		this.editorialProductora = editorialProductora;
	}

	public int getNumPaginasDuracion() {
		return numPaginasDuracion;
	}

	public void setNumPaginasDuracion(int numPaginasDuracion) {
		this.numPaginasDuracion = numPaginasDuracion;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public int getReplicas() {
		return replicas;
	}
	
	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Tipo: " + tipo + "\n" + "Titulo: " + titulo + "\n" + "Descripcion: " + descripcion
				+ "\n" + "Editorial/Productora: " + editorialProductora + "\n"
				+ "Num. Paginas/Duracion: " + numPaginasDuracion + "\n" + "Formato: " + formato + "\n"
				+ "Fecha de publicacion: " + fechaPublicacion + "\n" + "Replicas: " + replicas + "\n";
	}
}
