package app;

public class Usuario {
	private String usuario;
	private String password;
	private String email;

	public Usuario(String usuario, String password, String email) {
		this.usuario = usuario;
		this.password = password;
		this.email = email;
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

	@Override
	public String toString() {
		return "Usuario: " + usuario + "\n" + "Password: " + password + "\n" + "Email: " + email + "\n";
	}
}
