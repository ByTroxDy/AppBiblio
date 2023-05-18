package app;

public class Usuario {
	private String nombre;
	private String password;
	private String email;

	public Usuario(String nombre, String password, String email) {
		this.nombre = nombre;
		this.password = password;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Usuario{" + "nombre='" + nombre + '\'' + ", password='" + password + '\'' + ", email='" + email + '\''
				+ '}';
	}
}
