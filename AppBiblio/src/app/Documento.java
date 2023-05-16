package app;

public class Documento {

	private int id;
	private String titulo;
	private String autor;
	private String editorial;
	private int numPaginas;
	private String tematica;
	private int duracion;
	private String formato;
	private String director;
	private String actores;
	private String premios;
	private String productora;
	private String relacionados;
	private String isbn;

	public Documento(int id, String titulo, String autor, String editorial, int numPaginas, String tematica,
			int duracion, String formato, String director, String actores, String premios, String productora,
			String relacionados, String isbn) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.numPaginas = numPaginas;
		this.tematica = tematica;
		this.duracion = duracion;
		this.formato = formato;
		this.director = director;
		this.actores = actores;
		this.premios = premios;
		this.productora = productora;
		this.relacionados = relacionados;
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getPremios() {
		return premios;
	}

	public void setPremios(String premios) {
		this.premios = premios;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public String getRelacionados() {
		return relacionados;
	}

	public void setRelacionados(String relacionados) {
		this.relacionados = relacionados;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
