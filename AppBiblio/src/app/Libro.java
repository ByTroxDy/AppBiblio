package app;

public class Libro extends Documento {
	private String editorial;
	private int numeroPaginas;
	private String tematica;

	public Libro(int isbn, String titulo, String autor, int replicas, String editorial, int numeroPaginas,
			String tematica) {
		super(isbn, titulo, autor, replicas);
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
		this.tematica = tematica;
	}

	public Libro(int isbn, String editorial, int numeroPaginas, String tematica) {
		super(isbn);
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
		this.tematica = tematica;
	}

	
	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Titulo: " + titulo + "\n" + "Autor: " + autor + "\n" + "Replicas: " + replicas
				+ "\n" + "Editorial: " + editorial + "\n" + "Numero Paginas: " + numeroPaginas + "\n" + "Tematica: "
				+ tematica + "\n";
	}
}
