package app;

public class Usuario {
	private String usuario;
	private String password;
	private String email;
	private boolean ban;

	public Usuario(String usuario, String password, String email, boolean ban) {
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.ban = ban;
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
	
	public boolean getBan() {
		return ban;
	}

	@Override
	public String toString() {
		return "Usuario: " + usuario + "\n" + "Password: " + password + "\n" + "Email: " + email + "\n" + "Ban: " + ban + "\n";
	}
}
