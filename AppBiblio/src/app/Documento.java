package app;

public class Documento {
	protected int isbn;
	protected String titulo;
	protected String autor;
	protected int replicas;

	public Documento(int isbn, String titulo, String autor, int replicas) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.replicas = replicas;
	}
	
	public Documento(int isbn2, String titulo2, String autor2) {
		super();
		this.isbn = isbn2;
		this.titulo = titulo2;
		this.autor = autor2;
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
	
	public int getReplicas() {
		return replicas;
	}

	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" + "Titulo: " + titulo + "\n" + "Autor: " + autor + "\n" + "Replicas: " + replicas + "\n";
	}
}
