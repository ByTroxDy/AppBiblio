package app;

public class Usuario {
	private String usuario;
	private String password;
	private String email;
	private int sancion;

	public Usuario(String usuario, String password, String email, int sancion) {
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.sancion = sancion;
	}
	
	public Usuario(String usuario, String password, String email) {
		this.usuario = usuario;
		this.password = password;
		this.email = email;
	}
	
	public Usuario(String usuario2, String password2) {
		this.usuario = usuario2;
		this.password = password2;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public int getSancion() {
		return sancion;
	}

	@Override
	public String toString() {
		return "Usuario: " + usuario + "\n" + "Password: " + password + "\n" + "Email: " + email + "\n" + "Sancion: " + sancion + "\n";
	}
}
