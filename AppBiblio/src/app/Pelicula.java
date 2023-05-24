package app;

public class Pelicula extends Documento {
	private String director;
	private String actoresPrincipales;
	private String premiosConseguidos;
	private int duracion;
	private String formato;

	public Pelicula(int isbn, String titulo, String autor, int replicas, String director, String actoresPrincipales,
			String premiosConseguidos, int duracion, String formato) {
		super(isbn, titulo, autor, replicas);
		this.director = director;
		this.actoresPrincipales = actoresPrincipales;
		this.premiosConseguidos = premiosConseguidos;
		this.duracion = duracion;
		this.formato = formato;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActoresPrincipales() {
		return actoresPrincipales;
	}

	public void setActoresPrincipales(String actoresPrincipales) {
		this.actoresPrincipales = actoresPrincipales;
	}

	public String getPremiosConseguidos() {
		return premiosConseguidos;
	}

	public void setPremiosConseguidos(String premiosConseguidos) {
		this.premiosConseguidos = premiosConseguidos;
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
				+ "\n" + "Director: " + director + "\n" + "Actores Principales: " + actoresPrincipales + "\n"
				+ "Premios Conseguidos: " + premiosConseguidos + "\n" + "Duracion: " + duracion + "\n" + "Formato: "
				+ formato + "\n";
	}

}
