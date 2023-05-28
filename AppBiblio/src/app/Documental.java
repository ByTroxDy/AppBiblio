package app;

public class Documental extends Documento {
	private String productora;
	private String premiosConcedidos;
	private String documentalesRelacionados;
	private int duracion;
	private String formato;

	public Documental(int isbn, String titulo, String autor, int replicas, String productora, String premiosConcedidos,
			String documentalesRelacionados, int duracion, String formato) {
		super(isbn, titulo, autor, replicas);
		this.productora = productora;
		this.premiosConcedidos = premiosConcedidos;
		this.documentalesRelacionados = documentalesRelacionados;
		this.duracion = duracion;
		this.formato = formato;
	}
	
	public Documental(int isbn, String productora, String premiosConcedidos, String documentalesRelacionados, int duracion, String formato) {
		super(isbn);
		this.productora = productora;
		this.premiosConcedidos = premiosConcedidos;
		this.documentalesRelacionados = documentalesRelacionados;
		this.duracion = duracion;
		this.formato = formato;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public String getPremiosConcedidos() {
		return premiosConcedidos;
	}

	public void setPremiosConcedidos(String premiosConcedidos) {
		this.premiosConcedidos = premiosConcedidos;
	}

	public String getDocumentalesRelacionados() {
		return documentalesRelacionados;
	}

	public void setDocumentalesRelacionados(String documentalesRelacionados) {
		this.documentalesRelacionados = documentalesRelacionados;
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
				+ "\n" + "Productora: " + productora + "\n" + "Premios Concedidos: " + premiosConcedidos + "\n"
				+ "Documentales Relacionados: " + documentalesRelacionados + "\n" + "Duracion: " + duracion + "\n"
				+ "Formato: " + formato + "\n";
	}
}
