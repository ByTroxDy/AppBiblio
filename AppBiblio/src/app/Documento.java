package app;

import java.util.Date;

public class Documento {
	private int id;
	private String tipo;
	private String nombre;
	private String descripcion;
	private String autor;
	private String editorialProductora;
	private int numPaginasDuracion;
	private String formato;
	private Date fechaPublicacion;

	public Documento(int id, String tipo, String nombre, String descripcion, String autor, String editorialProductora,
			int numPaginasDuracion, String formato, Date fechaPublicacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.autor = autor;
		this.editorialProductora = editorialProductora;
		this.numPaginasDuracion = numPaginasDuracion;
		this.formato = formato;
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public Documento(int id2, String nombre2, String autor2, Date fechaPublicacion2) {
		this.id = id2;
		this.nombre = nombre2;
		this.autor = autor2;
		this.fechaPublicacion = fechaPublicacion2;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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

	@Override
	public String toString() {
		return "ID: " + id + "\n" + "Tipo: " + tipo + "\n" + "Nombre: " + nombre + "\n" + "Descripcion: " + descripcion
				+ "\n" + "Autor: " + autor + "\n" + "Editorial/Productora: " + editorialProductora + "\n"
				+ "Num. Paginas/Duracion: " + numPaginasDuracion + "\n" + "Formato: " + formato + "\n"
				+ "Fecha de publicacion: " + fechaPublicacion + "\n";
	}
}
