package app;

public class Documento {
	private int isbn;
	private String titulo;
	private String autor;

	public Documento(int isbn, String titulo, String autor) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
	}

	public int getISBN() {
		return isbn;
	}

	public void setISBN(int isbn) {
		this.isbn = isbn;
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

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Titulo: " + titulo + "\n" + "Autor: " + autor + "\n";
	}
}
